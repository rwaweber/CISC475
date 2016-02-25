build:
	javac -g -Xlint -d bin/ -cp .:lib/json.simple.jar src/main/*.java 
test:
	javac -g -Xlint -d bin/ -cp .:lib/\*:bin src/tests/*.java 
runtest:
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.FileParserTest
run: 
	java -cp bin main.Main
clean:
	rm -rf ./bin/*.class
