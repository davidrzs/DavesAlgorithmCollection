import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

class TestTargetShooting {

	@Test
	void testPi() {
		double pi = TargetShooting.targetPIShooting(10000);
		System.out.println("Pi is: "+ pi);
		assertTrue(Math.abs(Math.PI - pi)<0.1 );
	}
	
	@Test
	void testIntegration() {
		Function<Double,Double> xLinear = (x) -> x;
		Function<Double,Double> xInverted = (x) -> 1/x;
		
		Function<Double,Double> xSquaredHalfed = (x) -> (x*x)/2;
		Function<Double,Double> Logx = (x) -> Math.log(x);
		
		double resultXLinearIntegrated = TargetShooting.approximateIntegral(xLinear, 0, 5);
		double xSquaredResult = xSquaredHalfed.apply(5.0) - xSquaredHalfed.apply(0.0);
		
		System.out.println("Approximation: " + xSquaredResult + " Real Solution: "+ resultXLinearIntegrated);
		//assertTrue(Math.abs(resultXLinearIntegrated - xSquaredResult) < 0.1);
		
		
		double resultXInverted = TargetShooting.approximateIntegral(xInverted, 1.0, 200.0);
		double xLogResult = Logx.apply(200.0) - Logx.apply(1.0);
		System.out.println("Approximation: " + xLogResult + " Real Solution: "+ resultXInverted);

		assertTrue(Math.abs(resultXInverted - xLogResult) < 0.1);

	}

}
