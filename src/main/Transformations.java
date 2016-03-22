package main;
import java.util.*;



public class Transformations {

	public Transformations() {
		
	}
	
	public static double mean(Records records, int col) {
		return records.getCol(col).stream()
				.mapToInt(obj -> (int) Integer.parseInt((String) obj))
				.average()
				.getAsDouble();
	}
	
	public static double max(Records records, int col){
		return records.getCol(col).stream()
				.mapToDouble(obj -> (int) Integer.parseInt((String) obj))
				.max()
				.getAsDouble();
	}
	
	public static double min(Records records, int col){
		return records.getCol(col).stream()
				.mapToDouble(obj -> (int) Integer.parseInt((String) obj))
				.min()
				.getAsDouble();
	}
	
	
	
}
