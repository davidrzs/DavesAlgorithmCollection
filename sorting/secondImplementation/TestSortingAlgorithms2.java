package secondImplementation;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class TestSortingAlgorithms2 {

	static int[] referenceSolution = new int[] {-21,-3,0,0,1,2,2,4,4,4,5,6,6,12,91,99};
	
	@Test
	void testQuickSort() {
		int[] arrayToBeSorted = new int[] {5,2,99,12,6,4,4,91,-21,0,0,-3,1,6,4,2};
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(QuickSort.quickSort(arrayToBeSorted)));
		assertEquals(Arrays.toString(new int[] {-1,0,1,2,3,4,5}),Arrays.toString(QuickSort.quickSort(new int[] {5,4,2,3,0,-1,1})));
	}
	
	@Test
	void testHeapSort() {
		int[] arrayToBeSorted = new int[] {5,2,99,12,6,4,4,91,-21,0,0,-3,1,6,4,2};
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(HeapSort.heapSort(arrayToBeSorted)));
	}	
	@Test
	void testInsertionSort2() {
		int[] arrayToBeSorted = new int[] {5,2,99,12,6,4,4,91,-21,0,0,-3,1,6,4,2};
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(InsertionSort2.insertionSort(arrayToBeSorted)));
		assertEquals(Arrays.toString(new int[] {-1,0,1,2,3,4,5}),Arrays.toString(InsertionSort2.insertionSort(new int[] {5,4,2,3,0,-1,1})));
	}	
	
	@Test
	void testQuickSort2() {
		int[] arrayToBeSorted = new int[] {5,2,99,12,6,4,4,91,-21,0,0,-3,1,6,4,2};
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(QuickSort2.recQuickSort(arrayToBeSorted,0,arrayToBeSorted.length-1)));
		assertEquals(Arrays.toString(new int[] {-1,0,1,2,3,4,5}),Arrays.toString(QuickSort2.recQuickSort(new int[] {5,4,2,3,0,-1,1},0,6)));
	}

}
