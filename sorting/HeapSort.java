
public class HeapSort {

	
	static int[] heapSort(int[] arr) {
		for(int j = arr.length / 2; j >0; j--) {
			restoreHeapCondition(arr, j, arr.length);
		}
		for(int i = arr.length-1; i > 0; i--) {
			int temp = arr[i];
			arr[i] = arr[0];
			arr[0] = temp;
			// now we want to have the max on top again.
			restoreHeapCondition(arr, 1, i-1);	
		}
		return arr;
	}
	
	static int[] restoreHeapCondition(int[] arr, int i, int n) {
		return null;
	}
}
