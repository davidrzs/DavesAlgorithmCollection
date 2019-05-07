import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Random;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

class TestSkipList {

	// 	@Test
	void dummyTest() {
		SkipList skl = new SkipList();
		System.out.println(skl);
		skl.add(12);
		skl.add(1);
		skl.add(1234);
		skl.add(-213663);
		System.out.println(skl);
	}
	
	@Test
	void test() {
		int numberOfIterations = 10000;
		
		SkipList SLBench = new SkipList(15);
		LinkedList<Integer> LLBench = new LinkedList<Integer>();

		
		Random rand1 = new Random(42);
		long startSkipList = System.currentTimeMillis();
		for(int i = 0; i < numberOfIterations;i++) {
				SLBench.add(rand1.nextInt());
		}
		long endSkipList = System.currentTimeMillis();
		System.out.println("It took the SkipList " + (endSkipList-startSkipList) + " miliseconds");
		
		Random rand2 = new Random(42);
		long startLinkedList = System.currentTimeMillis();
		for(int i = 0; i < numberOfIterations;i++) {
			LLBench.add(rand2.nextInt());
		}
		Collections.sort(LLBench);
		long endLinkedList = System.currentTimeMillis();
		System.out.println("It took the LinkedList " + (endLinkedList-startLinkedList) + " miliseconds");
		Iterator<Integer> it = LLBench.iterator();
		
		int ctr = 0;
		while(it.hasNext()) {
			assertEquals(((int)it.next()), SLBench.get(ctr), "nooo");
			ctr++;
		}
		
	}
	
	
	@Test
	void benchmarkTest() {
		int numberOfIterations = 50000;

		for(int m = 1; m < 15; m++) {
			SkipList SLBench = new SkipList(m);
			
			Random rand1 = new Random(42);
			long startSkipList = System.currentTimeMillis();
			for(int i = 0; i < numberOfIterations;i++) {
					SLBench.add(rand1.nextInt());
			}
			long endSkipList = System.currentTimeMillis();
			System.out.println("It took the SkipList with " + m + " levels " + (endSkipList-startSkipList) + " miliseconds");
		}
	}

}
