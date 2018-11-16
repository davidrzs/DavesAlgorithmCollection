
public class SelectionSort {
	static int[] selectionSort(int[] A) {
		for(int i = 0; i < A.length; i++) {
			int min = Integer.MAX_VALUE;
			int minIndex = 0; 
			for(int j = i; j < A.length; j++) {
				if(A[j] < min) {
					minIndex = j;
					min = A[j];
				}
			}
			int temp = A[i];
			A[i] =  A[minIndex];
			A[minIndex] = temp;
		}
		return A;
	}
}
