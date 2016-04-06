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
	@Test
	public void testMean() throws IOException {
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 2, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 3);
		assertEquals(Transformations.mean(records,0),5.5,.001);
	}

	@Test
	public void testMax() throws IOException {
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 2, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 3);
		assertEquals(Transformations.max(records, 2), 98, 0.001);
	}

	@Test
	public void testMin() throws IOException {
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 2, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 3);
		assertEquals(Transformations.min(records, 2), 1, 0.001);
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
		List<Double> newColAdd = Transformations.addValueToCol(records, 0, 10);
		assertEquals(newColAdd.size(), 10);
		List<Double> testColAdd = new ArrayList<Double>();
		for(int i = 1; i <= 10; i++){
			testColAdd.add(new Double(i + 10));
		}
		assertTrue(newColAdd.equals(testColAdd));
		List<Double> newColSub = Transformations.addValueToCol(records, 0, -10);
		assertEquals(newColSub.size(), 10);
		List<Double> testColSub = new ArrayList<Double>();
		for(int i = 1; i <= 10; i++){
			testColSub.add(new Double(i - 10));
		}
		assertTrue(newColSub.equals(testColSub));
	}

}
