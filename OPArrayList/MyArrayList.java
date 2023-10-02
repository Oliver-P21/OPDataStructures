import java.util.List;

public class MyArrayList<T> {
	private int size;
	private Object[] elements;
	private final int DEFUALT_CAPACITY = 10;

	// Initializes the list with default capacity
	public MyArrayList() {
		size = 0;
		elements = new Object[DEFUALT_CAPACITY];
	}

	// Initializes the list with given capacity
	public MyArrayList(int givenCapacity) {
		size = 0;
		elements = new Object[givenCapacity];
	}

	// Adds the item to the list
	// TESTED
	public void add(T item) {
		checkAndResize();
		elements[size] = item;
		size++;
	}

	// Adds all items from a given list into this list
	// UNTESTED
	public void addList(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	// Replaces the item at the specific index with the given item
	// UNTESTED
	public void replace(T item, int index) throws ArrayIndexOutOfBoundsException {
		throwCheck(index);
		elements[index] = item;
	}

	// Inserts item above the specific index
	// UNTESTED
	public void insert(T item, int index) {
		throwCheck(index);
		checkAndResize();
		for (int i = size - 1; i >= index; i--) {
			elements[i + 1] = elements[i];
		}
		elements[index + 1] = item;
		size++;
	}

	// Returns the item at the specified index without removing it
	// TESTED
	@SuppressWarnings("unchecked")
	public T get(int index) throws ArrayIndexOutOfBoundsException {
		throwCheck(index);
		return (T) elements[index];
	}

	// Returns item at the end of the list
	// TESTED*
	@SuppressWarnings("unchecked")
	public T getLast() {
		return (T) elements[size - 1];
	}

	public void remove(int index) {
		
	}

	public void remove(T item) {

	}

	public void remove(int start, int end) {

	}

	public void removeAll(T item) {

	}
	
	public T take(int index) {
		
	}
	// Clears the list of all elements
	// UNTESTED
	public void clear() {
		elements = new Object[DEFUALT_CAPACITY];
	}

	// Returns the first index where the item is found, or -1 if it is not present
	// UNTESTED
	public int indexOf(T item) {
		for (int i = 0; i < size; i++) {
			if (item.equals(elements[1])) {
				return i;
			}
		}

		return -1;
	}

	// Returns the last index where the item is found, or -1 if it is not present
	// UNTESTED
	public int lastIndexOf(T item) {
		for (int i = size - 1; i <= 0; i--) {
			if (item.equals(elements[1])) {
				return i;
			}
		}

		return -1;
	}

	// Returns a sub list from the start index to the end, both ends inclusive
	// UNTESTED
	public MyArrayList<T> subList(int start, int end) {
		throwCheck(start);
		throwCheck(end);
		MyArrayList<T> subList = new MyArrayList<>();

		for (int i = start; i < end; i++) {
			subList.add(get(i));
		}

		return subList;
	}

	// Returns the number of times a specified item appears in the list
	// UNTESTED
	public int numberOf(T searchValue) {
		int total = 0;
		for (int i = 0; i < size; i++) {
			if (searchValue.equals(elements[i]))
				total++;
		}
		return total;
	}

	// Converts the list into a standard array
	// UNTESTED
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] array = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			array[i] = (T) elements[i];
		}
		return array;
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
	// Tested
	public boolean isEmpty() {
		return size == 0;
	}

	// Checks to see if the list contains the item
	// Tested
	public boolean contains(T item) {
		for (int i = 0; i < size; i++) {
			if (item.equals(elements[i])) {
				return true;
			}
		}

		return false;
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

	// Grows the array as necessary
	private void checkAndResize() {
		if (size == elements.length) {
			Object[] newElements = new Object[size + 10];
			for (int i = 0; i < elements.length; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
		}
	}

	// throws an exception if the index is out of bounds
	private void throwCheck(int index) throws ArrayIndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}
}
