package minimalSpanningTrees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import org.jgrapht.alg.util.UnionFind;
import org.jgrapht.graph.*;



// NOT FINISHED

/**
 * The idea behind prims algorithm is to always pick the lowest weight edge that connects two different connected components.
 * We stop when we are left with just one connected component.
 * @author David
 *
 */

public class Prim {

	public static int minimalSpanningTree(SimpleWeightedGraph<Integer, DefaultEdge> graph) {
		
//        ArrayList<DefaultEdge> allEdges = new ArrayList<>(graph.edgeSet());
//        allEdges.sort(Comparator.comparingDouble(graph::getEdgeWeight));
//		
//        UnionFind<Integer> uf = new UnionFind<>(graph.vertexSet());
//        
//        // we need a st
//        Integer startingVertex = graph.vertexSet().iterator().next();
//        
//        int length = 0;
//        
//        boolean hasNext = true;
//        
//        HashSet<Integer> verticesInMst = new HashSet<Integer>();
//        
//        PriorityQueue<Integer> numbers = new PriorityQueue<>();
//        
//        verticesInMst.add(startingVertex);
//        
//        //do this as long as there are still unconnected edges
//        while(verticesInMst.size() < graph.vertexSet().size()) {
//        	numbers.
//            ArrayList<DefaultEdge> allAdjacentEdges = new ArrayList<>(graph.outgoingEdgesOf(vertex));
//            allEdges.sort(Comparator.comparingDouble(graph::getEdgeWeight));
//        	
//        	// check if we can add this edge
//        	if(uf.find(graph.getEdgeSource(edge)) != uf.find(graph.getEdgeTarget(edge))) {
//        		// we now union the two components:
//        		uf.union(uf.find(graph.getEdgeSource(edge)), uf.find(graph.getEdgeTarget(edge)));
//        		System.out.println("Chose edge from " + graph.getEdgeSource(edge) + " to " + graph.getEdgeTarget(edge) + " with weight "+ graph.getEdgeWeight(edge));
//        		length += graph.getEdgeWeight(edge);
//        	}
//        }
//        
//		return length;
		return -1;
	}
}