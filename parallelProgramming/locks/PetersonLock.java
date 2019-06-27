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

/**
 * Implements the PetersonLock for two threads as seen in the lecture.
 * 
 * Since we cannot control the ID's of the threads we just give them the names "0" and "1", highly inefficient but still god for the learning experience.
 */
public class PetersonLock implements Lock {
	
	final static int TRUE = 1;
	final static int FALSE = 0;
	
	// we cannot use a volatile array since we then only the address is volatile not the contents. Using atomicarray is konda pointless since they already have their won synchronization
	private AtomicIntegerArray wantToEnterCriticalSection = new AtomicIntegerArray(2);
	private volatile int iWillWait;
	
	@Override
	public void lock() {
		int myId = Integer.parseInt(Thread.currentThread().getName());
		
		// signal that we would like to enter the critical section
		wantToEnterCriticalSection.set(myId, TRUE);
		// lets tell the other thread that we are prepared to wait.
		iWillWait = myId;
		// as long as the other wants to go and i am the one who is waiting i wont enter
		while(wantToEnterCriticalSection.get(1-myId) == TRUE && iWillWait == myId) {
			// we wait
		}
	
	}


	@Override
	public void unlock() {
		int myId = Integer.parseInt(Thread.currentThread().getName());
		// we are not interesting in entering anymore -> the other thread can go ahead
		wantToEnterCriticalSection.set(myId, FALSE);
	}

}
