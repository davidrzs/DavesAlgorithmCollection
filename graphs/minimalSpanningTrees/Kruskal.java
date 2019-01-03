package minimalSpanningTrees;

import java.util.ArrayList;
import java.util.Comparator;

import org.jgrapht.alg.util.UnionFind;
import org.jgrapht.graph.*;


public class Kruskal {
	
	public static int minimalSpanningTree(SimpleWeightedGraph<Integer, DefaultEdge> graph) {
		
        ArrayList<DefaultEdge> allEdges = new ArrayList<>(graph.edgeSet());
        allEdges.sort(Comparator.comparingDouble(graph::getEdgeWeight));
		graphDatastructures.UnionFind uf = new graphDatastructures.UnionFind(graph.vertexSet().size());
		for(Integer i : graph.vertexSet()) {
			uf.make(i);
		}
        
        //UnionFind<Integer> uf = new UnionFind<>(graph.vertexSet());
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
	
	public static void main(String[] args) {
		SimpleWeightedGraph<Integer, DefaultEdge> graph = new SimpleWeightedGraph<>(DefaultEdge.class);
		
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addVertex(6);
		graph.addVertex(7);
		graph.addVertex(8);
		graph.addVertex(9);
		graph.addVertex(10);
		graph.addVertex(11);
		graph.addVertex(12);
		graph.addVertex(13);

		graph.addEdge(0,1);
		graph.setEdgeWeight(0, 1, 9);
		
		graph.addEdge(0,5);
		graph.setEdgeWeight(0,5,5);
		
		graph.addEdge(0, 6);
		graph.setEdgeWeight(0,6,4);

		graph.addEdge(1, 6);
		graph.setEdgeWeight(1,6,17);
		
		
		//
		graph.addEdge(1, 2);
		graph.setEdgeWeight(1,2,5);
		
		graph.addEdge(1, 7);
		graph.setEdgeWeight(1,7,6);
		
		graph.addEdge(2,3);
		graph.setEdgeWeight(2,3,7);
		
		graph.addEdge(2,1);
		graph.setEdgeWeight(2,1,5);
		
		graph.addEdge(2,7);
		graph.setEdgeWeight(2,7,2);
		
		graph.addEdge(6,7);
		graph.setEdgeWeight(6,7,1);
		
		graph.addEdge(5,6);
		graph.setEdgeWeight(5,6,11);
		
		graph.addEdge(0,1);
		graph.setEdgeWeight(0,1,9);
		
		graph.addEdge(3,7);
		graph.setEdgeWeight(3,7,5);
		
		graph.addEdge(3,8);
		graph.setEdgeWeight(3,8,7);
		
		graph.addEdge(7,8);
		graph.setEdgeWeight(7,8,11);
		
		graph.addEdge(8,4);
		graph.setEdgeWeight(8,4,6);
		
		graph.addEdge(4,9);
		graph.setEdgeWeight(4,9,2);
		
		graph.addEdge(8,9);
		graph.setEdgeWeight(8,9,4);
		
		graph.addEdge(5,10);
		graph.setEdgeWeight(5,10,3);
		
		graph.addEdge(10,6);
		graph.setEdgeWeight(10,6,8);
		
		graph.addEdge(10,11);
		graph.setEdgeWeight(10,11,7);
		
		graph.addEdge(11,6);
		graph.setEdgeWeight(11,6,13);
		
		graph.addEdge(11, 7);
		graph.setEdgeWeight(11,7,8);
		
		graph.addEdge(7,12);
		graph.setEdgeWeight(7,12,3);
		
		graph.addEdge(12,13);
		graph.setEdgeWeight(12,13,6);
		
		graph.addEdge(8, 13);
		graph.setEdgeWeight(8,13,3);
		
		graph.addEdge(13, 9);
		graph.setEdgeWeight(13,9,1);
	
		System.out.println(minimalSpanningTree(graph));
	}
	
}
