package advancedAlgorithms;

import graphDatastructures.AdjacencyListGraph;

public class CutVerticesAndBridges {
	
	
	public static AdjacencyListGraph getGraph() {
		
		AdjacencyListGraph graph = new AdjacencyListGraph(19);
		
		graph.addEdge(0, 2);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(3, 2);
		
		graph.addEdge(3, 4);
		graph.addEdge(4, 6);
		graph.addEdge(6, 3);
		graph.addEdge(6, 5);
		graph.addEdge(6, 7);

		graph.addEdge(7, 8);
		graph.addEdge(8, 9);
		graph.addEdge(9, 7);
		graph.addEdge(8, 17);
		graph.addEdge(17, 16);
		graph.addEdge(16, 8);
		graph.addEdge(9, 10);
		
		graph.addEdge(10, 11);
		graph.addEdge(11, 12);
		graph.addEdge(10, 12);
		graph.addEdge(14, 12);
		graph.addEdge(11, 13);
		graph.addEdge(13, 14);
		graph.addEdge(13, 15);
		graph.addEdge(15, 14);

		return graph;	
	}

	public static void main(String[] args) {
		int startingVertex = 4;
		boolean[] visited = 
		
	}
	
	
	public static void cvbDfs(AdjacencyListGraph graph, int vertex) {
		
	}
	
}
