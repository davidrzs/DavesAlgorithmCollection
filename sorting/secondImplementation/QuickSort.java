package secondImplementation;


public class QuickSort {

	// second quicksort implementation to practice
			
	public static int[] quickSort(int[] arr) {
		try {
			return recQuickSort(arr,0,arr.length-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static int[] recQuickSort(int[] arr, int l, int r) throws Exception {
		if(l < r) {
			int p = partitionLeft(arr,l,r);
			recQuickSort(arr,l,p-1);
			recQuickSort(arr,p+1,r);
		}
		return arr;	
	}
	

	static int partition(int[] arr, int l, int r) {
		int pivot = arr[r];
		int leftPointer = l;
		int rightPointer = r-1;
		do {
			while(arr[leftPointer] <= pivot && leftPointer < r) {
				leftPointer++;
			}
			while(arr[rightPointer] >= pivot && rightPointer > l) {
				rightPointer--;
			}
			if(leftPointer < rightPointer) {
				//we need to swap:
				int temp = arr[leftPointer];
				arr[leftPointer] = arr[rightPointer];
				arr[rightPointer] = temp;
			}
		} while(leftPointer < rightPointer);
		
		//now we can swap the pivot:
		arr[r] = arr[leftPointer];
		arr[leftPointer] = pivot;
		
		return leftPointer;
	}
	static int partitionLeft(int[] arr, int l, int r) {
		int pivot = arr[l];
		int leftPointer = l+1;
		int rightPointer = r;
		do {
			while(arr[leftPointer] <= pivot && leftPointer < r) {
				leftPointer++;
			}
			while(arr[rightPointer] >= pivot && rightPointer > l) {
				rightPointer--;
			}
			if(leftPointer < rightPointer) {
				//we need to swap:
				int temp = arr[leftPointer];
				arr[leftPointer] = arr[rightPointer];
				arr[rightPointer] = temp;
			}
		} while(leftPointer < rightPointer);
		
		//now we can swap the pivot:
		arr[l] = arr[rightPointer];
		arr[rightPointer] = pivot;
		return rightPointer;
	}
	

	
}
