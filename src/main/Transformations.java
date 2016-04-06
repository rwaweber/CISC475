package main;
import java.util.*;

public class Transformations {

	public Transformations() {

	}

	public static double mean(Records records, int col) {
		return records.getCol(col).stream()
				.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
				.average()
				.getAsDouble();
	}

	public static double max(Records records, int col){
		return records.getCol(col).stream()
				.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
				.max()
				.getAsDouble();
	}

	public static double min(Records records, int col){
		return records.getCol(col).stream()
				.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
				.min()
				.getAsDouble();
	}
	public static long numDistinctElements(Records records, int col) {
		return records.getCol(col).stream()
				.distinct().count();
	}

	public static double sum(Records records, int col) {
		double sum = records.getCol(col).stream()
				.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
				.sum();
		return sum;
	}

	public static List<Double> addValueToCol(Records records, int col, double value){
		List<Double> newCol = new ArrayList<Double>();
		records.getCol(col).stream()
		.mapToDouble(obj -> (double) Double.parseDouble((String) obj) + value)
		.forEach(newCol::add);
		return newCol;
	}
	
	public static List<Double> normalizeGlobalExtrema(Records records, int col) {
//		(x - min) / (max - min)
		List<Double> newCol = new ArrayList<Double>();
		double max = max(records, col);
		double min = min(records, col);
		records.getCol(col).stream()
		.mapToDouble(obj -> (double) (Double.parseDouble((String) obj) - min)/(max - min))
		.forEach(newCol::add);
		return newCol;
	}



}
