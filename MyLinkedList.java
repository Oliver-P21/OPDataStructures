import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * List implementation using a links
 * 
 * @author OP
 *
 * @param <T> type of Object LinkedList holds
 */
public class MyLinkedList<T> implements MyList<T>, MyIterable<T> {
	private class Node {
		private T value;
		private Node next;
		private Node pre;

		public Node(T value) {
			this.value = value;
		}

		public Node(T value, Node pre, Node next) {
			this.value = value;
			this.pre = pre;
			this.next = next;
		}
	}

	private int size;
	private Node head;
	private Node tail;

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
			head = new Node(item);
			tail = head;
			size++;
			return;
		}

		tail.next = new Node(item, tail, null);
		tail = tail.next;
		size++;
	}

	// replaces a value at a specific index
	// TESTED
	public void replace(int index, T item) throws IndexOutOfBoundsException {
		Node ptr = traverse(index);
		ptr.value = item;

	}

	// inserts a value right above a specific index
	// TESTED
	public void insert(int index, T item) throws IndexOutOfBoundsException {
		if (index == size - 1) {
			add(item);
			return;
		}
		Node ptr = traverse(index);
		Node temp = new Node(item, ptr, ptr.next);
		ptr.next.pre = temp;
		ptr.next = temp;
		size++;
	}

	// inserts a value at the front of the list
	// TESTED*
	public void insertFront(T item) {
		if (isEmpty()) {
			add(item);
			return;
		}

		Node temp = new Node(item, null, head);
		head.pre = temp;
		head = temp;
		size++;
	}

	// Removes item at the specified index
	// TESTED
	public void remove(int index) throws IndexOutOfBoundsException {
		Node ptr = traverse(index);
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
	public void remove(T value) throws IndexOutOfBoundsException {
		if (contains(value))
			remove(indexOf(value));
	}

	// Removes a range of items from the start index to end index(Both inclusive)
	// TESTED
	public void remove(int start, int end) throws IndexOutOfBoundsException {
		if (end < start) {
			throw new IndexOutOfBoundsException("end index: " + end + "can't be less than start index: " + start);
		}
		if (end >= size) {
			throw new IndexOutOfBoundsException(end);
		}

		Node first = traverse(start);
		Node last = first;

		int counter = start;

		while (counter != end) {
			last = last.next;
			counter++;
		}

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
	public T get(int index) throws IndexOutOfBoundsException {
		return traverse(index).value;
	}

	// Returns item at the end of the list
	// TESTED*
	public T getLast() {
		return get(size - 1);
	}

	// Returns the item at the specified index and removes it
	// TESTED
	public T take(int index) throws IndexOutOfBoundsException {
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
		Node ptr = head;
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
		Node ptr = tail;
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
		Node ptr = head;
		while (ptr != null) {
			if (ptr.value.equals(searchValue))
				total++;
			ptr = ptr.next;
		}

		return total;
	}

	// Creates a file and writes all items in the list to the file
	// TESTED
	public void toFile(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		Node ptr = head;
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
	public void insertList(List<T> list, int index) throws IndexOutOfBoundsException {
		if (index == size - 1) {
			addList(list);
			return;
		}
		if (list.isEmpty()) {
			return;
		}

		MyLinkedList<T> newPart = new MyLinkedList<>();
		newPart.addList(list);

		Node ptr = traverse(index);

		newPart.tail.next = ptr;
		newPart.head.pre = ptr.pre;
		if (index == 0) {
			head = newPart.head;
		} else {
			ptr.pre.next = newPart.head;
		}
		ptr.pre = newPart.tail;
	}

	// Returns a reference of a deep copy of a sublist of the list. both index inclusive
	// TESTED
	public MyLinkedList<T> subList(int start, int end) {
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
		Node ptr = head;
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
		Node ptr = head;

		while (ptr != null) {
			listString = listString.concat((String) ptr.value);

			if (ptr.next != null)
				listString = listString.concat(", ");

			ptr = ptr.next;
		}

		return listString.concat("]");
	}

	@Override
	public boolean listEquals(List<T> other) {
		if (other.size() != size) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (other.get(i) != get(i)) {
				return false;
			}
		}
		return true;
	}

	// Returns true if the list contains the same exact items as this list.
	// TESTED
	public boolean myListEquals(MyList<T> other) {
		for (int i = 0; i < size(); i++) {
			if (other.get(i) != get(i)) {
				return false;
			}
		}
		return true;
	}

	// Points the pointer at specified node from the head
	private Node traverse(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		int counter = 0;
		Node ptr = head;
		while (counter != index) {
			counter++;
			ptr = ptr.next;
		}
		return ptr;
	}

	private class LinkedListIterator implements MyListIterator<T> {
		private Node cursor;
		private final String noNextMessage = "Iterator has reached end of list";
		private final String noPreMessage = "Iterator has reached beginning of list";

		public LinkedListIterator() {
			cursor = new Node(null);// makeing a "Between" node for more clear implementation
			cursor.next = head;
		}

		@Override
		public boolean hasNext() {
			return cursor.next != null;
		}

		@Override
		public boolean hasPrevious() {
			return cursor.pre != null;
		}

		@Override
		public T next() throws NoSuchElementException {
			checkNext();
			T item = cursor.next.value;
			cursor.pre = cursor.next;
			cursor.next = cursor.next.next;
			return item;
		}

		@Override
		public T previous() throws NoSuchElementException {
			checkPrevious();
			T item = cursor.pre.value;
			cursor.next = cursor.pre;
			cursor.pre = cursor.pre.pre;
			return item;
		}

		@Override
		public int nextIndex() {
			if(cursor.next == null) {
				return size;
			}
			int index = 0;
			Node ptr = head;
			while (ptr != cursor.next) {
				ptr = ptr.next;
				index++;
			}
			return index;
		}

		@Override
		public int previousIndex() {
			if(cursor.pre == null) {
				return -1;
			}
			int index = 0;
			Node ptr = head;
			while (ptr != cursor.pre) {
				ptr = ptr.next;
				index++;
			}
			return index;
		}

		@Override
		public void removeNext() throws NoSuchElementException {
			checkNext();

			Node temp = cursor.next;

			if (temp.pre == null) {
				head = temp.next;
			} else {
				temp.pre.next = temp.next;
			}

			if (temp.next == null) {
				tail = cursor.pre;
			} else {
				temp.next.pre = cursor.pre;
			}

			cursor.next = temp.next;

			temp.next = temp.pre = null;
			size--;
		}

		@Override
		public void removePrevious() throws NoSuchElementException {
			checkPrevious();

			Node temp = cursor.pre;

			if (temp.pre == null) {
				head = temp.next;
			} else {
				temp.pre.next = temp.next;
			}

			if (temp.next == null) {
				tail = temp.pre;
			} else {
				temp.next.pre = temp.pre;
			}

			cursor.pre = temp.pre;

			temp.next = temp.pre = null;
			size--;
		}

		@Override
		public void replaceNext(T item) throws NoSuchElementException {
			checkNext();;
			cursor.next.value = item;
		}

		@Override
		public void replacePrevious(T item) throws NoSuchElementException {
			checkPrevious();
			cursor.pre.value = item;
		}

		@Override
		public void addNext(T item) {
			Node temp = new Node(item, cursor.pre, cursor.next);

			if (cursor.pre == null) {
				head = temp;
			} else {
				cursor.pre.next = temp;
			}

			if (cursor.next == null) {
				tail = temp;
			} else {
				cursor.next.pre = temp;
			}

			cursor.next = temp;
			size++;

		}

		@Override
		public void addPrevious(T item) {
			Node temp = new Node(item, cursor.pre, cursor.next);

			if (cursor.pre == null) {
				head = temp;
			} else {
				cursor.pre.next = temp;
			}

			if (cursor.next == null) {
				tail = temp;
			} else {
				cursor.next.pre = temp;
			}

			cursor.pre = temp;
			size++;
		}

		@Override
		public void skipNext() throws NoSuchElementException {
			checkNext();
			cursor.pre = cursor.next;
			cursor.next = cursor.next.next;
		}

		@Override
		public void skipPrevious() throws NoSuchElementException {
			checkPrevious();
			cursor.next = cursor.pre;
			cursor.pre = cursor.pre.pre;
		}

		private void checkNext() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException(noNextMessage);
			}
		}

		private void checkPrevious() throws NoSuchElementException {
			if (!hasPrevious()) {
				throw new NoSuchElementException(noPreMessage);
			}
		}

	}

	@Override
	public MyListIterator<T> getIterator() {
		return new LinkedListIterator();
	}
	
	public void display() {
		System.out.print("[");
		Node ptr = head;

		while (ptr != null) {
			System.out.print(ptr.value);

			if (ptr.next != null)
				System.out.print(", ");

			ptr = ptr.next;
		}

		System.out.print("]");;
	}
	
	public void displayBackwards() {
		System.out.print("[");;
		Node ptr = tail;

		while (ptr != null) {
			System.out.print(ptr.value);

			if (ptr.pre != null) {
				System.out.print(", ");
			}

			ptr = ptr.pre;
		}

		System.out.print("]");
		
	}
}
