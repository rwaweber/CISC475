package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.Commands;
import main.Session;

public class SessionTest {
	
	String sourceFile = "/Users/benjaminrodd/git/CISC475/src/tests/testfiles/testNumeric.csv";
	String destFile = "/Users/benjaminrodd/git/CISC475/src/tests/testfiles/testSession.csv";
	
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
		session.transColToCol(0, Commands.SUM, "sum");
		session.transColToCol(0, Commands.SUM, "sum");
		assertEquals(session.getDestControl().getNumRows(), 11);
		assertEquals(session.getDestControl().getNumCols(), 2);
		session.getDestControl().clearFile();
	}
}
