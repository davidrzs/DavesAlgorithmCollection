# Dave's Algorithm Collection

This repository is a collection of common algorithms that are taught in first year algorithms classes at ETH Zurich:

- the Algorithms and Datastructures class
- the Algorithms and Probability class
- Parallel Programming

All algorithms are implemented in Java and most of them are NOT thread safe.

## On the state of these algorithms
These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class). This repo is by no means complete / tested / safe to use.
*Seriously: Using this code is really dangerous.* 
However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.


## Implemented Algorithms:

**Datastructures**:
 - AVL Tree
 - LinkedList
 - Queue
 - Stack
 - DecreaseKey Min Heap (like a priority queue but objects in the heap that change attributes can be reordered in log(n) time.)
 - SkipList

**Dynamic Programming**
- Knapsack
- Fleamarket Problem (Multi-constrained knapsack)
- CoinChange
- StockOptimum
- Paragraph Formatting / Optimal Line Break
- Mars rover optimal path (path of max value)
- Hiking Problem (Subset Sum splitting items evenly into two bags with solution reconstruction)
- Minimal Editing Distance (DNA mutation)
- Longest Common Subsequence
- Minimal Coin Change (minimal number of coins adding to a certain value) including solution reconstruction.
- Number of Different Ways to Change Coins 
- Maximizing Arithmetic Expression (with + and *)
- Longest Substring of balanced Parentheses and Brackets.
- Maximum Sum increasing subsequence
- Is string an interleafing of two other strings
- EggDropping : Given a number of Eggs and Floors returns minimal number of tries to find out at which level eggs start breaking.

**Graphs**:
- *Graph Datastructures*
	- Graph interface
	- Adjacency list based graph class
	- Matrix based graph class
	- Weighted matrix graph class
	- Union Find (using arrays)
	- Weighted Union Find
- *Simple Algorithms*
	- Simple Depth first search
	- Breadth first search
	- Topological ordering of directed acyclic graph
	- Directed Graph contains cycle ?
	- Undirected graph contains cycle ?
- *Shortest Paths*
	- Bellman Ford
	- Floyd-Warshall
	- Dijkstra
	- Highly optimized Dijkstra
	- BFS Shortest Path
- *Minimal Spanning Trees*
	- Kruskal
	- Prim
- *Advanced Algorithms*
	- Find euler tour
	- Find cut vertices and bridges
	- Min Cut (Karger's Algorithm)

**Sorting**:
- Selection Sort
- HeapSort
- Bubble Sort
- Sequential Merge Sort
- IsSorted, a small program checking whether a given array is sorted.
- Quicksort
- Quicksort with random pivot
- Recursive Mergesort
- Natural 2-way Mergesort
- Insertion Sort with linear search
- Insertion Sort with binary insertion

**Searching**:
- Linear Search
- Binary Search
- Exponential Search
- Interpolation Search

**Selecting**:
- Quickselect with random pivot

**Probabilistic Algorithms**
- Random Coins (calculate expected value and variance)
- Winter Season (probabilistic DP)
- Random Triangles (probabilistic graph theory)
- Miller-Rabin primality test
- Target Shooting
	- PI approximation
	- Monte Carlo Integration

**Locks**
- Peterson Lock
- Filter Lock
- Bakery Lock
- TAS lock (test-and-set)
- TTAS lock (test-and-test-and-set)
- Simple test cases for the locks


## License
All the code in this repository is licensed under the permissive MIT license.
