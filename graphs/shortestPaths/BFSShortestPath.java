package shortestPaths;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;


public class BFSShortestPath {

	public static HashMap<Integer,Integer> breadthFirstSearch(SimpleWeightedGraph<Integer, DefaultEdge> g, int startVertex) {
		Queue<Integer> s = new LinkedList<Integer>();
		HashMap<Integer,Integer> dist = new HashMap<Integer,Integer>();
		boolean[] visited = new boolean[g.vertexSet().size()+1];
		s.add(startVertex);
		
		// initialize distance map
		for(Integer i : g.vertexSet()) {
			dist.put(i, Integer.MAX_VALUE/2);
		}
		dist.put(startVertex, 0);
		
		while(!s.isEmpty()) {
			int currentVertex = s.poll();
			visited[currentVertex] = true;
			for(DefaultEdge adj : g.outgoingEdgesOf(currentVertex)) {
				int target = getTargetVertex(g, currentVertex, adj);
				if(false == visited[target]) {
					s.add(target);
					if(dist.get(currentVertex) + 1 < dist.get(target)) {
						dist.put(target,dist.get(currentVertex) + 1);
					}	
				}
			}
			
		}
		
		return dist;
	}
	
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
