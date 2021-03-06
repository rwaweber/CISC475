package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CommandParser {
	public Class cmds = Commands.class;

	private ArrayList<String> transformations = new ArrayList<String>();
	/**
	 * 
	 * @param currentSession
	 */
	public CommandParser() {

		// on creation, populate possible transformations
		Method[] methods = cmds.getDeclaredMethods();
		for (Method method : methods) {
			// dumb way to screen
			if (!method.getName().contains("lambda"))
				transformations.add(method.getName());
		}
	}

	// entered into the parser in full
	// transform mean col 2
	// transform sum col weight
	/**
	 * Consumes the list of arguments and the session state to parse commands
	 * @param lineofcommands
	 * @param sessioninstance
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws IOException 
	 */
	public void parse(String[] lineofcommands, Session sessioninstance) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		String verb = lineofcommands[1].toUpperCase();
		// accept input as capital letters and lowercase
		if (transformations.contains(verb)) {
			// retrieve transformation whose title matches the above input
			StringBuilder columnName = new StringBuilder();

			Method command = cmds.getMethod(verb);
			List<String> dummyStringList = Arrays.asList("");
			Object cmdPointer = command.invoke(dummyStringList);

			columnName.append(lineofcommands[2]+".");
			if (lineofcommands[2].equals("col") || lineofcommands[2].equals("column")) {
				columnName.append(lineofcommands[3]);
				columnName.append("."+verb);
				sessioninstance.transColToCol(Integer.parseInt(lineofcommands[3]), (ListToList) cmdPointer, columnName.toString());
			} else {
				System.out.println("those ranges not yet implemented");
			}

		} else {
			System.out.println("Operation '"+verb+"' not implemented");
		}
	}

	public void parseGraph(String[] lineOfCommands, Session session) throws IOException {
		if(lineOfCommands.length == 5){
			System.out.println("fourth command: " + lineOfCommands[4]);
			if(lineOfCommands[4].equals("histogram")){
				CSVController control = null;
				if(lineOfCommands[1].equals("source")){
					control = session.getSourceControl();
				}
				else
					control = session.getDestControl();
				int colIndex = Integer.parseInt(lineOfCommands[3]);
				Map<String,Integer> map = null;
				List<String> col = control.getCol(colIndex);
				String colName = col.get(0);
				col.remove(0);
				if(col.size() < Histogram.MAX_VALUES){
					map = Transformations.getSortedMap(Transformations.getFrequency(col));
				}
				else{
					map = Transformations.getSortedMap(
							Transformations.getTrimmedMap(
							Transformations.getFrequency(col),
							Histogram.MAX_VALUES
							));
				}
				new Histogram(
						colName + " Frequency",
						"Values",
						"Frequency",
						map
						);
			}
			else
				System.out.println("This graph is not implemented yet!");

		}
		else
			System.out.println("Invalid number of arguments!");
	}
}
