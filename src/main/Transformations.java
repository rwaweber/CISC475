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
	
	public static List<String> getStringZeroList(int size){
		ArrayList<String> list = new ArrayList<>(Arrays.asList(new String[size]));
		Collections.fill(list, "0"); 
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
	public static List<Double> numDistinctElements(List<String> list) {
		List<Double> newList = getZeroList(list.size());
		 newList.set(0, (double)list.stream()
				.distinct().count());
		 return newList;
	}

	public static List<Double> sum(List<String> list) {
		List<Double> newList = getZeroList(list.size());
		newList.set(0, list.stream()
				.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
				.sum());
		return newList;
	}

	public static List<Double> addValueToList(List<String> list, double value){
		List<Double> newList = new ArrayList<Double>();
		list.stream()
		.mapToDouble(obj -> (double) Double.parseDouble((String) obj) + value)
		.forEach(newList::add);
		return newList;
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

	public static List<Object> discretize(List<String> list) {
		return Arrays.asList(list.stream().map(s -> s.hashCode())
				.toArray());
	}

	public static List<Double> standDev(List<String> list){
		List<Double> newList = getZeroList(list.size());
		DescriptiveStatistics stats = new DescriptiveStatistics();
		list.stream()
		.mapToDouble(obj -> (double) Double.parseDouble((String) obj))
		.forEach(value -> stats.addValue(value));
		newList.set(0, stats.getStandardDeviation());
		return newList;
	}

}
