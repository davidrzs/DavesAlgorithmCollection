/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package advancedParallelSynchronizationMechanisms;


public class MonitorBarrier {

	volatile boolean draining = false;
	int nrOfThreads;
	volatile int counter;
	
	MonitorBarrier(int nrOfThreads){
		this.nrOfThreads = nrOfThreads;
	}
	
	
	synchronized void await() throws InterruptedException {
		
		// in case some threads arrive here while we are still draining
		while(draining) {
			wait();
		}
		
		// a thread just entered:
		counter++;
		
		// while we are not draining and not all threads are here we stop execution
		while(!draining && counter < nrOfThreads) {
			wait();
		}
		
		// the last thread just entered
		if(counter==nrOfThreads) {
			// set signal to draining
			draining = true;
			notifyAll();
		}
		
		// let's decrease the counter again
		counter--;
		
		if(counter == 0) {
			draining = false;
			notifyAll();
		}
		
		
	}
	
	
}
