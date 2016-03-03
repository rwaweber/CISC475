package main;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class MakeGUI extends JFrame{
    
	JTable tab;
	String[] colHeaders = {"Name", "Age", "UDID"};
	Object[][] cells = {{"Greg", 21, 702.123123},{"Ben", 53, 702.123124}, {"Will", 30, 702.123125}};
	private static int TABLE_WIDTH = 200;
	private static int TABLE_HEIGHT = 250;
	
	public MakeGUI(){
		
		tab = new JTable(cells, colHeaders);
		tab.setBounds(50, 50, TABLE_WIDTH, TABLE_HEIGHT);
		JScrollPane pane = new JScrollPane(tab);
		this.add(pane);
		this.setSize(300,400);
		this.setVisible(true);		
	}
    

    public static void main(String[] args){
    	new MakeGUI();
    }
    
   
    
}

