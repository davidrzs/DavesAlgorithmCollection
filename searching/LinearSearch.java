
public class LinearSearch {
	
	static int linearSearch(int[] arr, int key) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == key) {
				return i;
			}
		}
		// not found
		return -1;
	}
	
}
