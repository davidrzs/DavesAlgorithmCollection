/**
 * These algorithms were implemented with the goal to experiment with them and many of them were implemented from scratch from my memory (implementing what I could still remember from class).
 * This file is by no means complete / tested / safe to use. 
 *
 * Seriously: Using this code is really dangerous.
 * However, if you want to take a glimpse feel free to use my code as long as it complies with the MIT license.
 * File written by davidrzs - David Zollikofer 
 */
package parallelDatastructures;

/**
 * @author David
 *
 */
public class ParallelStackLocked<E> implements ParallelStack<E>{

	Node<E> top;
	
	public synchronized void push(E element) {
		Node<E> newHead = new Node<E>(element);
		newHead.next = top;
		top = newHead;
	}
	
	public synchronized E pop() {
		Node<E> toReturn = top;
		top = top.next;
		if(toReturn == null) {
			throw new RuntimeException("Stack Empty - cant pop value");
		}
		return toReturn.value;
	}
	
	public synchronized boolean isEmpty() {
		return top == null;
	}

}

class Node<E>{
	E value;
	Node<E> next;
	
	Node(E value){
		this.value = value;
	}
}
