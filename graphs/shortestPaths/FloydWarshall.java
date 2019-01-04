package shortestPaths;

import java.util.HashMap;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class FloydWarshall {

	public static int[][] floydWarshall(SimpleWeightedGraph<Integer, DefaultEdge> graph){
		int V = graph.vertexSet().size();
		int[][] DP = new int[V][V];
		
		// initialize the DP table
		for(int i = 0 ; i < V;i++) {
			for(int j = 0 ; j < V;j++) {
				if(i == j) {
					DP[i][j] = 0;
				} else if(graph.containsEdge(i, j)){
					DefaultEdge ed = graph.getEdge(i, j);
					DP[i][j] = (int) graph.getEdgeWeight(ed);
				} else {
					//System.out.println(i + "  " + j);
					// we cannot taker maxvalue since if we then add a weight we will have a very low number due to overflow.
					DP[i][j] = Integer.MAX_VALUE/2;					
				}
			}
		}
		
		//now we can do the DP recursion:
		for(int k = 0; k < V; k++) {
			for(int i = 0; i < V; i++) {
				for(int j = 0; j < V; j++) {
					DP[i][j] = min(DP[i][k] + DP[k][j], DP[i][j]);
				}
			}	
		}
		
		return DP;
	}
	
	public static int min(int a, int b) {
		return a > b ? b : a;
	}
	
}
