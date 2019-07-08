import java.util.Arrays;

public class MaximizeArithmeticExpression {

	// personal note: see https://github.com/jeffgerickson/algorithms/issues/50
	
	public static enum Operations{
		PLUS, TIMES
	}
	
	public static void main(String[] args) {
		int[] nArr = new int[] {1,3,2,0,1,6,7};
		Operations[] ops = new Operations[] {Operations.PLUS, Operations.TIMES, Operations.TIMES,Operations.PLUS, Operations.TIMES, Operations.PLUS};
		System.out.println(maximizeArithmeticExpression(nArr, ops));
	}
	
	public static int maximizeArithmeticExpression(int[] nArr, Operations[] ops) {
		// we will use a dynamic programming approach similar to the matrix multiplication problem.
		int[][] minA = new int[nArr.length][nArr.length];
		int[][] maxA = new int[nArr.length][nArr.length];
		
		// initialize the tables:
		for(int i = 0; i < nArr.length; i++) {
			minA[i][i] = nArr[i];
			maxA[i][i] = nArr[i];
		}
		
		for(int i = nArr.length-1; i >= 0; i--) {
			for(int j = i+1; j < nArr.length; j++) {
				int currentMax = Integer.MIN_VALUE;
				int currentMin = Integer.MAX_VALUE;
				for(int l = i; l < j; l++) {
					if(ops[l] == Operations.PLUS) {
						//minimum:
						int temp = min(minA[i][l] + minA[l+1][j],min(minA[i][l] + maxA[l+1][j], min(maxA[i][l] + minA[l+1][j],maxA[i][l] + maxA[l+1][j])));
						if(temp < currentMin) {
							currentMin = temp;
						}
						//maximum
						temp = max(minA[i][l] + minA[l+1][j],max(minA[i][l] + maxA[l+1][j], max(maxA[i][l] + minA[l+1][j],maxA[i][l] + maxA[l+1][j])));
						if(temp > currentMax) {
							currentMax = temp;
						}
					} else {
						// we are dealing with a multiplication
						int temp = min(minA[i][l] * minA[l+1][j],min(minA[i][l] * maxA[l+1][j], min(maxA[i][l] * minA[l+1][j],maxA[i][l] * maxA[l+1][j])));
						if(temp < currentMin) {
							currentMin = temp;
						}
						//max
						temp = max(minA[i][l] * minA[l+1][j],max(minA[i][l] * maxA[l+1][j], max(maxA[i][l] * minA[l+1][j],maxA[i][l] * maxA[l+1][j])));
						if(temp > currentMax) {
							currentMax = temp;
						}
					}
				}
				minA[i][j] = currentMin;
				maxA[i][j] = currentMax;
			}
		}
		//System.out.println(Arrays.deepToString(maxA));
		return maxA[0][nArr.length-1];
	}
	
	
	static int min(int a, int b) {
		return a > b ? b : a;
	}

	static int max(int a, int b) {
		return a > b ? a : b;
	}
	
}
