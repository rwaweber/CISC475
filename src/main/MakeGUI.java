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
	
	Object[][] newCells;
	
	public MakeGUI(String[] colHeaders, Object[][] cells){
		
		//flip 2D array:
		newCells = new Object[cells[0].length][colHeaders.length];
		for(int i=0; i<cells[0].length; i++){
			for(int k=0; k<cells.length; k++){
				newCells[i][k] = cells[k][i];
			}
		}
		
		//setup view:
		this.colHeaders = colHeaders;
		this.cells = newCells;
		tab = new JTable(newCells, colHeaders);
		tab.setBounds(50, 50, TABLE_WIDTH, TABLE_HEIGHT);
		JScrollPane pane = new JScrollPane(tab);
		this.add(pane);
		this.setSize(300,400);
		this.setVisible(true);	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
    

    public static void main(String[] args){
    	String[] a = {"Name", "Age", "Student ID", "Male"}; 
    	Object[][] b = {{"Greg", "Will", "Ben"}, {21, 35, 40}, {702.123123, 702.123124, 702.123125}, {true, true, true}};
    	MakeGUI x = new MakeGUI(a, b);
    }
    
   
    
}

