
/**
 * 
 * IMPORTANT NOTE. THIS CODE IS FLAWED. 
 * check out dynamicProgramming/WaysOfCoinChange.java for a working example.
 * 
 * 
 */


import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args){
        //System.out.println(getMax(new int[] {1, 3, 1, 2},2));
       // System.out.println(getWays(10,new int[] {2,5,3,6}));
        //System.out.println(getWays(75, new int[] {25, 10, 11, 29, 49, 31, 33, 39, 12, 36, 40, 22, 21, 16, 37, 8 ,18, 4, 27, 17, 26, 32, 6 ,38 ,2 ,30, 34}));
        int[] arrr = new int[] {6,1,2,7,3,8,5};
        //Arrays.sort(arrr);
        System.out.println(countCombinations(arrr,11));

    }
    // Complete the getWays function below.
    static long getWays(int n, int[] c) {
        Arrays.sort(c);
        long[][] exactSubsets = new long[c.length][n+1];
        //make zero column 1 for all coins
        for(int j = 0; j < c.length; j++){
            exactSubsets[j][0] = 1;
        }

        // initialize the first coin
        for(int i = 1; i < n+1; i+=c[0]){
            exactSubsets[0][i] = 1;
        }
        //now we can loop over the remaining coins:
        for(int i = 1; i < c.length; i++){
            for(int k = 0; k < n+1; k++){
                if(k-c[i]<0){
                    exactSubsets[i][k] = exactSubsets[i-1][k];
                } else{
                    exactSubsets[i][k] = exactSubsets[i-1][k] + exactSubsets[i][k-c[i]];
                }
            }
        }

        System.out.println(Arrays.deepToString(exactSubsets));
        return exactSubsets[c.length-1][n];
    }
    static int countCombinations(int[] numbers, int target) {
        // d[i][j] = n means there are n combinations of the first j numbers summing to i.
        int[][] d = new int[target + 1][numbers.length + 1];

        // There is always 1 combination summing to 0, namely the empty set.
        for (int j = 0; j <= numbers.length; ++j) {
            d[0][j] = 1;
        }

        // For each total i, calculate the effect of using or omitting each number j.
        for (int i = 1; i <= target; ++i) {
            for (int j = 1; j <= numbers.length; ++j) {
                // "First j numbers" is 1-indexed, our array is 0-indexed.
                int number = numbers[j - 1];

                // Initialize value to 0.
                d[i][j] = 0;

                // How many combinations were there before considering the jth number?
                d[i][j] += d[i][j - 1];

                // How many things summed to i - number?
                if (i - number >= 0) {
                    d[i][j] += d[i - number][j - 1];
                }
            }
        }
        System.out.println(Arrays.deepToString(d));

        // Return the entry in the table storing all the number combos summing to target.
        return d[target][numbers.length - 1];
    }


}
