package simpleAlgorithms;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

/*
 * 
 * THIS CODE IS DANGEROUS AND JUST A VERY BARE BONES PROTOTYPE
 * 
 * 
 */


public class HasCycleDirectedGraph {
	public static void main(String[] args) {
		SimpleDirectedGraph<Integer, DefaultEdge> g = new SimpleDirectedGraph<>(DefaultEdge.class);
		g.addVertex(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		g.addVertex(5);
		
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 4);
		g.addEdge(1, 5);
		g.addEdge(5, 3);
		
		
	
	
		// no cycle
		System.out.println(hasCycleDirectedGraph(g));
		

		g.addEdge(5, 2);		
		// has cycle
		System.out.println(hasCycleDirectedGraph(g));

	}
	
	public static boolean hasCycleDirectedGraph(SimpleDirectedGraph<Integer, DefaultEdge> g) {
		// we are just starting our algo once from vtx 1, usually we restart until all nodes have been visited
		boolean[] visited = new boolean[g.vertexSet().size()+1];
		boolean[] onStack = new boolean[g.vertexSet().size()+1];
		
		java.util.Stack<Integer> s = new java.util.Stack<Integer>();
		
		int startingNode = 1;
		onStack[1] = true;
		s.add(startingNode);
		while(!s.isEmpty()) {
			//System.out.println(s);
			int currentNode = s.peek();
			if(!visited[currentNode]) {
				visited[currentNode] = true;
				for(DefaultEdge ed : g.outgoingEdgesOf(currentNode)) {
					int target = g.getEdgeTarget(ed);
					//System.out.println(target);
					if(onStack[target]) {
						// we have reached this node from a second location. hence we must have a cycle.
						return true;
					}						
					if(!visited[target]) {
						onStack[target] = true;
						s.push(target);
					}
				}
			} else {
				// we have visited this node before -> we can pop it from the stack.
				int i = s.pop();
				onStack[i] = false;
			}
		}
		
		return false;
		
	}
}
