package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVController {

	private String fileName;

	ICsvListReader reader;
	ICsvListWriter writer;

	public CSVController(String fileName) throws IOException{
		this.fileName = fileName;
		initReader();
		initWriter();
	}

	private void initReader() throws FileNotFoundException {
		reader = new CsvListReader(new FileReader(fileName), CsvPreference.STANDARD_PREFERENCE);
	}
	
	private void initWriter() throws IOException {
		writer = new CsvListWriter(new FileWriter(fileName, true), CsvPreference.STANDARD_PREFERENCE);
	}

	public List<String> getRow(int rowIndex) throws IOException{
		for(int i = 0; i < rowIndex - 1; i++){
			reader.read();
		}
		return reader.read();
	}

	public List<String> getCol(int colIndex) throws IOException {
		ArrayList<String> col = new ArrayList<String>();
		List<String> thisRow;
		while((thisRow = reader.read()) != null){
			col.add(thisRow.get(colIndex));
		}		
		return col;
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("src/tests/testfiles/testNumeric.csv"));
		System.out.println(br.readLine());
	}

}
