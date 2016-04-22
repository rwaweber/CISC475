package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class CSVController {

	private String destFile;
	private String sourceFile;

	ICsvListReader listReader = null;

	public CSVController(String sourceFile, String destFile) throws FileNotFoundException{
		this.sourceFile = sourceFile;
		this.destFile = destFile;
		initListReader();
	}

	private void initListReader() throws FileNotFoundException {
		listReader = new CsvListReader(new FileReader(sourceFile), CsvPreference.STANDARD_PREFERENCE);
	}

	public List<String> getRow(int rowIndex) throws IOException{
		for(int i = 0; i < rowIndex - 1; i++){
			listReader.read();
		}
		return listReader.read();
	}

	public List<String> getCol(int colIndex) throws IOException {
		ArrayList<String> col = new ArrayList<String>();
		List<String> thisRow;
		while((thisRow = listReader.read()) != null){
			col.add(thisRow.get(colIndex));
		}		
		return col;
	}

}
