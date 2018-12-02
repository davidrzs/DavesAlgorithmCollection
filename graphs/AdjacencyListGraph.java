import java.util.List;

public class AdjacencyListGraph implements Graph {
	
	private int E;
	private final int V;
	
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
		// since we are working with directed graphs
		//nodes[w].add(v);
	}
	
	
	public List<Integer> adj(int w){
		return nodes[w];
	}



}
