
public class LinkedList {
	IntNode first;
	IntNode last;
	int size;
	
	void addLast(int toAdd) {
		size = size + 1;
		if(first == null) {
			first =  new IntNode(null,null, toAdd);
			last = first;
		} else {
			IntNode currentNode = first;
			while(currentNode.next != null) {
				currentNode = currentNode.next;
			}
			IntNode toInsert = new IntNode(currentNode,null, toAdd);
			currentNode.next = toInsert;
			this.last = toInsert;
		}
	}

	void addFirst(int toAdd) {
		size = size + 1;
		//check if the list is empty
		if(first == null) {
			this.first =  new IntNode(null,null, toAdd);
			last = first;
		} else { //list was not empty
			IntNode backPropNode = first;
			IntNode newNode = new IntNode(null, backPropNode, toAdd);
			first = newNode;
			backPropNode.previous = first;
		}
	}
	
	
	int removeFirst() {
		int value = 0;
		if(first != null) {
			value = first.value;
			first = first.next;
			if(first != null) {
				first.previous = null;
			}
			
			if(last == null) {
				last = first;
			}
		}else {
			throw new RuntimeException("removeFirst - nothing to remove");
		}
		size--;
		return value;
		
	}
	
	int removeLast() {
		int value = 0;
		//check if there is a last value
		if(last != null) {
			//extract the value
			value = last.value;
			//set a new last
			last = last.previous;
			if(last != null) {
				last.next = null;				
			} else {
				first = null;
			}
			if(first == null) {
				first = last;
			}
		}else {
			throw new RuntimeException("removeLast - nothing to remove");
		}
		size--;
		return value;
	}
	
	void clear() {
		this.size = 0;
		this.first = null;
		this.last = null;
	}
	
	boolean isEmpty() {
		return this.size == 0 ? true : false;
	}
	
	int get(int index) {
		if(index > size || index < 0) {
			throw new RuntimeException("illegal index");
		}
		int ctr = 0;
		IntNode current = first;
		while(ctr < index) {
			ctr++;
			current = current.next;
		}
		return current.value;
	}
	
	void set(int index, int value) {
		if(index > size || index < 0) {
			throw new RuntimeException("illegal index");
		}
		int ctr = 0;
		IntNode current = first;
		while(ctr < index) {
			ctr++;
			current = current.next;
		}
		current.value = value;
	}
	
	int[] toArray() {
		int[] arr = new int[size];
		IntNode cur = first;
		int ctr=0;
		while(cur != null) {
			arr[ctr] = cur.value;
			cur = cur.next();
			ctr++;
		}
		return arr;
	}
	
static  class IntNode {
	int value;
	IntNode next;
	IntNode previous;
	
	/**
	 * Constructor returns a new IntNode.
	 * 
	 * @param previous a reference to the previous node
	 * @param next a reference to the next node
	 * @param value value of this node
	 */
	IntNode(IntNode previous, IntNode next, int value){
		this.next = next;
		this.value = value;
		this.previous = previous;
	}
	
	public IntNode next() {
		return next;
	}
	
}

}
