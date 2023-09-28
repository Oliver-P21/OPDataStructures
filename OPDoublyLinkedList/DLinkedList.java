
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
	public int size() {
		return size;
	}

	
	// add an item at the end of the list
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
	public void replace(int index, T item) throws ArrayIndexOutOfBoundsException {
		Node<T> ptr = traverseForward(index);
		ptr.value = item;

	}

	
	// inserts a value right above a specific index
	public void insert(int index, T item) throws ArrayIndexOutOfBoundsException {
		if (index == size - 1) {
			add(item);
			return;
		}
		Node<T> ptr = traverseForward(index);
		Node<T> temp = new Node<T>(item,ptr,ptr.next);
		ptr.next.pre = temp;
		ptr.next = temp;
		size++;
	}

	
	// Removes item at the specified index
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
		size--;
	}
	
	
	//Removes first occurrence of specific item from the list 
	public void remove(T value) throws ArrayIndexOutOfBoundsException {
		remove(indexOf(value));
	}
	
	
	//Removes a range of items from the start index to end index(Both inclusive)
	public void remove(int start, int end) throws ArrayIndexOutOfBoundsException {		
		while(start <= end) {
			remove(start);
			end--;
		}
	}

	
	//Removes all elements with specified value from the list
	public void removeAll(T value) {		
		while(contains(value)) {
			remove(indexOf(value));
		}
	}

	
	// Returns the item at the specified index without removing it
	public T get(int index) throws ArrayIndexOutOfBoundsException {
		return traverseForward(index).value;
	}
	
	
	//Returns item at the end of the list
	public T getLast() {
		return traverseForward(size - 1).value;
	}
	
	
	// Returns the item at the specified index and removes it
	public T take(int index) throws ArrayIndexOutOfBoundsException {
		T item = get(index);
		remove(index);
		return item;
	}
	
	
	//Empties out the entire list
	public void clear() {
		while(!isEmpty()) {
			remove(size - 1);
		}
	}

	
	// Returns the first position of a specific value. Returns - 1 if its not in the
	// list
	public int indexOf(T searchValue) {
		int position = -1;
		Node<T> ptr = head;
		int count = 0;
		while (ptr != null) {
			if (searchValue == ptr.value) {
				position = count;
				break;
			}
			ptr = ptr.next;
			count++;
		}
		return position;
	}

	
	// Returns the last position of a specific value. Returns -1 if its not in the
	// list
	public int lastIndexOf(T searchValue) {
		int position = -1;
		Node<T> ptr = tail;
		int count = size - 1;
		while (ptr != null) {
			if (searchValue == ptr.value) {
				position = count;
				break;
			}
			ptr = ptr.pre;
			count--;
		}
		return position;
	}

	
	// Checks if a specified item is in the list
	public boolean contains(T searchValue) {
		return indexOf(searchValue) != -1;
	}

	
	// Checks if the list is empty
	public boolean isEmpty() {
		return size == 0;
	}

	
	// Returns the number of times a specified item appears in the list
	public int numberOf(T searchValue) {
		int total = 0;
		Node<T> ptr = head;
		while(ptr != null) 
			if(ptr.value.equals(searchValue)) total++;
		
		return total;
	}
	
	
	//Creates a file and writes all items in the list to the file
	public void listToFile(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		Node<T> ptr = head;
		while(ptr != null) {
			writer.write((String)ptr.value);
		}
		writer.close();
	}
	
	
	// Adds all items from a given list into the DLinkedList
	public void addList(List<T> list) {
		for(int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}
	
	
	// Copies list values into a conventional list
	public List<T> toArrayList() {
		List <T> list = new ArrayList<>();
		Node<T> ptr = head;
		while(ptr != null) {
			list.add(ptr.value);
		}
		return list;
	}

	// Returns a string representation of the list
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
	private Node<T> traverseForward(int index) throws ArrayIndexOutOfBoundsException {
		if(isEmpty()) {
			throw new ArrayIndexOutOfBoundsException();
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
