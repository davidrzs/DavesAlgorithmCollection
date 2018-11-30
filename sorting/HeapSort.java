import java.util.Arrays;

public class HeapSort {

	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(heapSort(new int[]{5,2,78,3,23})));
	}
	
	static int[] heapSort(int[] array) {
		//first we transform the array into a heap:
		for(int i = array.length/2; i >= 0; i--) {
			restoreHeapCondition(array,i, array.length-1);
		}
		
		//now we can start with the sorting:
		for(int rightPointer = array.length-1; rightPointer >= 0 ;rightPointer--) {
			restoreHeapCondition(array, 0,rightPointer);

			//System.out.println(Arrays.toString(array) + " " + rightPointer);
			
			//first we swap
			int temp = array[rightPointer];
			array[rightPointer] = array[0];
			array[0] = temp;
			
			
			//System.out.println(Arrays.toString(array) + " " + rightPointer);
		}
		
	return array;
	}
	
	static void restoreHeapCondition(int[] array,int pos, int rightPos) {
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
