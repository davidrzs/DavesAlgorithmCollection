
/**
 * Due to developer lazyness, this heap can only handle objects that aren't equal! (you can try it with objects that are equal but it might not work
 * it just hasn't been tested.)
 * 
 * @author David
 *
 */

public class DecreaseKeyMinHeap {
		int insertionCounter = 0;
		int capacity;
		Entry[] sqArr;
		DecreaseKeyMinHeap(int capacity){
			sqArr = new Entry[capacity];
			this.capacity = capacity;
		}
		
		Entry getMin() {
			return sqArr[0];
		}
		
		void insert(Entry s) {
			sqArr[insertionCounter] = s;
			s.SetPositionInHeap(insertionCounter);
			insertionCounter += 1;
		}
		
		/**
		 * if the 
		 * @param cS
		 */
		void decreaseKey(Entry cS) {
			boolean hasBeenZero = false;
			int index = cS.GetPositionInHeap();
			for(int i = index; i >= 0; i = (i-1)/2) {
				if(hasBeenZero) {
					break;
				}
				if(i == 0) {
					hasBeenZero = true;
				}
				if(sqArr[index].compareTo(sqArr[i]) == -1) {
					Entry swapper = sqArr[i];
			        sqArr[i] = sqArr[index];
			        sqArr[index] = swapper;
			        sqArr[index].SetPositionInHeap(index);
			        sqArr[i].SetPositionInHeap(i);
			        index = i;
				}
			}
		}
		
		/**
		 * removes the minimal element at the top of the array and rebalances.
		 */
		void removeMin() {
			//System.out.println("remove min triggered");
			sqArr[0] = sqArr[capacity-1];
			sqArr[0].SetPositionInHeap(0);
			capacity--;
			restoreHeapCondition(0);
		}
		
		/**
		 * Restores the heap condition at the current element and if a movement has been made calls itself again on its child thyt has been moved.
		 * @param index
		 */
		void restoreHeapCondition(int index) {
			int leftChild = index*2 + 1;
			int rightChild = leftChild + 1;
		    int smallest = index;			
			
		    if(leftChild < capacity && sqArr[leftChild].compareTo(sqArr[index]) == -1) {
		    	smallest = leftChild;  //LeftChild is bigger
		    }
		    if(rightChild < capacity && sqArr[rightChild].compareTo(sqArr[smallest]) == -1)
		    	smallest = rightChild; //RightChild is bigger than both leftChild and the index node

		    if(smallest != index) //If a swap is needed
		    {
		        //Swap
		        Entry swapper = sqArr[smallest];
		        sqArr[smallest] = sqArr[index];
		        sqArr[index] = swapper;
		        
		        sqArr[index].SetPositionInHeap(index);
		        sqArr[smallest].SetPositionInHeap(smallest);
		        restoreHeapCondition(smallest);
		    }
			
			
			
		}
		
		/**
		 * called at the beginning to initialize the heap to ensure the heap condition si fulfulled at all positions in the heap.
		 */
		void initializeHeap() {
			for(int i = (capacity + 1 / 2); i >= 0; i--) {
				restoreHeapCondition(i);
			}
		}

	
	
	/**
	 * 
	 * All objects using the decreaseKey MinHeap above must implement the interface below.
	 * 
	 * @author David
	 *
	 */
	public static interface Entry extends Comparable{
		public int GetPositionInHeap();
		public void SetPositionInHeap(int position);
	}
}
