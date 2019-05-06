import java.util.Random;


public class RandomQuickSelect {
	
	static Random rand = new Random();
	
	
	
	static int quickSelect(int[] array, int nthLargest) {
		if(nthLargest > array.length) {
			throw new IllegalArgumentException();
		}
		return recursiveQuickSelect(array, nthLargest-1, 0, array.length-1);
	}
	
	
	static int recursiveQuickSelect(int[] array, int nthLargest, int left, int right) {
		int p = partition(array, left, right);
		//System.out.println("p: " + p + " nthlargest: " + nthLargest);
		// we need to do the -1 since we start array indexing at 0 but counting the smallest elements at 1.
		if(p == nthLargest) {
			return array[p];
		}else if(p<nthLargest) {
			return recursiveQuickSelect(array, nthLargest, p+1, right);
		}else {
			return recursiveQuickSelect(array, nthLargest, left, p-1);
		}
	}
	
	
	static int partition(int[] arr, int l, int r) {
		if(r == l) {
			return l;
		}
		// generate a random pivot
		int pivotIndex = rand.nextInt(r-l)+l;
		// swap with last element -> so we can use the standard quicksort partitioning algorithm
		int tempPiv = arr[r];
		arr[r] = arr[pivotIndex];
		arr[pivotIndex] = tempPiv;
		
		
		
		
		// initialize variables
		int pivot = arr[r];
		int rr = r-1;
		int ll = l;
		// do the standard partitioning
		do {
			while(arr[ll] <= pivot && ll < r) {
				ll++;
			}
			
			while(arr[rr] >= pivot && rr > l) {
				rr--;
			}
			if(ll >= rr) {
				break;
			}
			
			if(ll < rr) {
				int temp = arr[ll];
				arr[ll] = arr[rr];
				arr[rr] = temp;
			}
		}while(ll < rr);
		arr[r] = arr[ll];
		arr[ll] = pivot;
		//System.out.println(Arrays.toString(arr) + " " + pivot + " " + ll + " " + rr);
		return ll;
		
		
	}
	
	
}
