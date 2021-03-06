package graphDatastructures;

import java.util.*;

public class UnionFind {
	
	// implementation as presented at: https://www.cadmo.ethz.ch/education/lectures/HS18/DA/vorlesung/mst.pdf
	// from the 2018 ETH Algorithms class.
	
	ArrayList<Set<Integer>> members;
	int[] rep;
	
	public UnionFind(int vertices){
		members = new ArrayList<Set<Integer>>(vertices);
		
		// we need to initialize the arraylist
		for(int i = 0;i<vertices;i++) {
			members.add(null);
		}
		rep = new int[vertices];
	}
	
	public void make(int u) {
		rep[u] = u;
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(u);
		members.set(u, set);
	}
	
	public int find(int u) {
		return rep[u];
	}
	
	public void union(int u, int v) {
		if(u != v) {
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
}
