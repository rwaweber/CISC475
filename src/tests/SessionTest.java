package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.Commands;
import main.Session;
import java.lang.reflect.*;
import java.util.ArrayList;

public class SessionTest {
	
	String sourceFile = "./src/tests/testfiles/testNumeric.csv";
	String destFile = "./src/tests/testfiles/testSession.csv";
	
	@Test
	public void testConstructor() throws IOException{
		Session session = new Session(sourceFile, destFile);
		assertNotNull(session);
		assertNotNull(session.getSourceControl());
		assertNotNull(session.getDestControl());
	}
	
	@Test
	public void testTransColToCol() throws IOException{
		Session session = new Session(sourceFile, destFile);
		session.getDestControl().clearFile();
		assertEquals(session.getDestControl().getNumRows(), 0);
		assertEquals(session.getDestControl().getNumCols(), 0);
		session.transColToCol(0, Commands.SUM(), "sum");
		session.transColToCol(0, Commands.SUM(), "sum");
		assertEquals(session.getDestControl().getNumRows(), 11);
		assertEquals(session.getDestControl().getNumCols(), 2);
		session.getDestControl().clearFile();
	}

    @Test
    public void testReflection() throws IOException {
    	Class x = Commands.class;
    	Method[] methods = x.getDeclaredMethods();
    	// this confuses me, why are there 12 possible methods in this interface?
    	assertEquals(methods.length, 12);
    	ArrayList<String> container = new ArrayList<String>();
    	for (Method method : methods) {
    		// dumb way to screen
    		if (!method.getName().contains("lambda"))
    			container.add(method.getName());
    	}
    	assertEquals(container.size(), 6);
    }
}
