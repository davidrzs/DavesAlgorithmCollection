import java.util.Arrays;
public class Knapsack {


    public static void main(String[] args){
        int[] values = new int[]{15,10,9,5};
        int[] weights = new int[]{1,5,3,4};
        int target = 8;

        System.out.println(Arrays.deepToString(solveKnapsack(values, weights, target)));
    }


    static int[][] solveKnapsack(int[] values, int[] weights, int target){
        int[][] dp = new int[values.length][target + 1];

        // now we can initialize the DP

        for(int i = 0; i < target + 1; i++){
            if(i >= weights[0]){
                dp[0][i] = values[0];
            }
        }

        for(int j = 1; j < values.length; j++) {
            for(int k = 0; k < target + 1; k++) {
                int otherValue = 0;
                if(k > weights[j]){
                    otherValue = values[j] + dp[j-1][k-weights[j]];
                }
                dp[j][k] = Math.max(dp[j-1][k], otherValue);
            }
        }


        return dp;
    }
}


