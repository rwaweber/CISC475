package main;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class MakeGUI extends JFrame{
    
	JTable tab;
	String[] colHeaders;
	Object[][] cells;
	private static int TABLE_WIDTH = 200;
	private static int TABLE_HEIGHT = 250;
	
	public MakeGUI(String[] colHeaders, Object[][] cells){
		
		this.colHeaders = colHeaders;
		this.cells = cells;
		tab = new JTable(cells, colHeaders);
		tab.setBounds(50, 50, TABLE_WIDTH, TABLE_HEIGHT);
		JScrollPane pane = new JScrollPane(tab);
		this.add(pane);
		this.setSize(300,400);
		this.setVisible(true);		
	}
    

    public static void main(String[] args){
    	String[] a = {"Name", "Age", "Student ID"}; 
    	Object[][] b = {{"Greg", 30, 702.123123}, {"Will", 35, 702.123124}, {"Ben", 40, 702.123125}};
    	new MakeGUI(a, b);
    }
    
   
    
}

