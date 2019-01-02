import java.util.Arrays;

public class WaysOfCoinChange {


	/*
	 * Comment on why you cannot use a subset sum like approach: 
	 * 
	 * if you used a subset sum like approach the set of coins [1,1,3] and [3,1,1]
	 * as well as [1,3,1] would be three different combinations.
	 * This however is not true; they are all equivalent from a monetary point fo view.
	 * Therefore we can use the one dimensional approach below.
	 * 
	 * I ahve tested this code on hackerrank and it passes all the tests. I therefore believe that the implementation below is correct
	 */
	

  public static void main(String[] args){
        int[] arrr = new int[] {6,1,2,7,3,8,5};
        int[] arr = new int[] {1,2,3};
        System.out.println(getWays(arr,4));

    }
    // Complete the getWays function below.
    static long getWays(int[] arr, int target) {
        
    	long[] DP = new long[target+1];
    	
    	//initialize the table:
    	
    	DP[0] = 1;
    	
    	for(int coin = 0; coin < arr.length; coin++) {
    		for(int index = 0; index < target+1; index++) {
    			if(arr[coin] <= index) {
    				DP[index] += DP[index-arr[coin]];
    			}
    		}
    	}
    	
    	return DP[target];
    	
    	
    }
	
}
