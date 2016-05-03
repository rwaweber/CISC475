package main;

import java.lang.StringBuilder;
import java.lang.reflect.InvocationTargetException;
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
	static Session state;
	static double version = 1.0;
	static String usage = "Usage: [verb] [dependent set of arguments]\n";
	static String versioninfo = "This cli data munging utility was created by undergraduate \n"
			+ "Students in Spring 2016 of a CISC475 class. \n"
			+ "Those students were: \n"
			+ "Will Weber, Ben Rodd, Johanna Jan, Greg Mohler, and Teague Forren\n"
			+ "And this is release version: " + version + ". ";
	static CmdHistoryWriter histWriter;
	static String helpInfo = "General Commands Menu:\n\n"
			+ "Command: view -- Display GUI of given file.\n"
			+ "Usage: view [file path] [start row] [end row] [start col] [end col]\n"
			+ "Command: quit -- Exit out of the REPL.\n"
			+ "Usage: quit\n"
			+ "Command: version -- Prints the description and current version.\n"
			+ "Usage: version\n"
			+ "Command: clear -- clears the REPL screen.\n"
			+ "Usage: clear\n"
			+ "Command: pwd -- Prints the working directory.\n"
			+ "Usage: pwd\n"
			+ "Command: ls -- Lists all files in the current directory.\n"
			+ "Usage: ls\n"
			+ "Command: history -- Prints the command history.\n"
			+ "Usage: history\n"
			+ "Command: start -- starts a data wrangling session.\n"
			+ "Usgae: start [/path/to/source/file] [/path/to/destination/file]\n"
			+ "Command: transform -- applies a transformation to column(s).\n"
			+ "*transform can only be used after starting a session (see 'start')*\n"
			+ "Usage: transform [transformation] [col/row] [index of col or row]";
			
			//add 'remove' documentation after its implemented

	public Repl() {
		System.out.println("Type 'help' for command info.\n");
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
	
	/**
	 * Parses a line as interpreted by the REPL into a Session object
	 * by the following syntax:
	 * start pathtosourcefile pathtodestination
	 * @param command
	 * @return
	 * @throws IOException 
	 */
	public static Session makeState (String[] command) throws IOException {
		Session srctodest = new Session(command[1], command[2]);
		return srctodest;
	}
	
	public static void setState(Session sourceToDestination) {
		state = sourceToDestination;
	}

	// consumes command sequence, should determine the root of the command tree
	public static boolean parseCommand(String[] commands) throws IOException, InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String root = commands[0];
		switch(root) {
		case "quit" :
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
		case "history":
			//histWriter.viewHistory();
			histWriter.commandCaller(commands);
			return true;
		case "help":
			System.out.println(helpInfo);
			return true;
		case "start":
			if (commands.length != 3){
				System.out.println("Wrong amount of arguments\n");
				System.out.println("Type 'help' for command usage");
			} else {
				setState(makeState(commands));
			}
			return true;
		case "transform":
			if (state != null) {
				CommandParser engine = new CommandParser(state);
				engine.parse(commands, state);
				return true;
			} else {
				System.out.println("No State set!");
				return false;
			}
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
				Records recs = FileParser.csvToRecords(commands[1]);
				String[] csv_headers = FileParser.getHeaders(commands[1]);

				MakeGUI view = new MakeGUI(csv_headers, recs.convertTo2DArray());
				return true;
			} catch(IOException e) {
				e.getMessage();
				e.printStackTrace();
				return false;
			}
		} 
		else if(commands.length == 6 && commands[1].contains(".csv")){
			try {
				Records recs = FileParser.csvToRecords(commands[1]);
				String[] csv_headers = FileParser.getHeaders(commands[1]);
				//grab input row and col limits
				int params[] = new int[4];
				for(int i=0; i<4; i++){
					params[i] = Integer.parseInt(commands[i+2]);
				}
				MakeGUI view = new MakeGUI(csv_headers, recs.convertTo2DArray(), 
						params[0], params[1], params[2], params[3]);
				return true;
			} catch(IOException e) {
				e.getMessage();
				e.printStackTrace();
				return false;
			}

		}

		else {
			// inform user of 
			System.out.println("incorrect argument count \n Usage: view [csvfile]\n");
			return false;
		}
	}

	public static void printLoop() throws IOException, InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Scanner scan = new Scanner(System.in);
		histWriter = new CmdHistoryWriter();
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

	public static void main(String[] args) throws IOException, InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		printLoop();
	}
}
