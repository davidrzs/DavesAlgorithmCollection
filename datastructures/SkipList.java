
import java.util.Random;

public class SkipList {
	
	Random rand;
	SkipListNode head;
	SkipListNode tail;
	
	int skipListHeight = 32;
	
	
	public SkipList() {
		rand = new Random();
		head = new SkipListNode(Integer.MIN_VALUE);
		tail = new SkipListNode(Integer.MAX_VALUE);
	}
	
	@Override
	public String toString() {
		String out = "";
		SkipListNode currentNode = head;
		while(currentNode.after[0] != null) {
			out = out + currentNode.value + " ";
			currentNode = currentNode.after[0];
		}
		return out;
	}
	
	
	public void add(int value) {
		SkipListNode currentNode = head;
		int searchLevel = skipListHeight-1;
		// search for insertion point
		while(searchLevel > 0 || currentNode.value != value) {
			
			if(currentNode.after[searchLevel].value < value) {	
				currentNode = currentNode.after[searchLevel];
			} else {
				searchLevel--;
			}
		}
		// we have found the insertion point
		
		if(currentNode.value == value) {
			currentNode.count++;
		} else {
			SkipListNode newNode = new SkipListNode(value);
			// now we need to add it to our skiplist
			for(int i = 0; i < skipListHeight;i++) {
				// set the new successor
				newNode.after[0] = currentNode.after[0];
				// set us as the predecessor of our successor
				currentNode.after[0].before[0] = newNode;
				// set our predecessor
				newNode.before[0] = currentNode;
				// set us as the sucessor of our predecessor
				currentNode.after[0] = newNode;
				while(rand.nextBoolean() == true) {
					
				} else {
					break;
				}
			}
		}
		while(head.after[searchLevel].value < value) {
			
		}
	}

	public void remove() {
		
	}
	
	public int find() {
		return 0;
	}
	
	
	/**
	 * class implementing a node in the skiplist representing a value
	 * @author David
	 * 
	 */
	class SkipListNode{
		int value;
		int count;
		SkipListNode[] before;
		SkipListNode[] after;
		
		SkipListNode(int value){
			this.value = value;
			count = 1;
			before = new SkipListNode[skipListHeight];
			after = new SkipListNode[skipListHeight];
		}
		
		void addAnotherOccurence() {
			count++;
		}
		
		
	}
	
}

