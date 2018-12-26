
public class MergeSort {

	static int[] mergeSort(int[] arr) {
		return PMergeSort(arr, 0, arr.length-1);
	}
	
	static int[] PMergeSort(int[] arr, int l, int r) {
		if(l != r) {
			int m = (l+r) / 2;
			System.out.println(m);
			PMergeSort(arr,l,m);
			PMergeSort(arr,m+1,r);
			merge(arr,l,m,r);	
		}
		return arr;
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
