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
	
	public static double targetPIShooting(double maxError) {
		return 0;
	}
	
}
