/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelDatastructures;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelQueue<E>{
	
	Lock lock = new ReentrantLock();
	Node<E> head;
	Node<E> tail;
	
	Condition empty = lock.newCondition();
	
	void enqueue(E item) {
		lock.lock();
		try {
			Node<E> toAdd = new Node<E>(item);
			tail.next = toAdd;
			tail = toAdd;
			empty.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public E deq() throws InterruptedException {
		lock.lock();
		try {
			while(head.next == null) {
				empty.await();
			}
			// the head contains a value that has already been returned - for simplicity it is
			// simpler to keep it in the queue.
			E returnValue = head.next.item;
			head = head.next;
			return returnValue;
		}finally {
			lock.unlock();
		}
	}
	
	
	class Node<E>{
		Node<E> next;
		E item;
		
		Node(E item){
			this.item = item;
		}
	}

}
