package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.FileParser;
import main.Records;
import main.Transformations;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

public class TransformationsTest {
	
	List<String> testList = new ArrayList<String>(Arrays.asList(new String[]{"2", "3", "1", "5", "4"}));


	@Test
	public void testMean() throws IOException {
		List<Double> newList = Transformations.mean(testList);
		assertEquals(newList.size(), testList.size());
		assertEquals(Transformations.mean(testList).get(0), 3.0, 0.0001);
		
	}

	@Test
	public void testMax() throws IOException {
		List<Double> newList = Transformations.max(testList);
		assertEquals(newList.size(), 5);
		assertEquals(Transformations.max(testList).get(0), 5, 0.001);
	}

	@Test
	public void testMin() throws IOException {
		List<Double> newList = Transformations.min(testList);
		assertEquals(newList.size(), 5);
		assertEquals(Transformations.min(testList).get(0), 1, 0.001);
	}

	@Test
	public void testNumDistinctElements() throws IOException {
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 3, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 4);
		assertEquals(Transformations.numDistinctElements(records, 3), 7, 0.001);
	}

	@Test
	public void testSum() throws IOException {
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 3, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 4);
		assertEquals(Transformations.sum(records, 0), 55, 0.001);
	}

	@Test
	public void testAddValueToCol() throws IOException {
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 3, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		List<Object> oldCol = records.getCol(0);
		List<Double> newColAdd = Transformations.addValueToCol(records, 0, 10);
		assertEquals(newColAdd.size(), 10);
		List<Double> testColAdd = new ArrayList<Double>();
		for(int i = 1; i <= 10; i++){
			testColAdd.add(new Double(i + 10));
		}
		assertTrue(newColAdd.equals(testColAdd));
		assertTrue(records.getCol(0).equals(oldCol));
		List<Double> newColSub = Transformations.addValueToCol(records, 0, -10);
		assertEquals(newColSub.size(), 10);
		List<Double> testColSub = new ArrayList<Double>();
		for(int i = 1; i <= 10; i++){
			testColSub.add(new Double(i - 10));
		}
		assertTrue(newColSub.equals(testColSub));
		assertTrue(records.getCol(0).equals(oldCol));

	}

	@Test
	public void testNormalizeLocalExtrema() throws IOException {
		List<Double> dList = Transformations.normalizeLocalExtrema(testList);
		assertEquals(dList.size(), 5);
		double min = Transformations.min(testList).get(0);
		double max = Transformations.max(testList).get(0);
		List<Double> testDList = new ArrayList<Double>();
		for(int i = 0; i < testList.size(); i++){
			Double temp = (Double.parseDouble(testList.get(i)) - min)/(max-min);
			testDList.add(temp);
		}
		assertTrue(dList.equals(testDList));
	}

	@Test
	public void testDiscretize() throws IOException {
		String fileName = "src/tests/testfiles/test.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		List<Object> list = Transformations.discretize(records, 6);
		assertNotNull(list);
		assertEquals(list.size(), 500);
		assertEquals(list.get(0), records.getCell(0, 6).hashCode());
		assertEquals(list.get(1), records.getCell(1, 6).hashCode());
		assertEquals(list.get(499), records.getCell(499, 6).hashCode());
		String newFileName = "src/tests/testfiles/testWrite.csv";
		FileParser.recordsToCSV(records, newFileName, FileParser.getHeaders("src/tests/testfiles/test.csv"));
	}
	
	@Test
	public void testStandDev() throws IOException {
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		Double standDev = Transformations.standDev(records, 0);
		assertEquals(standDev, 3.02765, 0.0001);
	}
	
	@Test
	public void testGetZeroList() throws IOException {
		List<Double> zeroList = Transformations.getZeroList(10);
		assertEquals(zeroList.size(), 10);
		for(Double d : zeroList){
			assertEquals(d, 0.0, 0.0);
		}
	}

}
