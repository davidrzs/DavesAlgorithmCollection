
import java.util.Random;

public class SkipList {
	
	Random rand;
	SkipListNode head;
	SkipListNode tail;
	
	int skipListHeight = 15;
	
	
	public SkipList() {
		rand = new Random();
		head = new SkipListNode(Integer.MIN_VALUE);
		tail = new SkipListNode(Integer.MAX_VALUE);
		
		for(int i = 0; i < skipListHeight;i++) {
			head.after[i] = tail;			
		}
	}
	
	// terrible to duplicate code - but i am really lazy right now
	public SkipList(int m) {
		skipListHeight = m;
		rand = new Random();
		head = new SkipListNode(Integer.MIN_VALUE);
		tail = new SkipListNode(Integer.MAX_VALUE);
		
		for(int i = 0; i < skipListHeight;i++) {
			head.after[i] = tail;			
		}
	}
	
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
		// this is a terrible way of doing this but its fine for the unit test - i am lazy at this point
		int ctr = -1;
		SkipListNode currentNode = head;
		while(ctr<position) {
			currentNode = currentNode.after[0];
			ctr++;
		}
		return currentNode.value;
	}
	
	
	public void add(int value) {
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
			int level = 0;
			do {
				/*
				print(42);
				// set the new successor
				newNode.after[level] = currentNode.after[level];
				// set us as the sucessor of our predecessor
				currentNode.after[level] = newNode;
				//print(Integer.toString(level));*/
				newNode.after[level] = predecessorList[level].after[level];
				predecessorList[level].after[level] = newNode;
				
				level++;
			}while(rand.nextBoolean() == true && level < skipListHeight);
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
	
	
	public static void print(String s) {
		System.out.println(s);
	}
	
}

