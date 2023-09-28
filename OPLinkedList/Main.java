
public class Main {
	public static void main(String[] args) {
		OPLinkedList<Integer> list = new OPLinkedList<>();
		list.addBack(1);
		list.addBack(2);
		list.addBack(3);
		list.addBack(4);
		
		
		list.removeBack();
		list.removeBack();
		list.removeBack();
		
		list.displayList();
		
	}
	
}
