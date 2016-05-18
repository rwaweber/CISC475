package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.univocity.parsers.csv.CsvParserSettings;

import main.CSVController;

public class CSVControllerTest {

	static String numericFile = "src/tests/testfiles/testNumeric.csv";
	static String destFile = "src/tests/testfiles/testWrite.csv";
	static String addColFile = "src/tests/testfiles/testAddCol.csv";
	static String replaceRowFile = "src/tests/testfiles/testReplaceRow.csv";
	static String clearFile = "src/tests/testfiles/testClear.csv";
	static String removeColFile = "src/tests/testfiles/testRemoveCol.csv";
	private String removeRowFile =  "src/tests/testfiles/testRemoveRow.csv";


	static List<String> firstRow = new ArrayList<String>(Arrays.asList(new String[]{"Bob", "Benson", "NY", "12345"}));
	static List<String> secondRow = new ArrayList<String>(Arrays.asList(new String[]{"Sam", "Samuelson", "DE", "54321"}));
	static List<String> thirdRow = new ArrayList<String>(Arrays.asList(new String[]{"Ray", "Reeves", "PA", "98765"}));
	static List<String> firstCol = new ArrayList<String>(Arrays.asList(new String[]{"Hello", "World", "123", "456", "789", "10"}));
	static List<String> secondCol = new ArrayList<String>(Arrays.asList(new String[]{"hi", "earth", "123", "456", "789", "10"}));
	static List<String> thirdCol = new ArrayList<String>(Arrays.asList(new String[]{"hola", "planet", "123", "456", "789", "10"}));

	static List<String> replaceRow = new ArrayList<String>(Arrays.asList(new String[]{"This", "Row", "Has", "Been", "Replaced"}));
	static String stringRow = "please,split,me";
	static List<String> trueCol = new ArrayList<String>(Arrays.asList(new String[]{ "Num", "110", "120", "130", "140", "15", "160", "170", "180", "190", "200"}));
	static List<String> falseCol = new ArrayList<String>(Arrays.asList(new String[]{"110", "120", "131", "140", "15", "160", "170", "180", "190", "200"}));
	static List<String> trueRow = new ArrayList<String>(Arrays.asList(new String[]{"4", "140", "14", "5"}));
	static List<String> falseRow = new ArrayList<String>(Arrays.asList(new String[]{"4", "141", "14", "5"}));
	static List<String> headers = new ArrayList<String>(Arrays.asList(new String[]{"4", "141", "14", "5"}));

	static String header1 = "header1";
	static String header2 = "header2";
	static String header3 = "header3";


	public void initCols(){
		firstCol = new ArrayList<String>(Arrays.asList(new String[]{"Hello", "World", "123", "456", "789", "10"}));
		secondCol = new ArrayList<String>(Arrays.asList(new String[]{"hi", "earth", "123", "456", "789", "10"}));
		thirdCol = new ArrayList<String>(Arrays.asList(new String[]{"hola", "planet", "123", "456", "789", "10"}));
	}
	
	@Test
	public void testConstructor() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		CsvParserSettings settings = csvController.getSettings();
		assertNotNull(settings);
		//assertEquals(settings.getFormat().getLineSeparator()[0], '\r');
	}
	
	@Test
	public void testGetSettings() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		CsvParserSettings settings = csvController.getSettings();
		assertNotNull(settings);
	}

	@Test
	public void testGetRow() throws IOException {
		CSVController csvController = new CSVController(numericFile);
		List<String> firstRow = csvController.getRow(0);
		assertNotNull(firstRow);
		assertEquals(firstRow.size(), 4);
		assertEquals(firstRow.get(0), "Count");
		assertEquals(firstRow.get(3), "Distinct");
		List<String> middleRow = csvController.getRow(5);
		assertNotNull(middleRow);
		assertEquals(middleRow.size(), 4);
		assertEquals(middleRow.get(0), "5");
		assertEquals(middleRow.get(3), "4");
		List<String> lastRow = csvController.getRow(10);
		assertNotNull(lastRow);
		assertEquals(lastRow.size(), 4);
		assertEquals(lastRow.get(0), "10");
		assertEquals(lastRow.get(3), "3");
	}

	@Test
	public void testGetLastRow() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> lastRow = csvController.getLastRow();
		assertNotNull(lastRow);
		assertEquals(lastRow.size(), 4);
		assertEquals(lastRow.get(0), "10");
		assertEquals(lastRow.get(3), "3");
	}

	@Test
	public void testGetLastCol() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> lastCol = csvController.getLastCol();
		assertNotNull(lastCol);
		assertEquals(lastCol.size(), 11);
		assertEquals(lastCol.get(0), "Distinct");
		assertEquals(lastCol.get(10), "3");
	}

	@Test
	public void testGetCol() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> firstCol = csvController.getCol(0);
		assertNotNull(firstCol);
		assertEquals(firstCol.size(), 11);
		assertEquals(firstCol.get(0), "Count");
		for(int i = 1; i < 11; i++){
			assertEquals(firstCol.get(i), ((Integer)i).toString());
		}
		List<String> col = csvController.getCol(1);
		assertEquals(col.size(), 11);
		assertEquals(col.get(0), "Num");
	}

	@Test
	public void testAddRow() throws IOException{
		CSVController csvController = new CSVController(destFile);
		csvController.clearFile();
		csvController.addRow(firstRow);
		List<String> addedRow = csvController.getRow(0);
		assertNotNull(addedRow);
		assertEquals(addedRow.size(), 4);
		assertEquals(addedRow, firstRow);
	}

	@Test
	public void testAddCol() throws IOException{
		CSVController csvController = new CSVController(addColFile);
		csvController.clearFile();
		assertEquals(csvController.getNumRows(), 0);
		assertEquals(csvController.getNumCols(), 0);
		//		
		csvController.addCol(firstCol, header1);
		assertEquals(csvController.getNumCols(), 1);
		csvController.addCol(secondCol, header2);
		assertEquals(csvController.getNumCols(), 2);
		csvController.addCol(thirdCol, header3);
		assertEquals(csvController.getNumCols(), 3);
		assertEquals(csvController.getNumRows(), 7);
		assertEquals(csvController.getRow(0).get(0), "header1");
		assertEquals(csvController.getRow(0).get(1), "header2");
		assertEquals(csvController.getRow(0).get(2), "header3");
		assertEquals(csvController.getRow(1).get(1), "hi");
		assertEquals(csvController.getLastRow().get(1), "10");
		assertEquals(csvController.getLastRow().get(2), "10");

		initCols();
		csvController.clearFile();
		assertEquals(csvController.getNumRows(), 0);
		assertEquals(csvController.getNumCols(), 0);
		csvController.addCol(firstCol, header1);
		assertEquals(csvController.getNumCols(), 1);
		csvController.addCol(secondCol, header2);
		assertEquals(csvController.getNumCols(), 2);
		csvController.addCol(1, thirdCol, header3 );
		assertEquals(csvController.getNumCols(), 3);
		assertEquals(csvController.getNumRows(), 7);
		assertEquals(csvController.getCol(1).get(0), "header3");
		assertEquals(csvController.getCol(1).get(csvController.getCol(1).size()-1), "10");



	}


	@Test
	public void testGetText() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> text = csvController.getText();
		assertNotNull(text);
		assertEquals(text.size(), 11);
		assertEquals(text.get(0), "Count,Num,Number,Distinct");
		assertEquals(text.get(10), "10,200,1,3");
	}

	@Test
	public void testGetListFromString() throws IOException{
		List<String> list = CSVController.getListFromString(stringRow);
		assertNotNull(list);
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), "please");
		assertEquals(list.get(1), "split");
		assertEquals(list.get(2), "me");
	}

	@Test
	public void testClearFile() throws IOException{
		CSVController csvController = new CSVController(clearFile);
		csvController.clearFile();
		assertEquals(csvController.getNumRows(), 0);
		assertEquals(csvController.getNumCols(), 0);
		csvController.addRow(secondRow);
		csvController.addRow(firstRow);
		csvController.addRow(firstRow);
		assertEquals(csvController.getNumRows(), 3);
		csvController.clearFile();
		assertEquals(csvController.getNumRows(), 0);
		assertEquals(csvController.getNumCols(), 0);
	}

	@Test
	public void testGetNumRows() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		assertEquals(csvController.getNumRows(), 11);
	}

	@Test
	public void getMaxNumCols() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		assertEquals(csvController.getNumCols(), 4);
	}

	@Test
	public void testGetColNoHeader() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> colNoHeader = csvController.getColNoHeader(0);
		assertNotNull(colNoHeader);
		assertEquals(colNoHeader.size(), 10);
		assertEquals(colNoHeader.get(0), "1");
		assertEquals(colNoHeader.get(9), "10");
	}

	@Test
	public void testGetRandomCol() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> randCol = csvController.getRandomCol();
		assertNotNull(randCol);
		assertTrue(csvController.containsCol(randCol));
	}

	@Test
	public void testGetRandomRow() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> randRow = csvController.getRandomRow();
		assertNotNull(randRow);
		assertTrue(csvController.containsRow(randRow));
	}

	@Test
	public void testContainsCol() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		assertTrue(csvController.containsCol(trueCol));
		assertFalse(csvController.containsCol(falseCol));
	}

	@Test
	public void testContainsRow() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		assertTrue(csvController.containsRow(trueRow));
		assertFalse(csvController.containsRow(falseRow));
	}

	@Test
	public void testGetColByNameTest() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> col = csvController.getColByName("Num");
		assertNotNull(col);
		assertEquals(col, trueCol);
		List<String> noCol = csvController.getColByName("false");
		assertNull(noCol);

	}

	@Test
	public void testGetHeaders() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<String> headers = csvController.getHeaders();
		assertNotNull(headers);
		assertEquals(headers.size(), 4);
		assertEquals(headers.get(0), "Count");
		assertEquals(headers.get(3), "Distinct");
	}

	@Test
	public void testGetValue() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		String value = csvController.getValue(1,1);
		assertNotNull(value);
		assertEquals(value, "110");
	}

	@Test
	public void testRemoveCol() throws IOException{
		CSVController csvController = new CSVController(removeColFile);
		initCols();
		csvController.clearFile();
		assertEquals(csvController.getNumRows(), 0);
		assertEquals(csvController.getNumCols(), 0);
		csvController.addCol(firstCol, header1);
		csvController.addCol(secondCol, header2);
		csvController.addCol(thirdCol, header3);
		assertEquals(csvController.getNumCols(), 3);
		assertEquals(csvController.getNumRows(), 7);
		csvController.removeCol(1);
		assertEquals(csvController.getNumCols(), 2);
		assertEquals(csvController.getNumRows(), 7);
		assertEquals(csvController.getRow(0).get(0), "header1");
		assertEquals(csvController.getRow(0).get(1), "header3");
		assertEquals(csvController.getLastRow().get(0), "10");
		assertEquals(csvController.getLastRow().get(1), "10");
	}

	@Test
	public void testRemoveRow() throws IOException{
		CSVController csvController = new CSVController(removeRowFile);
		csvController.clearFile();
		csvController.addRow(firstRow);
		csvController.addRow(secondRow);
		csvController.addRow(thirdRow);
		assertEquals(csvController.getNumRows(), 3);
		assertEquals(csvController.getNumCols(), 4);
		csvController.removeRow(1);
		assertEquals(csvController.getNumRows(), 2);
		assertEquals(csvController.getNumCols(), 4);
		assertEquals(csvController.getRow(0).get(0), "Bob");
		assertEquals(csvController.getRow(1).get(0), "Ray");


	}

	@Test
	public void testGetRows() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<List<String>> rows = csvController.getRows();
		assertNotNull(rows);
		assertEquals(rows.size(), 11);
		assertEquals(rows.get(0).size(), 4);
		assertEquals(rows.get(0).get(0), "Count");
		assertEquals(rows.get(rows.size()-1).get(rows.get(rows.size()-1).size()-1), "3");

	}

	@Test
	public void testGetCols() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		List<List<String>> cols = csvController.getCols();
		assertNotNull(cols);
		assertEquals(cols.size(), 4);
		assertEquals(cols.get(0).size(), 11);
		assertEquals(cols.get(0).get(0), "Count");
		assertEquals(cols.get(cols.size()-1).get(cols.get(cols.size()-1).size()-1), "3");

	}
	
	@Test
	public void testGetNumCols() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		assertEquals(csvController.getNumCols(), 4);

	}
	

}
