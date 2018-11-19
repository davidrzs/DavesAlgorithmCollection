
public class BubbleSort {

	static int[] bubbleSort(int[] A) {
		int len = A.length;
		for(int i = 0; i < len-1; i++) {
			for(int j = 0; j < len-1; j++) {
				if(A[j] > A[j+1]) {
					int temp = A[j];
					A[j] = A[j+1];
					A[j+1] = temp;
				}
			}
		}
		return A;
		
	}
}
