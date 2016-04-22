package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import main.CSVController;

public class CSVControllerTest {

	static String numericFile = "src/tests/testfiles/testNumeric.csv";
	static String destFile = "src/tests/testfiles/testWrite.csv";

	@Test
	public void testGetRow() throws IOException {
		CSVController csvController = new CSVController(numericFile);
		List<String> firstRow = csvController.getRow(0);
		assertNotNull(firstRow);
		assertEquals(firstRow.size(), 4);
		assertEquals(firstRow.get(0), "Count");
		assertEquals(firstRow.get(3), "Distinct");
		List<String> lastRow = csvController.getRow(10);
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
	

}
