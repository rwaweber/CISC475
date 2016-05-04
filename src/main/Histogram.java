package main;

import static org.junit.Assert.assertNotNull;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.data.category.DefaultCategoryDataset; 


public class Histogram extends ApplicationFrame{

	public static final int MAX_VALUES = 10;

	public Histogram(String title, String xLabel, String yLabel, Map<String, Integer> data) {
		super(title);
		JFreeChart histogram =  ChartFactory.createBarChart(
				title,
				xLabel,
				yLabel,
				getCategoryDataset((HashMap<String, Integer>) data),
				PlotOrientation.VERTICAL,
				true,
				true,
				false
				);
		initWindow(histogram);
	}

	private void initWindow(JFreeChart histogram) {
		ChartPanel chartPanel = new ChartPanel(histogram);        
		chartPanel.setPreferredSize(new Dimension(560 ,367));        
		setContentPane(chartPanel); 
		pack();
		setVisible(true);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {

	            // do other stuff....

	        	setVisible(false);
	        	dispose();
	        }
	    });
	}

	public static DefaultCategoryDataset getCategoryDataset(HashMap<String, Integer> frequency) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(String key : frequency.keySet()){
			dataset.addValue(frequency.get(key), key, key);
		}
		return dataset;

	}

	public static void main(String args[]){
		String title = "Example";
		String xLabel = "x-axis";
		String yLabel = "yAxis";
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("NY", 4);
		hashMap.put("DE", 10);
		hashMap.put("NJ", 5);
		hashMap.put("MD", 5);
		hashMap.put("PA", 7);
		Histogram graph = new Histogram(title, xLabel, yLabel, hashMap);
	}

}
