package main;

import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
/**
 * Purpose: to create CSV for output.
 * Output to CSV should be from a collection.
 * @author johanna_jan_tw
 *
 */
public class CSVWriter {
	
	private FileWriter fileWriter;
	private CSVFormat format;
	private CSVPrinter printer;
	private CSVParser parser;
	
	public CSVWriter(String fileName, String delimeter) throws IOException{
		fileWriter = new FileWriter(fileName);
		format = CSVFormat.DEFAULT.withRecordSeparator(delimeter);
		printer = new CSVPrinter(fileWriter, format);
		Reader inputFile = new FileReader(fileName);
		parser = new CSVParser(inputFile, CSVFormat.DEFAULT.withHeader());
	}

	public CSVPrinter getCP() {
		return printer;
	}
	
	public void printRow(List<String> row) throws IOException{
		printer.printRecord(row);
	}
	
	public void printCol(List<String> col) throws IOException{
		if(col.size() > parser.getRecords().size()){
			for(int i = parser.getRecords().size(); i < col.size(); i++){
				addEmptyRow();
			}
		}
	}
	
	public void addEmptyRow() throws IOException{
		List<String> emptyRow = 
				Transformations.getStringZeroList(
						parser.getRecords().get(0).size());
		printRow(emptyRow);
	}
	
	public void close() throws IOException{
		printer.close();
		fileWriter.close();
	}
	
	

}
