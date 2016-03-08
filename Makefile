build:
	javac -g -Xlint -d bin/ -cp .:lib/\* src/main/*.java 
test:
	javac -g -Xlint -d bin/ -cp .:lib/\*:bin src/tests/*.java 
run: 
	java -cp bin main.Main
runtest:
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.FileParserTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.CSVWriterTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.RecordsTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.ReplTest
	java -cp .:lib/\*:bin org.junit.runner.JUnitCore tests.TransformationsTest
rungui:
	java -cp bin main.MakeGUI
runrepl:
	java -cp bin main.Repl
clean:
	rm -rf ./bin/*.class
