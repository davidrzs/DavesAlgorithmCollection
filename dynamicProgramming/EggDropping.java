
public class EggDropping {
	public static void main(String[] args) {
		System.out.println(eggDrop(2,100));
	}
	
	/**
	 * This function tells you, given a number of floors and eggs,
	 * how many drops you need to perform to find out at which exact floor
	 * eggs start braking.
	 * 
	 * @param nrOfEggs the number of eggs you can maximally drop
	 * @param nrOfFloors the number of floors your building has
	 * @return the number of tries you minimally need to find the floor.
	 */
	public static int eggDrop(int nrOfEggs, int nrOfFloors) {
		
		int[][] DP = new int[nrOfEggs+1][nrOfFloors+1];
		
		// now we initialize the first row: with 1 egg in the worst case we need to test all floors:
		DP[1][1] = 1;
		for(int i = 1; i < nrOfFloors+1; i++) {
			DP[1][i] = 1 + DP[1][i-1];
		}
		for(int i = 1; i < nrOfEggs+1; i++) {
			DP[i][1] = 1;
		}
		
		
		// now we can do the recurrence:
		for(int j = 2; j <= nrOfEggs; j++) {
			for(int i = 2; i <=nrOfFloors; i++) {
				//find optimal floor to throw out egg:
				int tempMin = Integer.MAX_VALUE;
				for(int k = 1; k <= i; k++) {
					int temp2 = 1 + max(DP[j][i-k],DP[j-1][k-1]);
					if(temp2 < tempMin) {
						tempMin = temp2;
					}
				}
				DP[j][i] = Math.min(tempMin,DP[j-1][i]);

			}
		}
		
		return DP[nrOfEggs][nrOfFloors];
		
	}
	
	public static int max(int a, int b) {
		return a > b ? a : b;
	}
	
}
