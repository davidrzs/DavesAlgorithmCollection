package locks;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
	int sumTill = 10000;
	int nrOfThreads = 10;
	// we must define them here because we cannot define volatile vars in methods
	volatile int resultPeterson;
	volatile int resultFilter;
	volatile int resultBakery;
	volatile int resultTAS;
	volatile int resultTTAS;
	volatile int resultBackoff;
	
	@Test
	void testPeterson() throws InterruptedException {
		// init
		resultPeterson = 0;
		Lock l1 = new PetersonLock();
		
	
		Thread t1 = new Thread(()-> {
			for(int i = 0; i < sumTill; i++) {
				l1.lock();
				try {
					resultPeterson+=1;
				} finally {
					l1.unlock();					
				}
			}
		});
		
		// set the appropriate names
		t1.setName("1");
		Thread.currentThread().setName("0");

		// start the action
		t1.start();

		
		for(int i = 0; i < sumTill; i++) {
			l1.lock();
			l1.lock();
			try {
				resultPeterson+=1;
			} finally {
				l1.unlock();					
			}
		}
		
		t1.join();
		assertEquals(sumTill*2,resultPeterson);
	}
	
	
	@Test
	void testFilter() throws InterruptedException {
		// init
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
	
	
	
	
	@Test
	void testBakery() throws InterruptedException {
		// init
		// better keep the number small to test
		
		resultBakery = 0;
		
		Lock l1 = new BakeryLock(nrOfThreads);
		
		
		Thread[] th = new Thread[nrOfThreads];
	
		for(int k = 0; k < nrOfThreads; k++) {
			th[k] = new Thread(()-> {
				

				for(int i = 0; i < sumTill; i++) {
					l1.lock();
					resultBakery+=1;
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
		
		
		assertEquals(sumTill*nrOfThreads,resultBakery);
	}
	
	
	
	@Test
	void testTAS() throws InterruptedException {
		// init
		// better keep the number small to test
		resultTAS = 0;
		
		Lock l1 = new TASLock();	
		
		Thread[] th = new Thread[nrOfThreads];
	
		for(int k = 0; k < nrOfThreads; k++) {
			th[k] = new Thread(()-> {

				for(int i = 0; i < sumTill; i++) {
					l1.lock();
					resultTAS+=1;
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
		
		assertEquals(sumTill*nrOfThreads,resultTAS);
	}
	
	@Test
	void testTTAS() throws InterruptedException {
		// init
		// better keep the number small to test
		resultTAS = 0;
		
		Lock l1 = new TTASLock();	
		
		Thread[] th = new Thread[nrOfThreads];
	
		for(int k = 0; k < nrOfThreads; k++) {
			th[k] = new Thread(()-> {

				for(int i = 0; i < sumTill; i++) {
					l1.lock();
					resultTAS+=1;
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
		
		assertEquals(sumTill*nrOfThreads,resultTAS);
	}
	
	
	
	
	@Test
	void testBackoff() throws InterruptedException {
		// better keep the number small to test
		resultBackoff = 0;
		
		Lock l1 = new BackoffLock();	
		
		Thread[] th = new Thread[nrOfThreads];
	
		for(int k = 0; k < nrOfThreads; k++) {
			th[k] = new Thread(()-> {

				for(int i = 0; i < sumTill; i++) {
					l1.lock();
					resultBackoff+=1;
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
		
		assertEquals(sumTill*nrOfThreads,resultBackoff);
	}
	
	
	
	
	

}
