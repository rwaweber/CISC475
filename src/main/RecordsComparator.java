package main;

import java.util.Comparator;
import java.util.List;

public class RecordsComparator implements Comparator<List<Object>> {

	private int colIndex;

	private Records records;

	public RecordsComparator(Records records, int colIndex) {
		this.colIndex = colIndex;
		this.records = records;
	}

	public int getColIndex() {
		return colIndex;
	}

	public Records getRecords() {
		return records;
	}

	@Override
	public int compare(List<Object> o1, List<Object> o2) {
		return 0;
	}


}
