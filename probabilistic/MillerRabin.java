import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.*;

public class MillerRabin {

    static SecureRandom rand = new SecureRandom(); 

    
    public static void main(String[] args) {
    	isProbablyPrime(23);
    }
    
	public static boolean isProbablyPrime(int nr) {
		int trues = 0;
		int falses = 0;
		for(int i = 0; i < 10; i++) {
			if(millerRabin(nr)) {
				trues++;
			}else {
				falses++;
			}
		}
		System.out.println("Testing " + nr + " with " + trues + " trues " + falses +" falses");
		return trues <= falses ? false : true;
	}
	
	/**
	 * implements the Miller-Rabin primality test
	 */
	public static boolean millerRabin(int n) {
		if(n == 2) {
			return true;
		} else if(n<=1 || n%2==0) {
			return false;
		}
		// sample a random a number from [2,n-1]
		int a = rand.nextInt(n-2)+2; 
		// calculate k and d
		int k = 0;
		while((n-1)%Math.pow(2, k+1) == 0) {
			k++;
		}
		int d = (int) ((n-1 )/ Math.pow(2, k));
		assertTrue ((n-1) == d*Math.pow(2, k));
		// it is vital we use the modPow function since a normal Math.pow and then mod will result in roudning errors.
		int x = modPow(a,d,n);

		if(x == 1 || x == n-1) {
			return true;
		}
		for(int i = 0; i < k;i++) {
			x = (x*x)%n;
			if(x==1) {
				return false;
			}
			if(x==n-1) {
				return true;
			}
		}
		return false;
	}
	
	public static int modPow(int base, int exponent, int modulus) {
		int retVal = 1;
		
		for(int i = 1; i <= exponent; i++) {
			retVal *= base;
			retVal = retVal % modulus;
		}
		return retVal;
	}

}
