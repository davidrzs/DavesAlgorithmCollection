
/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */

/**
 * Question taken from http://www.math.hkbu.edu.hk/~zirui-zhou/MATH3817/Handouts/prob_dp.pdf
 *
 */
public class ParkingProblem {

	public static void main(String[] args) {
		
		double[] probabilityEmpty = new double[] {0.5, 0.2, 0.1, 0.2, 0.5};
		double[] cost = new double[] {2,1,0,1,2};
		double notParkingPenalty = 10;
		System.out.println(solveParkingProblem(cost, probabilityEmpty,notParkingPenalty));
		
	}
	
	public static double solveParkingProblem(double[] cost, double[] probabilityIsEmpty, double notParkingPenalty) {
		
		double[][] DP = new double[cost.length][2];
		// initialization
		DP[cost.length-1][0] = cost[cost.length-1];
		DP[cost.length-1][1] = notParkingPenalty;
		
		
		for(int i = cost.length-2; i >= 0; i--) {
			double costOfNotParkingHere = probabilityIsEmpty[i+1]*DP[i+1][0] + (1-probabilityIsEmpty[i+1])*DP[i+1][1];
			// since there is no space here we must move on to the next one
			DP[i][1] = costOfNotParkingHere;
			DP[i][0] = Math.min(cost[i], costOfNotParkingHere);
		}

		return probabilityIsEmpty[0]*DP[0][0] +(1 - probabilityIsEmpty[0])*DP[0][1];
			
	}
	
	
	
}
