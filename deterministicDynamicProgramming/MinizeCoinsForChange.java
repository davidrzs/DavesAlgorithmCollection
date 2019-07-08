import java.util.List;
import java.util.LinkedList;


public class MinizeCoinsForChange {
	public static void main(String[] args) {
		int[] coinDenominations = new int[] {0,1,5,6,8};
		int target = 17;
		System.out.println(minimalNumberOfCoins(coinDenominations,target));
	}
	
	public static List<Integer> minimalNumberOfCoins(int[] denominations, int target){
		int[][] DP = new int[denominations.length][target+1];
		
		boolean[][] reconstructSol =  new boolean[denominations.length][target+1];
		
		// initialize:
		for(int i = 0; i < denominations.length; i++) {
			for(int j = 1; j < target+1; j++) {
				DP[i][j] = Integer.MAX_VALUE/2;
			}
		}
		
		//now we can fill the table
		for(int i = 1; i < denominations.length; i++) {
			for(int j = 0; j < target+1; j++) {
				if(j<denominations[i]) {
					DP[i][j] = DP[i-1][j];
					reconstructSol[i][j] = false;
				}else {
					int s1 = (DP[i-1][j]);
					int s2 = DP[i][j-denominations[i]] + 1;
					if(s1 > s2) {
						reconstructSol[i][j] = true;
						DP[i][j] = s2;
					} else {
						reconstructSol[i][j] = false;
						DP[i][j] = s1;						
					}
				}
			}
		}
		
		
		// now we can reconstruct the solution:
		List<Integer> sol = new LinkedList<Integer>();
		int ctr = target;
		int curEl = denominations.length-1;
		while(ctr > 0 || curEl>0) {
			if(reconstructSol[curEl][ctr]) {
				ctr-=denominations[curEl];
				sol.add(denominations[curEl]);
			}else {
				curEl--;
			}
		}
		return sol;
	}
}
