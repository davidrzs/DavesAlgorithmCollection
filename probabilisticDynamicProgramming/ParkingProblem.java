/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */



/*
 * 
 * 
 * Problem taken from https://www.math.cuhk.edu.hk/~wei/opextraondp2.pdf
 *
 */

public class ParkingProblem {

	public static void main(String[] args) {
		double[] p = new double[]{8/9.0, 6/7.0, 4/5.0, 2/3.0, 1/10.0, 2/3.0, 4/5.0, 6/7.0, 8/9.0};
		double M = 100;
		double[] cost = new double[] {4,3,2,1,0,1,2,3,4};
		
		System.out.println(solveParkingProblem(p, cost, M));
		
	}
	
	/**
	 * 
	 * @return cost of parking
	 */
	public static double solveParkingProblem(double[] p, double[] cost, double M) {
		int nrOfSpaces = cost.length;
		double[] DP = new double[nrOfSpaces];
		// DP[0] is the minimal cost before we enter the parking spot
		// DP[nrOfSpaces] is the cost after passing the last parking lot. (-> we pay penalty M and leave)
		DP[nrOfSpaces] = M;
		
		
		// return the minimal cost before looking at a single parking lot.
		return DP[0];
		
	}
	
	

}
