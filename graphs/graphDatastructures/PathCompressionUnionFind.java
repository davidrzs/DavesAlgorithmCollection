/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package graphDatastructures;

/*
 *
 * This UnionFind Implementation is significantly faster than the other set based implementation.
 *
 */

public class PathCompressionUnionFind{
    int[] rank;
    int[] parent;
    
    PathCompressionUnionFind(int size){
      rank = new int[size];
      parent = new int[size];
      for(int i = 0; i < size; i++){
        parent[i] = i;
        rank[i] = 1;
      }
    }
    
    int find(int node){
      while (parent[node] != node){
        parent[node] = parent[parent[node]];
        node = parent[node];
      }
      return node;
    }
    
    void union(int a, int b){
      int aParent = find(a);
      int bParent = find(b);
      if(rank[aParent] > rank[bParent]){
        parent[bParent] = aParent;
        rank[aParent]+=rank[bParent];
        rank[bParent] = 0;
      }else {
        parent[aParent] = bParent;
        rank[bParent]+=rank[aParent];
        rank[aParent] = 0;
      }
    }
  }