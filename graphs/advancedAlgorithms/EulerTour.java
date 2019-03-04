package advancedAlgorithms;


import java.util.List;
import java.util.LinkedList;

import graphDatastructures.AdjacencyListGraph;

public class EulerTour {
	
	public static AdjacencyListGraph getGraph() {
		
		AdjacencyListGraph graph = new AdjacencyListGraph(10);
		
		// the graph also contains two self loops
		
		graph.addEdge(1, 2);
		graph.addEdge(1, 4);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(4, 5);
		graph.addEdge(5, 6);
		graph.addEdge(4, 6);
		graph.addEdge(3, 6);
		graph.addEdge(3, 7);
		graph.addEdge(6, 7);
		graph.addEdge(1, 8);
		graph.addEdge(1, 9);
		graph.addEdge(8, 9);
		
		
		return graph;
		
	}
	
	public static void main(String[] args) {
		//first we define the graph we are operating on:
		AdjacencyListGraph graph = getGraph();
		List<Integer> eulerTour;
		try {
			eulerTour = getEulerTour(graph);
		}catch(Exception ex) {
			ex.printStackTrace();
			eulerTour = new LinkedList<Integer>();
		}
		System.out.println(eulerTour.toString());
	}
	
	public static List<Integer> getEulerTour(AdjacencyListGraph g1) throws Exception {
		
		LinkedList<Integer> eulerTour = new LinkedList<Integer>();
		
		// first we need to duplicate our graph:
		AdjacencyListGraph g2 = g1.clone();

		// first we check that a euler cycle is possible
		
		for(int i = 0; i < g1.V(); i++) {
			if((g1.adj(i).size() % 2) != 0) {
				throw new Exception("impossible to build a euler tour. Uneven degree at " + i 
						+ " with degree " + g1.adj(i).size() + " " + g1.adj(i));
			}
		}
		
		// we loop for as long as there are still edges we haven't added to our eulertour
		
		int startVertex = g2.maxVertex();
		// System.out.println(startVertex);
		eulerTour.add(startVertex);
		
		
		int counter = 1;
		
		while(stillUnvisitedEdges(g1)) {
			if(g2.adj(startVertex).size() == 0) {
				counter++;
				startVertex = eulerTour.get(counter-1);
				continue;
			}
			LinkedList<Integer> rw = randomTour(g2, startVertex);
			eulerTour.addAll(counter, rw);
		}
		
		return eulerTour;
	}
	
	public static boolean stillUnvisitedEdges(AdjacencyListGraph g) {
		boolean retVal = false;
		for(int i = 0; i < g.V(); i++) {
			if( g.adj(i).size() > 0) {
				retVal = true;
			}
		}
		return retVal;
	}
	
	
	// the marathon runner from the example we have seen in the lecture
	public static LinkedList<Integer> randomTour(AdjacencyListGraph g, int start){
		LinkedList<Integer> tour = new LinkedList<Integer>();
		int currentVertex = g.adj(start).get(0);
		tour.add(currentVertex);
		g.removeEdge(start, currentVertex);
		while(currentVertex != start) {
			int temp = currentVertex;
			// get any outgoing edge
			currentVertex = g.adj(currentVertex).get(0);
			// add to random tour
			tour.add(currentVertex);
			// remove from our graph
			g.removeEdge(currentVertex, temp);
		}
		System.out.println("runner starting at "+ start + " found: "+ tour.toString());
		return tour;
	}
}
