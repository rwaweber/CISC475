package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/* Class for taking in JSON data files and transforming
into stream objects. */
public class FileParser {
	
	public void InputParser(){
		
	}
		
	public static void main(String[] args){
		
		System.out.println("test");
		
	}
	
	public static JSONObject[] streamToJSON(List<Stream> streams, List<String> colNames, int numRows){
		JSONObject[] jsonObjects = new JSONObject[numRows];
		for(int i = 0; i < jsonObjects.length; i++){
			jsonObjects[i] = new JSONObject();
		}
		for(int j = 0; j < streams.size(); j++){
			Object[] thisStream = streams.get(j).toArray();
			for(int k = 0; k < numRows; k++){
				jsonObjects[k].put(colNames.get(j), thisStream[k]);
			}
		}	
		return jsonObjects;
		
	}
	
	public static void writeJSONToFile(String fileName, JSONObject[] jsonObjects){
		File file = new File(fileName);
		try {
			if(file.createNewFile()){
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
}
