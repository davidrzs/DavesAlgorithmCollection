package graphDatastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WeightedUnionFind {

	// implementation as presented at: https://www.cadmo.ethz.ch/education/lectures/HS18/DA/vorlesung/mst.pdf
	// from the 2018 ETH Algorithms class.
	
	int[] size;
	ArrayList<Set<Integer>> members;
	int[] rep;
	
	public WeightedUnionFind(int vertices){
		size = new int[vertices];
		members = new ArrayList<Set<Integer>>(vertices);
		
		// we need to initialize the arraylist
		for(int i = 0;i<vertices;i++) {
			members.add(null);
		}
		rep = new int[vertices];
	}
	
	public void make(int u) {
		rep[u] = u;
		size[u] = 1;
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(u);
		members.set(u, set);
	}
	
	public int find(int u) {
		return rep[u];
	}
	
	public void union(int u, int v) {
		if(u != v) {
			System.out.println(Arrays.toString(size) + " " + u + " " + v);
			//check which is the larger one:
			if(size[u]>size[v]) {
				int temp = v;
				v = u;
				u = temp;
			}
			int ru = rep[u];
			int rv = rep[v];
			
			Iterator<Integer> it = members.get(ru).iterator();
			while(it.hasNext()) {
				int ce = it.next();
				rep[ce] = rv;
			}
			members.get(rv).addAll(members.get(ru));
			size[v] += size[u];
			size[u] = 0;	
		}
	}

}
