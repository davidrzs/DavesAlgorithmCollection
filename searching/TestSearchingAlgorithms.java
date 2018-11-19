import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class TestSearchingAlgorithms {

	static int[] arrayToSearch = new int[] {1,3,4,5,6,9,23,45,46,47,48,49,50,51,52,53,54,55,999};
	
	@Test
	void testLinearSearch() {
		assertEquals(4,LinearSearch.linearSearch(arrayToSearch, 6));
		assertEquals(0,LinearSearch.linearSearch(arrayToSearch, 1));
		assertEquals(18,LinearSearch.linearSearch(arrayToSearch, 999));
		assertEquals(-1,LinearSearch.linearSearch(arrayToSearch, 7));
		assertEquals(-1,LinearSearch.linearSearch(arrayToSearch, 8));
		assertEquals(-1,LinearSearch.linearSearch(arrayToSearch, 24));
	}

}
