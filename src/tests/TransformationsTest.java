package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
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
		String fileName = "src/tests/testfiles/testmean.csv";
		CSVParser parser = FileParser.getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		Records records = new Records(csvRecords, 0, 9, 0, 2);
		assertNotNull(records);
		assertEquals(records.getRecords().size(), 3);
		
		assertEquals(Transformations.mean(records,0),5.5,.001);
	}
}
