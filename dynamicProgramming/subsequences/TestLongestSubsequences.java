package subsequences;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestLongestSubsequences {

	@Test
	void testDescendingSubsequence() {
		int[] sequence = new int[] {8, 14, 16, 3, 4, 6, 7, 5, 18, 9, 1};
		List<Integer> listA = LongestDescendingSubsequence.longestDescendingSubsequence(sequence);
		List<Integer> listB = new LinkedList<Integer>();
		listB.add(16);
		listB.add(7);
		listB.add(5);
		listB.add(1);
		System.out.println(listA.toString());
		assertTrue(listA.containsAll(listB) && listB.containsAll(listA));
	}

}
