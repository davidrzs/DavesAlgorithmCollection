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




/*
 * 
 * WHY DOES THIS WORK
 * 
 * since there are n threads and n levels we can move to the next level 
 *  when we are not the victim anymore or there are no threads ahead of me.
 */

// TODO: explanations need revision



/**
 * implemented as seen in the lecture, slightly adapted
 */
public class FilterLock implements Lock {

	
	AtomicIntegerArray levels;
	AtomicIntegerArray victims;
	
	int nrOfLevels;
	
	
	FilterLock(int nrOfThreads){
		nrOfLevels = nrOfThreads;
		levels = new AtomicIntegerArray(nrOfThreads);
		victims = new AtomicIntegerArray(nrOfThreads);	
	}
	
	
	@Override
	public void lock() {
		int myId = Integer.parseInt(Thread.currentThread().getName());
		for(int i = 1; i < nrOfLevels ; i++) {
			levels.set(myId, i);
			victims.set(i, myId);
			
            for (int k = 0; k < nrOfLevels; k++) {
                while ((k != myId) && (levels.get(k) >= i && victims.get(i) == myId)) {
                	// wait spinning
                }
				
			}
		}
	}


	@Override
	public void unlock() {
		int myId = Integer.parseInt(Thread.currentThread().getName());
		levels.set(myId, 0);
	}

}
