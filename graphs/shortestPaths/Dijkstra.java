package shortestPaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Comparator;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Dijkstra {
	static class Vertex{
		int id;
		int distance;
		Vertex(int id, int distance){
			this.id = id;
			this.distance = distance;
		}
		
		// in case we will need to debug
		@Override
		public String toString() {
			return "id: "+String.valueOf(id) + " dist: " + String.valueOf(distance);
		}
	}
	
	public static HashMap<Integer,Integer> Dijkstra(SimpleWeightedGraph<Integer, DefaultEdge> graph, int startingNodeIndex){
		HashMap<Integer,Integer> distances = new HashMap<Integer,Integer>();
		
		// we do this with a list and not a priority queue since it will be a bit easier to implement and i dont feel like writing my own priorityqueue implementation
		
		
		// initializing a priority queue
		PriorityQueue<Vertex> PQ = new PriorityQueue<Vertex>(new Comparator<Vertex>(){
			@Override
		        public int compare(Vertex a, Vertex b) {
				    if (a.distance < b.distance)
				        return -1;
				    else if (a.distance > b.distance)
				        return 1;
				    return 0;
				}
	        }
	    );
		
		Vertex[] vertexList = new Vertex[graph.vertexSet().size()];
		
		for(int i : graph.vertexSet()) {
			Vertex to_add;
			if(i == startingNodeIndex) {
				to_add = new Vertex(i, 0);
			}else {
				to_add = new Vertex(i, Integer.MAX_VALUE);
			}
			PQ.add(to_add);
			vertexList[i] = to_add;
		}
		
		System.out.println(PQ);
		
		// while there are still vertices in our PQ
		while(!PQ.isEmpty()) {
			// remove vertex with smallest distance from PQ and add it to distances map
			Vertex vLowestDist = PQ.poll();
			distances.put(vLowestDist.id, vLowestDist.distance);
			Set<DefaultEdge> eds = graph.edgesOf(vLowestDist.id);
			for(DefaultEdge ed : eds) {
				int source = vLowestDist.id;
				int target = getTargetVertex(graph, vLowestDist.id,  ed);
				
				//check if this edge changes shortest paths:
				if(vertexList[target].distance > vLowestDist.distance + graph.getEdgeWeight(ed)) {
					// this is ridiculous but required: https://stackoverflow.com/questions/6952660/java-priority-queue-reordering-when-editing-elements
					PQ.remove(vertexList[target]);
					vertexList[target].distance = (int) (vLowestDist.distance + graph.getEdgeWeight(ed));
					PQ.add(vertexList[target]);
				}
				
				
			}
			
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
