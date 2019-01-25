package subsequences.secondImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestHelpers {
static int[] arrayToSearch = new int[] {1,3,4,5,6,9,23,45,46,47,48,49,50,51,52,53,54,55,999};
	

	@Test
	void testBinarySearch() {
		assertEquals(4,LongestAscendingSubsequence2.binarySearch(arrayToSearch, 6,arrayToSearch.length));
		assertEquals(1,LongestAscendingSubsequence2.binarySearch(arrayToSearch, 1,arrayToSearch.length));
		assertEquals(18,LongestAscendingSubsequence2.binarySearch(arrayToSearch, 999,arrayToSearch.length));
		assertEquals(5,LongestAscendingSubsequence2.binarySearch(arrayToSearch, 7,arrayToSearch.length));
		assertEquals(5,LongestAscendingSubsequence2.binarySearch(arrayToSearch, 8,arrayToSearch.length));
		assertEquals(7,LongestAscendingSubsequence2.binarySearch(arrayToSearch, 24,arrayToSearch.length));
	}
	
	
	
}
