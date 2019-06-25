import java.util.Random;
import java.util.function.Function;

public class TargetShooting {
	
	
	/**
	 * this function approximates the value of the integral of the function f with a left and right boundary
	 */
	
	public static double approximateIntegral(Function<Double , Double> f, double leftBoundary, double rightBoundary) {
		Random r = new Random();
		double sum = 0;
		int N = 100000;
		for(int i = 0; i < N;i++) {
			sum+= f.apply(r.nextDouble()*(rightBoundary-leftBoundary) + leftBoundary);
		}
		return (sum / N)*(rightBoundary-leftBoundary);
	}
	
	public static double targetPIShooting(int iterations) {
		Random r = new Random();
		
		double pi = 0;
		
		for(int i = 0; i < iterations; i++) {
			double sampleX = r.nextDouble()*2 -1; // to make it equally distributed within [-1,1]
			double sampleY = r.nextDouble()*2 -1; // to make it equally distributed within [-1,1]
			if(sampleX*sampleX + sampleY*sampleY <= 1) {
				pi++;
			}
		}
		
		return 4*pi/iterations;
		
	}
	
}
