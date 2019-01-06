package simpleAlgorithms;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class HasCycleUndirectedGraph {

	
	/*
	 * 
	 * THIS CODE IS DANGEROUS AND JUST A VERY BARE BONES PROTOTYPE
	 * 
	 * 
	 */
	
	
	
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
		
//		g.addEdge(1, 5);
//		g.addEdge(5, 1);
		
		g.addEdge(4, 5);
		g.addEdge(5, 4);
		
//		g.addEdge(5, 6);
//		g.addEdge(6, 5);
		// no cycle
		System.out.println(hasCycleUndirectedGraph(g));
		

		
		g.addEdge(1, 5);
		g.addEdge(5, 1);
	

	
		g.addEdge(5, 6);
		g.addEdge(6, 5);
		
		// has cycle
		System.out.println(hasCycleUndirectedGraph(g));

	}
	
	public static boolean hasCycleUndirectedGraph(SimpleDirectedGraph<Integer, DefaultEdge> g) {
		// we are just starting our algo once from vtx 1, usually we restart until all nodes have been visited
		boolean[] visited = new boolean[g.vertexSet().size()+1];
		boolean[] onStack = new boolean[g.vertexSet().size()+1];
		int[] predecessor = new int[g.vertexSet().size()+1];
		
		java.util.Stack<Integer> s = new java.util.Stack<Integer>();
		
		int startingNode = 1;
		s.add(startingNode);
		onStack[startingNode] = true;
		while(!s.isEmpty()) {
			//System.out.println(s);
			int currentNode = s.peek();
			if(!visited[currentNode]) {
				visited[currentNode] = true;
				for(DefaultEdge ed : g.outgoingEdgesOf(currentNode)) {
					int target = g.getEdgeTarget(ed);
					predecessor[target] = currentNode;
					//System.out.println(target);
					if(predecessor[currentNode]!= target) {
						if(onStack[target] || visited[target]) {
							// we have reached this node from a second location. hence we must have a cycle.
							return true;
						}						
					}
					onStack[target] = true;
					s.push(target);
				}
				
			} else {
				// we have visited this node before -> we can poop it from the stack.
				int i = s.pop();
				onStack[i] = false;
			}
		}
		
		return false;
		
	}

}
