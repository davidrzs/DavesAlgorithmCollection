/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package locks;

/**
 * Implements the PetersonLock for two threads as seen in the lecture.
 * 
 * Since we cannot control the ID's of the threads we just give them the names "0" and "1", highly inefficient but still god for the learning experience.
 */
public class PetersonLock implements Lock {

	// we cannot use a volatile array since we then only the address is volatile not the contents. Using atomicarray is konda pointless since they already have their won synchronization
	private volatile boolean iWannaEnterCriticalSection0 = false;
	private volatile boolean iWannaEnterCriticalSection1 = false;
	private volatile int iWillWait;
	
	@Override
	public void lock() {
		int myId = Integer.parseInt(Thread.currentThread().getName());
		//System.out.println(myId);

		if(myId == 0) {
			// signal that we would like to enter the critical section
			iWannaEnterCriticalSection0 = true;
			// lets tell the other thread that we are prepared to wait.
			iWillWait = myId;
			// as long as the other wants to go and i am the one who is waiting i wont enter
			while(iWannaEnterCriticalSection1 && iWillWait == myId) {
				// we wait
			}		
		} else {
			// signal that we would like to enter the critical section
			iWannaEnterCriticalSection1 = true;
			// lets tell the other thread that we are prepared to wait.
			iWillWait = myId;
			// as long as the other wants to go and i am the one who is waiting i wont enter
			while(iWannaEnterCriticalSection0 && iWillWait == myId) {
				// we wait
			}			
		}
	
	}

	/* (non-Javadoc)
	 * @see locks.Lock#unlock()
	 */
	@Override
	public void unlock() {
		int myId = Integer.parseInt(Thread.currentThread().getName());
		if(myId == 0) {
			iWannaEnterCriticalSection0 = false;			
		}else {
			iWannaEnterCriticalSection1 = false;			
		}
	}

}
