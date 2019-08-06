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
public class MilkDistributionProblem {


	public static void main(String[] args) {
		double[][] p = new double[][] {{0.6,0.0,0.4},
									   {0.5,0.1,0.4},
									   {0.4,0.3,0.3}};
		double M = 100;
		double[] cost = new double[] {4,3,2,1,0,1,2,3,4};
		
//		System.out.println(maximizeProfit(p, 50));
		System.out.println(maximizeProfitRecursively(0,2, p, 50,200,6,3));
	}
	
	/**
	 * 
	 * @return cost of parking
	 */
	public static double maximizeProfit(double[][] p, double refundPerLiter, int totalLiters) {
		int nrOfStores = p.length;
		int nrLitersPerStore = p[0].length;
		
		double[][] DP = new double[totalLiters][totalLiters];
		
		
		
		return 0;
		
		
	}
	
	public static double maximizeProfitRecursively(int currentStore, int maxStore, double[][] p, double refundPerLiter, double revenuePerLiter, int litersLeft, int maxMilkPerStore) {
		if(litersLeft == 0) {
			return 0;
		}
		
		// check if we are the last store
		
		if(currentStore == maxStore) {
			if(litersLeft>maxMilkPerStore) {
				return 0;
			}
		
			double probabilityOfSelling = p[currentStore][litersLeft-1];
			System.out.println(currentStore + " " +  litersLeft +" "+ probabilityOfSelling +" " + (probabilityOfSelling*litersLeft*revenuePerLiter + (1-probabilityOfSelling)*litersLeft*refundPerLiter));

			return probabilityOfSelling*litersLeft*revenuePerLiter + (1-probabilityOfSelling)*litersLeft*refundPerLiter;
		}
		double tempMax = 0;
		for(int i = 1; i <= maxMilkPerStore; i++) {
			double probabilityOfSelling = p[currentStore][i-1];
			double thisMilk = (probabilityOfSelling*i*revenuePerLiter + (1-probabilityOfSelling)*i)*refundPerLiter + maximizeProfitRecursively(currentStore+1, maxStore,p, refundPerLiter, revenuePerLiter, litersLeft-i, maxMilkPerStore);
			if(thisMilk > tempMax) {
				tempMax = thisMilk;
			}
		}
		System.out.println(currentStore + " " + litersLeft + " " + tempMax);
		return tempMax;
		
	}

}
