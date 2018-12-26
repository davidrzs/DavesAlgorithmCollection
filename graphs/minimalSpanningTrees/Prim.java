package minimalSpanningTrees;

import graphDatastructures.WeightedMatrixGraph;

import java.util.List;

import graphDatastructures.UnionFind;


/**
 * The idea behind prims algorithm is to always pick the lowest weight edge that connects two different connected components.
 * We stop when we are left with just one connected component.
 * @author David
 *
 */

public class Prim {
	public static void computeMinimalSpanningTree(WeightedMatrixGraph graph) {
		// make our unionfind datastructure:
		UnionFind uf = new UnionFind(graph.V());
		// init
		for(int i = 0; i < graph.V(); i++) {
			uf.make(i);
		}
		WeightedMatrixGraph minimalSpanningTree = new WeightedMatrixGraph(graph.V());
		int sumOfMinimalSpanningTreeWeights = 0;
		
		for(int i = 0; i < graph.V(); i++) {
			List<Integer> li =  graph.adj(i);
			for(Integer nb : li) {
				
			}
		}
	}
}
