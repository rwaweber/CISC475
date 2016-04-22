package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import main.CSVController;

public class CSVControllerTest {

	static String numericFile = "src/tests/testfiles/testNumeric.csv";
	static String destFile = "src/tests/testfiles/testWrite.csv";
	static String addColFile = "src/tests/testfiles/testAddCol.csv";
	static String replaceRowFile = "src/tests/testfiles/testReplaceRow.csv";
	static String clearFile = "src/tests/testfiles/testClear.csv";


	static List<String> firstRow = Arrays.asList(new String[]{"Bob", "Benson", "NY", "12345"});
	static List<String> firstCol = Arrays.asList(new String[]{"Hello", "World", "123", "456", "789", "10"});
	static List<String> replaceRow = Arrays.asList(new String[]{"This", "Row", "Has", "Been", "Replaced"});
	static String stringRow = "please,split,me";

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
	}

	@Test
	public void testAddRow() throws IOException{
		CSVController csvController = new CSVController(destFile);
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
			csvController.addCol(firstCol);
			assertEquals(csvController.getNumCols(), 1);
			csvController.addCol(firstCol);
			assertEquals(csvController.getNumCols(), 2);
			csvController.addCol(firstCol);
			assertEquals(csvController.getNumCols(), 3);
			assertEquals(csvController.getNumRows(), 6);
		}

	//	@Test
	//	public void testReplaceRow() throws IOException{
	//		CSVController csvController = new CSVController(replaceRowFile);
	//		csvController.clearDestFile();
	//		csvController.addRow(firstRow);
	//		csvController.addRow(firstRow);
	//		csvController.addRow(firstRow);
	//		List<String> lastRow = csvController.getLastRow();
	//		assertNotNull(lastRow);
	//		assertEquals(lastRow.size(), 4);
	//		assertEquals(lastRow, firstRow);
	//		csvController.replaceRow(1, replaceRow);
	//		List<String> newRow = csvController.getRow(1);
	//		assertEquals(newRow.size(), 5);
	//		csvController.clearDestFile();
	//		
	//	}

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
		csvController.addRow(firstRow);
		csvController.addRow(firstRow);
		csvController.addRow(firstRow);
		assertEquals(csvController.getNumRows(), 3);
		csvController.clearFile();
	}
	
	@Test
	public void getNumRows() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		assertEquals(csvController.getNumRows(), 11);
	}
	
	@Test
	public void getMaxNumCols() throws IOException{
		CSVController csvController = new CSVController(numericFile);
		assertEquals(csvController.getNumCols(), 4);
	}

}
