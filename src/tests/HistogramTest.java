package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYSeries;
import org.junit.Test;

import main.Histogram;

public class HistogramTest {
	
	static String title = "Example";
	static String xLabel = "x-axis";
	static String yLabel = "yAxis";


	@Test
	public void testConstructor() {
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("NY", 4);
		hashMap.put("DE", 10);
		hashMap.put("NJ", 5);
		hashMap.put("MD", 5);
		hashMap.put("PA", 7);
		Histogram graph = new Histogram(title, xLabel, yLabel, hashMap);
		assertNotNull(graph);
	}

	
	@Test
	public void testGetCategoryDataset(){
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("NY", 4);
		hashMap.put("DE", 10);
		hashMap.put("NJ", 5);
		hashMap.put("MD", 5);
		hashMap.put("PA", 7);
		DefaultCategoryDataset dataset = Histogram.getCategoryDataset(hashMap);
		assertNotNull(dataset);
		assertEquals(dataset.getColumnCount(), hashMap.keySet().size());
		assertEquals(dataset.getRowCount(), hashMap.keySet().size());

	}

}
