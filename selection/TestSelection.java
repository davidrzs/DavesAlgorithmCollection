import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSelection {

	int[] getTestSet() {
		return new int[] {-1,0,2,3,4,5,6,7,12,32,45};
	}
	
	@Test
	void testMedianOfMedians() {
		assertTrue(0 == MedianOfMedians.medianOfMedians(getTestSet(),4));
	}
	
	@Test
	void testQuickSelect() {
		assertTrue(0 == QuickSelect.quickSelect(getTestSet(),4));
	}

}
