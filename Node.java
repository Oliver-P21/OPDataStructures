public class Node<T>{
	public T value;
	public Node<T> next;
	public Node<T> pre;
	
	public Node(T value) {
		this.value = value;
	}
	public Node(T value, Node<T> pre, Node<T> next) {
		this.value = value;
		this.pre = pre;
		this.next = next;
	}
}
