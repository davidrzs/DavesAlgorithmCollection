/**
 * 
 * Alice and Bob are given two (unfair) coins. In particular, Alice has a coin which when thrown
lands ‘heads’ on average a out of b times, while Bob has a coin which lands ‘heads’ an average of
c out of d times.
They take turns throwing their coins, with Alice starting first. The first who throws ‘heads’ wins.
What is the probability that Alice wins?
Input The first line of the input file contains a number t ≤ 30 of test cases. Each of the t test
cases is described as follows.
• It starts with a line which consists of four integers a b c d, separated by a space, describing
Alice’s coin (0 ≤ a < b ≤ 200) and Bob’s coin (0 ≤ c < d ≤ 200).
Output For each test case output one line containing one real number denoting the probability
that Alice wins. Your solution is going to be accepted if it has an absolute or relative error of at
most 10−5
.
 *
 *
 */

class RandomCoins {
    public static void main(String[] args) {
        // Uncomment this line if you want to read from a file
        //In.open("public/sample.in");
        
        int t = In.readInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }
        
        // Uncomment this line if you want to read from a file
        //In.close();
    }

    public static void testCase() {
        // Input using In.java class
        int a = In.readInt();
        int b = In.readInt();
        int c = In.readInt();
        int d = In.readInt();
        
        double aliceWinP = ((float) a)/ ((float)b);
        double bobLooseP = 1 - ((float) c)/ ((float)d);
        double aliceLooseP = 1 -aliceWinP;
        
        
        
        // Output using Out.java class
        Out.println( aliceWinP / (1 - bobLooseP*aliceLooseP));
    }
}