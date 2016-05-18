package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.univocity.parsers.common.*;
import com.univocity.parsers.common.processor.*;
import com.univocity.parsers.common.record.*;
import com.univocity.parsers.conversions.*;
import com.univocity.parsers.csv.*;
import com.univocity.parsers.fixed.*;


public class CSVController {

	private String fileName;

	ICsvListReader reader;
	ICsvListWriter writer;

	private CsvParserSettings rowParserSettings;
	private CsvParserSettings colParserSettings;

	private CsvParser rowParser;
	private CsvParser colParser;

	private Reader fileReader;

	private int numRows;

	private int numCols;

	public CSVController(String fileName) throws IOException{
		this.fileName = fileName;
		initDimensions();
		initRowParser();
		initColParser();

	}

	private void initColParser() {
		ColumnProcessor rowProcessor = new ColumnProcessor();
		colParserSettings = new CsvParserSettings();
		colParserSettings.setRowProcessor(rowProcessor);
		colParser = new CsvParser(colParserSettings);
	}

	private void setColParserIndex(int colIndex){
		colParserSettings.selectIndexes(colIndex);
		colParser = new CsvParser(colParserSettings);
	}

	private void initDimensions() throws FileNotFoundException {
		CsvDimension dimensionProcessor = new CsvDimension();
		CsvParserSettings settings = new CsvParserSettings();
		settings.selectIndexes();
		settings.setColumnReorderingEnabled(false);
		settings.setRowProcessor(dimensionProcessor);
		CsvParser parser = new CsvParser(settings);
		parser.parse(new FileReader(fileName));
		numRows = dimensionProcessor.getNumRows();
		numCols = dimensionProcessor.getnumCols();

	}

	private void initRowParser() throws FileNotFoundException {
		rowParserSettings = new CsvParserSettings();
		if(System.getProperty(Constants.SYSTEM_PROPERTY).contains(Constants.MAC_OS)){
			rowParserSettings.getFormat().setLineSeparator(Constants.MAC_OS_LINE_SEPARATOR);
		}
		rowParser = new CsvParser(rowParserSettings);
	}

	private void startParsing() throws FileNotFoundException{
		fileReader = new FileReader(fileName);
		rowParser.beginParsing(fileReader);
	}

	private void stopParsing() throws FileNotFoundException{
		fileReader = new FileReader(fileName);
		rowParser.stopParsing();
	}

	private void parseRows() throws FileNotFoundException{
		fileReader = new FileReader(fileName);
		rowParser.parse(fileReader);
	}

	private void parseCols() throws FileNotFoundException{
		fileReader = new FileReader(fileName);
		colParser.parse(fileReader);
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
		numRows = 0;
		numCols = 0;
	}

	public List<String> getRow(int rowIndex) throws IOException{
		int thisIndex = 1;
		initRowParser();
		startParsing();
		String row[] = rowParser.parseNext();
		while(thisIndex <= rowIndex && (row = rowParser.parseNext()) != null){
			thisIndex++;
		}
		stopParsing();
		return new ArrayList<String>(Arrays.asList(row));
	}

	public List<String> getCol(int colIndex) throws IOException {
		setColParserIndex(colIndex);
		parseCols();
		return ((ColumnProcessor) 
				colParserSettings
				.getRowProcessor())
				.getColumn(0);
	}


	public void addRow(List<String> row) throws IOException {
		initWriter();
		writer.write(row);
		closeWriter();
		initDimensions();
//		Collection<Object[]> rows = new ArrayList<Object[]>();
//		rows.add(row.toArray(new String[row.size()]));
//		CsvWriter writer = new CsvWriter(new FileWriter(fileName), new CsvWriterSettings());
//		writer.writeRowsAndClose(rows);
//		initDimensions();
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

	public void addCol(List<String> col, String header) throws IOException {
		List<String> newCol = col;
		newCol.add(0, header);
		List<String> text = getText();
		int numRows = getNumRows();
		for(int index = 0; index < newCol.size(); index++){
			if(index < numRows){
				String oldRow = text.get(index);
				text.set(index, oldRow += "," + newCol.get(index));
			}
			else{
				text.add(index, newCol.get(index));
			}

		}
		clearFile();
		initWriter();
		for(String s : text){
			List<String> newRow = getListFromString(s);
			writer.write(newRow);
		}
		closeWriter();
		initDimensions();
		System.out.println("numCols = " + numCols);
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
		//		int numRows = 0;
		//		initParser();
		//		startParsing();
		//		while(parser.parseNext() != null){
		//			numRows++;
		//		}
		//		stopParsing();
		return numRows;
	}

	public int getNumCols() throws IOException {
		//		initReader();
		//		int numCols = 0;
		//		List<String> thisRow;
		//		while((thisRow = reader.read()) != null){
		//			int thisNumCols = thisRow.size();
		//			if(thisNumCols > numCols){
		//				numCols = thisNumCols;
		//			}
		//		}
		//		closeReader();
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
		//randCol.remove(0);
		return randCol;
	}

	public boolean containsCol(List<String> col) throws IOException {
		for(int colIndex = 0; colIndex < getNumCols(); colIndex++){
			List<String> thisCol = getCol(colIndex);
			//	thisCol.remove(0);
			if(thisCol.equals(col)){
				return true;
			}
		}
		return false;
	}

	public boolean containsRow(List<String> trueRow) throws IOException {
		System.out.println(getNumRows());
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

	// assumes that file has headers
	// returns null if no such column name exists
	public List<String> getColByName(String name) throws IOException {
		List<String> headers = getHeaders();
		for(int headerIndex = 0; headerIndex < headers.size(); headerIndex++){
			if(headers.get(headerIndex).equals(name)){
				return getCol(headerIndex);
			}
		}
		return null;
	}

	public List<String> getHeaders() throws IOException {
		List<String> headers = getRow(0);
		return headers;
	}

	public String getValue(int rowIndex, int colIndex) throws IOException {
		List<String> row = getRow(rowIndex);
		System.out.println(row);
		return row.get(colIndex);
	}

	public void removeCol(int removeIndex) throws IOException {
		List<List<String>> rows = new ArrayList<List<String>>();
		int numRows = getNumRows();
		for(int rowIndex = 0; rowIndex < numRows; rowIndex++){
			rows.add(getRow(rowIndex));
		}
		clearFile();
		initWriter();
		for(List<String> row : rows){
			row.remove(removeIndex);
			writer.write(row);
		}
		closeWriter();
		initDimensions();
	}

	public void removeRow(int removeIndex) throws IOException {
		List<List<String>> rows = new ArrayList<List<String>>();
		int numRows = getNumRows();
		for(int rowIndex = 0; rowIndex < numRows; rowIndex++){
			if(rowIndex != removeIndex){
				rows.add(getRow(rowIndex));
			}
		}
		clearFile();
		initWriter();
		for(List<String> row : rows){
			writer.write(row);
		}
		closeWriter();
		initDimensions();
	}

	public void addCol(int colIndex, List<String> newCol, String header) throws IOException {
		List<List<String>> cols = getCols();
		int numCols = getNumCols() + 1;
		clearFile();
		initWriter();
		int oldColIndex = 0;
		for(int index = 0; index < numCols; index++){
			if(index == colIndex){
				addCol(newCol, header);
			}
			else{
				List<String> oldCol = cols.get(oldColIndex);
				String oldHeader = oldCol.get(0);
				oldCol.remove(0);
				addCol(oldCol, oldHeader);
				oldColIndex++;
			}
		}
		closeWriter();

	}

	public List<List<String>> getRows() throws IOException {
		ArrayList<List<String>> rows = new ArrayList<List<String>>();
		int numRows = this.getNumRows();
		for(int rowIndex = 0; rowIndex < numRows; rowIndex++){
			rows.add(this.getRow(rowIndex));
		}
		return rows;
	}

	public List<List<String>> getCols() throws IOException {
		ArrayList<List<String>> cols = new ArrayList<List<String>>();
		int numCols = this.getNumCols();
		for(int colIndex = 0; colIndex < numCols; colIndex++){
			cols.add(this.getCol(colIndex));
		}
		return cols;
	}

	public static void main(String args[]) throws IOException{
		//		String bigFileName = "/Users/benjaminrodd/Desktop/bigFile25.csv";
		//		String smallFileName = "/Users/benjaminrodd/git/CISC475/src/tests/testfiles/test.csv";
		//
		//		//CSVController control = new CSVController(bigFileName);
		//
		//		AppTimer timer = new AppTimer();
		//		timer.startTimer();
		//
		//		CsvParserSettings settings = new CsvParserSettings();
		//		settings.getFormat().setLineSeparator("\r");
		//	//	settings.selectIndexes(0);
		//		RowListProcessor rowProcessor = new RowListProcessor();
		//
		//		settings.setRowProcessor(rowProcessor);
		//		settings.setHeaderExtractionEnabled(true);
		//
		//		
		//		
		//		CsvParser parser = new CsvParser(settings);
		//		parser.beginParsing(new FileReader(bigFileName));
		//		List<String[]> rows = rowProcessor.getRows();
		//		String[] row;
		//		while((row = parser.parseNext()) != null){
		//			System.out.println(rows.size());
		//		}
		//		System.out.println(rows.size());
		//
		//		timer.endTimer();
		//		System.out.println(timer.getElapsedTime());
		//		for(int i = 0; i < rows.size(); i++){
		//			System.out.println(rows.get(0));
		//		}
	}

	public CsvParserSettings getSettings() {
		return rowParserSettings;
	}



}
