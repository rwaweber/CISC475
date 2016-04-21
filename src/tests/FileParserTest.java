package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.csv.CSVParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import main.FileParser;
import main.Records;

public class FileParserTest {

	static List<String> colNames = Arrays.asList(new String[]{"Name", "Age", "ID"});

	static List<String> names = Arrays.asList(new String[]{"Bob", "Mary", "Albert"});
	static List<Integer> ages = Arrays.asList(new Integer[]{32, 40, 27});
	static List<Integer> ids = Arrays.asList(new Integer[]{12345, 98765, 11223});
	
	static String numericFile = "/Users/benjaminrodd/git/CISC475/src/tests/testfiles/testNumeric.csv";

	static int numRows = 3;

	@Test
	public void testJSONToStream() {

	}

	@Test
	public void testStreamToJSON(){

		Stream<String> namesStream = names.stream();
		Stream<Integer> agesStream = ages.stream();
		Stream<Integer> idsStream = ids.stream();

		List<Stream> streams = Arrays.asList(new Stream[]{namesStream, agesStream, idsStream});
		JSONObject[] jsonObjects = FileParser.streamToJSON(streams, colNames, numRows);
		assertNotNull(jsonObjects);
		assertEquals(jsonObjects.length, 3);
		assertEquals(jsonObjects[0].get("Name"), "Bob");
		assertEquals(jsonObjects[1].get("Age"), 40);
		assertEquals(jsonObjects[2].get("ID"), 11223);
	}

	@Test
	public void testCreateFile(){

		String fileName = "src/tests/testfiles/test.json";
		FileParser.createFile(fileName);

		File createdFile = new File(fileName);
		assertTrue(createdFile.exists());
	}

	@Test
	public void testGetJsonObjectList(){
		JSONObject[] jsonObjects = FileParser.getJsonObjectList(10);
		assertNotNull(jsonObjects);
		assertEquals(jsonObjects.length, 10);
		assertNotNull(jsonObjects[3]);
	}

	@Test
	public void testWriteJSONToFile() throws ParseException, IOException{

		Stream<String> namesStream = names.stream();
		Stream<Integer> agesStream = ages.stream();
		Stream<Integer> idsStream = ids.stream();

		List<Stream> streams = Arrays.asList(new Stream[]{namesStream, agesStream, idsStream});
		JSONObject[] jsonObjects = FileParser.streamToJSON(streams, colNames, numRows);

		String fileName = "src/tests/testfiles/test.json";
		FileParser.writeJSONToFile(fileName, jsonObjects);

		File noFile = new File("src/tests/testfiles/nofile.json");
		assertFalse(noFile.exists());
	}

	@Test
	public void testGetCSVParser() throws IOException{
		CSVParser parser =  FileParser.getCSVFileParser("src/tests/testfiles/test.csv");
		assertNotNull(parser);
	}

	@Test
	public void testCSVToArray() throws IOException{
		String fileName = "src/tests/testfiles/test.csv";
		Records records = FileParser.csvToArray(fileName,4,5,1,3);
		assertNotNull(records);
		assertEquals(records.numRows(), 2);
		assertEquals(records.numCols(), 3);
		assertEquals(records.getCell(0, 0), "Foller");
		assertEquals(records.getCell(0, 1), "Printing Dimensions");
		assertEquals(records.getCell(1, 0), "Morasca");
		assertEquals(records.getCell(1, 1), "Chapman, Ross E Esq");
		Records allRecords = FileParser.csvToRecords(fileName);
		assertEquals(allRecords.numRows(), 500);
		assertEquals(allRecords.numCols(), 12);
	}

	@Test
	public void testArrayToCSV() throws IOException{

		String sourceFile = "src/tests/testfiles/test.csv";
		String destFile = "src/tests/testfiles/testArrayToCSV.csv";
		Records records = FileParser.csvToArray(sourceFile,4,5,1,3);

		FileParser.recordsToCSV(records, destFile, new String[]{"first_name", "last_name", "company_name"});
		Records recordsFromCSV = FileParser.csvToArray(destFile, 0, 1, 0, 2);

		assertEquals(recordsFromCSV.numRows(), 2);
		assertEquals(recordsFromCSV.numCols(), 3);
		assertEquals(recordsFromCSV.getCell(0, 0), "Foller");
		assertEquals(recordsFromCSV.getCell(1, 1), "Chapman, Ross E Esq");
		assertEquals(recordsFromCSV.getCell(0, 0), "Foller");
		assertEquals(recordsFromCSV.getCell(1, 2), "3 Mcauley Dr");

	}

	@Test
	public void testGetListFromArray(){
		Object[] array = new Object[]{"Hello", 10, "World"};
		ArrayList<Object> list = FileParser.getListFromArray(array);
		assertNotNull(list);
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), "Hello");
		assertEquals(list.get(1), 10);
		ArrayList<Object> shortList = FileParser.getListFromArray(array, 0, 1);
		assertNotNull(shortList);
		assertEquals(shortList.size(), 2);
		assertEquals(shortList.get(0), "Hello");
		assertEquals(shortList.get(1), 10);
	}

	@Test
	public void testGetHeaders() throws IOException{
		String[] control = new String[] {"first_name","last_name","company_name","address","city","county","state","zip","phone1","phone2","email","web"};
		String[] test = FileParser.getHeaders("src/tests/testfiles/test.csv");
		assertEquals(control, test);
	}

	@Test
	public void testGetColFromFile() throws IOException{
		List<String> col = FileParser.getColFromFile(numericFile, 0);
		assertNotNull(col);
		assertEquals(col.size(), 10);
		List<String> testCol = new ArrayList<String>();
		for(int i = 1; i <= 10; i++){
			testCol.add(((Integer)i).toString());
		}
		assertEquals(testCol, col);
	}

}
