build:
	javac -g -Xlint -d bin/ -cp .:lib/\* src/main/*.java 
test:
	javac -g -Xlint -d bin/ -cp .:lib/\*:bin src/tests/*.java 
run: 
	java -cp .:lib/\*:bin main.Main
runtest:
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.CSVControllerTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.CSVWriterTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.FileParserTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.RecordsComparatorTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.RecordsTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.ReplTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.SessionTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.TransformationsTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.CommandParserTest

rungui:
	java -cp .:lib/\*:bin main.MakeGUI
runrepl:
	java -cp .:lib/\*:bin main.Repl
clean:
	rm -rf ./bin/*.class
