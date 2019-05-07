package advancedAlgorithms;

import java.util.LinkedList;
import java.util.stream.Collectors;

import graphDatastructures.AdjacencyListGraph;

public class CutVerticesAndBridges {
	
	/**
	 * correctness not guaranteed -> implemented without looking at a solution / reference from what i can still remember from the lecture.
	 */
	
	
	static int counter = 1;
	
	public static AdjacencyListGraph getGraph() {
		
		AdjacencyListGraph graph = new AdjacencyListGraph(18);
		
		graph.addEdge(0, 2);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(3, 2);
		
		graph.addEdge(3, 4);
		graph.addEdge(4, 6);
		graph.addEdge(6, 3);
		graph.addEdge(6, 5);
		graph.addEdge(6, 7);

		graph.addEdge(7, 8);
		graph.addEdge(8, 9);
		graph.addEdge(9, 7);
		graph.addEdge(8, 17);
		graph.addEdge(17, 16);
		graph.addEdge(16, 8);
		graph.addEdge(9, 10);
		
		graph.addEdge(10, 11);
		graph.addEdge(11, 12);
		graph.addEdge(10, 12);
		graph.addEdge(14, 12);
		graph.addEdge(11, 13);
		graph.addEdge(13, 14);
		graph.addEdge(13, 15);
		graph.addEdge(15, 14);

		return graph;	
	}

	public static void main(String[] args) {
		AdjacencyListGraph g = getGraph();
		int startingVertex = 3;
		
		int[] lowValue = new int[g.V()];
		int[] dfsValue = new int[g.V()];
		int[] parent = new int[g.V()];
		
		boolean[] visited = new boolean[g.V()];
		
		
		// we need to init the low values:
		for(int i = 0; i < lowValue.length; i++) {
			lowValue[i] = Integer.MAX_VALUE/2;
		}

		// we need to init the parent values:
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
		
		parent[startingVertex] = startingVertex;
		
		cvbDfs(g,startingVertex, dfsValue, lowValue, visited, parent);
		
//		System.out.println(Arrays.toString(dfsValue));
//		System.out.println(Arrays.toString(visited));
//		System.out.println("low: " +Arrays.toString(lowValue));
//		System.out.println("parent: " + Arrays.toString(parent));

		
		outputCutVerticesAndBridges(g, dfsValue, lowValue, parent, startingVertex);
	}
	
	
	public static void outputCutVerticesAndBridges(AdjacencyListGraph graph, int[] dfs, int[] low, int[] parent, int startVertex) {
		
		LinkedList<Integer> cutVertices = new LinkedList<Integer>();
		
		
		// first we figure out if the startvertex is a cut vertex:
		int count = -1; // since the strating vertex is its own parent 
		for(int i = 0; i < graph.V(); i++) { // we need to inspect all since we might have self loops which otherwise will break the -1 above.
			if(parent[i] == startVertex) {
				count++; 
			} 
			// so we are dealing with a vertex that is not the start vertex:
			
			for(int k : graph.adj(i)) {
				if(parent[k] == i || parent[i] == k) {
					if(k != startVertex && low[k] >= dfs[i]) {
						cutVertices.add(i);
						
					}	
				}
			}
				
			
		}
		if(count > 1) {
			cutVertices.add(startVertex);
		}
		
		// since we might have duplicates since a vertex may contain multiple neighbors that fulfill the condition above
		System.out.println("cut vertices are: " + cutVertices.stream().distinct().collect(Collectors.toList()).toString());
		
		
		
		// now we can find bridges - we have to look at all edges in the DFS tree:
		// loop over all vertices
		for(int i = 0; i < graph.V(); i++) {
			for(int j : graph.adj(i)) {
				if(parent[j] == i && dfs[i] < low[j]) {
					System.out.println("bridge found between vertices " + j + " and " + i );
				}
			}
		}
	}
	
	
	public static void cvbDfs(AdjacencyListGraph graph, int vertex, int[] dfsValue, int[] lowValue, boolean[] visited, int[] parent) {
		// check if vertex has already been visited
		if(!visited[vertex]) {
			dfsValue[vertex] = counter;
			lowValue[vertex] = counter;
			counter++;
//			System.out.println(vertex + " " + graph.adj(vertex).toString());
			visited[vertex] = true;
			for(int adjacent : graph.adj(vertex)) {
				
				if(parent[adjacent] == -1) {
					parent[adjacent] = vertex; 
				}
				
				cvbDfs(graph, adjacent, dfsValue, lowValue, visited, parent);
				
				
				//check low value 1 backwards edge:
				if(dfsValue[adjacent] < dfsValue[vertex] && parent[vertex] != adjacent) {
					if(dfsValue[adjacent] < lowValue[vertex]) {
						lowValue[vertex] = dfsValue[adjacent];
					}
				}


			}
		} 

		// logic to find the low value
		if(lowValue[parent[vertex]] > lowValue[vertex]) {
			lowValue[parent[vertex]] = lowValue[vertex];
		}

	}
	
}
