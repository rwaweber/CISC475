package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Session;

public class SessionTest {
	
	String sourceFile = "/Users/benjaminrodd/git/CISC475/src/tests/testfiles/testNumeric.csv";
	String destFile = "/Users/benjaminrodd/git/CISC475/src/tests/testfiles/testSession.csv";
	
	@Test
	public void testConstructor(){
		Session session = new Session(sourceFile, destFile);
		assertNotNull(session);
		assertNotNull(session.getSourceFile());
		assertNotNull(session.getDestFile());
		assertEquals(session.getSourceFile(), "/Users/benjaminrodd/git/CISC475/src/tests/testfiles/testNumeric.csv");
		assertEquals(session.getDestFile(), "/Users/benjaminrodd/git/CISC475/src/tests/testfiles/testSession.csv");
	}
}
