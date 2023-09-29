import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DLinkedList<T> {
	private int size;
	private Node<T> head;
	private Node<T> tail;

	public DLinkedList() {
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
		Node<T> ptr = traverseForward(index);
		ptr.value = item;

	}

	// inserts a value right above a specific index
	// TESTED
	public void insert(int index, T item) throws ArrayIndexOutOfBoundsException {
		if (index == size - 1) {
			add(item);
			return;
		}
		Node<T> ptr = traverseForward(index);
		Node<T> temp = new Node<T>(item, ptr, ptr.next);
		ptr.next.pre = temp;
		ptr.next = temp;
		size++;
	}

	// inserts a value at the front of the list
	// UNTESTED
	public void insertFront(T item) throws NullPointerException {

	}

	// Removes item at the specified index
	// TESTED
	public void remove(int index) throws ArrayIndexOutOfBoundsException {
		Node<T> ptr = traverseForward(index);
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
		if (start < 0)
			throw new ArrayIndexOutOfBoundsException(start);
		else if (end >= size)
			throw new ArrayIndexOutOfBoundsException(end);
		
		Node<T> first = traverseForward(start);
		Node<T> last = traverseForward(end);
		
		if(start == 0) {
			head = last.next;
		}
		else {
			first.pre.next = last.next;
		}
		
		if(end == size - 1) {
			tail = first.pre;
		}
		else {
			last.next.pre = first.pre;
		}
		
		size = size - (end - start + 1);
		
	}

	// Removes all elements with specified value from the list
	// UNTESTED
	public void removeAll(T value) {
		while (contains(value)) {
			remove(indexOf(value));
		}
	}

	// Returns the item at the specified index without removing it
	// TESTED
	public T get(int index) throws ArrayIndexOutOfBoundsException {
		return traverseForward(index).value;
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
	// UNTESTED
	public boolean contains(T searchValue) {
		return indexOf(searchValue) != -1;
	}

	// Checks if the list is empty
	// UNTESTED
	public boolean isEmpty() {
		return size == 0;
	}

	// Returns the number of times a specified item appears in the list
	// UNTESTED
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
	// UNTESTED
	public void listToFile(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		Node<T> ptr = head;
		while (ptr != null) {
			writer.write((String) ptr.value);
			ptr = ptr.next;
		}
		writer.close();
	}

	// Adds all items from a given list into the DLinkedList
	// UNTESTED
	public void addList(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	// Copies list values into a conventional list
	// UNTESTED
	public List<T> toArrayList() {
		List<T> list = new ArrayList<>();
		Node<T> ptr = head;
		while (ptr != null) {
			list.add(ptr.value);
			ptr = ptr.next;
		}
		return list;
	}

	// Returns a string representation of the list
	// UNTESTED
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

	// Points the pointer at specified node from the head
	// UNTESTED
	private Node<T> traverseForward(int index) throws ArrayIndexOutOfBoundsException {
		if (isEmpty()) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		int counter = 0;
		Node<T> ptr = head;
		while (counter != index) {
			counter++;
			ptr = ptr.next;
			if (ptr == null) {
				throw new ArrayIndexOutOfBoundsException();
			}
		}
		return ptr;
	}

}
