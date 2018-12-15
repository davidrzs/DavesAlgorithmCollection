
public class InterpolationSearch {
	
	public static int interpolationSearch(int[] arr, int key) {
		//like binary search but with dynamic splitting element. -> good if values are evenly distributed.
		int left = 0;
		int right = arr.length -1;
		
		while(left <= right) {
			// this is where this alog differs from normal binary search
			//check that we dont divide by zero:
			double rho;
			if(arr[right] == arr[left]) {
				rho = 0.5;
			} else {
				rho = 0.5;//(key-arr[left])/(arr[right]-arr[left]);				
			}
			int middle = (int) ((right+left)*rho);
			if(arr[middle] < key) {
				left = middle+1;
			}else if(arr[middle]> key){
				right = middle-1;
			} else {
				return middle;
			}
		}
		// we havent found it
		return -1;
		//int middle = (left + right) / 2;

		
	}

}
