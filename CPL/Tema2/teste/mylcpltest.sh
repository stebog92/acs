if [ $# -ne 1 ]
then
  echo Usage mylcpltest.sh "<path/to/semant/project>"
  exit
fi

SPATH=$1

clean()
{
rm *.ast *.run *.err *.out
}

test()
{
  echo === $1 ===
  FNAME=`basename $1`
  if [ $1 = "ref/advanced/io.lcpl" ]
  then
	./mylcplrun.sh $SPATH $1 > $FNAME.out 2> $FNAME.err << END
James
7
END
  else
	./mylcplrun.sh $SPATH $1 > $FNAME.out 2> $FNAME.err
  fi
  if [ -f ${1%lcpl}err ]
  then
	diff -wB $FNAME.err ${1%lcpl}err
  else
	diff -wB $FNAME.out ${1%lcpl}ref
  fi
}

clean

for l in ref/*.lcpl
do
	test $l
done
for l in ref/simple/*.lcpl
do
	test $l
done
for l in ref/error/*.lcpl
do
	test $l
done
for l in ref/advanced/*.lcpl
do
	test $l
done
for l in ref/complex/*.lcpl
do
	test $l
done
clean
