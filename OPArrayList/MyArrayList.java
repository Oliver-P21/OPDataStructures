import java.lang.reflect.Array;
import java.util.List;

public class MyArrayList<T> {
	private int size;
	private Object[] elements;
	private final int defaultCapacity = 10;

	// Initializes the list with default capacity
	public MyArrayList() {
		size = 0;
		elements = new Object[defaultCapacity];
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
		return (T) elements[index];
	}

	// Returns item at the end of the list
	// TESTED*
	@SuppressWarnings("unchecked")
	public T getLast() {
		return (T) elements[size - 1];
	}

	public void remove(T item) {

	}

	public void remove(int index) {

	}

	public void removeAll(T item) {

	}

	public void clear() {

	}

	public int indexOf(Object o) {
		return 0;
	}

	public int lastIndexOf(Object o) {
		return 0;
	}

	public List<T> subList(int fromIndex, int toIndex) {

		return null;
	}

	// Returns the number of times a specified item appears in the list
	// UNTESTED
	public int numberOf(T searchValue) {
		int total = 0;

		return total;
	}

	public Object[] toArray() {

		return null;
	}

	public T[] toArray(T[] a) {
		return null;
	}
	
	// Returns current number of elements in the list
	// TESTED
	public int size() {
		return size;
	}

	//Returns current total capacity of class array
	// UNTESTED
	public int maxSize() {
		return elements.length;
	}
	
	//Returns true if there are elements in the set
	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contains(T item) {
		return true;
	}

	public String toString() {
		String listString = "[";

		for (int i = 0; i < size; i++) {
			listString = listString.concat(elements[i].toString());

			if (i != size - 1)
				listString = listString.concat(", ");
		}

		return listString.concat("]");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object object) {
		
		if(object == null) {
			return false;
		}
		
		if(this.getClass() != object.getClass()) {
			return false;
		}
			
		if (object == this) {
			return true;
		}
		
		MyArrayList<T> list = (MyArrayList<T>) object;
		
		for (int i = 0; i < size(); i++) {
			if(list.get(i) != get(i)) {
				return false;
			}
		}

		return true;
	}

	private void checkAndResize() {
		if (size == elements.length) {
			Object[] newElements = new Object[size + 10];
			for (int i = 0; i < elements.length; i++) {
				newElements[i] = elements[i];
			}
			elements = newElements;
		}
	}
	
	private void throwCheck (int index) throws ArrayIndexOutOfBoundsException {
		if(index < 0 || index > elements.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
