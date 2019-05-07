
import java.util.Random;

public class SkipList {
	
	Random rand;
	SkipListNode head;
	SkipListNode tail;
	
	int skipListHeight;
	
	
	
	public SkipList(int m) {
		skipListHeight = m;
		rand = new Random();
		head = new SkipListNode(Integer.MIN_VALUE);
		tail = new SkipListNode(Integer.MAX_VALUE);
		
		for(int i = 0; i < skipListHeight;i++) {
			head.after[i] = tail;			
		}
	}
	
	// this method exists only for debugging purposes
	@Override
	public String toString() {
		String out = "";
		SkipListNode currentNode = head;
		while(currentNode.after[0] != null) {
			out = out + currentNode.value + "   ";
			currentNode = currentNode.after[0];
		}
		// fence post problem with "   "
		out += currentNode.value;
		return out;
	}
	
	
	public int get(int position) {
		// this is a terrible way of doing this but its fine for the unit test
		int ctr = -1;
		SkipListNode currentNode = head;
		while(ctr<position) {
			currentNode = currentNode.after[0];
			ctr++;
		}
		return currentNode.value;
	}
	
	
	public void add(int value) {
		// we need to store the predecessor on every level in order to add it to the skiplist
		SkipListNode[] predecessorList = new SkipListNode[skipListHeight];
		SkipListNode currentNode = head;
		int searchLevel = skipListHeight-1;
		predecessorList[searchLevel] = head;
		// search for insertion point
		while(searchLevel >= 0 && currentNode.value != value) {
			if(currentNode.after[searchLevel].value < value) {	
				currentNode = currentNode.after[searchLevel];
				// set as predecessor
				predecessorList[searchLevel] = currentNode;
			} else {
				predecessorList[searchLevel] = currentNode;
				searchLevel--;
			}
		}
		
		// we have found the insertion point
		if(currentNode.value == value) {
			currentNode.count++;
		} else {
			SkipListNode newNode = new SkipListNode(value);
			// now we need to add it to our skiplist
			// we go up the level by level
			int level = 0;
			do {
				newNode.after[level] = predecessorList[level].after[level];
				predecessorList[level].after[level] = newNode;
				level++;
			}while(rand.nextBoolean() == true && level < skipListHeight);
		}
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

