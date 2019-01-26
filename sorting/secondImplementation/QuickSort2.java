package secondImplementation;

public class QuickSort2 {
//	static int[] recQuickSort(int[] arr, int l, int r) {
//		if(l<r) {
//			int parted = partition2(arr,l,r);
//			recQuickSort(arr,l,parted-1);
//			recQuickSort(arr,parted+1,r);	
//		}
//		return arr;
//	}
	
	public static int[] quickSort(int[] arr) {
		return recQuickSort(arr, 0, arr.length-1);
	}
	
	static int[] recQuickSort(int[] arr, int l, int r) {
		if(l < r) {
			int p = partition(arr,l,r);
			recQuickSort(arr,l,p-1);
			recQuickSort(arr,p+1,r);
		}
		return arr;	
	}

	
	
	static int partition2(int[] arr, int l, int r) {
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
	

	static int partition(int[] arr, int l, int r) {
		int pivot = arr[r];
		int leftPointer = l;
		int rightPointer = r-1;
		
		do {
			while(arr[leftPointer] <= pivot && leftPointer<r) {
				leftPointer++;
			}
			while(arr[rightPointer] >= pivot && rightPointer >l) {
				rightPointer--;
			}
			if(leftPointer < rightPointer) {
				int temp = arr[leftPointer];
				arr[leftPointer] = arr[rightPointer];
				arr[rightPointer] = temp;
			}
		}while(leftPointer < rightPointer);
		
		//now we can swap the pivot:
		arr[r] = arr[leftPointer];
		arr[leftPointer] = pivot;
		return leftPointer;
	}
}
