package main;

import java.io.IOException;
import java.util.List;

public class Session {
	
	public String sourceFile;
	public String destFile;
	public CSVWriter csvWriter;

	public Session(String sourceFile, String destFile) throws IOException{
		this.sourceFile = sourceFile;
		this.destFile = destFile;
		csvWriter = new CSVWriter(destFile, "\n");
	}
	
	public void transColToCol(int colIndex, ListToList trans) throws IOException{
		List<String> col = FileParser.getColFromFile(destFile, colIndex);
		List<Double> newCol = trans.listToList(col);
		
		
	}
	
	public String getSourceFile() {
		return sourceFile;
	}

	public String getDestFile() {
		return destFile;
	}

}
