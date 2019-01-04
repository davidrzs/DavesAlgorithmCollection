package secondImplementation;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class TestSortingAlgorithms {

	static int[] referenceSolution = new int[] {-21,-3,0,0,1,2,2,4,4,4,5,6,6,12,91,99};
	
	@Test
	void testQuickSort() {
		int[] arrayToBeSorted = new int[] {5,2,99,12,6,4,4,91,-21,0,0,-3,1,6,4,2};
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(QuickSort.quickSort(arrayToBeSorted)));
	}
	
	@Test
	void testHeapSort() {
		int[] arrayToBeSorted = new int[] {5,2,99,12,6,4,4,91,-21,0,0,-3,1,6,4,2};
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(HeapSort.heapSort(arrayToBeSorted)));
	}	
	
	

}
