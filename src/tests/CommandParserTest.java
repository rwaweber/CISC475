package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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

}
