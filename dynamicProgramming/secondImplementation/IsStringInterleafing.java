package secondImplementation;


public class IsStringInterleafing {
	// Excercise taken from https://www.youtube.com/watch?v=ih2OZ9-M3OM . My own implementation though without looking at solution.
	public static void main(String[] args) {
		String a = "aab";
		String b = "axy";
		System.out.println(isStringInterleafing(b,a,"aaxaby"));
		System.out.println(isStringInterleafing(b,a,"abaaxy"));

	}
	public static boolean isStringInterleafing(String a, String b, String inter) {
		// we first convert them to character arrays:
		char[] ca = a.toCharArray();
		char[] cb = b.toCharArray();
		
		char[] ic = inter.toCharArray();
		
		
		boolean DP[][] = new boolean[ca.length+1][cb.length+1];
		
		// initialize the table:
		DP[0][0] = true;
		for(int i = 1; i < ca.length; i++) {
			DP[i][0] = (ic[i-1] == ca[i-1] && DP[i-1][0] == true) ? true : false;
		}

		for(int j = 1; j < cb.length; j++) {
			DP[0][j] = (ic[j-1] == cb[j-1] && DP[0][j-1] == true) ? true : false;
		}
		
		// now we can fill the table
		for(int i = 1; i <= ca.length; i++) {
			for(int j = 1; j <= cb.length; j++) {
				boolean canTakeFromAbove = ic[i+j-1] == ca[i-1] && DP[i-1][j];
				boolean canTakeFromLeft = ic[i+j-1] == cb[j-1] && DP[i][j-1];
				DP[i][j] = canTakeFromAbove || canTakeFromLeft;
			}
		}
		
		
		return DP[ca.length][cb.length];
	}
	
}
