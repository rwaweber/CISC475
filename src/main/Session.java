package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Session {
	
	public CSVController sourceControl;
	public CSVController destControl;

	public Session(String sourceFile, String destFile) throws IOException{
		this.sourceControl = new CSVController(sourceFile);
		this.destControl = new CSVController(destFile);
	}

	public void transColToCol(int colIndex, ListToList trans) throws IOException{
		List<Double> result = trans.listToList(sourceControl.getColNoHeader(colIndex));
		List<String> stringResult = new ArrayList<String>();
		for(int i = 0; i < result.size(); i++){
			stringResult.add(result.get(i).toString());
		}
		System.out.println(stringResult);
		destControl.addCol(stringResult);
	}
	
	public CSVController getSourceControl() {
		return sourceControl;
	}

	public CSVController getDestControl() {
		return destControl;
	}
}
