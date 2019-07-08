package subsequences;

import java.util.*;

public class LongestDescendingSubsequence {

	public static void main(String[] args) {
		longestDescendingSubsequence(new int[] {5,4,3,5,4,2,1});
	}
	
	
	public static LinkedList<Integer> longestDescendingSubsequence(int[] arr){
		
		int[] longestTillNow = new int[arr.length+1];
		int[] reconstruct = new int[arr.length];
		
		// initialize the longest till now table
		for(int i = 1; i < longestTillNow.length; i++) {
			longestTillNow[i] = Integer.MIN_VALUE / 2;
		}
		longestTillNow[0] = Integer.MAX_VALUE / 2;
		
		for(int i = 0; i < arr.length; i++) {
			// get the element we are currently trying to insert
			int currentElement = arr[i];
			for(int j = 0; j < longestTillNow.length; j++) {
				//check if we have an insertion spot.
				if(currentElement > longestTillNow[j]) {

					longestTillNow[j] = currentElement;
					reconstruct[i] = longestTillNow[j-1];
					break;
				}
			}
			//System.out.println(Arrays.toString(longestTillNow));
		}
		
		// find length of solution
		int solutionIndex = 1;
		while(solutionIndex+1 < longestTillNow.length && (longestTillNow[solutionIndex+1] != Integer.MIN_VALUE / 2 )) {
			solutionIndex++;
		}
		// reconstruct solution
		LinkedList<Integer> solution = new LinkedList<Integer>();

		int lastElement = longestTillNow[solutionIndex];
		
		//System.out.println(Arrays.toString(reconstruct));
		
		// we can add the last element.

		int lastElementIndex = Integer.MAX_VALUE;
		int upperBoundForIndex = arr.length-1;
		for(int counter = solutionIndex; counter > 0 ; counter--) {
			//find index
			solution.addFirst(lastElement);
			for(int i = upperBoundForIndex; i > 0; i--) {
				if(arr[i] == lastElement) {
					lastElementIndex = i;
					lastElement = reconstruct[lastElementIndex];
					break;
				}
			}
			
		}
		
		//System.out.println(solutionIndex);
		
		System.out.println(solution);

		return solution;
	}

}
