

/**
 * As always, farmers are afraid of a harsh winter, because too many snowy days can ruin the crops
for the whole year to come. More precisely, if out of n days it is snowing on at least k days, the
season is considered to be a disaster.
You know that the probability of snow on the ith day is pi
, independently of other days. Compute
the probability of a disaster season!
Input The first line of the input contains the number t ≤ 30 of test cases. Each of the t test
cases is described as follows.
• It starts with a line that contains two integers n k, separated by a space, where n denotes
the number of days the winter will last (1 ≤ n ≤ 103
), and the season will be a disaster if
the number of snowy days is at least k (0 ≤ k ≤ n).
• The following line defines the probabilities of snow on each of the n days. It contains n real
numbers p1
. . . pn
, separated by a space, denoting that the probability of a heavy snow on
the ith day is pi (0 ≤ pi ≤ 1, for all i ∈ {1, . . . , n}).
Output For each test case output one line containing one real number denoting the probability
of the season to be a disaster. Your solution is going to be accepted if it has an absolute or relative
error of at most 10−3
.
 */

import java.util.Arrays;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class WinterSeason {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        
        // TODO : UNCOMMENT AFTER DEBUGGING
        
        In.open("public/sample.in");
        
        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }
        
        // Uncomment this line if you want to read from a file
        In.close();
    }

    public static void testCase() {
        // Input using In.java class
        int n = In.readInt();
        int k = In.readInt();
        //Out.println(n + " " + k);
        double[] snowProb = new double[n];

        for(int i = 0; i < n; i++){
          snowProb[i] = In.readDouble();
        }
        
        // initialize the DP table:
        double[][] DP = new double[n][n+1]; // first axis -> all days / second axis -> number of snow days 
        DP[0][0] = 1 - snowProb[0];
        DP[0][1] = snowProb[0];
        for(int i = 1; i < n; i++){
          DP[i][0] = DP[i-1][0] * (1-snowProb[i]);
        }

        
        // we can now fill the DP table:
        
        for(int i = 1; i < n; i++){ // first we loop over all days
          for(int j = 1; j < n+1; j++){ // now we loop over the number of snow days
            DP[i][j] = DP[i-1][j-1]*(snowProb[i]) + DP[i-1][j]*(1-snowProb[i]);
          }
        }
  
        // now we can read out the result:
        double res = 0;
        for(int i = k; i < n+1; i++){
          res += DP[n-1][i];
        }
        
        // stolen from exercise two weeks ago
        DecimalFormat df = new DecimalFormat("0.0##");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        df.setMaximumFractionDigits(4);
        Out.println(df.format(res));

        // Output using Out.java class
        Out.println(Arrays.deepToString(DP));
    }
}