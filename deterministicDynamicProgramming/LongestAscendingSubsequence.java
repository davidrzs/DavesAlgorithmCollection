import java.util.Arrays;

public class LongestAscendingSubsequence {
	
	public static void main(String[] args) {
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 }; 
	
		System.out.println(longestIncreasingSubsequence(arr));
	}
	
	public static int longestAscendingSubsequence(int[] array) {
		
		int[] DP = new int[array.length];
		
		// initialize
		for(int i = 0; i < array.length; i++) {
			DP[i] = Integer.MAX_VALUE/2;
		}
		
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				if(DP[j] > array[i]) {
					DP[j] = array[i];
					break;
				}
			}
		}
		
		//System.out.println(Arrays.toString(DP));
		// find maximal length
		int maxSoFar = 0;
		for(int i = 0; i < array.length; i++) {
			if(DP[i] != Integer.MAX_VALUE / 2) {
				maxSoFar = i;
			}
		}
		System.out.println(maxSoFar+1);
		return maxSoFar+1;
	}
	
	
	static int binarySearch(int[] arr,int key, int r) {
		int ll = 0;
		int rr = Math.min(r, arr.length);
		int middle = ((r) / 2);
		while(ll < rr) {
			if(arr[middle] > key) {
				rr = middle;
			} else {
				ll = middle+1;
			}
			middle = (ll + rr) / 2;
		}
		return ll;
	}
	
	
public static int longestAscendingSubsequenceLogarithmic(int[] array) {
		
		int[] DP = new int[array.length];
		
		// initialize
		for(int i = 0; i < array.length; i++) {
			DP[i] = Integer.MAX_VALUE/2;
		}
		int currentMax = 1;
		for(int i = 0; i < array.length; i++) {
			int index = binarySearch(DP,array[i], currentMax);
			if(DP[index] > array[i]) {
				DP[index] = array[i];
				currentMax++;
			} 
		}
		
		//System.out.println(Arrays.toString(DP));
		// find maximal length
		int maxSoFar = 0;
		for(int i = 0; i < array.length; i++) {
			if(DP[i] != Integer.MAX_VALUE / 2) {
				maxSoFar = i;
			}
		}
		System.out.println(maxSoFar+1);
		return maxSoFar+1;
	}

// https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
public static int longestIncreasingSubsequence(int[] nums) {
    int[] DP = new int[nums.length];
    int size = 0;
    for (int x : nums) {
    	// binary search to find insertion spot
        int i = 0, j = size;
        while (i != j) {
            int m = (i + j) / 2;
            if (DP[m] < x)
                i = m + 1;
            else
                j = m;
        }
        DP[i] = x;
        if (i == size)
            ++size;
    }
    return size;
}
	
}
