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
 * Problem taken from https://www.math.cuhk.edu.hk/~wei/opextraondp2.pdf -> its the russian roulette one
 *
 */

public class MaximizeCasinoPayout {
	
	public static void main(String[] args) {
		double[] p = new double[] {0.3, 0.25, 0.2, 0.15, 0.1};
		System.out.println(maxmimizedPayout(p,p.length));
	}
	
	public static double maxmimizedPayout(double[] p, int rounds) {
		return 0;
	}
	
}
