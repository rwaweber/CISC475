package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

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
	private static int PANE_WIDTH = 500;
	private static int PANE_HEIGHT = 500;
	
	Object[][] newCells;
	Object[][] colFixedCells;
	Object[][] totalFixedCells;
	
	
	public MakeGUI(String[] colHeaders, Object[][] cells, int startRow, int endRow, int startCol, int endCol){
		
		//flip columns and rows
		Object[][] newCells = flipTable(colHeaders, cells);
		
		//setup adjusted column table
		Object[][] colFixedCells = new Object[newCells.length][endCol-startCol +1];
		
		//adjust column headers to match
		colHeaders = Arrays.copyOfRange(colHeaders, startCol, endCol+1);
		
		//take only requested columns
		for(int i=0; i<newCells[0].length;i++){
			if(i >= startCol && i <= endCol){
				for(int j=0; j<newCells.length;j++){
					colFixedCells[j][i-startCol] = newCells[j][i];
				}
			}
		}
		
		//take only requested rows
		Object[][] totalFixedCells = new Object[endRow-startRow +1][colFixedCells[0].length];
		for(int i=0; i<colFixedCells.length;i++){
			if(i >= startRow && i <= endRow){
				for(int j=0; j<colFixedCells[i].length;j++){
					totalFixedCells[i-startRow][j] = colFixedCells[i][j];
				}
			}
		}
		
		//setup view:
		tab = new JTable(totalFixedCells, colHeaders);
		tab.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
		JScrollPane pane = new JScrollPane(tab);
		this.add(pane);
		this.setSize(PANE_WIDTH, PANE_HEIGHT);
		this.setVisible(true);	
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setFocusable(true);
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ESCAPE){
					dispose();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		System.out.println("Esc to close window.");
		
	}
	
	public MakeGUI(String[] colHeaders, Object[][] cells){
		new MakeGUI(colHeaders, cells, 0, cells[0].length-1, 0, cells.length-1);
	}
	
	private Object[][] flipTable(String[] colHeaders, Object[][] cells){
		//flip rows and columns array:
		newCells = new Object[cells[0].length][colHeaders.length];
		for(int i=0; i<cells[0].length; i++){
			for(int k=0; k<cells.length; k++){
				newCells[i][k] = cells[k][i];
			}
		}
		return newCells;
	}

    public static void main(String[] args){
    	String[] testColHeaders = {"Name", "Age", "Student ID"}; 
    	Object[][] testCells = {{"Greg", "Will", "Ben", "Sue"}, {21, 35, 40, 80}, {702.123123, 702.123124, 702.123125, 702.123126}};
    	int testStartRow = 0;
    	int testEndRow = 1;
    	int testStartCol = 1;
    	int testEndCol = 2;
    	MakeGUI gui = new MakeGUI(testColHeaders, testCells,testStartRow, testEndRow, testStartCol, testEndCol);
    }
  
}
