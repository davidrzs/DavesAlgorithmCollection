import java.util.Arrays;
import java.util.Stack;

public class LongestBalancedParensAndBrackets {

	public static void main(String[] args) {
		System.out.println(longestBalancedParensAndBrackets("[([][])[](([))]"));

	}
	
	/**
	 * This is not really a DP solution since my DP solution requires n^3 time to run whereas this stack based approach requires only n time to run.
	 * @param pb
	 * @return
	 */
	public static int longestBalancedParensAndBrackets(String pb) {
		char[] cArr = pb.toCharArray();
		
		Stack<Character> cStack = new Stack<Character>();
		
		int maxSoFar = 0;
		int currentCounter = 0;
		//initialize the stack
		
		int solStart=0;
		int solEnd=0;
		
		int bestSolStart=0;
		int bestSolEnd=0;
		
		cStack.push(cArr[0]);
		
		for(int i = 1; i < cArr.length; i++) {
			if((cArr[i] == ']' && cStack.peek() == '[') || (cArr[i] == ')' && cStack.peek() == '(')) {
				
				cStack.pop();
				
				currentCounter+=2;
				solEnd = i+1;
				if(currentCounter > maxSoFar) {
					maxSoFar = currentCounter;
					bestSolEnd = solEnd;
					bestSolStart = solStart;
				}
			}else if(cArr[i] == ']' || cArr[i] == ')'){
				currentCounter = 0;
				solStart = i+1;
			} else {
				cStack.push(cArr[i]);				
			}
		}
		System.out.println(Arrays.copyOfRange(cArr, bestSolStart, bestSolEnd));
		return maxSoFar;
	}

}
