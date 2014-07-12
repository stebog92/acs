#!/bin/bash

TESTS="_tests"
RUNTIME="_runtime"

function fail {
		printf "FAIL (%s)\n" "$1"
}

function pass {
	printf "PASS\n"
}

run()
{
FNAME=`basename $1`
EXECNAME="${FNAME%.*}"

java -jar _lib/LCPLParser.jar $1 $FNAME.ast
java -jar _lib/LCPLSemant.jar $FNAME.ast $FNAME.run
java -cp "bin/:_lib/*" LCPLCodeGen $FNAME.run $FNAME.ir

if [ -f $FNAME.ir ]
then
llc $FNAME.ir
clang  $FNAME.ir.s $RUNTIME/lcpl_runtime.c -o $EXECNAME
./$EXECNAME
rm $EXECNAME
fi
}

test()
{
  echo  === $1 ===
  FNAME=`basename $1`
  if [ $1 = "$TESTS/advanced/io.lcpl" ]
  then
	run $1 > $FNAME.out 2> $FNAME.err << END
James
7
END
  else
	run $1 > $FNAME.out 2> $FNAME.err
  fi
  
 if [ -z "$( diff -q $FNAME.out ${1%lcpl}ref )" ]; then
	pass
 else
	fail "Different program output. First lines of diff below"
	echo "-----"
	diff $FNAME.out ${1%lcpl}ref | head -n 30
	echo "-- Error log: --"
	cat $FNAME.err
	echo "-----"
	echo
fi
}

echo "******************************"
echo "**** Running simple tests ****"
echo "******************************"
echo 

for l in $TESTS/*.lcpl
do
	test $l
done
for l in $TESTS/simple/*.lcpl
do
	test $l
done

echo
echo


echo "********************************"
echo "**** Running advanced tests ****"
echo "********************************"
echo

for l in $TESTS/advanced/*.lcpl
do
	test $l
done

echo
echo

echo "*******************************"
echo "**** Running complex tests ****"
echo "*******************************"
echo
for l in $TESTS/complex/*.lcpl
do
	test $l
done

rm -f __stdout
rm -f __stderr
