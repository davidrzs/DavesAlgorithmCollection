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



class TestLocks {

	// we must define them here because we cannot define volatile vars in methods
	volatile int resultPeterson;
	volatile int resultFilter;

	@Test
	void testPeterson() throws InterruptedException {
		// init
		int sumTill = 1000;
		resultPeterson = 0;
		Lock l1 = new PetersonLock();
		
	
		Thread t1 = new Thread(()-> {
			for(int i = 0; i < sumTill; i++) {
				l1.lock();
				
				int oldVal = resultPeterson;
				resultPeterson+=1;
				// we have this while loop to give the volatile update some time.
				while(resultPeterson < oldVal+1) {
					System.out.println("fuck");
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
	
	
	@Test
	void testFilter() throws InterruptedException {
		// init
		int sumTill = 1000;
		int nrOfThreads = 15;
		
		resultFilter = 0;
		Lock l1 = new FilterLock(nrOfThreads);
		
		
		Thread[] th = new Thread[nrOfThreads];
	
		for(int k = 0; k < nrOfThreads; k++) {
			th[k] = new Thread(()-> {
				

				for(int i = 0; i < sumTill; i++) {
					l1.lock();
					
					int oldVal = resultFilter;
					resultFilter+=1;
					// we have this while loop to give the volatile update some time.
					while(resultFilter < oldVal+1) {
					}
					
					l1.unlock();				
				}
			});
			
			th[k].setName(Integer.toString(k));
		}
		
	
		for(int k = 0; k < nrOfThreads; k++) {
			th[k].start();
		}
		
	
		
		for(int k = 0; k < nrOfThreads; k++) {
			th[k].join();
		}
		
		
		assertEquals(sumTill*nrOfThreads,resultFilter);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
