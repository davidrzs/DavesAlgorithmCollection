
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;


public class HikingBackpacks {
	// the task is to split the items such that they can be packed into two bags where every item is in a bag but the weight difference between the two bags is minimal.

	public static void main(String[] args) {
		int[] items = new int[] {4,5,9,9,21,13};
		System.out.println(splitItemsFairly(items));
	}
	
	public static List<Integer> splitItemsFairly(int[] items){
		int totalSum = 0;
		for(int i = 0; i < items.length; i++) {
			totalSum += items[i];
		}
		
		boolean[][] dp = new boolean[items.length][totalSum+1];
		int[][] predecessor = new int[items.length][totalSum+1];
		
		//init first row:
		for(int ss = 0; ss < totalSum+1;ss++) {
			if (ss == items[0]) {
				dp[0][ss] = true;
			}
			predecessor[0][ss] = -1;
		}
		
		// weight of zero can also be achieved for every element:
		for(int j = 0; j < items.length; j++) {
			dp[j][0] = true;
		}
		
		for(int itemNr = 1; itemNr < items.length; itemNr++) {
			for(int ss = 0; ss < totalSum+1;ss++) {
				
				int currentItemWeight = items[itemNr];
				
				if(currentItemWeight <= ss) {
					if(dp[itemNr-1][ss]) {
						dp[itemNr][ss] = true;
						predecessor[itemNr][ss] = -1;
					} else if(dp[itemNr-1][ss-currentItemWeight]) {
						dp[itemNr][ss] = true;
						predecessor[itemNr][ss] = itemNr-1;
					}
				} else {
					if(dp[itemNr-1][ss]) {
						dp[itemNr][ss] = true;
						predecessor[itemNr][ss] = -1;
					}
				}

			}
		}
		System.out.println(Arrays.deepToString(dp));
		System.out.println(Arrays.deepToString(predecessor));
		System.out.println(Arrays.toString(dp[items.length-1]));
		
		//find fair solution:
		int multiplier = 1;
		int adder = 1;
		int index = totalSum / 2;
		int middle = totalSum / 2;
		while(index >= 0 && index <= totalSum) {
			if(dp[items.length-1][index] == true) {
				System.out.println("Fair division at " + index + " with totalsum of " + totalSum);
				break;
			}
			index = middle + multiplier*adder;
			multiplier *= -1;
			if(multiplier == 1) {
				adder++;
			}
		}
		//reconstruct solution:
		LinkedList<Integer> bag1 = new LinkedList<Integer>();
		int i = items.length-1;
		int w = index;
		while(i>= 0 && w >= 0) {
			if(predecessor[i][w] == -1) {
				i--;
			} else {
				int prev = predecessor[i][w];
				bag1.add(i);
				i = prev;
			}
		}
		System.out.println(bag1);
		return bag1;
	}

}
