package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import main.FileParser;
import main.Records;

public class RecordsTest {

	@Test
	public void testConstructor() throws IOException {
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 10, 0, 10);
		assertNotNull(records);
	}
	
	@Test
	public void testGetRecords() throws IOException {
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 100, 152, 3, 7);
		assertNotNull(records.getRecords());
	}
	
	@Test
	public void testGetNumberRows() throws IOException {
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 5, 3, 7);
		assertEquals(records.numRows(), 6);
	}
	
	@Test
	public void testGetNumberCols() throws IOException {
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 5, 3, 7);
		assertEquals(records.numCols(), 5);
	}
	
	@Test
	public void testGetCell() throws IOException{
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 5, 3, 7);
		assertEquals(records.getCell(3, 3), "639 Main St");
	}

}
