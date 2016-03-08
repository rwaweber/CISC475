package main;
import java.util.*;



public class Transformations {

	public Transformations() {
		
	}
	
	public static double mean(Records record, int col) {
		ArrayList<Object> colRec = record.getCol(col);
		OptionalDouble mean = colRec.stream().mapToInt(obj -> (int) Integer.parseInt((String) obj)).average();
		return mean.getAsDouble();
	}
}
