package minimalSpanningTrees;

import java.util.ArrayList;
import java.util.Comparator;

import org.jgrapht.alg.util.UnionFind;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class ReverseKruskal {
	public static int minimalSpanningTree(SimpleWeightedGraph<Integer, DefaultEdge> graph) {
		
        ArrayList<DefaultEdge> allEdges = new ArrayList<>(graph.edgeSet());
        allEdges.sort(Comparator.comparingDouble(graph::getEdgeWeight));
		
        UnionFind<Integer> uf = new UnionFind<>(graph.vertexSet());
        int length = 0;
        for(DefaultEdge edge : allEdges) {
        	// check if we can add this edge
        	if(uf.find(graph.getEdgeSource(edge)) != uf.find(graph.getEdgeTarget(edge))) {
        		// we now union the two components:
        		uf.union(uf.find(graph.getEdgeSource(edge)), uf.find(graph.getEdgeTarget(edge)));
        		System.out.println("Chose edge from " + graph.getEdgeSource(edge) + " to " + graph.getEdgeTarget(edge) + " with weight "+ graph.getEdgeWeight(edge));
        		length += graph.getEdgeWeight(edge);
        	}
        }
        
		return length;
	}
	
}
