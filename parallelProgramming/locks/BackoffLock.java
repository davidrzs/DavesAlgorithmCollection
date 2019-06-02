/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package locks;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author david
 *
 */
public class BackoffLock implements Lock {

	AtomicBoolean locked = new AtomicBoolean(false);
	Backoff backoff = new Backoff(1,10);
	
	@Override
	public void lock() {
		while(true) {
			while(locked.get()) {
				// spin while it is locked				
			}
			// it unlocked -> lets try to get it:
			if(!locked.getAndSet(true)) {
				return;
			} else {
				try {
					backoff.backoff();
				} catch (InterruptedException e) {
					System.out.println("since this is just for training we don't care about errors.");
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void unlock() {
		locked.set(false);
	}

}
