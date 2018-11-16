
public class BubbleSort {

	static int[] bubbleSort(int[] A) {
		int len = A.length;
		for(int i = 0; i < len-1; i++) {
			for(int j = 0; j < len; j++) {
				if(A[i] > A[i+1]) {
					int temp = A[i+1];
					A[i+1] = A[i];
					A[i] = temp;
				}
			}
		}
		return A;
		
	}
}
