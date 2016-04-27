package main;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class CommandParser {
	public Class cmds = Commands.class;

	private ArrayList<String> transformations = new ArrayList<String>();
	/**
	 * 
	 * @param currentSession
	 */
	public CommandParser(Session currentSession) {

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
		// accept input as capital letters and lowercase
		if (transformations.contains(lineofcommands[1]) || transformations.contains(lineofcommands[1].toUpperCase())) {
			// retrieve transformation whose title matches the above input
			StringBuilder columnName = new StringBuilder();

			Method command = cmds.getMethod(lineofcommands[1]);
			List<String> dummyStringList = Arrays.asList("");
			Object cmdPointer = command.invoke(dummyStringList);

			columnName.append(command.toString());
			if (lineofcommands[2] == "col" || lineofcommands[2] == "column") {
				columnName.append(lineofcommands[3]);
				sessioninstance.transColToCol(Integer.parseInt(lineofcommands[3]), (ListToList) cmdPointer, columnName.toString());
			}

		}
	}
}
