package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;

import main.CommandParser;
import main.Session;

public class CommandParserTest {
	String sourceFile = "./src/tests/testfiles/testNumeric.csv";
	String destFile = "./src/tests/testfiles/testSession.csv";

	@Test
	public void parseSingleCommand() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {

    	
		Session session = new Session(sourceFile, destFile);
		CommandParser main = new CommandParser(session);
		String[] lineofcommands = new String[]{"transform", "SUM", "col", "2"};
		session.getDestControl().clearFile();
		main.parse(lineofcommands, session);
		main.parse(lineofcommands, session);
		main.parse(lineofcommands, session);
		assertEquals(session.getDestControl().getNumRows(), 11);
		assertEquals(session.getDestControl().getNumCols(), 3);
	}
        @Test
	public void parseMultiCommand() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		Session session = new Session(sourceFile, destFile);
		CommandParser main = new CommandParser(session);
		String[] lineofcommands = new String[]{"transform", "SUM", "col", "2"};
		session.getDestControl().clearFile();
		main.parse(lineofcommands, session);
		lineofcommands = new String[]{"transform", "MIN", "col", "2"};
		main.parse(lineofcommands, session);
		lineofcommands = new String[]{"transform", "MAX", "col", "2"};
		main.parse(lineofcommands, session);
		assertEquals(session.getDestControl().getNumRows(), 11);
		assertEquals(session.getDestControl().getNumCols(), 3);
	}
    
        @Test
	public void parseNormalizeCommand() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		Session session = new Session(sourceFile, destFile);
		CommandParser main = new CommandParser(session);
		String[] lineofcommands = new String[]{"transform", "SUM", "col", "2"};
		session.getDestControl().clearFile();
		main.parse(lineofcommands, session);
		lineofcommands = new String[]{"transform", "NORMALIZE_LOCAL_EXTREMA", "col", "2"};
		main.parse(lineofcommands, session);
		assertEquals(session.getDestControl().getNumRows(), 11);
		assertEquals(session.getDestControl().getNumCols(), 2);
	}
    
        @Test
	public void parseStandardDeviation() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {	
		Session session = new Session(sourceFile, destFile);
		CommandParser main = new CommandParser(session);
		String[] lineofcommands = new String[]{"transform", "sum", "col", "2"};
		session.getDestControl().clearFile();
		main.parse(lineofcommands, session);
		lineofcommands = new String[]{"transform", "stand_dev", "col", "2"};
		main.parse(lineofcommands, session);
		assertEquals(session.getDestControl().getNumRows(), 11);
		assertEquals(session.getDestControl().getNumCols(), 2);
	}

}
