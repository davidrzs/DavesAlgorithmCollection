/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelAlgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

/**
 * @author David
 *
 */
public class Filter {

	/**
	 * This function takes an array and removes all elements that are not smaller than the number.
	 * @param arrayToFilter
	 * @return filtered array
	 */
	static int[] smallerThan(int[] arrayToFilter, int number) {
		// this is to test the parallel prefix sum - the bit mask and mapping is still done sequentially .
		// we first apply a bitmask:
		
		int[] bitMask = new int[arrayToFilter.length];
		for(int i = 0; i < arrayToFilter.length; i++) {
			if(arrayToFilter[i] < number) {
				bitMask[i] = 1;
			}
		}
		
		System.out.println(Arrays.toString(bitMask));

		ParallelPrefixSum pps = new ParallelPrefixSum(bitMask);
		int[] mappingVector = pps.calculate();
		System.out.println(Arrays.toString(mappingVector));
		int[] target = new int[mappingVector[mappingVector.length-1]];
		for(int i = 0; i < arrayToFilter.length; i++) {
			if(bitMask[i] == 1) {
				target[mappingVector[i]-1] = arrayToFilter[i];
			}
		}
		return target;
		
	}
	
}
