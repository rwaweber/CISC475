package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.json.simple.JSONObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


/* Class for taking in JSON data files and transforming
into stream objects. */
public class FileParser {

	// makes a list of JSON Object Streams: 
	//	List<Stream<JSONObject>> masterList;
	//	public List<Stream<JSONObject>> jsonToStream(JSONObject[] list){
	//		for(int i=0;i<list.length;i++){
	//			Stream<JSONObject> stream = Stream.of(list[i]);
	//			masterList.add(stream);
	//		}
	//		return masterList;
	//	}
    
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
		return file;
	}

	public static void writeJSONToFile(String fileName, JSONObject[] jsonObjects) throws IOException{
		File file = createFile(fileName);
		FileWriter fw = new FileWriter(file);
		for(int i = 0; i < jsonObjects.length; i++){
			fw.write(jsonObjects[i].toJSONString());
		}
		fw.close();
	}

	public static CSVParser getCSVFileParser(String fileName) throws IOException {
		Reader inputFile = new FileReader(fileName);
		return new CSVParser(inputFile, CSVFormat.DEFAULT.withHeader());

	}

	public static Records csvToArray(String fileName, int startRow, int endRow, int startCol, int endCol) throws IOException{
		CSVParser parser = getCSVFileParser(fileName);
		List<CSVRecord> csvRecords = parser.getRecords();
		parser.close();
		return new Records(csvRecords, startRow, endRow, startCol, endCol, new ArrayList<String>(Arrays.asList(getHeaders(fileName))));
	}

	public static Records csvToRecords(String fileName) throws IOException{
		CSVParser parser = getCSVFileParser(fileName);
		List<CSVRecord> records = parser.getRecords();
		parser.close();
		return new Records(records, 0, records.size()-1, 0, parser.getHeaderMap().size()-1, new ArrayList<String>(Arrays.asList(getHeaders(fileName))));
	}

	public static ArrayList<String> getListFromArray(String[] array){
		return new ArrayList<String>(Arrays.asList(array));
	}

	public static ArrayList<Object> getListFromArray(Object[] array, int start, int end){
		ArrayList<Object> list =  new ArrayList<Object>();
		for(int i = start; i <= end - start; i++){
			list.add(array[i]);
		}
		return list;
	}

	public static void recordsToCSV(Records records, String fileName, String[] fileHeader) throws IOException{
		createFile(fileName);
		CSVWriter csvWriter = new CSVWriter(fileName, "\n");
		csvWriter.printRow(getListFromArray(fileHeader));
		for(int thisRecord = 0; thisRecord < records.numRows(); thisRecord++){
			csvWriter.printRow(records.getRecord(thisRecord));
		}
		csvWriter.close();
	}
        
        public static String[] getHeaders(String fileName) throws IOException{
	        CSVParser parser = getCSVFileParser(fileName);
		Map<String,Integer> header_map = parser.getHeaderMap();
		parser.close();
		Set<String> header_set = header_map.keySet();
		String[] headers = header_set.toArray(new String[header_set.size()]);
		return headers;
	}


	public static List<String> getRowFromFile(String fileName, int rowIndex) throws IOException {
		CSVParser parser = getCSVFileParser(fileName);
		List<CSVRecord> records = parser.getRecords();
		List<String> row = Transformations.getStringZeroList(records.get(0).size());
		CSVRecord record = records.get(rowIndex);
		for(int thisCol = 0; thisCol < record.size(); thisCol++){
			row.set(thisCol, record.get(thisCol));
		}
		return row;
		
	}
	public static List<String> getColFromFile(String fileName, int colIndex) throws IOException {
		CSVParser parser = getCSVFileParser(fileName);
		List<CSVRecord> records = parser.getRecords();
		List<String> col = Transformations.getStringZeroList(records.size());
		int index = 0;
		for(CSVRecord record : records){
			col.set(index++, record.get(colIndex));
		}
		return col;
	}


	public static void addColToFile(String destFile, List<String> names) {
		
	}

	public static void addRowToFile(String destFile, List<String> row) throws IOException {
		CSVWriter cw = new CSVWriter(destFile, "\n");
		cw.printRow(row);
		cw.close();
	}

	public static void main(String[] args) throws IOException{
		String destFile = "/Users/benjaminrodd/Desktop/addRow.csv";
		List<String> row = Arrays.asList(new String[]{"sdf", "dsf", "gg"});
		addRowToFile(destFile, row);

	}

}
