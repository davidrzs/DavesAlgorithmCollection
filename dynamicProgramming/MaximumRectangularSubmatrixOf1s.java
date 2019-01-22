import java.util.Arrays;

// inspired by https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MaximumRectangularSubmatrixOf1s.java

public class MaximumRectangularSubmatrixOf1s {

	public static void main(String[] args) {
        int input[][] = {
        		{1,1,1,0},
                {1,1,1,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,0,1},
                {1,1,1,1}};
		int maxRectangle = maximalRectangularSubsumOfOnes(input);
		System.out.println("Max rectangle is of size " + maxRectangle);
		assert maxRectangle == 8;
	}
	
	
	public static int maximalRectangularSubsumOfOnes(int[][] arr) {
		class Entry{
			int horizontal;
			int vertikal;
			Entry(int horizontal, int vertikal){
				this.horizontal = horizontal;
				this.vertikal = vertikal;
			}
			
			@Override
			public String toString() {
				return "(" + horizontal + "," + vertikal + ")";
			}
		}
		
		
		Entry[][] resA = new Entry[arr.length][arr[0].length];
		
		// init first col and row:
		if(arr[0][0]==1) {
			resA[0][0] = new Entry(1,1);	
		} else {
			resA[0][0] = new Entry(0,0);
		}
		
		for(int i = 1; i < resA.length; i++) {
			if(arr[i][0] == 1) {
				resA[i][0] = new Entry(1,1+resA[i-1][0].vertikal);
			}else {
				resA[i][0] = new Entry(0,0);
			}
		}
		
		for(int i = 1; i < resA[0].length; i++) {
			if(arr[0][i] == 1) {
				resA[0][i] = new Entry(1+resA[0][i-1].horizontal,1);
			}else {
				resA[0][i] = new Entry(0,0);
			}
		}		
		
		for(int i = 1; i < resA.length; i++) {
			for(int j = 1; j < resA[0].length; j++) {
				if(arr[i][j] == 1) {
					if(resA[i-1][j].horizontal) {
						
					}
				} else {
					resA[i][j]  = new Entry(0,0);
				}
			}
		}
		
		System.out.println(Arrays.deepToString(resA));
		
		return 0;
	}
	


}
