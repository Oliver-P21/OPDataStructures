
public class OPLinkedList<T> {
	public int size;
	public Node<T> head;
	public Node<T> tail;

	public OPLinkedList() {
		size = 0;
	}

	public void addFront(T value) {
		if(size == 0) {
			head = new Node<T>(value);
			tail = head;
			size++;
			return;
		}
		Node<T> temp = new Node<T>(value);
		temp.next = head;
		head = temp;
		size++;
	}

	public void addBack(T value) {
		if(size == 0) {
			addFront(value);
			return;
		}
		tail.next = new Node<T>(value);
		tail = tail.next;
		size++;
	}

	public T removeFront() {
		T item = head.value;
		
		head = head.next;
		
		if(size == 1) {
			tail = head;
		}
		size--;
		return item;
		
	}

	public T removeBack() {
		if(size == 1) {
			T item = removeFront();
			return item;
		}
		
		T item = tail.value;
		
		Node<T> ptr = head;
		
		while(!(ptr.next.next == null)) {
			ptr = ptr.next;
		}
		
		tail = ptr;
		ptr.next = null;
		size--;
		return item;
		
	}

	public int size() {
		return size;
	}
	
	public void displayList() {
		Node<T> ptr = head;
		while(!(ptr == null)) {
			System.out.println(ptr.value);
			ptr = ptr.next;
		}
	}
}
