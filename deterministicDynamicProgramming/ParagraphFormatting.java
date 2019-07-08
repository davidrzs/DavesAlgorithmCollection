/**
 * 
 * This program takes a list of words and a maximal line length and using dynamic programming determines what the least "loss" is.
 * Loss is defined as the square of all empty spaces needed to fill the line.
 * 
 * This was my homework submission for the Algorithms and Datastructures Class Homework 11 b).
 * It passed all tests and was within the given time limits.
 * 
 * @author david
 *
 */


public class ParagraphFormatting {

	public static void main(String[] args) {
		String[] text = new String[] {"Lorem", "ipsum", "dolor", "sit", "amet,", "consectetur", "adipiscing", "elit." };
		System.out.println(minimizeImbalance(text,18));
	}
	
	
	public static long minimizeImbalance(String[] text, int line_width) {
		long[] DP = new long[text.length+1];
		
		int[] wordLengths = wordLengthArray(text);
		
		//init
		DP[text.length] = 0;
		DP[text.length-1] = 0;
		//since last one doesn't cost anything
		
		DP[text.length-2] = 4;
		//now we can calculate the other entries.
		for(int i = text.length-2; i >= 0; i--) {
			int bestj = 0;
			//System.out.println("Currently at: " +i);
			long tempMin = Long.MAX_VALUE/2;
			int upperLimit = text.length;
			int curr_line_length=-1;
			int j = i;
			while(curr_line_length <= line_width && j < text.length) {
				curr_line_length = curr_line_length + 1 + wordLengths[j];
				//System.out.println("i: " + i + " j: " + j);
				long temp2 = DP[j+1] + cost(i,j,wordLengths,line_width);
				//System.out.println("cost: " + cost(i,j,wordLengths,line_width));
				if(temp2<tempMin) {
					tempMin = temp2;
					bestj = j;
					//System.out.println("updating cost with: " + temp2);
				}
				j++;
			}
			if(bestj == text.length-1) {
				DP[i] = 0;
			} else {
				DP[i] = tempMin;				
			}
		}
		
		return DP[0];
	}
	
	public static long cost(int i1, int i2, int[] wordLengths, int line_width) {
		int totallength = 0;
		for(int k = i1; k <= i2;k++) {
			totallength += wordLengths[k] + 1;
		}
		//since we do not have a space at the end
		totallength-=1;
		if(line_width < totallength) {
			return Long.MAX_VALUE/2;
		} else {
			return penalty(totallength, line_width);
		}
	}
	
	public static int penalty(int length, int line_width) {
		return (line_width - length)*(line_width - length);
	}
	
	public static int[] wordLengthArray(String[] text){
		int[] lengths = new int[text.length];
		for(int i = 0; i < text.length; i++) {
			lengths[i] = text[i].length();
		}
		return lengths;
	}

}
