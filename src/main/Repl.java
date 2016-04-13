package main;

import java.lang.StringBuilder;
import java.lang.ProcessBuilder;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import main.MakeGUI;
import main.FileParser;
import main.Records;

public class Repl {
    boolean state = false;
    static double version = 1.0;
    static String usage = "Usage: [verb] [dependent set of arguments]\n";
    static String versioninfo = "This cli data munging utility was created by undergraduate \n"
	+ "Students in Spring 2016 of a CISC475 class. \n"
	+ "Those students were: \n"
	+ "Will Weber, Ben Rodd, Johanna Jan, Greg Mohler, and Teague Forren\n"
	+ "And this is release version: " + version + ". ";
    
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
    public static boolean parseCommand(String[] commands) throws IOException, InterruptedException {
	String root = commands[0];
	switch(root) {
	case "quit" :
	    quit(0);
	    return true;
	case "break":
	    quit(0);
	    return true;
	case "version":
	    System.out.println(versioninfo);
	    return true;
	case "clear":
	    clear();
	    return true;
	case "view":
	    viewOrganizer(commands);
	    return true;
	case "" :
	    return true;
	case "pwd":
	    pbBuild(commands);
	    return true;
	case "ls":
	    pbBuild(commands);
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
	    System.out.println("incorrect argument count \n Usage: view [csvfile]\n");
	    return false;
	}
    }
	    
    public static void printLoop() throws IOException, InterruptedException {
	Scanner scan = new Scanner(System.in);
	CmdHistoryWriter histWriter = new CmdHistoryWriter();
	try {
	    System.out.print("> ");
	    while(scan.hasNextLine()) {
		String line = scan.nextLine().toLowerCase();
		if (parseCommand(commandify(line))) {
		    System.out.print("> ");
		    histWriter.store(line);
		} else {
		    System.out.println("No command '" + line + "' found");
		    System.out.print(usage);
		    System.out.print("> ");
		}
	    }
	} finally {
	    scan.close();
	}
    }

    public static boolean pbBuild(String[] arguments) throws IOException, InterruptedException {
	String line = "";
	String output = "";
	try {	    
	    Process p = Runtime.getRuntime().exec(arguments);
	    p.waitFor();
	    BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

	    while ((line = buf.readLine()) != null) {
		output += line + "\n";
	    }
	    System.out.println(output);
	    return true;
	}
	catch(InterruptedException e) {
	    e.getMessage();
	    e.printStackTrace();
	    return false;
	}
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
	printLoop();
    }
}
