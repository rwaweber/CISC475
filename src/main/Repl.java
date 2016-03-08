package main;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Scanner;
import main.MakeGUI;

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

    public static String[] commandify(String line) {
	return line.split("\\s+");
    }
		
    // consumes command sequence, should determine the root of the command tree
    public static boolean parseCommand(String[] commands){
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
	    System.out.println("Initializing view");
	    return true;
	default:
	    return false;
	}
    }
	    
    public static void printLoop() {
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
    
    public static void main(String[] args) {
	printLoop();
    }
}
