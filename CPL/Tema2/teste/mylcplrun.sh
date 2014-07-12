if [ $# -ne 2 ]
then
  echo Usage mylcplrun.sh "<path/to/semant/project>" "<file.lcpl>"
  exit
fi
FNAME=`basename $2`
java -jar LCPLParser.jar $2 $FNAME.ast
java -cp "$1/bin:$1/lib/*" LCPLSemant $FNAME.ast $FNAME.run
#java -cp "$1/bin;$1/lib/*" LCPLSemant $FNAME.ast $FNAME.run
if [ -f $FNAME.run ]
then
	java -jar LCPLSemant.jar $FNAME.run --run
fi
