package graphDatastructures;

import java.util.*;

public class UnionFind {
	
	// implementation as presented at: https://www.cadmo.ethz.ch/education/lectures/HS18/DA/vorlesung/mst.pdf
	// from the 2018 ETH Algorithms class.
	
	int[] size;
	ArrayList<Set<Integer>> members;
	int[] rep;
	
	UnionFind(int vertices){
		size = new int[vertices];
		members = new ArrayList<Set<Integer>>(vertices);
		
		// we need to initialize the arraylist
		for(int i = 0;i<vertices;i++) {
			members.add(null);
		}
		rep = new int[vertices];
	}
	
	void make(int u) {
		rep[u] = u;
		size[u] = 1;
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(u);
		members.set(u, set);
	}
	
	int find(int u) {
		return rep[u];
	}
	
	void union(int u, int v) {
		int ru = rep[u];
		int rv = rep[v];
		
		Iterator<Integer> it = members.get(ru).iterator();
		while(it.hasNext()) {
			int ce = it.next();
			rep[ce] = rv;
		}
		members.get(rv).addAll(members.get(ru));
	}
}
