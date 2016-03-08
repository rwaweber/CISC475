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
		assertEquals(records.getRecords().size(), 11);
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
		assertEquals(records.getCell(3, 3), "AK");
	}
	
	@Test
	public void testConvertTo2DArray() throws IOException{
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 3, 0, 3);
		Object[][] array = records.convertTo2DArray();
		assertNotNull(array);
		assertEquals(array.length, 4);
		assertEquals(array[0][0], "James");
		assertEquals(array[3][3], "639 Main St");
		assertEquals(array[1][2], "Venere");
	}

}
