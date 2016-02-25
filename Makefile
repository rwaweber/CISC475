build:
	javac -g -d ./bin/ -cp lib/json.simple.jar src/main/*.java 
run: 
	java -cp bin main.Main
clean:
	rm -rf ./bin/*.class
