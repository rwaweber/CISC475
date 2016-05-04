package main;
import java.util.*;
import java.util.Map.Entry;
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

	public static List<Double> discretize(List<String> list) {
		List<Object> objList =  Arrays.asList(list.stream().map(s -> (double)s.hashCode())
				.toArray());
		List<Double> doubleList = new ArrayList<Double>();
		for(Object o : objList){
			doubleList.add((double)o);
		}
		return doubleList;
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

	public static Map<String, Integer> getFrequency(List<String> data) {
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();
		for(String s : data){
			if(frequency.get(s) == null){
				frequency.put(s, 1);
			}
			else
				frequency.put(s, frequency.get(s) + 1);
		}
		return frequency;
	}

	public static Map<String,Integer> getSortedMap(Map<String, Integer> map){
		List<Map.Entry<String, Integer>> list =
	            new LinkedList<Map.Entry<String,Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return -1*(o1.getValue()).compareTo( o2.getValue());
			}
		});
		Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list){
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
	}

	public static Map<String, Integer> getTrimmedMap(Map<String, Integer> map, int sizeNewMap) {
		Map<String, Integer> trimmedMap = map;
		int oldSize = trimmedMap.size();
		Set<String> keySet = trimmedMap.keySet();
		for(int thisTrim = 0; thisTrim < oldSize - sizeNewMap; thisTrim++){
			Iterator<String> iter = keySet.iterator();
			String minKey = iter.next();
			int minValue = trimmedMap.get(minKey);
			while(iter.hasNext()){
				String thisKey = iter.next();
				int thisValue = trimmedMap.get(thisKey);
				if(thisValue < minValue){
					minValue = thisValue;
					minKey = thisKey;
				}
			}
			keySet.remove(minKey);
			trimmedMap.remove(minKey);
		}
		return trimmedMap;
	}

}
