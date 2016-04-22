package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

	public void clearFile() throws IOException{
		writer = new CsvListWriter(new FileWriter(fileName), CsvPreference.STANDARD_PREFERENCE);
		writer.close();
		initWriter();
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

	public void addCol(List<String> col) throws IOException {	
		List<String> text = getText();
		for(int index = 0; index < col.size(); index++){
			String oldRow = text.get(index);
			oldRow += ", " + col.get(index);
		}
		clearFile();
		initWriter();
		for(String s : text){
			List<String> newRow = Arrays.asList(s.split(","));
		}
		closeWriter();
	}

	// assumes all rows have same number of columns
	public List<String> getLastCol() throws IOException {
		initReader();
		ArrayList<String> col = new ArrayList<String>();
		List<String> thisRow;
		while((thisRow = reader.read()) != null){
			col.add(thisRow.get(thisRow.size()-1));
		}		
		closeReader();
		return col;
	}

	public void replaceRow(int rowIndex, List<String> newRow) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
		int thisIndex = 0;
		String thisLine = "";
		while(thisIndex < rowIndex){
			thisLine = br.readLine();
			thisIndex++;
		}
		System.out.println(thisLine);
		
	}

	public List<String> getText() throws IOException {
		List<String> text = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String thisLine;
		while((thisLine = br.readLine()) != null){
			text.add(thisLine);
		}
		br.close();
		return text;
	}

	public static List<String> getListFromString(String stringRow) {
		return Arrays.asList(stringRow.split(","));
	}

}
