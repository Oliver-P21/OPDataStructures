/**
 * Interface used for classes to implements a MyIterator
 * @author OP
 *
 * @param <T> the type of object that the MyIterator iterates over
 */
public interface MyIterable<T> {
	
	/**
	 * Generates a MyIterator of the a collection
	 * @return an iterator
	 */
	public MyListIterator<T> getIterator();
		
	
}
