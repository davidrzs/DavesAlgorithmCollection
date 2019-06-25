/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package flows;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class FordFulkerson {

	
	
	static boolean doesAugmentingPathExist(int[] path,  ArrayList<Integer>[] graph, double[][] capacity, double[][] flow) {
		
		int startVertex = 0; // our s
		int target = path.length-1;
		boolean[] visited = new boolean[path.length];
		
		for(int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		visited[startVertex] = true;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startVertex);
		
		while(!queue.isEmpty()) {
			int currentVertex = queue.remove();
			for(int neighbor : graph[currentVertex]) {
				//System.out.println(currentVertex);
				if(!visited[neighbor] && capacity[currentVertex][neighbor] > flow[currentVertex][neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
					path[neighbor] = currentVertex;
					if(neighbor == target) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	
	public static double calculateMaximalflow(double[][] capacity, double[][] flow, ArrayList<Integer>[] graph) {
		
		int[] augmentingPath = new int[graph.length];
		
		int maxFlow = 0;
		
		while(doesAugmentingPathExist(augmentingPath,graph,capacity,flow)) {
			
			double maximalFlowRemainingOnPath = Float.MAX_VALUE;
			
			// we now need to find the bottleneck capacity: -> we start at t and go backwards towards s
			for(int i = graph.length-1; i != 0; i = augmentingPath[i]) {
				// check if the current edge is a bottleneck
				maximalFlowRemainingOnPath = Math.min(capacity[augmentingPath[i]][i] - flow[augmentingPath[i]][i], maximalFlowRemainingOnPath);
			}
			
			// we have our bottleneck capacity -> we can now change the flow on our path
			for(int i = graph.length-1; i != 0; i = augmentingPath[i]) {
				// subtract new flow from capacity and add to flow:
				capacity[augmentingPath[i]][i] -= maximalFlowRemainingOnPath;
				capacity[i][augmentingPath[i]] += maximalFlowRemainingOnPath;
			}
			maxFlow += maximalFlowRemainingOnPath;
		}
		
		return maxFlow;
		
	}
	
	
}
