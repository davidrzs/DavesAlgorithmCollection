import java.util.Arrays;

public class IsStringInterleafing {
	// Excercise taken from https://www.youtube.com/watch?v=ih2OZ9-M3OM . My own implementation though without looking at solution.
	public static void main(String[] args) {
		String a = "aab";
		String b = "axy";
		System.out.println(isStringInterleafing(a,b,"aaxaby"));
		System.out.println(isStringInterleafing(a,b,"abaaxy"));

	}
	
	static boolean isStringInterleafing(String a, String b, String interleafed) {
		boolean[][] DP = new boolean[a.length()+1][b.length()+1];

		// init table:
		DP[0][0] = true;
		
		for(int i = 0; i < a.length(); i++) {
			DP[0][i+1] = DP[0][i] && a.charAt(i) == interleafed.charAt(i);
		}
		
		for(int i = 0; i < b.length(); i++) {
			DP[i+1][0] = DP[i][0] && b.charAt(i) == interleafed.charAt(i);
		}

		for(int j = 1; j <= b.length(); j++) {
			for(int i = 1; i <= a.length(); i++) {
				//System.out.println(i + " " + " " + j + "   "+ a.charAt(i-1) + " " + b.charAt(j-1) +" "+ interleafed.charAt(i+j-1));
				boolean b1 = DP[j][i-1] && (a.charAt(rC(i)) == interleafed.charAt(i+j-1));
				boolean b2 = (DP[j-1][i] )&& (b.charAt(j-1) == interleafed.charAt(i+j-1));
				DP[j][i] = b1 || b2;
			}	
		}
		
		
		
		//System.out.println(Arrays.deepToString(DP));
		return DP[a.length()][b.length()];
	}
	
	static int rC(int i) {
		return i-1;
	}

}
