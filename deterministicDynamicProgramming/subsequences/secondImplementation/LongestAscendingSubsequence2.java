package subsequences.secondImplementation;

import java.util.*;

public class LongestAscendingSubsequence2 {
	public static void main(String[] args) {
		int[] arr = new int[] {15,27,14,38,26,55,46,65,85};
		System.out.println(longestAscendingSubsequence(arr));
	}
	
	static List<Integer> longestAscendingSubsequence(int[] arr){
		int[] DP = new int[arr.length+1];
		int[] reconstruct = new int[arr.length];
		DP[0] = Integer.MIN_VALUE+100;
		// initialize the DP table
		for(int i = 1; i < DP.length; i++) {
			DP[i] = Integer.MAX_VALUE-100;
		}
		int upperBound = 1;
		for(int i = 0; i < arr.length; i++) {
			//find insertion point
			int insertPoint = binarySearch(DP,arr[i], upperBound);
			DP[insertPoint] = arr[i];
			reconstruct[i] = DP[insertPoint-1];
			if(insertPoint == upperBound) {
				upperBound++;
			}

		}
		
		// find the length of the longest subsequence
		int i = 1;
		for(; i < DP.length; i++) {
			if(DP[i] == Integer.MAX_VALUE-100) {
				i--;
				break;
			}
		}
		
		int index = arr.length-1;
		int vorganger = DP[i];
		//now we can get to solution reconstruction
		LinkedList<Integer> li = new LinkedList<Integer>();
		li.add(vorganger);
		while(index > 1) {
			for(int q = Math.min(index, arr.length-1); q > 0; q--) {
				if(vorganger == arr[q]) {
					index = q;
					break;
				}
			}
			vorganger = reconstruct[index];
			li.addFirst(vorganger);
		}
	
		
		return li;
	}
	
	static int binarySearch(int[] arr, int key, int upperBound) {
		int ll = 1;
		int rr = upperBound;
		int middle;
		while(ll!=rr) {
			middle = (rr+ll)/2;
			if(arr[middle]<key) {
				ll = middle+1;
			}else {
				rr = middle;
			}
		}
		return rr;
	}

	
}
