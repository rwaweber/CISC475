package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class CmdHistoryWriter {
	private static LinkedList<String> commandHistory = new LinkedList<String>();
	private static HashMap<String, Boolean> commandBlacklist = new HashMap<String, Boolean>();
	
	public CmdHistoryWriter(){
		commandBlacklist.put("history", true);
		commandBlacklist.put("version", true);
		commandBlacklist.put("clear", true);
	}
	
	//takes in a string and adds it to the linked list
	//Pretty much a wrapper. for the linklist add method
	public void store(String command){
		String root = command.split("\\s+")[0];
		if (commandBlacklist.containsKey(root)){
			//command is blacklisted. Do not add it.
		}else{
			//System.out.println("Storing: " + command);	
			commandHistory.add(command);
		}
	}
	
	/*TODO
	 * removes the last command line stored in the history list
	 */
	private void removeLast(){
		if (commandHistory.size() == 0){
			System.out.println("Command history empty. Nothing to remove.");
		}
		else {
			commandHistory.remove(commandHistory.size() - 1);			
		}
	}
	
	/*viewHistory
	 * takes nothing returns nothing
	 * iterates over commandHistory list and prints all stored commands
	 */
	private void viewHistory(){
		if (commandHistory.size() == 0){
			System.out.println("Command history empty. Nothing to view.");
		}else{
			Iterator<String> historyIterator = commandHistory.iterator();
			System.out.println("Command History:");
			while (historyIterator.hasNext()){
				System.out.println(historyIterator.next());
			}
		}
	}
	
	/*TODO
	 * takes nothing returns nothing
	 * should throw exception if unable to write to file
	 */
	private void Export() {
		/*Iterator<String> historyIterator = commandHistory.iterator();
		while (historyIterator.hasNext()){
			System.out.println(historyIterator.next());
		}
		FileOutputStream fop = null;
		File file;
		
		try {
			file = new File("c:/underfish/history/history.ba");
			fop = new FileOutputStream(file);

			// if file doesn't exists, create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			
			e.printStackTrace();
		}*/
		
	}
	
	
	/*commandCaller
	 * main function caller of a CmdHistoryWriter.
	 * Takes in REPL arguments to determine what history-related function needs to be called
	 */
	public boolean commandCaller(String[] commands){
		//Records recs = FileParser.csvToRecords(commands[1]);
		//String[] csv_headers = FileParser.getHeaders(commands[1]);
		//System.out.println(commands[0] + " " + commands[1]);
		
		//user enters only "history" default "history view"
		if (commands.length == 1){
			this.viewHistory();
		}
		//user entered more than just "history" so evaluate the command
		else { 
			switch(commands[1]){
			
			case "view":
				this.viewHistory();
				return true;
				
			case "export":
				this.Export();
				return true;
				
			case "removelast":
				this.removeLast();
				return true;
				
			default:
				this.viewHistory();
				return true;
			}//end switch
		}//end commands length check
		
		return true;
	}//end command caller
	
	
}//end class
