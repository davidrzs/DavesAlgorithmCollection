/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */

package advancedParallelSynchronizationMechanisms;


public class Semaphore {

	volatile int count;
	
	
	public Semaphore(int count){
		this.count = count;
	}
	
	
	public synchronized void acquire() throws InterruptedException {
		while(count == 0) {
			wait();
		}
		count--;
	}
	
	
	public synchronized void release() {
		count++;
		notifyAll();
	}
	
}
