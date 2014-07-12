if [ $# -ne 1 ]
then
  echo Usage lcplrun.sh "<file.lcpl>"
  exit
fi
FNAME=`basename $1`
java -jar LCPLParser.jar $1 $FNAME.ast
java -jar LCPLSemant.jar $FNAME.ast $FNAME.run
if [ -f $FNAME.run ]
then
	java -jar LCPLSemant.jar $FNAME.run --run
fi
