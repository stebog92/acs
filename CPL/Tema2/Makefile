all: build

build:
	javac -cp "lib/*" -d ./bin -sourcepath ./src ./src/LCPL*.java ./src/ro/pub/cs/lcpl/*.java

clean:
	rm -rf ./bin/*

jar: clean build
	echo "Main-Class: LCPLSemant" > bin/manifest.txt
	(cd bin && jar xf ../lib/snakeyaml-1.13.jar)
	jar cfm LCPLSemant.jar bin/manifest.txt  -C bin .
