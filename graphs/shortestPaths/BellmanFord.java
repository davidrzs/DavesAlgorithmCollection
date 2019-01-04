package shortestPaths;

import java.util.Arrays;
import java.util.HashMap;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class BellmanFord {

	public static HashMap<Integer,Integer> BellmanFord(SimpleWeightedGraph<Integer, DefaultEdge> graph, int startingNodeIndex){
		HashMap<Integer,Integer> distances = new HashMap<Integer,Integer>();
		
		long[] DP = new long[graph.vertexSet().size()];
		
		// initialize the DP table
		
		for(int i = 0; i < DP.length; i++) {
			DP[i] = Long.MAX_VALUE;
		}
		
		//set the distance form the starting node to itself to zero.
		DP[startingNodeIndex] = 0;
		
		for(int i = 0; i <= graph.vertexSet().size()-1; i++) {
			for(DefaultEdge ed : graph.edgeSet()) {
				int target = graph.getEdgeTarget(ed);
				int source = graph.getEdgeSource(ed);
				double weight = graph.getEdgeWeight(ed);
				// since the graph is undirected we will check both ways.
				if(DP[target] + weight < DP[source]) {
					DP[source] = (long) (DP[target] + weight);
				}
				if(DP[source] + weight < DP[target]) {
					DP[target] = (long) (DP[source] + weight);
				}
			}
		}
		
//		for(Integer vertex : graph.vertexSet()) {
//			if(vertex != 0) {
//				for(DefaultEdge ed : graph.edgesOf(vertex)) {
//					int targetVertex = getTargetVertex(graph, vertex, ed);
//					System.out.println(vertex + " " + targetVertex + " " + graph.getEdgeWeight(ed));
//					System.out.println(DP[targetVertex] + graph.getEdgeWeight(ed));
//					if(DP[targetVertex] + graph.getEdgeWeight(ed) < DP[vertex]) {
//						DP[vertex] = (long) (DP[targetVertex] + graph.getEdgeWeight(ed));
//					}
//				}	
//			}
//		}
		System.out.println(Arrays.toString(DP));
		for(int i = 0; i < DP.length;i++) {
			distances.put(i, (int) DP[i]);
		}
		return distances;
	}
	
	/**
	 * A function which given an edge and a verte touching an edge returns the other vertex.
	 * 
	 * @param graph
	 * @param currentVertex
	 * @param ed
	 * @return the other vertex touching the edge
	 */
	private static int getTargetVertex(SimpleWeightedGraph<Integer, DefaultEdge> graph, int currentVertex, DefaultEdge ed) {
		int s1 = graph.getEdgeSource(ed);
		int s2 = graph.getEdgeTarget(ed);
		
		if(s1 != currentVertex) {
			return s1;
		} else {
			return s2;
		}
	}
	
}
