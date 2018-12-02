import java.util.Queue;
import java.util.LinkedList;

public class BreadthFirstSearch {
	public static void main(String[] args) {
		Graph g = new MatrixGraph(10);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		g.addEdge(3, 6);
		g.addEdge(1, 4);
		g.addEdge(1, 5);
		g.addEdge(4, 5);
		g.addEdge(5, 6);
		
		System.out.println(breadthFirstSearch(g));
	}

	public static String breadthFirstSearch(Graph G) {
		String retString =  "";
		Queue<Integer> s = new LinkedList<Integer>();
		boolean[] active = new boolean[G.V()];
		// graph cannot be empty
		if(G.V() == 0) {
			return "";
		}
		// add first edge to the stack
		s.add(1);
		while(!s.isEmpty()) {
			//System.out.println(s.toString());
			int currV = s.remove();
				
			retString += " " + currV +  "\n";
			for(int w : G.adj(currV)) {
				if(active[w] == false) {
					// mark it as visited:
					active[w] = true;
					s.add(w);
				}
			
			}
			
		}
		
		return retString;
	}
}
