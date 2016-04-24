package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Graph;

public class GraphTest {

	@Test
	public void testConstructor() {
		Graph graph = new Graph();
		assertNotNull(graph);
	}

}
