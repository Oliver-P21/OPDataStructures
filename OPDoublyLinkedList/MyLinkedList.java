import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyLinkedList<T> {
	private int size;
	private Node<T> head;
	private Node<T> tail;

	public MyLinkedList() {
		size = 0;
	}

	// returns number of items in list
	// TESTED
	public int size() {
		return size;
	}

	// add an item at the end of the list
	// TESTED
	public void add(T item) {
		if (head == null) {
			head = new Node<T>(item);
			tail = head;
			size++;
			return;
		}

		tail.next = new Node<T>(item, tail, null);
		tail = tail.next;
		size++;
	}

	// replaces a value at a specific index
	// TESTED
	public void replace(int index, T item) throws ArrayIndexOutOfBoundsException {
		Node<T> ptr = traverse(index);
		ptr.value = item;

	}

	// inserts a value right above a specific index
	// TESTED
	public void insert(int index, T item) throws ArrayIndexOutOfBoundsException {
		if (index == size - 1) {
			add(item);
			return;
		}
		Node<T> ptr = traverse(index);
		Node<T> temp = new Node<T>(item, ptr, ptr.next);
		ptr.next.pre = temp;
		ptr.next = temp;
		size++;
	}

	// inserts a value at the front of the list
	// TESTED*
	public void insertFront(T item) throws NullPointerException {
		if (isEmpty()) {
			add(item);
			return;
		}

		Node<T> temp = new Node<T>(item, null, head);
		head.pre = temp;
		head = temp;
		size++;
	}

	// Removes item at the specified index
	// TESTED
	public void remove(int index) throws ArrayIndexOutOfBoundsException {
		Node<T> ptr = traverse(index);
		if (index != 0) { // if the node is not the first one. Sets head if it is
			ptr.pre.next = ptr.next;
		} else {
			head = ptr.next;
		}

		if (index < size - 1) // if the node removed is not the last one. Sets tail if it is
			ptr.next.pre = ptr.pre;
		else {
			tail = ptr.pre;
		}
		ptr.pre = ptr.next = null;
		size--;
	}

	// Removes first occurrence of specific item from the list
	// TESTED
	public void remove(T value) throws ArrayIndexOutOfBoundsException {
		remove(indexOf(value));
	}

	// Removes a range of items from the start index to end index(Both inclusive)
	// TESTED
	public void remove(int start, int end) throws ArrayIndexOutOfBoundsException {

		Node<T> first = traverse(start);
		Node<T> last = traverse(end);

		if (start == 0) {
			head = last.next;
		} else {
			first.pre.next = last.next;
		}

		if (end == size - 1) {
			tail = first.pre;
		} else {
			last.next.pre = first.pre;
		}

		size = size - (end - start + 1);

	}

	// Removes all elements with specified value from the list
	// TESTED
	public void removeAll(T value) {
		while (contains(value)) {
			remove(indexOf(value));
		}
	}

	// Returns the item at the specified index without removing it
	// TESTED
	public T get(int index) throws ArrayIndexOutOfBoundsException {
		return traverse(index).value;
	}

	// Returns item at the end of the list
	// TESTED*
	public T getLast() {
		return get(size - 1);
	}

	// Returns the item at the specified index and removes it
	// TESTED
	public T take(int index) throws ArrayIndexOutOfBoundsException {
		T item = get(index);
		remove(index);
		return item;
	}

	// Empties out the entire list
	// TESTED
	public void clear() {
		while (!isEmpty()) {
			remove(size - 1);
		}
	}

	// Returns the first position of a specific value. Returns - 1 if its not
	// present
	// TESTED
	public int indexOf(T searchValue) {
		int position = -1;
		Node<T> ptr = head;
		int count = 0;
		while (ptr != null) {
			if (searchValue.equals(ptr.value)) {
				position = count;
				break;
			}
			ptr = ptr.next;
			count++;
		}
		return position;
	}

	// Returns the last position of a specific value. Returns -1 if its present
	// TESTED*
	public int lastIndexOf(T searchValue) {
		int position = -1;
		Node<T> ptr = tail;
		int count = size - 1;
		while (ptr != null) {
			if (searchValue.equals(ptr.value)) {
				position = count;
				break;
			}
			ptr = ptr.pre;
			count--;
		}
		return position;
	}

	// Checks if a specified item is in the list
	// TESTED
	public boolean contains(T searchValue) {
		return indexOf(searchValue) != -1;
	}

	// Checks if the list is empty
	// TESTED
	public boolean isEmpty() {
		return size == 0;
	}

	// Returns the number of times a specified item appears in the list
	// TESTED
	public int numberOf(T searchValue) {
		int total = 0;
		Node<T> ptr = head;
		while (ptr != null) {
			if (ptr.value.equals(searchValue))
				total++;
			ptr = ptr.next;
		}

		return total;
	}

	// Creates a file and writes all items in the list to the file
	// TESTED
	public void listToFile(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		Node<T> ptr = head;
		while (ptr != null) {
			writer.write((String) ptr.value + "\n");
			ptr = ptr.next;
		}
		writer.close();
	}

	// Adds all items from a given list into this list
	// TESTED
	public void addList(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	// Inserts all items from a given list into this list below the given index
	// TESTED
	public void insertList(List<T> list, int index) throws ArrayIndexOutOfBoundsException{
		if(index == size - 1) {
			addList(list);
			return;
		}
		if(list.isEmpty()) {
			return;
		}
		
		MyLinkedList<T> newPart = new MyLinkedList<>();
		newPart.addList(list);
		
		Node<T> ptr = traverse(index);
		
		newPart.tail.next = ptr;
		newPart.head.pre = ptr.pre;
		if(index == 0) {
			head = newPart.head;
		}
		else {
			ptr.pre.next = newPart.head;
		}
		ptr.pre = newPart.tail;				
	}
	
	
	//Returns a reference of a deep copy of a sublist of the list. index inclusive
	//TESTED
	public MyLinkedList<T> subList(int start, int end){		
		MyLinkedList<T> subList = new MyLinkedList<>();
		
		while (start <= end) {
			subList.add(get(start));
			start++;
		}
		
		return subList;
	}
	
	
	// Copies list values into an ArrayList
	// TESTED
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<>();
		Node<T> ptr = head;
		while (ptr != null) {
			list.add(ptr.value);
			ptr = ptr.next;
		}
		return list;
	}

	// Returns a string representation of the list
	// TESTED
	@Override
	public String toString() {
		String listString = "[";
		Node<T> ptr = head;

		while (ptr != null) {
			listString = listString.concat((String) ptr.value);

			if (ptr.next != null)
				listString = listString.concat(", ");

			ptr = ptr.next;
		}

		return listString.concat("]");
	}

	// Returns true if the list contains the same exact items as this list.
	// TESTED
	public boolean equals(MyLinkedList<T> other) {
		if(other == null) {
			return false;
		}
		
		if (other == this) {
			return true;
		}
		
		if (other.size() != size()) {
			return false;
		}

		for (int i = 0; i < size(); i++) {
			if(other.get(i) != get(i)) {
				return false;
			}
		}

		return true;
	}

	// Points the pointer at specified node from the head
	private Node<T> traverse(int index) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		int counter = 0;
		Node<T> ptr = head;
		while (counter != index) {
			counter++;
			ptr = ptr.next;

		}
		return ptr;
	}

}
