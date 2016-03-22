package tests;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import main.MakeGUI;

public class MakeGUITest {
	
	static String[] colNames = new String[]{"Name", "Age", "Student ID", "Male"};
//	static String[] names = new String[]{"Greg", "Will", "Ben"};
//	static int[] ages = new int[]{21, 35, 40};
//	static double[] ids = new double[]{702.123123, 702.123124, 702.123125};
//	static boolean[] male = new boolean[]{true, true, true};
	Object[][] cells = {{"Greg", "Will", "Ben"}, {21, 35, 40}, {702.123123, 702.123124, 702.123125}, {true, true, true}};
	
	

	@Test
	public void testMakeGUI() {
		
		MakeGUI gui = new MakeGUI(colNames, cells);
		equals(gui.isActive() == true);
		equals(gui.isDisplayable() == true);
		equals(gui.isEnabled() == true);
		equals(gui.isResizable() == true);
		equals(gui.isVisible() == true);
		

	}

}
