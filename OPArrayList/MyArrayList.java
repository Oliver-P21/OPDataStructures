import java.io.IOException;
import java.util.List;

public class MyArrayList<T> {
	private int size;
	private T[] elements;
	private final int DEFUALT_CAPACITY = 10;

	@SuppressWarnings("unchecked")
	// Initializes the list with default capacity
	public MyArrayList() {
		size = 0;
		elements = (T[]) new Object[DEFUALT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	// Initializes the list with given capacity
	public MyArrayList(int givenCapacity) {
		size = 0;
		elements = (T[]) new Object[givenCapacity];
	}

	// Returns current number of elements in the list
	// TESTED
	public int size() {
		return size;
	}

	// Returns current total capacity of class array
	// TESTED
	public int maxSize() {
		return elements.length;
	}

	// Returns true if there are elements in the list
	// TESTED
	public boolean isEmpty() {
		return size == 0;
	}

	// Checks to see if the list contains the item
	// TESTED
	public boolean contains(T item) {
		for (int i = 0; i < size; i++) {
			if (item.equals(elements[i])) {
				return true;
			}
		}

		return false;
	}

	// Adds the item to the list
	// TESTED
	public void add(T item) {
		checkAndResize();
		elements[size] = item;
		size++;
	}

	// Replaces the item at the specific index with the given item
	// TESTED
	public void replace(int index, T item) throws ArrayIndexOutOfBoundsException {
		throwCheck(index);
		elements[index] = item;
	}

	// Inserts item above the specific index
	// TESTED
	public void insert(T item, int index) {
		throwCheck(index);
		checkAndResize();
		for (int i = size - 1; i >= index; i--) {
			elements[i + 1] = elements[i];
		}
		elements[index + 1] = item;
		size++;
	}

	// inserts a value at the front of the list
	// TESTED*
	public void insertFront(T item) {
		checkAndResize();
		if (size == 0) {
			add(item);
			return;
		}
		for (int i = size - 1; i >= 0; i--) {
			elements[i + 1] = elements[i];
		}

		elements[0] = item;
		size++;
	}

	// Returns the item at the specified index without removing it
	// TESTED
	public T get(int index) throws ArrayIndexOutOfBoundsException {
		throwCheck(index);
		return (T) elements[index];
	}

	// Returns item at the end of the list
	// TESTED*
	public T getLast() {
		return (T) elements[size - 1];
	}

	// Removes the item a the given index
	// TESTED
	public void remove(int index) throws ArrayIndexOutOfBoundsException {
		throwCheck(index);
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		size--;
	}

	// Removes all values from the start index to the last(Both inclusive)
	// TESTED
	public void remove(int start, int end) throws ArrayIndexOutOfBoundsException {
		throwCheck(start);
		throwCheck(end);
		int offset = end - start + 1;

		for (int i = start; i <= end; i++) {
			elements[i] = null;
		}

		for (int i = end + 1; i < size; i++, start++) {
			elements[start] = elements[i];
		}

		size -= offset;
	}

	// Removes the first item appearance from the list
	// TESTED
	public void remove(T item) throws ArrayIndexOutOfBoundsException {
		remove(indexOf(item));
	}

	// Removes all appearences of item from the list
	// TESTED
	public void removeAll(T item) throws ArrayIndexOutOfBoundsException {
		while (contains(item)) {
			remove(item);
		}
	}

	// Gets then removes item at index from the list
	// TESTED
	public T take(int index) throws ArrayIndexOutOfBoundsException {
		throwCheck(index);
		T item = get(index);
		remove(index);
		return item;
	}

	@SuppressWarnings("unchecked")
	// Clears the list of all elements
	// TESTED
	public void clear() {
		elements = (T[]) new Object[DEFUALT_CAPACITY];
		size = 0;
	}

	// Returns the first index where the item is found, or -1 if it is not present
	// TESTED
	public int indexOf(T item) {
		for (int i = 0; i < size; i++) {
			if (item.equals(elements[i])) {
				return i;
			}
		}

		return -1;
	}

	// Returns the last index where the item is found, or -1 if it is not present
	// TESTED
	public int lastIndexOf(T item) {
		for (int i = size - 1; i >= 0; i--) {
			if (item.equals(elements[i])) {
				return i;
			}
		}

		return -1;
	}

	// Returns the number of times a specified item appears in the list
	// TESTED
	public int numberOf(T searchValue) {
		int total = 0;
		for (int i = 0; i < size; i++) {
			if (searchValue.equals(elements[i]))
				total++;
		}
		return total;
	}

	// Returns a sub list from the start index to the end, both ends inclusive
	// TESTED
	public MyArrayList<T> subList(int start, int end) {
		throwCheck(start);
		throwCheck(end);
		MyArrayList<T> subList = new MyArrayList<>();

		for (int i = start; i <= end; i++) {
			subList.add(get(i));
		}

		return subList;
	}

	

	// Adds all items from a given list into this list
	// UNTESTED
	public void addList(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	// Inserts all items from a given list into this list below the given index
	// UNTESTED
	@SuppressWarnings("unchecked")
	public void insertList(List<T> list, int index) throws ArrayIndexOutOfBoundsException {
		T[] newElements = (T[]) new Object[size + list.size()];
		int i = 0;
		for(; i < index;i++) {
			newElements[i] = elements[i];
		}
		
		for(int j = 0; j < list.size();j++, i++) {
			newElements[i] = list.get(j);
		}
		
		for(;i < list.size() + size ;i++) {
			newElements[i] = elements[i - list.size()];
		}
		
		elements = newElements;
		size += list.size();
	}

	// Creates a file and writes all items in the list to the file
	// UNTESTED
	public void listToFile(String filename) throws IOException {
		
	}


	// Returns string representation of list
	// UNTETSED
	@Override
	public String toString() {
		String listString = "[";

		for (int i = 0; i < size; i++) {
			listString = listString.concat(elements[i].toString());

			if (i != size - 1)
				listString = listString.concat(", ");
		}

		return listString.concat("]");
	}

	// Checks to see if the given list is equal to this list
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object object) {

		if (object == null) {
			return false;
		}

		if (this.getClass() != object.getClass()) {
			return false;
		}

		if (object == this) {
			return true;
		}

		MyArrayList<T> list = (MyArrayList<T>) object;

		for (int i = 0; i < size(); i++) {
			if (list.get(i) != get(i)) {
				return false;
			}
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	// Grows the array as necessary
	private void checkAndResize() {
		if (size == elements.length) {
			Object[] newElements = new Object[size + 10];
			for (int i = 0; i < elements.length; i++) {
				newElements[i] = elements[i];
			}
			elements = (T[]) newElements;
		}
	}

	// throws an exception if the index is out of bounds
	private void throwCheck(int index) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}
}
