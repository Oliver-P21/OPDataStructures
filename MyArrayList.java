import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * List implementation using an array
 * 
 * @author OP
 *
 * @param <T> type of Object the list holds
 */
public class MyArrayList<T> implements MyList<T>, MyIterable<T> {
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
	public void replace(int index, T item) throws IndexOutOfBoundsException {
		throwCheck(index);
		elements[index] = item;
	}

	// Inserts item above the specific index
	// TESTED
	public void insert(int index, T item) throws IndexOutOfBoundsException {
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
	public T get(int index) throws IndexOutOfBoundsException {
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
	public void remove(int index) throws IndexOutOfBoundsException {
		throwCheck(index);
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		size--;
	}

	// Removes all values from the start index to the last(Both inclusive)
	// TESTED
	public void remove(int start, int end) throws IndexOutOfBoundsException {
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
	public void remove(T item) throws IndexOutOfBoundsException {
		remove(indexOf(item));
	}

	// Removes all appearences of item from the list
	// TESTED
	public void removeAll(T item) throws IndexOutOfBoundsException {
		while (contains(item)) {
			remove(item);
		}
	}

	// Gets then removes item at index from the list
	// TESTED
	public T take(int index) throws IndexOutOfBoundsException {
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
	// TESTED
	public void addList(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			add(list.get(i));
		}
	}

	// Inserts all items from a given list into this list below the given index
	// TESTED
	@SuppressWarnings("unchecked")
	public void insertList(List<T> list, int index) throws IndexOutOfBoundsException {
		T[] newElements = (T[]) new Object[size + list.size()];
		int i = 0;
		for (; i < index; i++) {
			newElements[i] = elements[i];
		}

		for (int j = 0; j < list.size(); j++, i++) {
			newElements[i] = list.get(j);
		}

		for (; i < list.size() + size; i++) {
			newElements[i] = elements[i - list.size()];
		}

		elements = newElements;
		size += list.size();
	}

	// Creates a file and writes all items in the list to the file
	// UNTESTED
	public void toFile(String filename) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

		for (int i = 0; i < size; i++) {
			writer.write((String) elements[i] + "\n");
		}

		writer.close();
	}

	// Returns string representation of list
	// TESTED
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

	// Checks to see if the given list is equal to this list
	public boolean myListEquals(MyList<T> other) {
		for (int i = 0; i < size(); i++) {
			if (other.get(i) != get(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ArrayList<T> toArrayList() {

		return null;
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
	private void throwCheck(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
	}

	private class ArrayIterator implements MyListIterator<T> {
		private int cursor;
		private final String noNext = "Iterator has reached end of list";
		private final String noPre = "Iterator has reached beginning of list";

		public ArrayIterator() {
			cursor = -1;

		}

		@Override
		public boolean hasNext() {
			return cursor < size - 1;
		}

		@Override
		public boolean hasPrevious() {
			return cursor > -1;
		}

		@Override
		public T next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException(noNext);
			}
			return elements[++cursor];
		}

		@Override
		public T previous() throws NoSuchElementException {
			if (!hasPrevious()) {
				throw new NoSuchElementException(noPre);
			}
			return elements[cursor--];
		}

		@Override
		public int nextIndex() {
			return cursor + 1;
		}

		@Override
		public int previousIndex() {
			return cursor;
		}

		@Override
		public void removeNext() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException(noNext);
			}
			remove(cursor + 1);
		}

		@Override
		public void removePrevious() throws NoSuchElementException {
			if (!hasPrevious()) {
				throw new NoSuchElementException(noPre);
			}
			remove(cursor--);
		}

		@Override
		public void replaceNext(T item) throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException(noNext);
			}
			replace(cursor + 1, item);
		}

		@Override
		public void replacePrevious(T item) throws NoSuchElementException {
			if (!hasPrevious()) {
				throw new NoSuchElementException(noPre);
			}
			replace(cursor, item);
		}

		@Override
		public void addNext(T item) {
			if (cursor == -1) {
				insertFront(item);
				return;
			}
			if (cursor == size - 1) {
				add(item);
				return;
			}

			insert(cursor, item);
		}

		@Override
		public void addPrevious(T item) {
			if (cursor == -1) {
				insertFront(item);
				cursor++;
				return;
			}
			if (cursor == size - 1) {
				add(item);
				cursor++;
				return;
			}

			insert(cursor, item);
			cursor++;
		}

		@Override
		public void skipNext() throws NoSuchElementException {
			cursor++;
		}

		@Override
		public void skipPrevious() throws NoSuchElementException {
			cursor--;
		}

	}

	@Override
	public MyListIterator<T> getIterator() {
		return new ArrayIterator();
	}

	public void display() {
		System.out.print("[");
		for (int i = 0; i < size; i++) {
			System.out.print(elements[i]);
			if (i != size - 1) {
				System.out.print(", ");
			}
		}
		System.out.print("]");
	}

	public void displayBackwards() {
		System.out.print("[");
		for (int i = size - 1; i >= 0; i--) {
			System.out.print(elements[i]);
			if (i != 0) {
				System.out.print(", ");
			}
		}
		System.out.print("]");
	}

}
