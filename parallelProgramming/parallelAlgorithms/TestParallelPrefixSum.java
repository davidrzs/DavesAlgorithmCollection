/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelAlgorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author David
 *
 */
class TestParallelPrefixSum {

	@Test
	void test() {
		ParallelPrefixSum pps = new ParallelPrefixSum(new int[] {1,1,3,1,1,6,1,1});
		assertEquals(15,pps.calculate()[7]);
		
		ParallelPrefixSum pps2 = new ParallelPrefixSum(new int[] {2,5,1,1,2,3,5,7,1});
		assertEquals(27,pps2.calculate()[8]);
		
	}

}
