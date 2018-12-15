package minimalSpanningTrees;

import graphDatastructures.WeightedMatrixGraph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMinimalSpanningTreeAlgos {
	
	
	WeightedMatrixGraph createSampleGraph() {
		WeightedMatrixGraph graph = new WeightedMatrixGraph(14);
		
		// this is the same graph that can be found on exercise sheet 12 of
		// the ETH algorithms class.
		
		graph.addUndirectedEdge(0,1,9);
		graph.addUndirectedEdge(0,5,5);
		graph.addUndirectedEdge(0,6,4);
		graph.addUndirectedEdge(1,6,17);
		graph.addUndirectedEdge(1,2,5);
		graph.addUndirectedEdge(1,7,6);
		graph.addUndirectedEdge(2,3,7);
		graph.addUndirectedEdge(2,1,9);
		graph.addUndirectedEdge(2,7,2);
		graph.addUndirectedEdge(6,7,1);
		graph.addUndirectedEdge(5,6,11);
		graph.addUndirectedEdge(0,1,9);
		graph.addUndirectedEdge(3,7,5);
		graph.addUndirectedEdge(3,8,7);
		graph.addUndirectedEdge(7,8,11);
		graph.addUndirectedEdge(8,4,6);
		graph.addUndirectedEdge(4,9,2);
		graph.addUndirectedEdge(8,9,4);
		graph.addUndirectedEdge(5,10,3);
		graph.addUndirectedEdge(10,6,8);
		graph.addUndirectedEdge(10,11,7);
		graph.addUndirectedEdge(11,6,13);
		graph.addUndirectedEdge(11,7,8);
		graph.addUndirectedEdge(7,12,3);
		graph.addUndirectedEdge(12,13,6);
		graph.addUndirectedEdge(8,13,3);
		graph.addUndirectedEdge(13,9,1);
		return graph;
		
	}
	

	@Test
	void testPrim() {
		WeightedMatrixGraph graph = createSampleGraph();
		fail("Not yet implemented");
	}

	@Test
	void testKruskal() {
		WeightedMatrixGraph graph = createSampleGraph();
		fail("Not yet implemented");
	}
	
	@Test
	void testReverseKruskal() {
		WeightedMatrixGraph graph = createSampleGraph();
		fail("Not yet implemented");
	}
	
	
}
