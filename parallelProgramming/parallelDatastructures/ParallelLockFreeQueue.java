/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelDatastructures;

import java.util.concurrent.atomic.AtomicReference;

/*
 * Source: The Art of Multiprocessor Programming
 * 
 */
public class ParallelLockFreeQueue<E>{

	AtomicReference<Node<E>> head;
	AtomicReference<Node<E>> tail;
	
	
	
	void enqueue(E item) {
		Node<E> toEnq = new Node<E>(item);
		while (true) {
			Node<E> last = tail.get();
			Node<E> next = last.next.get();
			if (last == tail.get()) {
				if(next == null) {
					if (last.next.compareAndSet(next, toEnq)) {
						tail.compareAndSet(last, toEnq);
							return;
					}
				} else {
					tail.compareAndSet(last, next);
				}
			}
		}
	}
	
	public E deq() {
		while(true) {
			Node<E> first = head.get();
			Node<E> last = tail.get();
			Node<E> next = first.next.get();
			if(first == head.get()) { // why  is this line useful? -> things can still after this line
				if(first == last) {
					if(next == null) {
						throw new RuntimeException("not enough elements in queue");
					}
					tail.compareAndSet(last, next); // we can do this since there is only one element in the queue so first = last
				} else {
					// more than 1 element in queue
					E item = next.item;
					if(head.compareAndSet(first, next)) {
						return item;
					}
				}
			}
			
		}
	}
	
	
	class Node<E>{
		AtomicReference<Node<E>> next = new AtomicReference<Node<E>>();
		E item;
		
		Node(E item){
			this.item = item;
			next = new AtomicReference<Node<E>>(null);
		}
	}
}
