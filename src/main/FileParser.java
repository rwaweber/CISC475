package main;

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
	
	

}
