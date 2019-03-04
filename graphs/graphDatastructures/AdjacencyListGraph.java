package graphDatastructures;

import java.util.Iterator;
import java.util.List;


/**
 * 
 * A class to represent an unweighted undirected graph.
 * 
 * @author David
 *
 */

public class AdjacencyListGraph implements Graph, Cloneable {
	
	private int E;
	private int V;
	
	// we need to do this since I have a second linked list in my buildpath.
	private java.util.LinkedList<Integer>[] nodes;
	
	// to get rid of the array of lists flaw in java: https://stackoverflow.com/questions/217065/cannot-create-an-array-of-linkedlists-in-java
	@SuppressWarnings("unchecked")
	public AdjacencyListGraph(int vertices){
		this.V = vertices;
		nodes = new java.util.LinkedList[vertices];
		// now we initialize all of them
		for(int i = 0; i < V; i++) {
			nodes[i] = new java.util.LinkedList<Integer>();
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		nodes[v].add(w);
		nodes[w].add(v);
		E++;
	}
	
	@Override
	public String toString() {
		String toRet = "";
		for(int i = 0; i < V; i++) {
			toRet += ( Integer.toString(i) + " " + nodes[i].toString() + "\n");
		}
		return toRet;
	}
	
	public int maxVertex() {
		int cMaxVertex = 0;
		int cMax = 0;
		for(int i = 0; i < V; i++) {
			if(cMax < nodes[i].size()) {
				cMax = nodes[i].size();
				cMaxVertex = i;
			}
		}
		return cMaxVertex;
	}
	
	public void removeEdge(int v, int w) {
		
		// we must remove the edge from two lists in our adjacency list.
		
		Iterator<Integer> it1 = nodes[v].iterator();
		
		int temp1 = it1.next();
		
		while(temp1 != w) {	
			temp1 = it1.next();
		}
		if(temp1 == w) {
			it1.remove();
		}
		

		Iterator<Integer> it2 = nodes[w].iterator();

		int temp2 = it2.next();
		
		while(temp2 != v) {	
			temp2 = it2.next();
		}
		if(temp2 == v) {
			it2.remove();
		}
		


	}
	
	
	public List<Integer> adj(int w){
		return nodes[w];
	}
	

	/**
	 * is used to get euler tours of a graph.
	 */
	@Override
	public AdjacencyListGraph clone() throws CloneNotSupportedException {
		AdjacencyListGraph clonedObj = (AdjacencyListGraph) super.clone();
	    clonedObj.nodes = this.nodes.clone();
	    clonedObj.E = this.E;
	    clonedObj.V = this.V;
	    return clonedObj;
	}



}
