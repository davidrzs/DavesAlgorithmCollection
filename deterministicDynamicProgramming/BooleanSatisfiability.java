
public class BooleanSatisfiability {

	public static enum Operations{
		AND, OR, XOR
	}
	
	// check if a boolean expression can be evaluated to true using parentheses.
	
	public static void main(String[] args) {
		boolean[] bArr = new boolean[] {true, false, true};
		Operations[] ops = new Operations[] {Operations.AND, Operations.XOR};
		System.out.println(isSatisfiable(bArr, ops)); 
	}
	
	public static boolean isSatisfiable(boolean[] arr, Operations[] ops) {
		return false;
	}

}
