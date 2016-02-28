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

public class FileParserTest {

	static List<String> colNames = Arrays.asList(new String[]{"Name", "Age", "ID"});

	static List<String> names = Arrays.asList(new String[]{"Bob", "Mary", "Albert"});
	static List<Integer> ages = Arrays.asList(new Integer[]{32, 40, 27});
	static List<Integer> ids = Arrays.asList(new Integer[]{12345, 98765, 11223});

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
	public void testCSVToStream(){
		String fileName = "src/tests/testfiles/test.csv";
		List<Stream<Object>> streams = FileParser.csvToStream(fileName, 5, 10);
		assertNotNull(streams);
		assertEquals(streams.size(), 12);
		assertEquals(streams.get(0).toArray().length, 5);
		assertEquals(streams.get(4).toArray()[0], "Hamilton");
	}
	
	@Test
	public void testStreamToCSV() throws IOException{
		
		Stream<String> namesStream = names.stream();
		Stream<Integer> agesStream = ages.stream();
		Stream<Integer> idsStream = ids.stream();
		
		List<Stream> streams = Arrays.asList(new Stream[]{namesStream, agesStream, idsStream});
		
		String fileName = "src/tests/testfiles/testStreamToCSV.csv";
		
		FileParser.streamToCSV(streams, 3, fileName);
		
		List<Stream<Object>> streamsFromCSV = FileParser.csvToStream(fileName, 1, 2);
		
		assertEquals(streamsFromCSV.size(), 3);
		assertEquals(streamsFromCSV.get(1).toArray()[0], "40");
		
	}

}
