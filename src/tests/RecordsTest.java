package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
		Records records = new Records(csvRecords, 0, 10, 0, 10, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 11);
	}

	@Test
	public void testGetRecords() throws IOException {
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 100, 152, 3, 7, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records.getRecords());
	}

	@Test
	public void testGetNumberRows() throws IOException {
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 5, 3, 7, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertEquals(records.numRows(), 6);
	}

	@Test
	public void testGetNumberCols() throws IOException {
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 5, 3, 7, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertEquals(records.numCols(), 5);
	}

	@Test
	public void testGetCell() throws IOException{
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 5, 3, 7, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertEquals(records.getCell(3, 3), "AK");
	}

	@Test
	public void testConvertTo2DArray() throws IOException{
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 3, 0, 3, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		Object[][] array = records.convertTo2DArray();
		assertNotNull(array);
		assertEquals(array.length, 4);
		assertEquals(array[0][0], "James");
		assertEquals(array[3][3], "639 Main St");
		assertEquals(array[1][2], "Venere");
	}

	@Test
	public void testGetCol() throws IOException{
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 3, 0, 3, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		List<Object> col0 = records.getCol(0);
		assertNotNull(col0);
		assertEquals(col0.get(0), "James");
		assertEquals(col0.get(3), "Lenna");
		List<Object> col3 = records.getCol(3);
		assertEquals(col3.get(0), "6649 N Blue Gum St");
		assertEquals(col3.get(3), "639 Main St");
	}
	
	@Test
	public void test() throws IOException{
		String fileName = "src/tests/testfiles/testmean.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 2, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		List<Object> col0 = records.getCol(0);
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 3);
	}
	
	@Test
	public void testAddCol() throws IOException{
		String fileName = "src/tests/testfiles/testmean.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 2, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		records.addCol("new col name");
		assertEquals(records.getRecords().size(), 4);
	}

}
