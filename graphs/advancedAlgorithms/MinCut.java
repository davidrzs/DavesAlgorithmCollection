package advancedAlgorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Iterator;


/**
 * 
 * copy of my online submission -> reference only - do not use
 * 
 * @author david
 *
 */
public class MinCut {
	static Scanner In = new Scanner(System.in);
	public static void main(String[] args) {        
        int t = In.nextInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }
            }

    public static void testCase() {
        // Input using In.java class
        int n = In.nextInt();
        int m = In.nextInt();
        
        int number_of_iterations = n;
        int minCut = Integer.MAX_VALUE;
        
        
        // we first read the graph:
        
        LinkedList<Integer>[] graph = new LinkedList[n];
        LinkedList<Edge> graphE = new LinkedList<Edge>();
        for(int i = 0; i < n; i++){
          graph[i] = new LinkedList<Integer>();
        }
        
        for(int i = 0; i < m; i++){
          int u = In.nextInt();
          int v = In.nextInt();
          graph[u].add(v);
          graph[v].add(u);
          
          graphE.add(new Edge(u,v));
          
        }
        
        
        for(int i = 0; i < number_of_iterations; i++){
          int currMinCut = 0;
          // our union find datastructure for this round
          UnionFind uf = new UnionFind();
          for(int k = 0; k<n; k++){
            uf.make(k);
          }
          // shuffle the edges
          Collections.shuffle(graphE);
          // contract as long as there are more than two vertices left
          Iterator<Edge> it =  graphE.iterator();
          while(uf.numberOfVerticesLeft() > 2){
            Edge currentEdge = it.next();
            uf.union(currentEdge.u, currentEdge.v);
          }
          // now we need to find the vertices of the min cut:
          for(Edge e : graphE){
            if(!uf.inSameCategory(e.u,e.v)){
              currMinCut++;
            }
          }
          System.out.println(currMinCut);
          if(minCut> currMinCut){
            minCut = currMinCut;
          }
        }
        
        System.out.println("\n" + minCut + "\n");
    }
}


class Edge{
  int u;
  int v;
  public Edge(int u, int v){
    this.u = u;
    this.v = v;
  }
}

class UnionFind{
	
	HashMap<Integer, Set<Integer>> hm;
	int[] sizes;
	HashMap<Integer, Integer> parent;
  int numberOfCategories;
	
	public UnionFind(){
		hm = new HashMap<Integer, Set<Integer>>();
		parent = new HashMap<Integer, Integer>();
		numberOfCategories = 0;
	}
	
	public void make(int key) {
		// make a list of all kids
		Set<Integer> toAdd = new HashSet<Integer>();
		toAdd.add(key);
		hm.put(key, toAdd);
		
		// set the parent - every vertex is its own parent
		parent.put(key, key);
		numberOfCategories++;
	}
	
	public boolean inSameCategory(int i, int j) {
		return parent.get(i) == parent.get(j);
	}
	
	public void union(int i, int j) {
	  if(inSameCategory(i,j)){
	    return;
	  }
	  numberOfCategories--;
		int larger;
		int smaller;
		if(hm.get(parent.get(i)).size()>hm.get(parent.get(j)).size()) {
			larger = parent.get(i);
			smaller = parent.get(j);
		}else {
			larger = parent.get(j);
			smaller = parent.get(i);
		}
		
		// first we need to union the set of the smaller with the larger
		hm.get(larger).addAll(hm.get(smaller));
		
		// now we need to set the parents of the set in the smaller one again.
		for(int k : hm.get(smaller)) {
			parent.put(k, larger);
		}

	}
	
	public int find(int key) {
		return parent.get(key);
	}
	
	public int numberOfVerticesLeft(){
    return numberOfCategories;
	}
	
	
	
	
	
}
