/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package advancedParallelSynchronizationMechanisms;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class SemaphoreBarrier {	
	
	Semaphore barrier;
	Semaphore mutex;
	volatile int count = 0;
	int n;
	
	
	
	
	SemaphoreBarrier(int n){
		this.barrier = new Semaphore(0);
		this.mutex = new Semaphore(1);
		this.n = n;
	}


	/**
	 * This implementation uses the concepts we have seen in the lecture.
	 * @throws InterruptedException
	 */
	void awaitSemaphore() throws InterruptedException {
		mutex.acquire();
		count++;
		if(count == n) {
			barrier.release();
		}
		mutex.release();
		
		// turnstyle -> only one can go through at a time
		barrier.acquire();
		barrier.release();
		
		mutex.acquire();
		count--;
		if(count==0) {
			barrier.acquire();
		}
		mutex.release();
	}
	
}
