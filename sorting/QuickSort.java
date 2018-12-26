import java.util.Arrays;

public class QuickSort {

	
	// IT TOOK ME 4 HOURS TO GET THE INDICES RIGHT. THIS ALGORITHM IS TORTURE. 
	
	static int[] quickSort(int[] arr) {
		try {
			return recQuickSort(arr,0,arr.length-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static int[] recQuickSort(int[] arr, int l, int r) throws Exception {
		if(l < r) {
			int p = partition(arr,l,r);
			recQuickSort(arr,l,p-1);
			recQuickSort(arr,p+1,r);
		}
		return arr;	
	}
	
	static int partition(int[] arr, int  l, int r) throws Exception {
		int pivot = arr[r];
		int ll = l;
		int rl = r-1;
		do {
			while(arr[ll] <= pivot && ll < r) {
				ll++;
			}
			
			while(arr[rl] >= pivot && rl > l) {
				rl--;
			}
			
			if(ll >= rl) {
				break;
			} 
			if(ll<rl) {
				int temp = arr[ll];
				arr[ll] = arr[rl];
				arr[rl] = temp;
			}
			
			
		
		}while(ll < rl);
				arr[r] = arr[ll];
		arr[ll] = pivot;
		return ll;
	}
	
	

	
}
