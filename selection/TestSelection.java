import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

class TestSelection {

	static int[] testSet1 = new int[] {-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10};
	static int[] testSet2 = new int[] {1,4,2,-3,3,-9,-10,10,8,-1,-4,1,-7,6,0};
	static int[] sortedSet2 = testSet2.clone();
	
	int[] getTestSet2() {
		return new int[] {1,4,2,-3,3,-9,-10,10,8,-1,-4,1,-7,6,0};
	}
	
	static{
		Arrays.sort(sortedSet2);
	}
	
	
	
	@Test
	void testQuickSelect() {
		Random rnd = new Random();
		for(int i = 0; i < 100000;i++) {
			int kThSmallest = rnd.nextInt(testSet2.length-1)+1;
			assertEquals(sortedSet2[kThSmallest-1], RandomQuickSelect.quickSelect(getTestSet2(),kThSmallest), "failed: " + kThSmallest + "-smallest element");			
		}
	}

}
