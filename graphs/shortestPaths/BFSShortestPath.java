package shortestPaths;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;


public class BFSShortestPath {
	public static void main(String[] args) {
		SimpleDirectedGraph<Integer, DefaultEdge> g = new SimpleDirectedGraph<>(DefaultEdge.class);
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		g.addVertex(6);
		
		g.addEdge(2,1);
		g.addEdge(1, 2);
		
		g.addEdge(2, 3);
		g.addEdge(3, 2);
		
		g.addEdge(3, 6);
		g.addEdge(6, 3);
		
		g.addEdge(1, 4);
		g.addEdge(4, 1);
		
		g.addEdge(1, 5);
		g.addEdge(5, 1);
		
		g.addEdge(4, 5);
		g.addEdge(5, 4);
		
		g.addEdge(5, 6);
		g.addEdge(6, 5);
		
		System.out.println(breadthFirstSearch(g,1).toString());
	}

	public static HashMap<Integer,Integer> breadthFirstSearch(SimpleDirectedGraph<Integer, DefaultEdge> g, int startVertex) {
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
				int target = g.getEdgeTarget(adj);
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
}
