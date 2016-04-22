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

	static List<String> firstRow = Arrays.asList(new String[]{"Bob", "Benson", "NY", "12345"});
	static List<String> firstCol = Arrays.asList(new String[]{"Hello", "World", "123", "456", "789", "10"});
	static List<String> replaceRow = Arrays.asList(new String[]{"This", "Row", "Has", "Been", "Replaced"});

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

//	@Test
//	public void testAddCol() throws IOException{
//		CSVController csvController = new CSVController(addColFile);
//		csvController.clearDestFile();
//		csvController.addCol(firstCol);
//		List<String> lastRow = csvController.getLastRow();
//		assertNotNull(lastRow);
//		//csvController.clearDestFile();
//		//assertEquals(lastRow.get(lastRow.size() - 1), "456");
//	}

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


}
