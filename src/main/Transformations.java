package main;
import java.util.*;



public class Transformations {

	public Transformations() {
		
	}
	
	public static double mean(Records record, int col) {
		return record.getCol(col).stream()
				.mapToInt(obj -> (int) Integer.parseInt((String) obj))
				.average()
				.getAsDouble();
	}
}
