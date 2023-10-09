import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface that lets user implement abstract operation of a list
 * 
 * @author OP
 *
 * @param <T> the type of objects the MyList contains
 */
public interface MyList<T> {
	/**
	 * Returns number of elements in the list
	 * 
	 * @return number of items in the list
	 */
	public int size();

	/**
	 * Adds an item to the end of the list
	 * 
	 * @param item to be added
	 */
	public void add(T item);

	/**
	 * Replace the item at the index with another item
	 * 
	 * @param index of the old item
	 * @param the   new item to be set
	 * @throws IndexOutOfBoundsException if an invalid index is entered
	 */
	public void replace(int index, T item) throws IndexOutOfBoundsException;

	/**
	 * Inserts an item at the index
	 * 
	 * @param index to be moved either up or down
	 * @param item  to be inserted
	 * @throws IndexOutOfBoundsException if an invalid index is entered
	 */
	public void insert(int index, T item) throws IndexOutOfBoundsException;

	/**
	 * Inserts an item to the front of the list.
	 * @param item to be inserted
	 */
	public void insertFront(T item);

	/**
	 * Removes the item at the index from the list
	 * @param index of the item to be removed
	 * @throws IndexOutOfBoundsException if an invalid index is entered
	 */
	public void remove(int index) throws IndexOutOfBoundsException;

	/**
	 * Removes the the first occurrence of the item. Does nothing if it is not present
	 * @param item to be added
	 */
	public void remove(T item);

	/**
	 * Removes an entire subsection of the list
	 * @param start of the removing range
	 * @param end of the removing range
	 * @throws IndexOutOfBoundsException if an invalid index is entered
	 */
	public void remove(int start, int end) throws IndexOutOfBoundsException;

	/**
	 * Removes all occurrences of the item from the list 
	 * @param item to be removed
	 */
	public void removeAll(T item);

	/**
	 * Gets the item in the index position
	 * @param the index entered
	 * @return the item at that index
	 * @throws IndexOutOfBoundsException if an invalid index is entered
	 */
	public T get(int index) throws IndexOutOfBoundsException;

	/**
	 * Gets the last value from the list(I added this to make testing less tedious) 
	 * @return last item from the list
	 */
	public T getLast();

	/**
	 * Gets then removes the item from index provided
	 * @param index of item to be removed
	 * @return item removed
	 * @throws IndexOutOfBoundsException if an invalid index is entered
	 */
	public T take(int index) throws IndexOutOfBoundsException;

	/**
	 * Completely empties the list
	 */
	public void clear();

	/**
	 * Searches the list and returns the index of the first occurrence in the list
	 * @param item to be searched for
	 * @return first index the item appears in. -1 if not present
	 */
	public int indexOf(T searchValue);

	/**
	 * Searches the list and returns the index of the last occurrence in the list
	 * @param item to be searched for
	 * @return first index the item appears in. -1 if not present
	 */
	public int lastIndexOf(T searchValue);

	/**
	 * Checks the list to see if the list contains a specfic item
	 * @param item to be searched for
	 * @return true if it is in the list. False if it is not
	 */
	public boolean contains(T searchValue);

	/**
	 * Checks if the list is empty
	 * @return true if it is empty. false if not
	 */
	public boolean isEmpty();

	/**
	 * Counts the amount of occurrences of item in the list
	 * @param item to be counted
	 * @return number of occurrences in the list
	 */
	public int numberOf(T searchValue);

	/**
	 * Converts the list into a file
	 * @param the name of the file to be created
	 * @throws IOException if something crazy happens
	 */
	public void toFile(String filename) throws IOException;

	/**
	 * Adds another list onto the list
	 * @param list to be added
	 */
	public void addList(List<T> list);

	/**
	 * Inserts the given list into the list below the specified index.
	 * @param list to be inserted
	 * @param index item that moves over
	 * @throws IndexOutOfBoundsException if an invalid index is entered
	 */
	public void insertList(List<T> list, int index) throws IndexOutOfBoundsException;

	/**
	 * Creates a sublist 
	 * @param start index of the sublist
	 * @param end index of the sublist
	 * @return a sublist from the list
	 */
	public MyList<T> subList(int start, int end);

	/**
	 * Copies the list to an ArrayList
	 * @return ArrayList version of the list
	 */
	public ArrayList<T> toArrayList();
	
	/**
	 * Determines if a MyList object holds the same contents as this list
	 * @param other MyList object
	 * @return true if they are equal by content
	 */
	public boolean myListEquals(MyList<T> other);
	/**
	 * Determines if a List<T> object holds the same contents as this list
	 * @param other List Object
	 * @return true if the lists are equal by content
	 */
	public boolean listEquals(List<T> other);
}
