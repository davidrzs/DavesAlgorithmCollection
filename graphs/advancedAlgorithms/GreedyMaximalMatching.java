/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package advancedAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * @author david
 *
 */
public class GreedyMaximalMatching {

	// setting size as a global static variable is a bad idea
	static int size;
	
	public static void main(String[] args) {
		ArrayList<Edge> graph = getGraph();
		
		for(int i = 0; i < graph.size()*graph.size(); i++) {
			System.out.println((getGreedyMaximalMatching(graph).size()));			
		}
	}
	
	
	public static List<Edge> getGreedyMaximalMatching(List<Edge> graph){

		Collections.shuffle(graph);
		
		LinkedList<Edge> matching = new LinkedList<Edge>();
		
		boolean[] covered = new boolean[size];
		
		for(int i = 0; i < graph.size(); i++) {
			Edge ce = graph.get(i);
			if(!covered[ce.u] && !covered[ce.v]) {
				matching.add(ce);
				covered[ce.u] = true;
				covered[ce.v] = true;
			}
		}
		
		return matching;
			
	}
	
	
	
	public static ArrayList<Edge> getGraph(){
		ArrayList<Edge> graph = new ArrayList<Edge>();
		graph.add(new Edge(0,1));
		graph.add(new Edge(1,2));
		graph.add(new Edge(1,3));
		graph.add(new Edge(2,3));
		graph.add(new Edge(2,8));
		graph.add(new Edge(3,4));
		graph.add(new Edge(4,5));
		graph.add(new Edge(5,6));
		graph.add(new Edge(3,7));
		graph.add(new Edge(6,7));
		size = 9;
		return graph;
	}
	
	static class Edge {
		int u;
		int v;
		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
	
	
}
