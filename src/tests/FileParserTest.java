package tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.junit.Test;

import main.FileParser;

public class FileParserTest {

	@Test
	public void testJSONToStream() {
		
	}
	
	@Test
	public void testStreamToJSON(){
				
		List<String> colNames = Arrays.asList(new String[]{"Name", "Age", "ID"});
		
		List<String> names = Arrays.asList(new String[]{"Bob", "Mary", "Albert"});
		List<Integer> ages = Arrays.asList(new Integer[]{32, 40, 27});
		List<Integer> ids = Arrays.asList(new Integer[]{12345, 98765, 11223});
		
		Stream<String> namesStream = names.stream();
		Stream<Integer> agesStream = ages.stream();
		Stream<Integer> idsStream = ids.stream();
		
		List<Stream> streams = new ArrayList<Stream>();
		streams.add(namesStream);
		streams.add(agesStream);
		streams.add(idsStream);
		
		int numRows = 3;
		
		JSONObject[] jsonObjects = FileParser.streamToJSON(streams, colNames, numRows);
		
		assertNotNull(jsonObjects);
		assertEquals(jsonObjects.length, 3);
		assertEquals(jsonObjects[0].get("Name"), "Bob");
		assertEquals(jsonObjects[1].get("Age"), 40);
		assertEquals(jsonObjects[2].get("ID"), 11223);

	}
	
	

}
