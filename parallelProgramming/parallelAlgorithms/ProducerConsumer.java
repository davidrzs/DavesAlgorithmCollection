/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelAlgorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
	
	
	public static void main(String[] args) {
		
		Lock queueLock = new ReentrantLock();
		Queue<Food> queue = new LinkedList<Food>();
		Condition empty = queueLock.newCondition();
		Condition full = queueLock.newCondition();

		
		Consumer consumer = new Consumer(queueLock, queue, full, empty);
		Producer producer = new Producer(queueLock, queue, full, empty);
		
		ExecutorService exs = Executors.newFixedThreadPool(2);
		exs.submit(consumer);
		exs.submit(producer);
	}
	
}



class Consumer implements Runnable{
	
	Queue<Food> queue;
	Lock lock;
	Condition full;
	Condition empty;
	
	Consumer(Lock lock, Queue<Food> queue, Condition full, Condition empty){
		this.queue = queue;
		this.lock = lock;
		this.full = full;
		this.empty = empty;
	}

	public void run() {
		lock.lock();
		try {
			while(true) {
				while(queue.isEmpty()) {
					empty.signalAll();
					full.await();
				}
				Food currentFood = queue.remove();
				System.out.println(currentFood);
				Thread.sleep(currentFood.consumptionTime);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
}

class Producer implements Callable<Void>{
	
	static final int FILL_LEVEL = 10;
	static String[] foods = new String[] {"Pizza", "Broccoli", "Ham", "Casserole", "Meat Loaf", "Pulled Pork", "Sushi", "Salad"};
	static Random rand = new Random();
	
	Queue<Food> queue;
	Lock lock;
	Condition full;
	Condition empty;
	
	Producer(Lock lock, Queue<Food> queue, Condition full, Condition empty){
		this.queue = queue;
		this.lock = lock;
		this.full = full;
		this.empty = empty;
	}

	public Void call() throws Exception {
		lock.lock();
		try {
			while(true) {
				while(!queue.isEmpty()) {
					empty.await();
				}
				System.out.println("\nfilling\n");
				for(int i = queue.size(); i < FILL_LEVEL; i++) {
					queue.add(new Food(foods[rand.nextInt(foods.length)],rand.nextInt(700)));
				}
				full.signalAll();
			}
		}finally {
			lock.unlock();
		}
	}
	
}



class Food{
	
	String food;
	int consumptionTime;
	
	Food(String food, int consumptionTime){
		this.food = food;
		this.consumptionTime = consumptionTime;
		
	}
	
	public String toString() {
		return "Mhh. I am eating " + food;
	}
}

