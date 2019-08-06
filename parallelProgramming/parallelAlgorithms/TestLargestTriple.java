/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelAlgorithms;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author David
 *
 */
class TestLargestTriple {

	static final int TEST_CASES = 10;
	static Random rand = new Random(System.currentTimeMillis());
	
	@Test
	void testLargestTriple() {
		
		for(int i = 0; i < TEST_CASES; i++) {
			int[] arr = new int[rand.nextInt(100)+3];
			for(int k = 0; k < arr.length; k++) {
				arr[k] = rand.nextInt(200);
			}
			assertEquals(LargestTriple.largestTripleSequential(arr), LargestTriple.largestTripleParallel(arr));
		}
	}
	

}
