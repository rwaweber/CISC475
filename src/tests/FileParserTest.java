package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

	static Stream<String> namesStream = names.stream();
	static Stream<Integer> agesStream = ages.stream();
	static Stream<Integer> idsStream = ids.stream();

	static List<Stream> streams = Arrays.asList(new Stream[]{namesStream, agesStream, idsStream});

	static int numRows = 3;

	static JSONObject[] jsonObjects = FileParser.streamToJSON(streams, colNames, numRows);


	@Test
	public void testJSONToStream() {
	 
	}

	@Test
	public void testStreamToJSON(){
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
	public void testWriteJSONToFile() throws ParseException, IOException{
		String fileName = "src/tests/testfiles/test.json";
		FileParser.writeJSONToFile(fileName, jsonObjects);

		

		File noFile = new File("src/tests/testfiles/nofile.json");
		assertFalse(noFile.exists());
	}

}
