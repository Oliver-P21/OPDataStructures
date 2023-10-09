import java.util.NoSuchElementException;

/**
 * Allows iteration over a MyList
 * 
 * @author OP
 * 
 * @param<T> The type of object that the MyIterators iterates over
 */
public interface MyListIterator<T> {

	/**
	 * 
	 * 
	 * Determines if next() would be able to return an element
	 * 
	 * @return true if there are more elements ahead in the list
	 */
	public boolean hasNext();

	/**
	 * 
	 * 
	 * Determines if previous() would return an element
	 * 
	 * @return true if there are more elements behind in the list
	 */
	public boolean hasPrevious();

	/**
	 * 
	 * 
	 * Gets the value in front of cursor and moves the cursor up
	 * 
	 * @return item at cursor position
	 */
	public T next() throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Gets the value behind of cursor and moves the cursor down
	 * 
	 * @return item at cursor position
	 * @throws NoSuchElementException if iterator can't move back
	 */
	public T previous() throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Gets the index of the next item in the list
	 * 
	 * @return
	 * @throws NoSuchElementException if iterator can't move forward
	 */
	public int nextIndex() throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Gets the index of the previous item in the list
	 * 
	 * @return
	 * @throws NoSuchElementException if iterator can't move back
	 */
	public int previousIndex() throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Removes the item in front of the cursor from the list
	 * 
	 * @throws NoSuchElementException if iterator can't move forward
	 */
	public void removeNext() throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Removes the item behind the cursor from the list
	 * 
	 * @throws NoSuchElementException if iterator can't move back
	 */
	public void removePrevious() throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Replaces the item in front of the cursor with the given item
	 * 
	 * @throws NoSuchElementException if iterator can't move forward
	 * @param the new item
	 */
	public void replaceNext(T item) throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Replaces the item behind the cursor with the given item
	 * 
	 * @param the new item
	 * @throws NoSuchElementException if iterator can't move back
	 */
	public void replacePrevious(T item) throws NoSuchElementException;

	/**
	 * 
	 * 
	 * adds the given item in front of the cursor
	 * 
	 * @throws NoSuchElementException if iterator can't move forward
	 * @param the new item
	 */
	public void addNext(T item) throws NoSuchElementException;

	/**
	 * 
	 * 
	 * adds the given item behind the cursor
	 * 
	 * @param the new item
	 * @throws NoSuchElementException if iterator can't move back
	 */
	public void addPrevious(T item) throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Moves the cursor one item ahead
	 * 
	 * @throws NoSuchElementException if iterator can't move forward
	 */
	public void skipNext() throws NoSuchElementException;

	/**
	 * 
	 * 
	 * Moves cursor one item behind
	 * 
	 * @throws NoSuchElementException if iterator can't move back
	 */
	public void skipPrevious() throws NoSuchElementException;

}
