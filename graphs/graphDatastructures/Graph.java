package graphDatastructures;

import java.util.List;

public interface Graph {
	
	
	// inspired by Sedgewick/Wayne Algorithms 4
	
	int V();
	
	int E();
	
	void addEdge(int v, int w);
		
	List<Integer> adj(int w);

}
