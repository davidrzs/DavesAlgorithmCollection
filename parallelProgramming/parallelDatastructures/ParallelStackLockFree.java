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

/**
 * @author David
 *
 */
public class ParallelStackLockFree<E> implements ParallelStack<E>{

	// defined in the locked version of this stack
	AtomicReference<Node<E>> top;
	
	
	public void push(E element) {
		Node<E> head;
		Node<E> newlyAdded = new Node<E>(element);
		do {
			head = top.get();
			newlyAdded.next = head;
		}while(top.compareAndSet(head, newlyAdded));
	}
	
	public E pop() {
		Node<E> head;
		Node<E> successor;
		
		do {
			head = top.get();
			successor = head.next;
			if(successor == null) {
				throw new RuntimeException("Stack Empty - cant pop value");
			}
		}while(top.compareAndSet(head, successor));
		return head.value;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
}

