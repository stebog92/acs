JAVA=java
JAVAC=javac
JFLEX=jflex

all: *.class
	jar cfm Turing.jar manifest ./*.class

%.class: TuringLex.java %.java
	$(JAVAC) $^

TuringLex.java: Turing.lex
	$(JFLEX) Turing.lex

clean:
	rm -f *.class *~ TuringLex.java
