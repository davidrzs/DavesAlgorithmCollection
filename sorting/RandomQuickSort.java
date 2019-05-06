import java.util.Arrays;
import java.util.Random;


public class RandomQuickSort {
	
	static Random rand = new Random();
	
	
	
	static int[] quickSort(int[] array) {
		recursiveQuickSort(array, 0, array.length-1);
		return array;
	}
	
	
	static void recursiveQuickSort(int[] array, int left, int right) {
		if(left < right) {
			int p = partition(array, left, right);
			recursiveQuickSort(array, left, p-1);
			recursiveQuickSort(array, p+1, right);	
		}
		return;
	}
	
/**
 * taken from random quick select
 */
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
