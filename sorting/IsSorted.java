
public class IsSorted {
	static boolean isSorted(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i+1] < arr[i]) {
				return false;
			}
		}
		return true;
	}
}
