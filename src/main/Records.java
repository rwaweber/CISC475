package main;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

public class Records {
	
	private Object[][] records;
	
	public Records(List<CSVRecord> csvRecords, int startRow, int endRow, int startCol, int endCol){
		this.records = new Object[endRow-startRow+1][endCol-startCol+1];
		for(int thisRow = startRow; thisRow <= endRow; thisRow++){
			CSVRecord thisRecord = csvRecords.get(thisRow);
			for(int thisCol = 0; thisCol <= endCol-startCol; thisCol++){
				this.records[thisRow-startRow][thisCol] = thisRecord.get(thisCol);
			}
		}
	}

	public Object[][] getRecords() {
		return records;
	}
	
	public int numRows(){
		return records.length;
	}
	
	public int numCols(){
		return records[0].length;
	}
	
	public Object getCell(int row, int col){
		return records[row][col];
	}

}
