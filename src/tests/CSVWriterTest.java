package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import main.CSVWriter;

public class CSVWriterTest {

	@Test
	public void testConstructor() throws IOException {
		CSVWriter csvWriter = new CSVWriter("src/tests/testfiles/testStreamToCSV.csv", "\n");
		assertNotNull(csvWriter);
	}
	
	@Test
	public void testGetCP() throws IOException {
		CSVWriter csvWriter = new CSVWriter("src/tests/testfiles/testStreamToCSV.csv", "\n");
		assertNotNull(csvWriter.getCP());
	}

}
