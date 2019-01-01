import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


// copy of my submission to the fleemarket problem.

public class FleaMarket {
	
	
	public static void read_and_solve(InputStream in, PrintStream out) {

		Scanner scanner = new Scanner(in);

		int tests = scanner.nextInt();
		for (int t = 0; t < tests; t += 1) {
			//
			// Scan n, S, W
			//
			int n = scanner.nextInt();
			int S = scanner.nextInt();
			int W = scanner.nextInt();
			//
			// Allocate space
			//
			int [] space  = new int[n+1];
			int [] weight = new int[n+1];
			int [] price  = new int[n+1];
			//
			// Scan the values
			//
			for (int i = 1; i <= n; i += 1) {
				space [i] = scanner.nextInt();
				weight[i] = scanner.nextInt();
				price [i] = scanner.nextInt();
			}
			//
			// Provide your solution here
			//
			
			
			
			
			
			// version 1.0 space inneficient
			long[][][] DP = new long[n+2][S+1][W+1];
			
			
			// init is necessary to prevent sumbission of solutiosn that don't exist.
			// We must differentiate between a state we can achieve and a state we cannot achieve -> we cannot build a solution on the latter.
			
			// init 
			//loop over items:
			for(int i = 0; i < n+1; i++) {
	
				//loop over weight
				for(int w = 0; w < W+1; w++) {
					//loop over space freed
					for(int j = 0; j < S+1; j++) {
						DP[i][j][w] = Integer.MIN_VALUE;
					}
				}
				
			}
			
			for(int q = 0; q < W+1; q++) {
				DP[0][0][q] = 0;
			}
			
			
			//loop over items:
			for(int i = 1; i < n+1; i++) {
	
				//loop over weight
				for(int w = 0; w < W+1; w++) {
					//loop over space freed
					for(int j = 0; j < S+1; j++) {
						if(weight[i] > w) {
							DP[i][j][w] = DP[i-1][j][w];
						}else {
							DP[i][j][w] = Math.max(DP[i-1][j][w], DP[i-1][Math.max(0, j-space[i])][w-weight[i]] + price[i]);
						}
					}
				}
				
			}
			
			long cMax = 0;
			for(int i = 0; i < W+1; i++) {
				if(DP[n][S][i] > cMax) {
					cMax = DP[n][S][i];
				}
			}
			
			out.println(cMax);
			
		}

		scanner.close();
	}

	//
	// Do not modify the main method, and keep the method read_and_solve
	// 
	public static void main(String[] args) {	
		read_and_solve(System.in, System.out);		
	}
	
}
