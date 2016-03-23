package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	
	//KeyListener l;
	
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		System.out.println("here");
		this.setFocusable(true);
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ESCAPE){
					dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

    public static void main(String[] args){
    	String[] testColHeaders = {"Name", "Age", "Student ID", "Male"}; 
    	Object[][] testCells = {{"Greg", "Will", "Ben"}, {21, 35, 40}, {702.123123, 702.123124, 702.123125}, {true, true, true}};
    	MakeGUI gui = new MakeGUI(testColHeaders, testCells);
    }
  
}

