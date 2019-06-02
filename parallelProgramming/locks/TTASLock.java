/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package locks;

import java.util.concurrent.atomic.*;


public class TTASLock implements Lock {

	
	AtomicBoolean locked = new AtomicBoolean(false);

	@Override
	public void lock() {
		while(true) {
			while(locked.get()) {
				// spin while it is locked				
			}
			// it unlocked -> lets try to get it:
			if(!locked.getAndSet(true)) {
				return;
			}
		}
	}

	@Override
	public void unlock() {
		locked.set(false);
	}

}
