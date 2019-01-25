package secondImplementation;

public class QuickSort2 {
	static int[] recQuickSort(int[] arr, int l, int r) {
		int parted = partition(arr,l,r);
		recQuickSort(arr,l,parted-1);
		recQuickSort(arr,parted+1,r);
		return arr;
	}
	

	static int partition(int[] arr, int l, int r) {
		int pivot = arr[r];
		int leftPointer = l;
		int rightPointer = r-1;
		do {
			
		}while(leftPointer < rightPointer);
	}
}
