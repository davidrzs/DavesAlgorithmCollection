import java.util.Arrays;

public class MinimalEditingDistance {
	public static void main(String[] args) {
		System.out.println(minimalEditingDistance("Ziege", "Tiger"));
		
		//DNA mutation example from http://www.bioinformatics.org/sms2/mutate_dna.html
		
		System.out.println(minimalEditingDistance("atgtctgattcgctaaatcatccatcgagttctacggtgcatgcagatgatggattcgag\r\n" + 
				"ccaccaacatctccggaagacaacaacaaaaaaccgtctttagaacaaattaaacaggaa\r\n" + 
				"agagaagcgttgtttacggatctattcgcagatcgtcgacgaagcgctcgttctgtgatt\r\n" + 
				"gaagaagctttccaaaacgaactcatgagtgctgaaccagtccagccaaacgtgccgaat\r\n" + 
				"ccacattcgattcccattcgtttccgtcatcaaccagttgctggacctgctcatgatgtt\r\n" + 
				"ttcggagacgcggtgcattcaatttttcaaaaaataatgtccagaggagtgaacgcggat\r\n" + 
				"tatagtcattggatgtcatattggatcgcgttgggaatcgacaaaaaaacacaaatgaac\r\n" + 
				"tatcatatgaaaccgttttgcaaagatacttatgcaactgaaggctccttagaagcgaaa\r\n" + 
				"caaacatttactgataaaatcaggtcagctgttgaggaaattatctggaagtccgctgaa\r\n" + 
				"tattgtgatattcttagcgagaagtggacaggaattcatgtgtcggccgaccaactgaaa\r\n" + 
				"ggtcaaagaaataagcaagaagatcgttttgtggcttatccaaatggacaatacatgaat\r\n" + 
				"cgtggacagagtgacatttcacttcttgcggtgttcgatgggcatggcggacacgagtgc\r\n" + 
				"tctcaatatgcagctgctcatttctgggaagcatggtccgatgctcaacatcatcattca\r\n" + 
				"caagatatgaaacttgacgaactcctagaaaaggctctagaaacattggacgaaagaatg\r\n" + 
				"acagtcagaagtgttcgagaatcttggaaaggtggaaccactgctgtctgctgtgctgtt\r\n" + 
				"gatttgaacactaatcaaatcgcatttgcctggcttggagattcaccaggttacatcatg\r\n" + 
				"tcaaacttggagttccgcaaattcactactgaacactccccgtctgacccggaggaatgt\r\n" + 
				"cgacgagtcgaagaagtcggtggccagatttttgtgatcggtggtgagctccgtgtgaat\r\n" + 
				"ggagtactcaacctgacgcgagcactaggagacgtacctggaagaccaatgatatccaac\r\n" + 
				"aaacctgataccttactgaagacgatcgaacctgcggattatcttgttttgttggcctgt\r\n" + 
				"gacgggatttctgacgtcttcaacactagtgatttgtacaatttggttcaggcttttgtc\r\n" + 
				"aatgaatatgacgtagaagattatcacgaacttgcacgctacatttgcaatcaagcagtt\r\n" + 
				"tcagctggaagtgctgacaatgtgacagtagttataggtttcctccgtccaccagaagac\r\n" + 
				"gtttggcgtgtaatgaaaacagactcggatgatgaagagagcgagctcgaggaagaagat\r\n" + 
				"gacaatgaatag", "atgtctgattcgctaaatcatccatcgagttctacggtgcatgcagatgatggattcgag\r\n" + 
						"ccaccaacatctccggaagacaacaacaaaaaatcgtctttagaacaaattaaacaggaa\r\n" + 
						"agagaagcgttgtttacggatctattcgcagatcgtggacgaagcgctcgttctgtgatt\r\n" + 
						"gaagaagctttccaaaccgaactcatgagtgctgaaccagtccagccaaacgtgccgaat\r\n" + 
						"ccacattcgattcccattcgtttccgtcatcaaccagttgctggacctgctcatgatgtt\r\n" + 
						"ttcggagacgcggtgcattcaatttttcaaaaaataatgtccagaggagtgaacgcggat\r\n" + 
						"tatagtcattggatgtcatattggatcgcgttgggaatcgacaaaaaaacacaaatgaac\r\n" + 
						"tatcatatgaaaccgttttgcaaagatacttatgcaactgaaggctccttagaagcgaaa\r\n" + 
						"caaacatttactgataaaatcaggtcagctgttgaggaaattatctggaagtccgctgaa\r\n" + 
						"tattgtgatattcttagcgaaaagtggacaggaattcatgtgtcggccgaccaactgaaa\r\n" + 
						"ggtcaaagaaataagcaagaagatcgttttgtggcttatccaaatggacaatacctgaat\r\n" + 
						"cgtggacagagtgacatttcacttcttgcggtgttcgatgggcatggcggacacgagagc\r\n" + 
						"tctcaatatgcagctgctcatttctgggaagcatggtccgatgctcaacatcatcattca\r\n" + 
						"caagatatgaaacttgacgaactcctagaaaaggctctagtaacattggacgaaagaatg\r\n" + 
						"acagtcagaagtgttcgagaatcttggaaaggtggaaccactgctgtctgctgtgctgtt\r\n" + 
						"gatttgaacactaatcaaatcgcatttgcctggcttggagattcaccaggttacatcatg\r\n" + 
						"tcaaacttggagttccgcaaattcactactgaacactccccgtctgaccaggaggaatgt\r\n" + 
						"cgacgagtcgaagaagtcggtggccagagttttgtgatcggtggtgagctccgtgtgaat\r\n" + 
						"ggagtactcaacctgacgcgagcactaggagacgtacctggaagaccaatgatatccaac\r\n" + 
						"aaacctgataccttactgaagacgatcgaacctgcggattatcttgttttgttggcctgt\r\n" + 
						"gacgggatttctgacgtcttcaacactagtgatttgtacaatttggttcaggcttttgtc\r\n" + 
						"aatgaatatgacgtagaagattatcacgaacttgcacgctacatttgcaatcaagcagtt\r\n" + 
						"tcagctggaagtgctgacaatgtgacagtagttataggtttcctccgtccaccagaagac\r\n" + 
						"gtttggcctgtaatgaaaacagactcggatgatgaagagagcgagctcgaggaagaagat\r\n" + 
						"gacaatgaatag"));

	}
	
	public static int minimalEditingDistance(String a, String b) {
		char[] ac = a.toCharArray();
		char[] bc = b.toCharArray();
		
		int[][] dp = new int[ac.length+1][bc.length+1];
		
		// now we can initialize it:
		for(int i = 0; i < ac.length+1; i++) {
			dp[i][0] = i;
		}
		
		for(int i = 0; i < bc.length+1; i++) {
			dp[0][i] = i;
		}
		
		//now we can fill our dp table:
		
		for(int i = 1; i < ac.length+1; i++) {
			for(int j = 1; j < bc.length+1; j++) {
				int adder = ac[i-1] == bc[j-1] ? 1 : 0;
				//System.out.println(adder);
				int minPrev =  min(min(dp[i-1][j]+1,dp[i][j-1]+1),dp[i-1][j-1] +1 - adder);
				dp[i][j] = minPrev;
			}
		}
		
		//System.out.println(Arrays.deepToString(dp));
		return dp[ac.length][bc.length];
	}
	
	public static int min(int a, int b) {
		return a > b ? b : a;
	}
	
}
