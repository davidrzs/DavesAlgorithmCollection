package secondImplementation;

public class InsertionSort2 {
	static int[] insertionSort(int[] arr) {
		for(int i = 1; i < arr.length; i++) {
			int insertPos = binarySearch(arr,arr[i],0,i);
			int temp = arr[insertPos];
			for(int j = insertPos; j <i; j++) {
				int temp2= arr[j+1];
				arr[j+1] = temp;
				temp = temp2;
			}
			arr[insertPos] = temp;
		}
		return arr;
	}
	
	public static int binarySearch(int[] arr, int key, int l, int r) {
		int left = l;
		int right = r;
		while(left<=right) {
			int middle = (left + right )/ 2;
			if(key == arr[middle]) {
				return middle;
			} else if(key > arr[middle]) {
				left = middle+1;
			} else {
				right = middle-1;
			}
		}
		return left;
	}
}
