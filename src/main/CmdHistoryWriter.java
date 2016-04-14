package main;

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
	public void removeLast(){
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
	public void viewHistory(){
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
	public void Export(){
		Iterator<String> historyIterator = commandHistory.iterator();
		while (historyIterator.hasNext()){
			System.out.println(historyIterator.next());
		}
	}
	
	
}
