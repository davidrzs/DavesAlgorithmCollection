import java.util.Arrays;

public class ExponentialSearch {
	
	public static int exponentialSearch(int[] arr, int key) {
		int base = 2;
		int exponent = 0;
		int upperbound = 0;
		int lowerbound = 0;
		int index = (int) Math.pow(base, exponent);
		while(arr[index] < key) {
			exponent++;
			int new_index = (int) Math.pow(base, exponent);
			if(new_index > arr.length-1) {
				upperbound = arr.length-1;
				break;
			}
			lowerbound = index;
			index = new_index;
			upperbound = new_index;
		}
		int[] ref_arr = new int[upperbound-lowerbound+1];
		System.arraycopy(arr, lowerbound, ref_arr, 0, upperbound-(lowerbound-1));
		int id = BinarySearch.binarySearch(ref_arr, key);
		if(id == -1) {
			return -1;
		}
		return id + lowerbound ;
	}
	
}