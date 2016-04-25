package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import main.FileParser;
import main.Records;
import main.Transformations;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

public class TransformationsTest {
	
	List<String> testList = new ArrayList<String>(Arrays.asList(new String[]{"2", "3", "1", "5", "4"}));
	List<String> dupList = new ArrayList<String>(Arrays.asList(new String[]{"2", "3", "3", "5", "4"}));
	List<Double> addList = new ArrayList<Double>(Arrays.asList(new Double[]{4.0,5.0,3.0,7.0,6.0}));
	List<Double> subList = new ArrayList<Double>(Arrays.asList(new Double[]{0.0,1.0,-1.0,3.0,2.0}));
	List<String> nonNumeric = new ArrayList<String>(Arrays.asList(new String[]{"Ben", "Johanna", "Greg", "Teague", "Will"}));
	List<String> frequencyList = new ArrayList<String>(Arrays.asList(new String[]{"NY", "DE", "NY", "PA", "DE", "DE"}));
	
	@Test
	public void testMean() {
		List<Double> newList = Transformations.mean(testList);
		assertEquals(newList.size(), testList.size());
		assertEquals(Transformations.mean(testList).get(0), 3.0, 0.0001);
		
	}

	@Test
	public void testMax()  {
		List<Double> newList = Transformations.max(testList);
		assertEquals(newList.size(), 5);
		assertEquals(Transformations.max(testList).get(0), 5, 0.001);
	}

	@Test
	public void testMin()  {
		List<Double> newList = Transformations.min(testList);
		assertEquals(newList.size(), 5);
		assertEquals(Transformations.min(testList).get(0), 1, 0.001);
	}

	@Test
	public void testNumDistinctElements()  {
		assertEquals(Transformations.numDistinctElements(dupList).get(0), 4, 0.0);
	}

	@Test
	public void testSum()  {
		assertEquals(Transformations.sum(testList).get(0), 15, 0.0);
	}

	@Test
	public void testAddValueToList() {
		List<Double> addList = Transformations.addValueToList(testList, 2);
		assertEquals(addList.size(), 5);
		assertTrue(addList.equals(this.addList));
		List<Double> subList = Transformations.addValueToList(testList, -2);
		assertEquals(subList.size(), 5);
		assertTrue(subList.equals(this.subList));
	}

	@Test
	public void testNormalizeLocalExtrema() {
		List<Double> norm = Transformations.normalizeLocalExtrema(testList);
		assertEquals(norm.size(), 5);
		double min = Transformations.min(testList).get(0);
		double max = Transformations.max(testList).get(0);
		List<Double> testNorm = new ArrayList<Double>();
		for(int i = 0; i < testList.size(); i++){
			Double temp = (Double.parseDouble(testList.get(i)) - min)/(max-min);
			testNorm.add(temp);
		}
		assertTrue(norm.equals(testNorm));
	}

	@Test
	public void testDiscretize() {
		List<Object> discretize = Transformations.discretize(nonNumeric);
		assertEquals(discretize.size(), 5);
		for(int i = 0; i < discretize.size(); i++){
			assertEquals(discretize.get(i), nonNumeric.get(i).hashCode());
		}
		

	}
	
	@Test
	public void testStandDev() {
		List<Double> standDev = Transformations.standDev(testList);
		assertEquals(standDev.get(0), 1.58114, 0.0001);
	}
	
	@Test
	public void testGetZeroList() {
		List<Double> zeroList = Transformations.getZeroList(10);
		assertEquals(zeroList.size(), 10);
		for(Double d : zeroList){
			assertEquals(d, 0.0, 0.0);
		}
	}
	
	@Test
	public void testGetStringZeroList() {
		List<String> stringZeroList = Transformations.getStringZeroList(10);
		assertEquals(stringZeroList.size(), 10);
		for(String s : stringZeroList){
			assertEquals(s, "0");
		}
	}
	
	@Test
	public void testGetFrequency(){
		Map<String, Integer> frequency = Transformations.getFrequency(frequencyList);
		assertNotNull(frequency);
		assertEquals(frequency.keySet().size(), 3);
		assertEquals(frequency.values().size(), 3);
		assertEquals(frequency.get("DE"), (Integer)3);
		assertEquals(frequency.get("NY"), (Integer)2);
		assertEquals(frequency.get("PA"), (Integer)1);

	}

}
