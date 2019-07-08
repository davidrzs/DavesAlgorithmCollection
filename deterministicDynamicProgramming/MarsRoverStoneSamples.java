import java.util.Arrays;

public class MarsRoverStoneSamples {
	
	public static void main(String[] args) {
		int[][] stoneVals = new int[][] {new int[] {0,9,2,5,11,8},
			new int[] {17,21,32,5,15,3},
			new int[] {2,2,3,8,1,5}, 
			new int[] {8,2,8,11,15,9},
			new int[] {0,5,3,10,4,0}};
			System.out.println(optimalPath(stoneVals));
	}
	
	
	public static int optimalPath(int[][] M) {
		int[][] DP = new int[M.length][M[0].length];
		
		// now we init the DP matrix not neccessary but good style
		
		DP[0][0] = 0;

		for(int i = 1; i < M.length; i++) {
			DP[i][0] = DP[i-1][0] + M[i][0];
		}
		
		for(int i = 1; i < M[0].length; i++) {
			DP[0][i] = DP[0][i-1] + M[0][i];
		}
		
		for(int i = 1; i < M.length; i++) {
			for(int j = 1; j < M[0].length; j++) {
				DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]) + M[i][j];
			}
		}
		System.out.println(Arrays.deepToString(DP));
		
		return DP[M.length-1][M[0].length-1];
	}
	
}
