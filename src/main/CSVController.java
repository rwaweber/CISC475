package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
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
	}

	private void initReader() throws FileNotFoundException {
		reader = new CsvListReader(new FileReader(fileName), CsvPreference.STANDARD_PREFERENCE);
	}
	
	private void closeReader() throws IOException{
		reader.close();
	}
	
	private void initWriter() throws IOException {
		writer = new CsvListWriter(new FileWriter(fileName, true), CsvPreference.STANDARD_PREFERENCE);
	}
	
	private void closeWriter() throws IOException{
		writer.close();
	}
	
	public List<String> getRow(int rowIndex) throws IOException{
		initReader();
		List<String> thisRow;
		int thisIndex = 0;
		while((thisRow = reader.read()) != null && thisIndex < rowIndex){
			thisIndex++;
		}
		closeReader();
		return thisRow;
	}

	public List<String> getCol(int colIndex) throws IOException {
		initReader();
		ArrayList<String> col = new ArrayList<String>();
		List<String> thisRow;
		while((thisRow = reader.read()) != null){
			col.add(thisRow.get(colIndex));
		}		
		closeReader();
		return col;
	}
	

	public void addRow(List<String> row) throws IOException {
		initWriter();
		writer.write(row);
		closeWriter();

	}

	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("src/tests/testfiles/testNumeric.csv"));
		System.out.println(br.readLine());
	}

	public List<String> getLastRow() throws IOException {
		initReader();
		List<String> thisRow = reader.read();
		List<String> lastRow = thisRow;
		while(thisRow != null){
			lastRow = thisRow;
			thisRow = reader.read();
		}
		closeReader();
		return lastRow;
	}

}
