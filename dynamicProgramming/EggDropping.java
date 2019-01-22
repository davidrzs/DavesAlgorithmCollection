import java.util.Arrays;

public class EggDropping {
	public static void main(String[] args) {
		System.out.println(eggDrop(2,100));
	}
	
	public static int eggDrop(int nrOfEggs, int nrOfFloors) {
		
		int[][] DP = new int[nrOfEggs][nrOfFloors];
		
		// now we initialize the first row: with 1 egg in the worst case we need to test all floors:
		DP[0][0] = 1;
		for(int i = 1; i < nrOfFloors; i++) {
			DP[0][i] = 1 + DP[0][i-1];
		}
		System.out.println(Arrays.deepToString(DP));
		// now we can do the recurrence:
		for(int j = 1; j < nrOfEggs; j++) {
			for(int i = 1; i < nrOfFloors; i++) {
				DP[j][i] = 1 + Math.max(DP[j-1][i-1], DP[j][i-1]);
			}
		}
		
		return 0;
		
	}
}
