
public class Queue {
	private LinkedList lst;
	Queue(){
		lst = new LinkedList();
	}
	
	void add(int w) {
		lst.addLast(w);
	}
	
	int get() {
		return lst.removeFirst();
	}
	int peek() {
		return lst.get(0);
	}
}
