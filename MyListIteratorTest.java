import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyListIteratorTest {
	MyLinkedList<String> linkedList;
	MyArrayList<String> arrayList;
	ArrayList<String> normalList;
	MyListIterator<String> litr;
	MyListIterator<String> aitr;
	ListIterator<String> norItr;
	private final String noNextMessage = "Iterator has reached end of list";
	private final String noPreMessage = "Iterator has reached beginning of list";

	@BeforeEach
	void setUp() {
		linkedList = new MyLinkedList<>();
		arrayList = new MyArrayList<>();
		normalList = new ArrayList<>();

		linkedList.add("Fanta");
		linkedList.add("Sprite");
		linkedList.add("MtDew");
		linkedList.add("Dr.Pepper");
		linkedList.add("Coke");

		arrayList.add("Fanta");
		arrayList.add("Sprite");
		arrayList.add("MtDew");
		arrayList.add("Dr.Pepper");
		arrayList.add("Coke");

		normalList.add("Fanta");
		normalList.add("Sprite");
		normalList.add("MtDew");
		normalList.add("Dr.Pepper");
		normalList.add("Coke");

		litr = linkedList.getIterator();
		aitr = arrayList.getIterator();
		norItr = normalList.listIterator();

	}

	@AfterEach
	void tearDown() {
		linkedList = null;
		arrayList = null;
		normalList = null;
	}

	@Test
	void testEquality() {
		assertTrue(arrayList.myListEquals(linkedList));
		assertTrue(linkedList.myListEquals(arrayList));
		assertTrue(linkedList.listEquals(normalList));
		assertTrue(arrayList.listEquals(normalList));
	}

	@Test
	void testHasNext() {
		assertTrue(litr.hasNext());

		SkipNext(litr, 4);
		assertTrue(litr.hasNext());

		litr.skipNext();
		assertFalse(litr.hasNext());
		// ==============================
		assertTrue(aitr.hasNext());

		SkipNext(aitr, 4);
		assertTrue(aitr.hasNext());

		aitr.skipNext();
		assertFalse(aitr.hasNext());

	}

	@Test
	void testHasPrevious() {
		assertFalse(litr.hasPrevious());

		litr.skipNext();
		assertTrue(litr.hasPrevious());

		SkipNext(litr, 4);
		assertTrue(litr.hasPrevious());

		SkipPrevious(litr, 4);
		assertTrue(litr.hasPrevious());

		litr.skipPrevious();
		assertFalse(litr.hasPrevious());
		// =========================================

		assertFalse(aitr.hasPrevious());

		aitr.skipNext();
		assertTrue(aitr.hasPrevious());

		SkipNext(aitr, 4);
		assertTrue(aitr.hasPrevious());

		SkipPrevious(aitr, 4);
		assertTrue(aitr.hasPrevious());

		aitr.skipPrevious();
		assertFalse(aitr.hasPrevious());

	}

	@Test
	void testNext() {
		assertEquals(linkedList.get(0), litr.next());
		assertEquals(linkedList.get(1), litr.next());
		assertEquals(linkedList.get(2), litr.next());
		assertEquals(linkedList.get(3), litr.next());
		assertEquals(linkedList.get(4), litr.next());

		assertEquals(arrayList.get(0), aitr.next());
		assertEquals(arrayList.get(1), aitr.next());
		assertEquals(arrayList.get(2), aitr.next());
		assertEquals(arrayList.get(3), aitr.next());
		assertEquals(arrayList.get(4), aitr.next());

		litr.skipPrevious();
		aitr.skipPrevious();

		assertEquals(linkedList.get(4), litr.next());
		assertEquals(arrayList.get(4), aitr.next());

		ArrayList<String> testlist = new ArrayList<>();
		ArrayList<String> testlist2 = new ArrayList<>();
		tearDown();
		setUp();

		while (litr.hasNext()) {
			testlist.add(litr.next());
		}

		while (aitr.hasNext()) {
			testlist2.add(aitr.next());
		}

		assertTrue(linkedList.listEquals(testlist));
		assertTrue(linkedList.listEquals(testlist2));
		assertTrue(arrayList.listEquals(testlist));
		assertTrue(arrayList.listEquals(testlist2));

		Exception e = assertThrows(NoSuchElementException.class, () -> {
			testlist.add(litr.next());
		});

		Exception e2 = assertThrows(NoSuchElementException.class, () -> {
			testlist2.add(aitr.next());
		});

		assertEquals(noNextMessage, e.getMessage());
		assertEquals(noNextMessage, e2.getMessage());

	}

	@Test
	void testPrevious() {
		SkipNext(litr, 5);
		SkipNext(aitr, 5);

		assertEquals(linkedList.get(4), litr.previous());
		assertEquals(linkedList.get(3), litr.previous());
		assertEquals(linkedList.get(2), litr.previous());
		assertEquals(linkedList.get(1), litr.previous());
		assertEquals(linkedList.get(0), litr.previous());

		assertEquals(arrayList.get(4), aitr.previous());
		assertEquals(arrayList.get(3), aitr.previous());
		assertEquals(arrayList.get(2), aitr.previous());
		assertEquals(arrayList.get(1), aitr.previous());
		assertEquals(arrayList.get(0), aitr.previous());

		litr.skipNext();
		aitr.skipNext();

		assertEquals(linkedList.get(0), litr.previous());
		assertEquals(arrayList.get(0), aitr.previous());

		ArrayList<String> testlist = new ArrayList<>();
		ArrayList<String> testlist2 = new ArrayList<>();

		tearDown();
		setUp();

		SkipNext(litr, 5);
		SkipNext(aitr, 5);

		while (litr.hasPrevious()) {
			testlist.add(litr.previous());
		}

		while (aitr.hasPrevious()) {
			testlist2.add(aitr.previous());
		}

		for (int i = 0; i < testlist.size(); i++) {
			assertEquals(testlist.get(i), linkedList.get(linkedList.size() - i - 1));
			assertEquals(testlist2.get(i), arrayList.get(arrayList.size() - i - 1));
		}

		Exception e = assertThrows(NoSuchElementException.class, () -> {
			testlist.add(litr.previous());
		});

		Exception e2 = assertThrows(NoSuchElementException.class, () -> {
			testlist2.add(aitr.previous());
		});

		assertEquals(noPreMessage, e.getMessage());
		assertEquals(noPreMessage, e2.getMessage());

	}

	@Test
	void testIndexes() {
		for (int i = 0; litr.hasNext(); i++) {
			assertEquals(i, litr.nextIndex());
			litr.skipNext();
		}
		assertEquals(5, litr.nextIndex());

		for (int i = linkedList.size() - 1; litr.hasPrevious(); i--) {
			assertEquals(i, litr.previousIndex());
			litr.skipPrevious();
		}
		assertEquals(-1, litr.previousIndex());

		for (int i = 0; aitr.hasNext(); i++) {
			assertEquals(i, aitr.nextIndex());
			aitr.skipNext();
		}
		assertEquals(5, aitr.nextIndex());

		for (int i = arrayList.size() - 1; aitr.hasPrevious(); i--) {
			assertEquals(i, aitr.previousIndex());
			aitr.skipPrevious();
		}
		assertEquals(-1, aitr.previousIndex());
	}

	// Sprite, MtDew, Dr.Pepper, Coke
	@Test
	void testRemoveNext() {
		litr.removeNext();
		assertEquals("Sprite", litr.next());
		assertFalse(linkedList.contains("Fanta"));

		litr.removeNext();
		assertEquals("Dr.Pepper", litr.next());
		assertFalse(linkedList.contains("MtDew"));
		SkipNext(litr, 1);

		Exception e = assertThrows(NoSuchElementException.class, () -> {
			litr.removeNext();
		});

		assertEquals(noNextMessage, e.getMessage());

		// ==========================================================

		aitr.removeNext();
		assertEquals("Sprite", aitr.next());
		assertFalse(arrayList.contains("Fanta"));

		aitr.removeNext();
		assertEquals("Dr.Pepper", aitr.next());
		assertFalse(arrayList.contains("MtDew"));
		SkipNext(aitr, 1);

		Exception e2 = assertThrows(NoSuchElementException.class, () -> {
			aitr.removeNext();
		});

		assertEquals(noNextMessage, e2.getMessage());

		assertTrue(linkedList.myListEquals(arrayList));
		assertEquals(3, linkedList.size());
		assertEquals(3, arrayList.size());
	}

	@Test
	void testRemovePrevious() {
		Exception e = assertThrows(NoSuchElementException.class, () -> {
			litr.removePrevious();
		});

		assertEquals(noPreMessage, e.getMessage());

		SkipNext(litr, 5);

		litr.removePrevious();
		assertEquals("Dr.Pepper", litr.previous());
		assertFalse(linkedList.contains("Coke"));

		litr.removePrevious();
		assertEquals("Sprite", litr.previous());
		assertFalse(linkedList.contains("MtDew"));

		// ==========================================================

		Exception e2 = assertThrows(NoSuchElementException.class, () -> {
			aitr.removePrevious();
		});

		assertEquals(noPreMessage, e2.getMessage());

		SkipNext(aitr, 5);

		aitr.removePrevious();
		assertEquals("Dr.Pepper", aitr.previous());
		assertFalse(arrayList.contains("Coke"));

		aitr.removePrevious();
		assertEquals("Sprite", aitr.previous());
		assertFalse(arrayList.contains("MtDew"));

		assertTrue(linkedList.myListEquals(arrayList));
		assertEquals(3, linkedList.size());
		assertEquals(3, arrayList.size());

	}

	@Test
	void testReplaceNext() {
		litr.replaceNext("Pepsi");
		assertEquals("Pepsi", litr.next());
		assertEquals("Pepsi", linkedList.get(0));
		assertFalse(linkedList.contains("Fanta"));

		litr.skipNext();
		litr.replaceNext("Crush");
		assertEquals("Crush", litr.next());
		assertEquals("Crush", linkedList.get(2));
		assertFalse(linkedList.contains("MtDew"));

		SkipNext(litr, 2);

		Exception e = assertThrows(NoSuchElementException.class, () -> {
			litr.replaceNext("Water");
		});

		assertEquals(noNextMessage, e.getMessage());
		// ======================================================
		aitr.replaceNext("Pepsi");
		assertEquals("Pepsi", aitr.next());
		assertEquals("Pepsi", arrayList.get(0));
		assertFalse(arrayList.contains("Fanta"));

		aitr.skipNext();
		aitr.replaceNext("Crush");
		assertEquals("Crush", aitr.next());
		assertEquals("Crush", arrayList.get(2));
		assertFalse(arrayList.contains("MtDew"));

		SkipNext(aitr, 2);

		Exception e2 = assertThrows(NoSuchElementException.class, () -> {
			aitr.replaceNext("Water");
		});

		assertEquals(noNextMessage, e2.getMessage());

		assertTrue(linkedList.myListEquals(arrayList));
		assertEquals(5, linkedList.size());
		assertEquals(5, arrayList.size());
	}

	@Test
	void testReplacePrevious() {
		litr.replaceNext("Pepsi");
		assertEquals("Pepsi", litr.next());
		assertEquals("Pepsi", linkedList.get(0));
		assertFalse(linkedList.contains("Fanta"));

		litr.skipNext();
		litr.replaceNext("Crush");
		assertEquals("Crush", litr.next());
		assertEquals("Crush", linkedList.get(2));
		assertFalse(linkedList.contains("MtDew"));

		SkipNext(litr, 2);

		Exception e = assertThrows(NoSuchElementException.class, () -> {
			litr.replaceNext("Water");
		});

		assertEquals(noNextMessage, e.getMessage());
		// ======================================================
		aitr.replaceNext("Pepsi");
		assertEquals("Pepsi", aitr.next());
		assertEquals("Pepsi", arrayList.get(0));
		assertFalse(arrayList.contains("Fanta"));

		aitr.skipNext();
		aitr.replaceNext("Crush");
		assertEquals("Crush", aitr.next());
		assertEquals("Crush", arrayList.get(2));
		assertFalse(arrayList.contains("MtDew"));

		SkipNext(aitr, 2);

		Exception e2 = assertThrows(NoSuchElementException.class, () -> {
			aitr.replaceNext("Water");
		});

		assertEquals(noNextMessage, e2.getMessage());

		assertTrue(linkedList.myListEquals(arrayList));
	}

	// [Fanta, Sprite, MtDew, Dr.Pepper, Coke]
	@Test
	void testAddNext() {
		litr.addNext("Starry");
		assertEquals("Starry", linkedList.get(0));
		assertEquals(linkedList.get(0), litr.next());
		assertEquals(6, linkedList.size());

		litr.addNext("7Up");
		assertEquals("7Up", linkedList.get(1));
		assertEquals("Fanta", linkedList.get(2));
		assertEquals(linkedList.get(1), litr.next());
		assertEquals(7, linkedList.size());
		assertEquals("Fanta", litr.next());

		SkipNext(litr, 4);

		litr.addNext("A&W");
		assertEquals("A&W", linkedList.getLast());
		assertEquals("A&W", litr.next());
		assertEquals(8, linkedList.size());
		// =============================================

		aitr.addNext("Starry");
		assertEquals("Starry", arrayList.get(0));
		assertEquals(arrayList.get(0), aitr.next());
		assertEquals(6, arrayList.size());

		aitr.addNext("7Up");
		assertEquals("7Up", arrayList.get(1));
		assertEquals("Fanta", arrayList.get(2));
		assertEquals(arrayList.get(1), aitr.next());
		assertEquals(7, arrayList.size());
		assertEquals("Fanta", aitr.next());

		SkipNext(aitr, 4);

		aitr.addNext("A&W");
		assertEquals("A&W", arrayList.getLast());
		assertEquals("A&W", aitr.next());
		assertEquals(8, arrayList.size());

	}

	// [Fanta, Sprite, MtDew, Dr.Pepper, Coke, 7Up, Starry]
	@Test
	void testAddPrevious() {
		SkipNext(litr, 5);

		litr.addPrevious("Starry");
		assertEquals("Starry", linkedList.getLast());
		assertEquals(linkedList.get(5), litr.previous());
		assertEquals(6, linkedList.size());

		litr.addPrevious("7Up");
		assertEquals("7Up", linkedList.get(5));
		assertEquals("Coke", linkedList.get(4));
		assertEquals(linkedList.get(5), litr.previous());
		assertEquals(7, linkedList.size());
		assertEquals("Coke", litr.previous());

		SkipPrevious(litr, 4);

		litr.addPrevious("A&W");
		assertEquals("A&W", linkedList.get(0));
		assertEquals("A&W", litr.previous());
		assertEquals(8, linkedList.size());

		// ===================================================

		SkipNext(aitr, 5);

		aitr.addPrevious("Starry");
		assertEquals("Starry", arrayList.getLast());
		assertEquals(arrayList.get(5), aitr.previous());
		assertEquals(6, arrayList.size());

		aitr.addPrevious("7Up");
		assertEquals("7Up", arrayList.get(5));
		assertEquals("Coke", arrayList.get(4));
		assertEquals(arrayList.get(5), aitr.previous());
		assertEquals(7, arrayList.size());
		assertEquals("Coke", aitr.previous());

		SkipPrevious(aitr, 4);
		arrayList.display();
		arrayList.displayBackwards();
		
		aitr.addPrevious("A&W");
		assertEquals("A&W", arrayList.get(0));
		assertEquals("A&W", aitr.previous());
		assertEquals(8, arrayList.size());
	}

	private void SkipNext(MyListIterator<?> itr, int total) {
		int num = 0;
		while (num != total) {
			itr.skipNext();
			num++;
		}
	}

	private void SkipPrevious(MyListIterator<?> itr, int total) {
		int num = 0;
		while (num != total) {
			itr.skipPrevious();
			num++;
		}
	}

	@SuppressWarnings("unused")
	private void testNavigate() {
		System.out.println("LinkedList");
		linkedList.display();
		System.out.println();
		linkedList.displayBackwards();

		System.out.println();
		System.out.println();

		System.out.println("ArrayList");
		arrayList.display();
		System.out.println();
		arrayList.displayBackwards();

		System.out.println();
		System.out.println("================================================");
	}
}
