
public class BinarySearch {

	
	public static int binarySearch(int[] arr, int key) {
		int left = 0;
		int right = arr.length-1;
		while(left <= right) {
			//calculate our middle
			int middle = (left + right) / 2;
			//lets look at all the cases
			if(arr[middle] < key) {
				left = middle + 1;
			} else if( arr[middle] > key){
				right = middle-1;
			} else /*arr[middle] == ke */ {
				return middle;
			}
		}
		return -1;
	}
}
