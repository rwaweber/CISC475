build:
	javac -d ./bin/ src/main/*.java 
run: 
	java -cp bin main.Main
clean:
	rm -rf ./bin/*.class
