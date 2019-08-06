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

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * @author David
 *
 */
class TestFilter {

	@Test
	void testSmallerThan() {
		int[] arr = new int[] {1,2,3,4,5,6,7,6,5,4,3,2,1};
		
		System.out.println(Arrays.toString(Filter.smallerThan(arr, 4)));
		assertTrue(Arrays.equals(new int[] {1,2,3,3,2,1}, Filter.smallerThan(arr, 4)));
	}

}
