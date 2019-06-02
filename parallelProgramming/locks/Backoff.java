/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package locks;

import java.util.Random;

/*
 * 
 * inspired by chapter 7.4 of Herlihy & Shavit
 * 
 */
public class Backoff {

	
	final int minDelay, maxDelay;
	int limit;
	final Random random;
	
	public Backoff(int min, int max) {
		minDelay = min;
		maxDelay = min;
		limit = minDelay;
		random = new Random();
	}
	
	public void backoff() throws InterruptedException{
		int delay = random.nextInt(limit);
		limit = Math.min(maxDelay, 2*limit);
		Thread.sleep(delay);
	}
	
}
