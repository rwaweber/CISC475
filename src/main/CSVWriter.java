package main;

import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
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
	
	public CSVWriter(String fileName, String delimeter) throws IOException{
		fileWriter = new FileWriter(fileName);
		format = CSVFormat.DEFAULT.withRecordSeparator(delimeter);
		printer = new CSVPrinter(fileWriter, format);
	}

	public CSVPrinter getCP() {
		return printer;
	}
	
	public void printRecord(ArrayList<Object> record) throws IOException{
		printer.printRecord(record);
	}
	
	public void closePrinter() throws IOException{
		printer.close();
	}
	

}
