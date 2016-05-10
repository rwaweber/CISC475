package main;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class CmdHistoryWriter {
	private static LinkedList<String> commandHistory = new LinkedList<String>();
	private static HashMap<String, Boolean> commandBlacklist = new HashMap<String, Boolean>();
	private static HashMap<String, Boolean> scriptWhiteList = new HashMap<String, Boolean>();

	
	public CmdHistoryWriter(){
		commandBlacklist.put("history", true);
		commandBlacklist.put("version", true);
		commandBlacklist.put("clear", true);
		
		scriptWhiteList.put("start", true);
		scriptWhiteList.put("transform", true);
	}
	
	/*store
	/*takes in a string and adds it to the linked list
	/*Pretty much a wrapper. for the linklist add method
	*/
	public void store(String command){
		String root = command.split("\\s+")[0];
		if (commandBlacklist.containsKey(root)){
			//command is blacklisted. Do not add it.
		}else{
			//System.out.println("Storing: " + command);	
			commandHistory.add(command);
		}
	}
	
	/*removeLast
	 * removes the last command line stored in the history list
	 * Takes in nothing returns nothing
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
	
	/*Export
	 * takes in the desired path to write the export script to
	 * Exports boolean if successfully wrote new file
	 * should throw exception if unable to write to file
	 */
	private boolean Export(String directoryPath) {
		
		boolean result = false;
		File directory = new File(directoryPath);

		if (commandHistory.size() == 0){
			System.out.println("Command history empty. Nothing to export.");
		}
		else {
			//Path not specified, use default
			if (directoryPath == "") {
				directory = new File("C:/Underfish/scripts/history.ba");
			}
			//whether path is default or provided, check if it exists
			if (!directory.exists()) {
				result = createDirectory(directory);
			}
			
			result = writeToFile(directory);
			
		}//end commandHistory.size check
		
		
		return result;
		
	}//end export
	
	
	/*createDirectory HELPER FOR EXPORT
	 * Checks if the default script export directory exists
	 * If it doesn't, it creates it.
	 * Takes in the Directory as a File, Returns boolean if successful
	 */
	private boolean createDirectory(File directory){
		
		//check if file exists
		File defaultDir = directory;
		boolean result = false;

		// if the directory does not exist, create it
		if (!defaultDir.exists()) {
		    System.out.println("directory does not exist! creating directory: " + defaultDir.getParentFile());
		    
		    try{
		    	defaultDir.getParentFile().mkdirs();
		        result = true;
		    } 
		    catch(SecurityException se){
		        System.out.println("Error creating script file:");
		        result = false;
		        se.printStackTrace();
		    }        
		}//end default path creation
			
		return result;
		
	} //end createDefaultDirectory
	
	/*writeToFile HELPER FOR EXPORT
	 * takes in the directory as a File
	 * overwrited the file if it already exists, creates a new one if it doesn't
	 * returns boolean if successful
	 */
	private boolean writeToFile(File directory){
		
		boolean result = false;
		
		try {
			PrintWriter writer = new PrintWriter(directory, "UTF-8");
			
			for(String line : commandHistory){
				if (filterHistory(line)) {
					writer.println(line);
				}
			};

			writer.close();//saves file
			result = true;
			System.out.println("Done");

		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
		
	} //end writeToFile
	
	
	/*filterHistory HELPER FOR writeToFile
	 * Checks if the current line in history and checks if it's something we care to export to script
	 * Takes in a line as a String
	 * Returns boolean if we want to keep the line or not
	 */
	private boolean filterHistory(String line){
		boolean result = false;
		
		String[] split = line.split("\\s+");

		//Command in white list
		if (scriptWhiteList.containsKey(split[0])){
			result = true;
		}
		//command not in white list, ignore it
		else { 
			result = false;
		}
		return result;
	}
	
	/*printHelp
	 * Invoked with the command history help
	 * returns a brief manual for the available help functions
	 */
	private void printHelp() {
		System.out.println("========== History Commands ==========\n"
				+ "view . . . . . . . . . . . . . . Prints all recorded commands stored in history\n"
				+ "removelast . . . . . . . . . . . Removes the last saved command in history\n"
				+ "exportScript Path:/To/File.ba. . Exports the stored history in script format(ignores functionally insignificant commands)");
	}
	
	
	/*commandCaller
	 * main function caller of a CmdHistoryWriter.
	 * Takes in REPL arguments to determine what history-related function needs to be called
	 */
	public boolean commandCaller(String[] commands){
		
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
				
			case "exportScript":
				if (commands.length > 2){ //desired path specified
					this.Export(commands[2]);
				}
				else { //no path given, will let export use default path
					this.Export("");
				}
				return true;
				
			case "removelast":
				this.removeLast();
				return true;
				
			case "help":
				this.printHelp();
				return true;

			default:
				this.viewHistory();
				return true;
			}//end switch
		}//end commands length check
		
		return true;
	}//end command caller
	
	
}//end class
