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
import main.RecordsComparator;

public class RecordsComparatorTest {

	@Test
	public void testConstructor() throws IOException {
		String fileName = "src/tests/testfiles/testNumeric.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, new ArrayList<String>(Arrays.asList(FileParser.getHeaders(fileName))));
		RecordsComparator rc = new RecordsComparator(records, 3);
		assertNotNull(rc);
		assertNotNull(rc.getRecords());
		assertEquals(rc.getRecords(), records);
		assertEquals(rc.getColIndex(), 3);
	}

}
