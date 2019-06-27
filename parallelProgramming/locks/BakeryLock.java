/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package locks;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * implements Lamport's bakery lock as seen in the lecture
 *
 */
public class BakeryLock implements Lock {


	AtomicIntegerArray flag;
	AtomicIntegerArray label;
	//AtomicReferenceArray<Label> label;
	
	final int nrOfThreads;
	
	/**
	 * @param nrOfThreads
	 */
	public BakeryLock(int nrOfThreads) {
		this.nrOfThreads = nrOfThreads;
		flag = new AtomicIntegerArray(nrOfThreads);
		label = new AtomicIntegerArray(nrOfThreads);
	}


	@Override
	public void lock() {
		// get my thread id
		int myId = Integer.parseInt(Thread.currentThread().getName());
		// signal that we are interested in entering
		flag.set(myId,1);
		// our label is one bigger than the maximal label around
		label.set(myId, (findMaximalLabel()+1));
		// now we loop over all other threads and check for every thread if it has a label 
		// smaller than mine or if we have the same label its threadID is smaller than mine.
		// this defines a lexicographical ordering on the set of threads. Only if there is no thread
		// with a lower label or one of same label and lower threadID can we go ahead and enter 
		// the critical section
		for(int k = 0; k < nrOfThreads; k++) {
			while((k != myId) && flag.get(k) == 1 && (label.get(k) < label.get(myId)  || (label.get(k) == label.get(myId) && k < myId ))) {
				// spinning
			}
		}
	}

	
	@Override
	public void unlock() {
		int myId = Integer.parseInt(Thread.currentThread().getName());
		flag.set(myId, 0);
	}
	

	/**
	 * Returns the biggest label that has been assigned until now.
	 * @return maximal label found in labels array
	 */
	public int findMaximalLabel() {
		int max = 0;
		for(int i = 0; i < label.length(); i++) {
			if(label.get(i)> max) {
				max = label.get(i);
			}
		}
		return max;
	}
}



