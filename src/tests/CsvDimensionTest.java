package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.univocity.parsers.common.ParsingContext;

import main.CsvDimension;

public class CsvDimensionTest {
	
	static String firstRow[] = new String[]{"1", "2", "3"};
	static String secondRow[] = new String[]{"1", "2", "3", "4", "5"};

	@Test
	public void testConstructor() {
		CsvDimension dimension = new CsvDimension();
		assertNotNull(dimension);
		int numRows = dimension.getNumRows();
		assertEquals(numRows, 0);
		int numCols = dimension.getnumCols();
		assertEquals(numCols, 0);
	}
	
	@Test
	public void testRowProcessed(){
		CsvDimension dimension = new CsvDimension();
		ParsingContext ParsingContext = null;
		dimension.rowProcessed(firstRow, ParsingContext);
		assertEquals(dimension.getNumRows(), 1);
		assertEquals(dimension.getnumCols(), 3);
		dimension.rowProcessed(secondRow, ParsingContext);
		assertEquals(dimension.getNumRows(), 2);
		assertEquals(dimension.getnumCols(), 5);
	}

}
