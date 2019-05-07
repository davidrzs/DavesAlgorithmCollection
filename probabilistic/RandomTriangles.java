import java.math.BigInteger;


/**
 * 
 * 
In this exercise you are supposed to examine the random variable denoting the number of triangles
in a random graph G ∼ Gn,p.
For n ∈ N and p ∈ [0, 1], we let Gn,p denote the probability space of graphs on n vertices where
each edge is present with probability p independently of other edges. Let X be the random variable
denoting the number of triangles in G ∼ Gn,p. Compute the expected number of triangles E[X]
and its variance Var[X].
Input The first line of the input file contains a number t ≤ 30 of test cases. Each of the t test
cases is described as follows.
• It starts with a line which consists of an integer and a real n p, separated by a space, denoting
the number of vertices (1 ≤ n ≤ 1000) and the probability of an edge existing (0 ≤ p ≤ 1)
of a graph G ∼ Gn,p.
Output For each test case output a single line with two valus E[X] and Var[X], separated by a
space. Your solution is going to be accepted if it has an absolute or relative error of at most 10−5
 *
 */

class RandomTriangles {
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
        int n = In.readInt();
        double p = In.readFloat();
        double expectedValue = ((choose(n,3).doubleValue() * p )* p) * p;        
        // Output using Out.java class
        Out.println(expectedValue);
        
        double variance = -expectedValue*expectedValue;
        variance += ((((choose(n,3).doubleValue()*choose(n,3).doubleValue() - 2*choose(n,2).doubleValue()*choose(n-2,2).doubleValue() - choose(n,3).doubleValue() )*p)*p)*p)*p*p*p;
        variance += (((choose(n,4).doubleValue()*6*2*p)*p)*p)*p*p;
        variance += choose(n,3).doubleValue()*p*p*p;
        Out.println(variance);

        //System.out.println(choose(10,3));
    }
    
    
    public static BigInteger choose(int n, int k){
      BigInteger retVal = BigInteger.ONE;
      
      BigInteger up = factorial(n);
      BigInteger down = factorial(n-k).multiply(factorial(k));
      retVal = up.divide(down);
      return retVal;
    }
    
    public static BigInteger factorial(int n){
      BigInteger retVal = BigInteger.ONE;
      for(int i = n; i > 0; i--){
        retVal = retVal.multiply(BigInteger.valueOf(i));
      }
      return retVal;
    }
    
}