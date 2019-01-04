package secondImplementation;

import java.util.Arrays;

public class HeapSort {

	static int[] heapSort(int[] array) {
		// build a max heap
		for(int i = (array.length/2+1); i >= 0; i--) {
			restoreHeapCondition(array,i,array.length-1);
		}
		//System.out.println(Arrays.toString(array));
		
		// now we can extract the max n times:
		
		for(int i = array.length-1; i >= 0; i--) {
			int temp = array[i];
			array[i] = array[0];
			System.out.print(array[0] + " ");
			array[0] = temp;
			restoreHeapCondition(array,0,i-1);
		}
		
		return array;
	}
	
	static void restoreHeapCondition(int[] array,int pos, int rightPos) {
		int leftChildPos = pos*2+1;
		int rightChildPos = pos*2 + 2;
		
		int largestPos = pos;
		
		if(leftChildPos<= rightPos) {
			if(array[leftChildPos] > array[pos]) {
				largestPos = leftChildPos;
			}	
		}
		
		 if(rightChildPos<=rightPos) {
			 if(array[rightChildPos] > array[largestPos]) {
					largestPos = rightChildPos;
			 } 
		 }

		
		if(largestPos != pos) {
			int temp = array[pos];
			array[pos] = array[largestPos];
			array[largestPos] = temp;
			
			restoreHeapCondition(array,largestPos,rightPos);
		}
		
	}
	
	static void restoreHeapCondition2(int[] array,int pos, int rightPos) {
		int leftChildIndex = pos*2+1;
		int rightChildIndex = pos*2 + 2;
		int largerIndex = pos;
		
		//System.out.println(pos+ " " +leftChildIndex + " " + rightChildIndex);
		
		
		//check if the position exists
		if(leftChildIndex <= rightPos) {
			if(array[leftChildIndex] > array[pos]) {
				largerIndex = leftChildIndex;
			}
		}

		//check if the position exists
		if(rightChildIndex <= rightPos) {
			if(array[rightChildIndex] > array[largerIndex]) {
				largerIndex = rightChildIndex;
			}
		}
		
		//check if we found a larger child
		if(pos != largerIndex) {
			// we need to swap
			int temp = array[pos];
			array[pos] = array[largerIndex];
			array[largerIndex] = temp;
			// since we have moved stuff we must make sure it didnt screw up something else in the "tree"
			restoreHeapCondition(array,largerIndex,rightPos);
		}
	}
}
