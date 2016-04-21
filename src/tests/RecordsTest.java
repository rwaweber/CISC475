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
		Records allRecords = new Records(csvRecords,  new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(allRecords);
		assertEquals(allRecords.numRows(), 500);
		assertEquals(allRecords.numCols(), 12);
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
		List<String> col0 = records.getCol(0);
		assertNotNull(col0);
		assertEquals(col0.get(0), "James");
		assertEquals(col0.get(3), "Lenna");
		List<String> col3 = records.getCol(3);
		assertEquals(col3.get(0), "6649 N Blue Gum St");
		assertEquals(col3.get(3), "639 Main St");
	}
	
	@Test
	public void test() throws IOException{
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 2, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		List<String> col0 = records.getCol(0);
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 3);
	}
	
	@Test
	public void testAddCol() throws IOException{
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 2, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertEquals(records.getHeaders().size(), 4);
		records.addCol("new col name");
		assertEquals(records.getRecords().size(), 4);
		assertEquals(records.getHeaders().size(), 5);
		assertEquals(records.getHeaders().get(4), "new col name");

	}
	
	@Test
	public void testRemoveCol() throws IOException{
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 3, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		records.removeCol(1);
		assertEquals(records.getRecords().size(), 3);
		assertEquals(records.getRecords().get(1).get(0), "37");
		assertEquals(records.numCols(), 3);
	}
	
	@Test
	public void testGetRandomCol() throws IOException{
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		ArrayList<String> randCol = records.getRandCol();	
		System.out.println("randCol: " + randCol);
		assertTrue(records.containsCol(randCol));
	}
	
	@Test
	public void testColEquals() throws IOException{
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		ArrayList<String> testCol = new ArrayList<String>();
		for(int i = 1; i <= 10; i++){
			testCol.add(new String(new Integer(i).toString()));
		}
		assertTrue(records.colEquals(0, testCol));
		assertTrue(records.colEquals(records.getCol(0), testCol));
	}
	
	@Test
	public void testSize() throws IOException{
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertEquals(records.colSize(2), 10);
	}
	
	@Test
	public void testContainsCol() throws IOException{
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertTrue(records.containsCol(records.getCol(2)));
		ArrayList<String> falseCol = new ArrayList<String>();
		for(int i = 100; i < 110; i++){
			falseCol.add(new Integer(i).toString());
		}
		assertFalse(records.containsCol(falseCol));
	}
	
	@Test
	public void testTranspose() throws IOException{
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		List<ArrayList<Object>> transpose = records.transpose();
		assertNotNull(transpose);
		assertEquals(transpose.size(), 10);
	}
	
	@Test
	public void testGetRowFromColLists(){
		List<ArrayList<Object>> colLists = new ArrayList<ArrayList<Object>>();
		for(int i = 0; i <= 4; i+= 2){
			ArrayList<Object> col = new ArrayList<Object>();
			col.add(new Integer(i+1));
			col.add(new Integer(i+2));
			colLists.add(col);
		}
		assertEquals(colLists.size(), 3);
		assertEquals(colLists.get(0).get(1), 2);
		ArrayList<Object> row = Records.getRowFromColLists(colLists, 0);
	}
}
