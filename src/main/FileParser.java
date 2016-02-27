package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/* Class for taking in JSON data files and transforming
into stream objects. */
public class FileParser {

	// makes a list of JSON Object Streams: 
	List<Stream<JSONObject>> masterList;
	public List<Stream<JSONObject>> jsonToStream(JSONObject[] list){
		for(int i=0;i<list.length;i++){
			Stream<JSONObject> stream = Stream.of(list[i]);
			masterList.add(stream);
		}
		return masterList;
	}

	public static JSONObject[] getJsonObjectList(int numJsonObjects){
		JSONObject[] jsonObjects = new JSONObject[numJsonObjects];
		for(int i = 0; i < jsonObjects.length; i++){
			jsonObjects[i] = new JSONObject();
		}
		return jsonObjects;
	}

	public static JSONObject[] streamToJSON(List<Stream> streams, List<String> colNames, int numRows){
		JSONObject[] jsonObjects = getJsonObjectList(numRows);
		for(int j = 0; j < streams.size(); j++){
			Object[] thisStream = streams.get(j).toArray();
			for(int k = 0; k < numRows; k++){
				jsonObjects[k].put(colNames.get(j), thisStream[k]);
			}
		}	
		return jsonObjects;

	}

	public static File createFile(String fileName){
		File file = new File(fileName);
		try {
			if(file.createNewFile()){
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	public static void writeJSONToFile(String fileName, JSONObject[] jsonObjects){
		File file = createFile(fileName);
		try {
			FileWriter fw = new FileWriter(file);
			for(int i = 0; i < jsonObjects.length; i++){
				fw.write(jsonObjects[i].toJSONString());
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Stream<Object>> csvToStream(String fileName, int startLine, int endLine){
		List<Stream<Object>> streams = new ArrayList<Stream<Object>>();
		try {
			Reader input = new FileReader(fileName);
			CSVParser parser = new CSVParser(input, CSVFormat.DEFAULT.withHeader());
			List<CSVRecord> records = parser.getRecords();
			for(int streamIndex = 0; streamIndex < parser.getHeaderMap().size(); streamIndex++){
				List<Object> streamList = new LinkedList<Object>();
				for(int recordIndex = startLine - 1; recordIndex < endLine - 1; recordIndex++){
					streamList.add(records.get(recordIndex).get(streamIndex));
				}
				Stream<Object> stream = streamList.stream();
				streams.add(stream);
				//System.out.println(stream.toArray()[5]);
			}
			parser.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return streams;

	}

	public static void main(String[] args){

		System.out.println("test");

	}

}
