package main;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.AbstractRowProcessor;

public class CsvDimension extends AbstractRowProcessor {

	private int numRows = 0;
	private int numCols = 0;

	public int getNumRows() {
		return numRows;
	}

	public int getnumCols() {
		return numCols;
	}
	
	@Override
	public void rowProcessed(String[] row, ParsingContext context) {
		numRows++;
		if(numCols < row.length){
			numCols = row.length;
		}
	}

}
