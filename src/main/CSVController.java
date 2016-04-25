package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
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
		int numRows = getNumRows();
		for(int index = 0; index < col.size(); index++){
			if(index < numRows){
				String oldRow = text.get(index);
				text.set(index, oldRow += ", " + col.get(index));
			}
			else{
				text.add(index, col.get(index));
			}
		}
		clearFile();
		initWriter();
		for(String s : text){
			List<String> newRow = getListFromString(s);
			writer.write(newRow);
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

	public int getNumRows() throws IOException {
		int numRows = 0;
		initReader();
		while(reader.read() != null){
			numRows++;
		}
		closeReader();
		return numRows;
	}

	public int getNumCols() throws IOException {
		initReader();
		int numCols = 0;
		List<String> thisRow;
		while((thisRow = reader.read()) != null){
			int thisNumCols = thisRow.size();
			if(thisNumCols > numCols){
				numCols = thisNumCols;
			}
		}
		closeReader();
		return numCols;
	}

	public List<String> getColNoHeader(int i) throws IOException {
		List<String> col = getCol(i);
		col.remove(0);
		return col;
		
	}

	// does not include column header
	public List<String> getRandomCol() throws IOException {
		List<String> randCol = getCol((int)(getNumCols() * Math.random()));
		randCol.remove(0);
		return randCol;
	}

	public boolean containsCol(List<String> col) throws IOException {
		for(int colIndex = 0; colIndex < getNumCols(); colIndex++){
			List<String> thisCol = getCol(colIndex);
			thisCol.remove(0);
			if(thisCol.equals(col)){
				return true;
			}
		}
		return false;
	}

	public boolean containsRow(List<String> trueRow) throws IOException {
		for(int rowIndex = 0; rowIndex < getNumRows(); rowIndex++){
			if(getRow(rowIndex).equals(trueRow)){
				return true;
			}
		}
		return false;
	}

	// does not consider the row of headers
	public List<String> getRandomRow() throws IOException {
		int rowIndex = (int)(getNumRows() * Math.random());
		while(rowIndex == 0){
			rowIndex = (int)(getNumRows() * Math.random());
		}
		return getRow(rowIndex);
		
	}
	
}
