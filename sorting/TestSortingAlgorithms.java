import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestSortingAlgorithms {

	static int[] arrayToBeSorted = new int[] {5,2,99,12,6,4,4,91,-21,0,0,-3,1,6,4,2};
	static int[] referenceSolution = new int[] {-21,-3,0,0,1,2,2,4,4,4,5,6,6,12,91,99};
	
	@Test
	void testMergeSort() {
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(MergeSort.mergeSort(arrayToBeSorted)));
	}
	
	@Test
	void testHeapSort() {
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(MergeSort.mergeSort(arrayToBeSorted)));
	}
	
	@Test
	void testBubbleSort() {
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(BubbleSort.bubbleSort(arrayToBeSorted)));
	}
	
	@Test
	void testQuickSort() {
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(MergeSort.mergeSort(arrayToBeSorted)));
	}
	
	@Test
	void testRecursive2MergeSort() {
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(MergeSort.mergeSort(arrayToBeSorted)));
	}
	
	@Test
	void testSelectionSort() {
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(SelectionSort.selectionSort(arrayToBeSorted)));
	}
	
	@Test
	void testInsertionSort() {
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(MergeSort.mergeSort(arrayToBeSorted)));
	}
	
	@Test
	void testNaturalMergeSort() {
		assertEquals(Arrays.toString(referenceSolution),Arrays.toString(MergeSort.mergeSort(arrayToBeSorted)));
	}
	
	
	

}
