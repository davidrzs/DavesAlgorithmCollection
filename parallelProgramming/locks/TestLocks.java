package locks;
import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;

/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */

/**
 * @author david
 *
 */

import java.util.concurrent.locks.*;

class TestLocks {

	volatile int resultPeterson;
	
	@Test
	void testPeterson() throws InterruptedException {
		// init
		int sumTill = 1000;
		resultPeterson = 0;
		Lock l1 = new PetersonLock();
		
		ReentrantLock l2 = new ReentrantLock();
	
		Thread t1 = new Thread(()-> {
			for(int i = 0; i < sumTill; i++) {
				l1.lock();
				
				int oldVal = resultPeterson;
				resultPeterson+=1;
				// we have this while loop to give the volatile update some time.
				while(resultPeterson < oldVal+1) {
				}
				
				l1.unlock();
			}
		});
		
		// set the appropriate names
		t1.setName("1");
		Thread.currentThread().setName("0");

		// start the action
		t1.start();

		
		for(int i = 0; i < sumTill; i++) {
			l1.lock();
			int oldVal = resultPeterson;
			resultPeterson+=1;
			while(resultPeterson < oldVal+1) {
			}
			l1.unlock();

		}
		
			
		t1.join();
		
		assertEquals(sumTill*2,resultPeterson);
		
	}

}
