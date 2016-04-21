package main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class Records {

	private List<ArrayList<String>> records;
	private ArrayList<String> headers;
	private int numRows;
	private int numCols;

	public Records(List<CSVRecord> csvRecords, int startRow, int endRow, int startCol, int endCol, ArrayList<String> header){
		this.headers = header;
		numRows = endRow - startRow + 1;
		numCols = endCol - startCol + 1;
		this.records = new ArrayList<ArrayList<String>>();
		initLists(endCol - startCol + 1);
		for(int thisRow = startRow; thisRow <= endRow; thisRow++){
			CSVRecord thisRecord = csvRecords.get(thisRow);
			addRecord(thisRecord, startCol, endCol);
		}
	}

	public Records(List<CSVRecord> csvRecords,  ArrayList<String> header){
		this.headers = header;
		numRows = csvRecords.size();
		numCols = csvRecords.get(0).size();
		this.records = new ArrayList<ArrayList<String>>();
		initLists(numCols);
		for(int thisRow = 0; thisRow < numRows; thisRow++){
			CSVRecord thisRecord = csvRecords.get(thisRow);
			addRecord(thisRecord);
		}
	}

	public List<ArrayList<String>> getRecords() {
		return records;
	}

	public void initLists(int numElements){
		for(int thisList = 0; thisList < numElements; thisList++){
			records.add(new ArrayList<String>());
		}
	}

	public void addRecord(CSVRecord record, int startCol, int endCol){
		for(int thisCol = startCol; thisCol <= endCol; thisCol++){
			records.get(thisCol - startCol).add(record.get(thisCol));
		}
	}

	public void addRecord(CSVRecord record){
		for(int thisCol = 0; thisCol < records.size(); thisCol++){
			records.get(thisCol).add(record.get(thisCol));
		}
	}

	public int numRows(){
		return numRows;
	}

	public int numCols(){
		return numCols;
	}

	public Object getCell(int row, int col){
		return records.get(col).get(row);
	}

	public ArrayList<String> getRecord(int recordNum){
		ArrayList<String> record = new ArrayList<String>();
		for(ArrayList<String> col : records){
			record.add(col.get(recordNum));
		}
		return record;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("RECORDS:" + "\n");
		for(int thisRow = 0; thisRow < numRows; thisRow++){
			ArrayList<String> record = getRecord(thisRow);
			sb.append("\n");
			for(int thisCol = 0; thisCol < numCols; thisCol++){
				sb.append(record.get(thisCol) + ", ");
			}
		}
		return sb.toString();
	}

	public Object[][] convertTo2DArray(){
		return records.stream()
				.map(l -> l.stream()
						.toArray(Object[]::new))
				.toArray(Object[][]::new);
	}

	public ArrayList<String> getCol(int colNum) {
		return records.get(colNum);
	}

	public void addCol(String colName) {
		records.add(new ArrayList<String>());
		headers.add(colName);

	}

	public void removeCol(int col) {
		records.remove(col);
		numCols--;
	}

	public ArrayList<String> getHeaders() {
		return headers;
	}

	public ArrayList<String> getRandCol() {
		return getCol((int)(Math.random()*numCols));
	}

	public boolean colEquals(int colIndex, ArrayList<String> testCol) {
		return getCol(colIndex).equals(testCol);
	}

	public boolean colEquals(ArrayList<String> thisCol, ArrayList<String> testCol) {
		return thisCol.equals(testCol);
	}

	public Object colSize(int colIndex) {
		return this.getCol(colIndex).size();
	}

	public boolean containsCol(ArrayList<String> randCol) {
		return records.contains(randCol);
	}

	
	public List<ArrayList<Object>> transpose() {
		List<ArrayList<Object>> transpose = new ArrayList<ArrayList<Object>>();
		for(int thisRow = 0; thisRow < records.get(0).size(); thisRow++){
			transpose.add(new ArrayList<Object>());
		}
		return transpose;
	}

	public static ArrayList<Object> getRowFromColLists(List<ArrayList<Object>> colLists, int rowIndex) {
		// TODO Auto-generated method stub
		return null;
	}



}
