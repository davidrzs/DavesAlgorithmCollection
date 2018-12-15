package simpleAlgorithms;

import java.util.Stack;
import graphDatastructures.*;

public class DepthFirstSearch {
	
	public static void main(String[] args) {
		Graph g = new AdjacencyListGraph(10);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 6);
		g.addEdge(1, 4);
		g.addEdge(1, 5);
		g.addEdge(4, 5);
		g.addEdge(5, 6);
		
		System.out.println(depthFirstSearch(g));
	}

	public static String depthFirstSearch(Graph G) {
		String retString =  "";
		Stack<Integer> s = new Stack<Integer>();
		boolean[] marked = new boolean[G.V()];
		// graph cannot be empty
		if(G.V() == 0) {
			return "";
		}
		// add first edge to the stack
		s.push(1);
		while(!s.isEmpty()) {
			//System.out.println(s.toString());
			int currV = s.pop();
			if(marked[currV] == false) {
				// mark it as visited:
				marked[currV] = true;
				retString += " " + currV +  "\n";
				for(int w : G.adj(currV)) {
					if(marked[w] == false) {
						s.push(w);
					}
				}
			}
			
		}
		
		return retString;
	}
	
}
