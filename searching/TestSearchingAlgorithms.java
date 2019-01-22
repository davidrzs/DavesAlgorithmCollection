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

	
	@Test
	void testBinarySearch() {
		assertEquals(4,BinarySearch.binarySearch(arrayToSearch, 6));
		assertEquals(0,BinarySearch.binarySearch(arrayToSearch, 1));
		assertEquals(18,BinarySearch.binarySearch(arrayToSearch, 999));
		assertEquals(-1,BinarySearch.binarySearch(arrayToSearch, 7));
		assertEquals(-1,BinarySearch.binarySearch(arrayToSearch, 8));
		assertEquals(-1,BinarySearch.binarySearch(arrayToSearch, 24));
	}
	
	@Test
	void testBinarySearch2() {
		assertEquals(4,BinarySearch2.binarySearch(arrayToSearch, 6));
		assertEquals(0,BinarySearch2.binarySearch(arrayToSearch, 1));
		assertEquals(18,BinarySearch2.binarySearch(arrayToSearch, 999));
		assertEquals(-1,BinarySearch2.binarySearch(arrayToSearch, 7));
		assertEquals(-1,BinarySearch2.binarySearch(arrayToSearch, 8));
		assertEquals(-1,BinarySearch2.binarySearch(arrayToSearch, 24));
	}
	
	@Test
	void testExponentialSearch() {
		assertEquals(4,ExponentialSearch.exponentialSearch(arrayToSearch, 6));
		assertEquals(0,ExponentialSearch.exponentialSearch(arrayToSearch, 1));
		assertEquals(18,ExponentialSearch.exponentialSearch(arrayToSearch, 999));
		assertEquals(-1,ExponentialSearch.exponentialSearch(arrayToSearch, 7));
		assertEquals(-1,ExponentialSearch.exponentialSearch(arrayToSearch, 8));
		assertEquals(-1,ExponentialSearch.exponentialSearch(arrayToSearch, 24));
	}	
	
	@Test
	void testInterpolationSearch() {
		assertEquals(4,InterpolationSearch.interpolationSearch(arrayToSearch, 6));
		assertEquals(0,InterpolationSearch.interpolationSearch(arrayToSearch, 1));
		assertEquals(18,InterpolationSearch.interpolationSearch(arrayToSearch, 999));
		assertEquals(-1,InterpolationSearch.interpolationSearch(arrayToSearch, 7));
		assertEquals(-1,InterpolationSearch.interpolationSearch(arrayToSearch, 8));
		assertEquals(-1,InterpolationSearch.interpolationSearch(arrayToSearch, 24));
	}	
	
}
