
public class BinarySearch2 {

	public static void main(String[] args) {
	}

	public static int binarySearch(int[] arr, int key) {
		int left = 0;
		int right = arr.length-1;
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
		return -1;
	}
}
