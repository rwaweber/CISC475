package main;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import main.MakeGUI;
import main.FileParser;
import main.Records;

public class Repl {
    boolean state = false;
    
    public Repl() {
	this.state = true;
    }
    
    // clear screen with escape code gibberish
    public static void clear() {
	    System.out.print("\033[H\033[2J");
    }

    // exit system
    public static void quit(int code){
	System.exit(code);
    }

    public static String[] commandify(String line) throws IOException {
	return line.split("\\s+");
    }
		
    // consumes command sequence, should determine the root of the command tree
    public static boolean parseCommand(String[] commands) throws IOException {
	String root = commands[0];
	switch(root) {
	case "quit" :
	    quit(0);
	    return true;
	case "break":
	    quit(0);
	    return true;
	case "clear":
	    clear();
	    return true;
	case "view":
	    viewOrganizer(commands);
	    return true;
	default:
	    return false;
	}
    }
    
    // offloader function to not clog command tree
    // commandlist -> gui
    public static boolean viewOrganizer(String[] commands) throws IOException{
	// silly way of asserting file format and no input specificity
	if(commands.length == 2 && commands[1].contains(".csv")){
	    try {
		Records recs = FileParser.csvToArray(commands[1]);
		String[] csv_headers = FileParser.getHeaders(commands[1]);

		MakeGUI view = new MakeGUI(csv_headers, recs.convertTo2DArray());
		return true;
	    } catch(IOException e) {
		e.getMessage();
		e.printStackTrace();
		return false;
	    }
	} else {
	    // inform user of 
	    System.out.println("incorrect argument count \nUsage: view [csvfile]\n");
	    return false;
	}
    }
	    
    public static void printLoop() throws IOException {
	Scanner scan = new Scanner(System.in);
	try {
	    System.out.print("> ");
	    while(scan.hasNextLine()) {
		String line = scan.nextLine().toLowerCase();
		parseCommand(commandify(line));
		System.out.print("> ");
	    }
	} finally {
	    scan.close();
	}
    }
    
    public static void main(String[] args) throws IOException {
	printLoop();
    }
}
