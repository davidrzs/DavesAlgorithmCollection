package simpleAlgorithms;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class TopologicalOrdering {
	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);
		//
		// Read the number of paragraphs and page width
		//

		//
		// Read the number of vertices and edges
		//
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int i;
		//
		// There are several representations of the graph, choose what seems
		// useful:
		//
		// For every edge, the source and target vertex
		//
		int[] edge_from = new int[m];
		int[] edge_to   = new int[m];
		//
		// For every vertex V, List of vertices X with direct edge from V to X
		// 
		List<List<Integer>> v_out = new ArrayList<List<Integer>>();
		//
		// For every vertex V, List of vertices X with direct edge to V from X
		// 
		List<List<Integer>> v_in  = new ArrayList<List<Integer>>();

		// Create the empty lists
		for (i = 0; i < n; i++) {
			v_out.add (new ArrayList<Integer>());
			v_in.add  (new ArrayList<Integer>());
		}
		//	
		// Read elements of A and elements of B
		//
		for (i = 0; i < m; i++) {
			// Read the edge
			int s = scanner.nextInt();
			int t = scanner.nextInt();
			// Insert into all three graph representations
			edge_from[i] = s;
			edge_to[i] = t;
			v_out.get(s).add(t);
			v_in.get(t).add(s);
		}

		// First, compute the topological order
		
		// The List to hold the topological order, empty
		ArrayList<Integer> order = new ArrayList<Integer>();

		
		// first we gotta do DFS
		Stack<Integer> dfsStack = new Stack<Integer>();
		Stack<Integer> visitedStack = new Stack<Integer>();
		boolean[] visitedArr = new boolean[n];

		// we need to find node with smallest eingangsgrad
		int[] eingangsGrad = new int[n];
		int ctr = 0;
		for(List<Integer> c : v_in) {
			eingangsGrad[ctr] = c.size();
			ctr++;
		}
		
		//get largest
		for(int ll = 0; ll < n; ll++) {
			if(eingangsGrad[ll] == 0) {
				dfsStack.push(ll);
				//System.out.println("eg0: " + ll);
			}
		}

		while(!dfsStack.isEmpty()) {
			int currentVertex = dfsStack.pop();
			//System.out.println("currently at: " + currentVertex);
			//add the current vertex to the visitedStack
			visitedStack.push(currentVertex);
			for(int outgoing  : v_out.get(currentVertex)) {
				eingangsGrad[outgoing] -= 1;
				if(eingangsGrad[outgoing] == 0) {
					dfsStack.push(outgoing);
				}
				
			}
		}

		
		
		// now we can reverse the visited stack to get the topological order
		while(!visitedStack.isEmpty()) {
			order.add(visitedStack.pop());
		}
		//System.out.println(order);

		//System.out.println("order size: " + order.size());
		//System.out.println("nr of vertices: " + n);
		
		// now we can get to the DP part. The idea is the following:
		
		int[] longestPathLengthTillNow = new int[n];
		
		for(int j = order.size()-1; j >= 0; j--) {
			int currentNode = order.get(j);
			int maxLen = 0;
			boolean hasBeenChanged = false;
			for(int preceeding : v_in.get(currentNode)) {
				if (maxLen <= longestPathLengthTillNow[preceeding]) {
					maxLen = longestPathLengthTillNow[preceeding];
					hasBeenChanged = true;
				}
			}
			longestPathLengthTillNow[currentNode] = maxLen + 1;
		}
		
		
		
		int longestPath = 0;
		for(int c : order) {
			if (longestPathLengthTillNow[c] > longestPath) {
				longestPath = longestPathLengthTillNow[c];
			}
		}

		out.println(longestPath);		

	}
}
