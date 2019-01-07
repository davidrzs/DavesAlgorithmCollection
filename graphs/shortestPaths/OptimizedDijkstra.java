package shortestPaths;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


/**
 * 
 * From exercise sheet  at ETHZ
 * 
 * @author David
 *
 */

public class OptimizedDijkstra {
	
	static int N;
	
	static class MinHeap {
		int insertionCounter = 0;
		int capacity;
		Square[] sqArr;
		MinHeap(int capacity){
			sqArr = new Square[capacity];
			this.capacity = capacity;
		}
		
		Square getMin() {
			return sqArr[0];
		}
		
		void insert(Square s) {
			sqArr[insertionCounter] = s;
			s.positionInHeap = insertionCounter;
			insertionCounter += 1;
		}
		
		void bubbleUp(Square cS) {
			boolean hasBeenZero = false;
			int index = cS.positionInHeap;
			for(int i = index; i >= 0; i = (i-1)/2) {
				if(hasBeenZero) {
					break;
				}
				if(i == 0) {
					hasBeenZero = true;
				}
				if(sqArr[i].distance > sqArr[index].distance) {
					Square swapper = sqArr[i];
			        sqArr[i] = sqArr[index];
			        sqArr[index] = swapper;
			        sqArr[index].positionInHeap = index;
			        sqArr[i].positionInHeap = i;
			        index = i;
				}
			}
		}
		
		void removeMin() {
			//System.out.println("remove min triggered");
			sqArr[0] = sqArr[capacity-1];
			sqArr[0].positionInHeap = 0;
			capacity--;
			restoreHeapCondition(0);
		}
			
		void restoreHeapCondition(int index) {
			int leftChild = index*2 + 1;
			int rightChild = leftChild + 1;
		    int smallest = index;			
			
		    if(leftChild < capacity && sqArr[leftChild].distance < sqArr[index].distance) {
		    	smallest = leftChild;  //LeftChild is bigger
		    }
		    if(rightChild < capacity && sqArr[rightChild].distance < sqArr[smallest].distance)
		    	smallest = rightChild; //RightChild is bigger than both leftChild and the index node

		    if(smallest != index) //If a swap is needed
		    {
		        //Swap
		        Square swapper = sqArr[smallest];
		        sqArr[smallest] = sqArr[index];
		        sqArr[index] = swapper;
		        
		        sqArr[index].positionInHeap = index;
		        sqArr[smallest].positionInHeap = smallest;
		        restoreHeapCondition(smallest);
		    }
			
			
			
		}
		
		void initializeHeap() {
			for(int i = (capacity + 1 / 2); i >= 0; i--) {
				restoreHeapCondition(i);
			}
		}

		@Deprecated
		public void rebalance(Square cS) {
			Square temp = sqArr[0];
			sqArr[0] = cS;
			sqArr[cS.positionInHeap] = temp;
			sqArr[cS.positionInHeap].positionInHeap = cS.positionInHeap;
			sqArr[0].positionInHeap = 0;
			bubbleUp(null);
		}
	}
	
	
	static class Square implements Comparable{
		int positionInHeap;
		int distance;
		int x;
		int y;
		Square(int distance, int x, int y){
			this.distance = distance;
			this.x = x;
			this.y = y;
		}
		
		Square(int x, int y){
			this.distance = Integer.MAX_VALUE/2;
			this.x = x;
			this.y = y;
		}
		
		// to debug:
		public String toString() {
			return "[(" + x + "," + y + ") - " + distance+ " " + positionInHeap+" ]";
		}

		@Override
		public int compareTo(Object arg0) {
			Square tc = (Square) arg0;
			return distance > tc.distance ? 1 : -1;
		}
	}
	
	/**
	 * Checks if the square is on the edge of the jungle.
	 * @param s
	 * @return
	 */
	public static boolean isTerminus(Square s) {
		if(s.x == 0 || s.y == 0 || s.x == N-1 || s.y == N-1) {
			return true;
		}
		return false;
	}
	

	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);
		//
		// Read the number of cases and loop over the cases
		//
		int case_no, cases = scanner.nextInt();
		
		for (case_no = 0; case_no < cases; case_no += 1) {
			//
			// Read the jungle size
			//
			int n = scanner.nextInt();
			N = n;
			//
			// Create a new 2D array for the times
			//
			int[][] T = new int[n][n]; 
			//
			// Read the times
			//
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					T[i][j] = scanner.nextInt();
				}
			}
			//
			// Provide your solution here
			//			
			
			// we make an array with the squares:
			
			// we need to create this dummy var otherwise the compiler complains
			
			Square[][] squares = new Square[n][n];
			boolean[][] visited = new boolean[n][n];
			
			MinHeap mHeap = new MinHeap(n*n);
						
			// initialize
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					//check if this is the starting point
					if(T[i][j]==0) {
						Square a =  new Square(0,i,j);
						squares[i][j] = a;
						mHeap.insert(a);
					}else {
						Square a =  new Square(i,j);
						squares[i][j] = a;
						mHeap.insert(a);
					}
					// add to the priority queue
				}
			}			
			mHeap.initializeHeap();
			/*
			 * Now we can start Dijkstra !
			 */
			//System.out.println(Arrays.toString(mHeap.sqArr));
			
			
			while(true) {
				//Square currentSquare = pq.poll();
				Square currentSquare = mHeap.getMin();
				mHeap.removeMin();

				visited[currentSquare.x][currentSquare.y] = true;
				if(isTerminus(currentSquare)) {
					// if we can leave the jungle print distance and stop program execution
					out.println(currentSquare.distance);
					break;
				}
				// we now check all four adjacent squares:
				if(visited[currentSquare.x+1][currentSquare.y] == false) {
					Square cS = squares[currentSquare.x+1][currentSquare.y];
					int newDist = currentSquare.distance + T[currentSquare.x+1][currentSquare.y];
					if(cS.distance > newDist) {
						cS.distance = newDist;
						mHeap.bubbleUp(cS);
					}
				}
				if(visited[currentSquare.x-1][currentSquare.y] == false) {
					Square cS = squares[currentSquare.x-1][currentSquare.y];
					int newDist = currentSquare.distance + T[currentSquare.x-1][currentSquare.y];
					if(cS.distance > newDist) {
						cS.distance = newDist;
						mHeap.bubbleUp(cS);
					}
				}
				if(visited[currentSquare.x][currentSquare.y+1] == false) {
					Square cS = squares[currentSquare.x][currentSquare.y+1];
					int newDist = currentSquare.distance + T[currentSquare.x][currentSquare.y+1];
					if(cS.distance > newDist) {
						cS.distance = newDist;
						mHeap.bubbleUp(cS);
					}
				}
				if(visited[currentSquare.x][currentSquare.y-1] == false) {
					Square cS = squares[currentSquare.x][currentSquare.y-1];
					int newDist = currentSquare.distance + T[currentSquare.x][currentSquare.y-1];
					if(cS.distance > newDist) {
						cS.distance = newDist;
						mHeap.bubbleUp(cS);
					}
				}
				
			}
			
			
			
			
			
		}
		scanner.close();
		
	}


	//
	// Do not modify the main method, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
}