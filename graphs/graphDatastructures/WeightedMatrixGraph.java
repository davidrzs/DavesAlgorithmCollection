package graphDatastructures;
import java.util.List;

public class WeightedMatrixGraph {


		
	private int E;
	private final int V;
	
	// we need to do this since I have a second linked list in my buildpath.
	private int[][] matrix;


	public WeightedMatrixGraph(int vertices){
		this.V = vertices;
		matrix = new int[V][V];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				matrix[i][j] = Integer.MIN_VALUE;
			}			
		}
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w, int weight) {
		matrix[v][w] = weight;
	}
	
	
	public List<Integer> adj(int w){
		List<Integer> li = new java.util.LinkedList<Integer>();
		for(int k = 0; k < V; k++) {
			if(matrix[w][k] != Integer.MIN_VALUE) {
				li.add(k);
			}
		}
		return li;
	}



}


