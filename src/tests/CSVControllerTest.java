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
	
	static List<String> firstRow = Arrays.asList(new String[]{"Bob", "Benson", "NY", "12345"});

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
	

}
