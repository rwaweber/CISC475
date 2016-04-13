package main;

import java.util.Iterator;
import java.util.LinkedList;

public class CmdHistoryWriter {
	private static LinkedList<String> commandHistory = new LinkedList<String>();
	
	public CmdHistoryWriter(){
		//commandHistory = new LinkedList<String>();	
	}
	
	//takes in a string and adds it to the linked list
	//Pretty much a wrapper. for the linklist add method
	public void store(String command){
		if (command == ""){
			//Nothing
		}else{
		//System.out.println("Storing: " + command);	
		commandHistory.add(command);
		}
	}
	
	/*TODO
	 * removes the last command line stored in the history list
	 */
	public void removeLast(){
		
	}
	
	/*viewHistory
	 * takes nothing returns nothing
	 * iterates over commandHistory list and prints all stored commands
	 */
	public void viewHistory(){
		Iterator<String> historyIterator = commandHistory.iterator();
		System.out.println("Command History:");
		while (historyIterator.hasNext()){
			System.out.println(historyIterator.next());
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
