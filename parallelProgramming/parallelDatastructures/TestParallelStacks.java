/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelDatastructures;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * @author David
 *
 */
class TestParallelStacks {

	@Test
	void testStackPushingLocked() throws InterruptedException {
		
		ParallelStack<StackItem> stack = new ParallelStackLocked<StackItem>();
		
		Thread[] manipulators = new Thread[10];
		
		for(int i = 0; i < 10; i++) {
			manipulators[i] = new Thread() {
				public void run() {
					Random rand = new Random();
					for(int i = 0; i < 10000; i++) {
							stack.push(new StackItem(rand.nextInt()));
					}
				}
			};
			manipulators[i].start();
		}
		
		
		for(int i = 0; i < 10; i++) {
			manipulators[i].join();
		}
		
		for(int i = 0; i < 10; i++) {
			manipulators[i] = new Thread() {
				public void run() {
					for(int i = 0; i < 10000; i++) {
							stack.pop();
					}
				}
			};
			manipulators[i].start();
		}
		
		
		for(int i = 0; i < 10; i++) {
			manipulators[i].join();
		}
		
		assertTrue(stack.isEmpty());
		
		
	
	}
	
	
	
	
	@Test
	void testStackPushingUnlocked() throws InterruptedException {
		
		ParallelStack<StackItem> stack = new ParallelStackLockFree<StackItem>();
		
		Thread[] manipulators = new Thread[10];
		
		for(int i = 0; i < 10; i++) {
			manipulators[i] = new Thread() {
				public void run() {
					Random rand = new Random();
					for(int i = 0; i < 10000; i++) {
							stack.push(new StackItem(rand.nextInt()));
					}
				}
			};
			manipulators[i].start();
		}
		
		
		for(int i = 0; i < 10; i++) {
			manipulators[i].join();
		}
		
		for(int i = 0; i < 10; i++) {
			manipulators[i] = new Thread() {
				public void run() {
					for(int i = 0; i < 10000; i++) {
							stack.pop();
					}
				}
			};
			manipulators[i].start();
		}
		
		
		for(int i = 0; i < 10; i++) {
			manipulators[i].join();
		}
		
		assertTrue(stack.isEmpty());
		
		
	
	}
	

}


class StackItem{
	long timestamp;
	int value;
	
	StackItem(int value){
		this.value = value;
		timestamp = System.nanoTime();
	}
}
