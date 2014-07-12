JAVA=java
JAVAC=javac
JFLEX=jflex

all: *.class
	jar cfm Turing.jar manifest ./*.class

%.class: %.java
	$(JAVAC) $^

TuringLex.java: Turing.lex
	$(JFLEX) Turing.lex

clean:
	rm -f *.class *~
