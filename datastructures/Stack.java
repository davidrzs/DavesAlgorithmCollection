

public class Stack {
	
	private LinkedList lst;
	Stack(){
		lst = new LinkedList();
	}
	
	void add(int w) {
		lst.addFirst(w);
	}
	
	int get() {
		return lst.removeFirst();
	}
	int peek() {
		return lst.get(0);
	}
	
}
