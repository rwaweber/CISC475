package main;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class Records {

	private List<ArrayList<Object>> records;
	private int numRows;
	private int numCols;

	public Records(List<CSVRecord> csvRecords, int startRow, int endRow, int startCol, int endCol){
		numRows = endRow - startRow + 1;
		numCols = endCol - startCol + 1;
		this.records = new ArrayList<ArrayList<Object>>();
		initLists(endCol - startCol + 1);
		for(int thisCol = startCol; thisCol <= endCol; thisCol++){
			for(int thisRow = startRow; thisRow <= endRow; thisRow++){
				CSVRecord thisRecord = csvRecords.get(thisRow);
				addRecord(thisRecord, startCol, endCol);
			}
		}
	}

	public List<ArrayList<Object>> getRecords() {
		return records;
	}

	public void initLists(int numElements){
		for(int thisList = 0; thisList < numElements; thisList++){
			records.add(new ArrayList<Object>());
		}
	}

	public void addRecord(CSVRecord record, int startCol, int endCol){
		for(int thisCol = startCol; thisCol <= endCol; thisCol++){
			records.get(thisCol - startCol).add(record.get(thisCol));
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
	
	public ArrayList<Object> getRecord(int recordNum){
		ArrayList<Object> record = new ArrayList<Object>();
		for(ArrayList<Object> col : records){
			record.add(col.get(recordNum));
		}
		return record;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("RECORDS:" + "\n");
		for(int thisRow = 0; thisRow < numRows; thisRow++){
			ArrayList<Object> record = getRecord(thisRow);
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

	public List<Object> getCol(int colNum) {
		return records.get(colNum);
	}

}
