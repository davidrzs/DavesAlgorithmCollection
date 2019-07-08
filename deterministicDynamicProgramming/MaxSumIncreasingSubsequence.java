import java.util.List;
import java.util.LinkedList;



// https://www.youtube.com/watch?v=99ssGWhLPUE

public class MaxSumIncreasingSubsequence {

	public static void main(String[] args) {
		int[] arr = new int[] {4,6,1,3,8,4,6};
		System.out.println(maxSumIncreasingSubsequence(arr));
	}
	public static List<Integer> maxSumIncreasingSubsequence(int[] arr){
		int[] DP = new int[arr.length];
		int[] recoverSolution = new int[arr.length];
		
		// initialize both arrays
		DP[0] = arr[0];
		recoverSolution[0] = 0;
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < i; j++) {
				// check if we can append to the preceding subsequence
				// this is the case if the element we have used in the rpeceding subsequence is smaller
				// and the subsequence we can form is larger than the one we currently have. 
				if(arr[j] <= arr[i] && DP[i] < DP[j] + arr[i]) {
					DP[i] = DP[j] + arr[i];
					recoverSolution[i] = j;
				} else if(DP[i] < arr[i]){
					DP[i] = arr[i];
					recoverSolution[i] = i;
				}
			}
		}
		
		// find out at which index we can find the biggest subsequence
		int maxIndex = 0;
		int maxSoFar = Integer.MIN_VALUE/2;
		for(int i = 0; i < arr.length; i++) {
			if(DP[i] > maxSoFar) {
				maxSoFar = DP[i];
				maxIndex = i;
			}
		}
		//System.out.println(maxIndex);
		
		// recover biggest subsequence:
		LinkedList<Integer> li = new LinkedList<Integer>();
		int k = maxIndex;
		li.add(arr[k]);
		while(recoverSolution[k] != k) {
			k = recoverSolution[k];
			li.addFirst(arr[k]);
		}
		//System.out.println(Arrays.toString(DP));
		return li;
	}
	

}
