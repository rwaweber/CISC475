package main;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Transformations {

	public Transformations() {

	}

	public static List<Double> getZeroList(int size){
		ArrayList<Double> list = new ArrayList<>(Arrays.asList(new Double[size]));
		Collections.fill(list, 0.0); 
		return list;
	}

	public static List<Double> mean(List<String> list) {
		List<Double> newList = getZeroList(list.size());
		newList.set(0, list.stream()
				.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
				.average()
				.getAsDouble());
		return newList;
	}

	public static List<Double> max(List<String> list){
		List<Double> newList = getZeroList(list.size());
		newList.set(0, list.stream()
				.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
				.max()
				.getAsDouble());
		return newList;
	}

	public static List<Double> min(List<String> list){
		List<Double> newList = getZeroList(list.size());
		newList.set(0, list.stream()
				.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
				.min()
				.getAsDouble());
		return newList;
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

	public static List<Double> normalizeLocalExtrema(List<String> list) {
		List<Double> newCol = new ArrayList<Double>();
		double max = max(list).get(0);
		double min = min(list).get(0);
		list.stream()
		.mapToDouble(obj -> (double) (Double.parseDouble((String) obj) - min)/(max - min))
		.forEach(newCol::add);
		return newCol;
	}

	public static List<Object> discretize(Records records, int colNum) {
		return Arrays.asList(records.getCol(colNum).stream().map(s -> s.hashCode())
				.toArray());
	}

	public static double standDev(Records records, int colNum){
		DescriptiveStatistics stats = new DescriptiveStatistics();
		records.getCol(colNum).stream()
		.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
		.forEach(value -> stats.addValue(value));
		return stats.getStandardDeviation();
	}

}
