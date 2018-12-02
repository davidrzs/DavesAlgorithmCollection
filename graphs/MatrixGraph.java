import java.util.List;

public class MatrixGraph implements Graph {

	
	private int E;
	private final int V;
	
	// we need to do this since I have a second linked list in my buildpath.
	private boolean[][] matrix;
	

	public MatrixGraph(int vertices){
		this.V = vertices;
		matrix = new boolean[V][V];
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	public void addEdge(int v, int w) {
		matrix[v][w] = true;
	}
	
	
	public List<Integer> adj(int w){
		List<Integer> li = new java.util.LinkedList<Integer>();
		for(int k = 0; k < V; k++) {
			if(matrix[w][k] == true) {
				li.add(k);
			}
		}
		return li;
	}



}
