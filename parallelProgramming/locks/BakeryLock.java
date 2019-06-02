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
		//label = new AtomicReferenceArray<Label>(nrOfThreads);
		flag = new AtomicIntegerArray(nrOfThreads);
		label = new AtomicIntegerArray(nrOfThreads);
	}


	@Override
	public void lock() {
		int myId = Integer.parseInt(Thread.currentThread().getName());
		// we would like to enter
		flag.set(myId,1);
		//label.set(myId, new Label(findMaximalLabel()+1));
		label.set(myId, (findMaximalLabel()+1));
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
	
	
	
//	boolean topologicalOrderedBefore(int thread1, int thread2) {
//		if(label.get(thread1) == null) {
//			return true;
//		}
//		if(label.get(thread2) == null) {
//			return false;
//		}		
//		return label.get(thread1).getLabel() < label.get(thread2).getLabel()  || (label.get(thread1).getLabel() == label.get(thread2).getLabel() && thread1 < thread2 );
//	}
	
	
//	public int findMaximalLabel() {
//		int max = 0;
//		for(int i = 0; i < label.length(); i++) {
//			if(label.get(i) != null && label.get(i).getLabel()> max) {
//				max = label.get(i).getLabel();
//			}
//		}
//		return max;
//	}
	

	
	public int findMaximalLabel() {
		int max = 0;
		for(int i = 0; i < label.length(); i++) {
			if(label.get(i)> max) {
				max = label.get(i);
			}
		}
		return max;
	}
	
	class Label{
		volatile int label;
		
		
		Label(int label){
			this.label = label;
		}
		
		void setLabel(int label) {
			this.label = (label % 100000);
		}
		
		int getLabel() {
			return label;
		}
		
		
	}

}



