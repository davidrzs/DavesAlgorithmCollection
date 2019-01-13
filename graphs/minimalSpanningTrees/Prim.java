package minimalSpanningTrees;


import org.jgrapht.graph.*;
import graphDatastructures.DecreaseKeyMinHeap;



/**
 * The idea behind Prim's algorithm is to always pick the lowest weight edge, reaching out from a partial spanning tree to other vertices that aren't in the spanning tree yet.
 * We stop when we are left with just one connected component.
 * @author David
 *
 */

public class Prim {
	
	//wrapper class
	static class Vertex implements graphDatastructures.DecreaseKeyMinHeap.Entry {
		int id, minDist,positionInHeap = 0;
		Vertex(int id, int minDist){
			this.id = id;
			this.minDist = minDist;
		}
		
		@Override
		public int compareTo(Object o2){
			Vertex e2 = (Vertex) o2;
			if(e2.minDist > minDist) {
				return -1;
			}else if(e2.minDist == minDist) {
				return 0;
			}else {
				return 1;
			}
		}

		@Override
		public int GetPositionInHeap() {
			return positionInHeap;
		}

		@Override
		public void SetPositionInHeap(int position) {
			positionInHeap = position;
		}
	}
	

	public static int minimalSpanningTree(SimpleWeightedGraph<Integer, DefaultEdge> graph) {
		graphDatastructures.UnionFind uf = new graphDatastructures.UnionFind(graph.vertexSet().size());

		//initialize the min heap
		DecreaseKeyMinHeap mh = new DecreaseKeyMinHeap(graph.vertexSet().size());
		
		
		Vertex[] vA = new Vertex[graph.vertexSet().size()];
		
		//transform the graph into a usable format 
		for(Integer i : graph.vertexSet()) {
			Vertex vt = new Vertex(i, Integer.MAX_VALUE/2 + i);
			vA[i] = vt;
			mh.insert(vt);
			uf.make(i);
		}
		mh.initializeHeap();
	
		
		Vertex currentVertex;
		Vertex startVertex = (Vertex) mh.getMin();
		
		//the weight of adding the first vertex is zero.
		startVertex.minDist = 0;
		
		// to keep track of the total weight of our mst
		int MSTWeight = 0;
		
		// while there are still vertices in our MinHeap
		while(mh.capacity > 0) {
			currentVertex= (Vertex) mh.getMin();
			//add weight of edge we used to come here.
			MSTWeight+= currentVertex.minDist;
			mh.removeMin();
			for( DefaultEdge ed : graph.outgoingEdgesOf(currentVertex.id)) {
				int target = getTargetVertex(graph, currentVertex.id, ed);
				//if this edge forms a cycle we don't want to add it
				if(uf.find(currentVertex.id) != uf.find(target)) {
					// find out if there is a new smaller weight edge to a vertex
					if(vA[target].minDist > (int) graph.getEdgeWeight(ed)) {
						// set the new weight
						vA[target].minDist = (int) graph.getEdgeWeight(ed);
						// update position of vertex in min heap
						mh.decreaseKey(vA[target]);
					}
				}
			}
			
		}
		
		return MSTWeight;
		
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