public class NaturalMergeSort {
	public static int[] naturalMergeSort(int[] arr) {
		
		
	// no clue how this is supposed to be implemented. This is just a quick and dirty way how I would sovle this problem.
	
	// we can merge at max 2 subarrays at one time, these are their "coordinates" in the array
		
	
	// we abort as soon as there aren't any subarrays that need sorting
	boolean haveWeChangedInThisRound = true;
	
	
	// since the last element is an absolute pain concerning edge cases we just add padding
	int[] withPadding = new int[arr.length+1];
	for(int i = 0; i < withPadding.length-1; i++) {
		withPadding[i] = arr[i];
	}
	// we need some super small value that all runs definitely end at arr[length-2]
	withPadding[withPadding.length-1] = Integer.MIN_VALUE+1;
	int[] arrCopy = arr;
	arr = withPadding;
	while(haveWeChangedInThisRound) {
		haveWeChangedInThisRound = false;
		int r1s=0;
		int r1e = -1;
		int r2s = -1;
		int r2e = -1;
		for(int i = 0; i < arr.length-1; i++) {
			// check if we need to start a new run
			if(arr[i] > arr[i+1]) {
				// if we already have an r1:
				if(r1e != -1) {
					r2e = i;
					//check if this is correct
					merge(arr, r1s, r1e,r2e);
					//reset vars
					r1s = r2e+1;
					r1e = -1;
					r2s = -1;
					r2e = -1;
					haveWeChangedInThisRound = true;
				} else { // we don't have an r1
					r1e = i;
					r2s = i+1;
				} 
			}
		}
	}
	for(int i = 0; i < arrCopy.length; i++)
		arrCopy[i] = withPadding[i];
		return arrCopy;
		
		
	}
	
	static int[] merge(int[] arr, int l, int m, int r) {
		// the error that needed fixing was that p1 = 0 is nonsense.
		int p1 = l;
		int p2 = m+1;
		//to store the merged array
		int[] temp = new int[r-l+1];
		int counter = 0;
		
		//now we can continue
		while(p1 <= m && p2 <= r) {
			if(arr[p1] > arr[p2]) {
				temp[counter] = arr[p2];
				p2++;
			}else {
				temp[counter] = arr[p1];
				p1++;
			}
			counter++;
		}
		while(p1 <= m) {
			temp[counter] = arr[p1];
			counter++;
			p1++;
		}
		while(p2 <= r) {
			temp[counter] = arr[p2];
			counter++;
			p2++;
		}
		// copy back
		for(int i = 0; i < temp.length; i++) {
			arr[l+i] = temp[i];
		}
		//update reference
		return arr;
	}
	
}
