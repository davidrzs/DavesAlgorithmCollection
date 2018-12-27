import java.util.Arrays;

public class InsertionSort {
	static int[] insertionSort(int[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			int toInsert = arr[i+1];
			int insertionPoint = binarySearch(arr,arr[i+1],  0, i+1);
			
			int[] toCopy = new int[i-insertionPoint+2];
			for(int j =insertionPoint; j <= i;j++) {
				toCopy[j-insertionPoint] = arr[j];
			}
			
			for(int j = insertionPoint; j <= i;j++) {
				arr[j+1] = toCopy[j-insertionPoint];
			}
			arr[insertionPoint] = toInsert;
		}
		return arr;
	}
	
	static int binarySearch(int[] arr,int object,  int l, int r) {
		int ll = l;
		int rr = r;
		int middle = l+((r-l) / 2);
		while(ll < rr) {
			if(arr[middle] > object) {
				rr = middle;
			} else {
				ll = middle+1;
			}
			middle = (ll + rr) / 2;
		}
		return ll;
	}
	
	static int linearSearch(int[] arr,int object,  int l, int r) {
		int pointer = l;
		while(pointer <= r) {
			if(arr[pointer]==object) {
				return pointer;
			}else if(arr[pointer]<object) {
				pointer++;
			}else { // arr[pointer]>object{
				return pointer;
			}
		}
		return pointer;
	}
	
}
