import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyArrayListTest {
	MyArrayList<String> list;
	private String arrayMessage;

	@BeforeEach
	void setUp() {
		list = new MyArrayList<>();
		list.add("Japan");
		list.add("US");
		list.add("France");
		list.add("Kenya");
		list.add("Brazil");
		arrayMessage = "Array index out of range: ";
	}

	@AfterEach
	void tearDown() {
		list = null;
		arrayMessage = null;
	}

	@Test
	void testSize() {
		assertEquals(5, list.size());
		assertEquals(10, list.maxSize());

		list.add("Germany");
		list.add("Canada");
		list.add("Argentina");
		list.add("South Africa");
		list.add("Poland");
		assertEquals(list.maxSize(), list.size());

		list.remove(3);
		assertEquals(9, list.size());

		list.add("Russia");
		list.add("Thailand");
		assertEquals(11, list.size());
		assertEquals(20, list.maxSize());

		list.remove(0);
		list.remove(0);
		assertEquals(9, list.size());
		assertEquals(20, list.maxSize());

		list.clear();
		assertEquals(0, list.size());
		assertEquals(10, list.maxSize());
		assertTrue(list.isEmpty());

	}

	@Test
	void testAdd() {
		assertEquals(5, list.size());
		assertEquals("Japan", list.get(0));
		assertEquals("US", list.get(1));
		assertEquals("France", list.get(2));
		assertEquals("Kenya", list.get(3));
		assertEquals("Brazil", list.get(4));
	}

	@Test
	void testAddList() {
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("Venezuela");
		arrayList.add("Argentina");
		arrayList.add("Mongolia");
		arrayList.add("DR");
		arrayList.add("Cuba");
		
		list.addList(arrayList);
		
		tearDown();
		setUp();
		
		list.insertList(arrayList, 0);
	}

	@Test
	void testReplace() {
		assertEquals("[Japan, US, France, Kenya, Brazil]", list.toString());
		list.replace(0, "Korea");
		assertEquals("[Korea, US, France, Kenya, Brazil]", list.toString());
		list.replace(4, "Peru");
		assertEquals("[Korea, US, France, Kenya, Peru]", list.toString());
		list.replace(2, "Sweden");
		assertEquals("[Korea, US, Sweden, Kenya, Peru]", list.toString());

		assertEquals(5, list.size());
	}

	@Test
	void testInsert() {
		// Insert(index)==========================================================
		assertEquals(5, list.size());
		assertEquals("US", list.get(1));
		list.insert(0,"Egypt");
		assertEquals(6, list.size());
		assertEquals("Japan", list.get(0));
		assertEquals("US", list.get(2));
		assertEquals("Egypt", list.get(1));

		list.insert(5,"New Zealand");
		assertEquals("Brazil", list.get(5));
		assertEquals("New Zealand", list.getLast());

		list.insert(2,"Mexico");
		assertEquals("Mexico", list.get(3));

		tearDown();
		setUp();

		Exception e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.insert(-1,"Mars");
		});
		assertEquals(arrayMessage + "-1", e.getMessage());

		Exception e2 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.insert(5, "Moon");
		});
		assertEquals(arrayMessage + "5", e2.getMessage());
		// insertFront()==================================================================

		tearDown();
		setUp();

		MyArrayList<Integer> list2 = new MyArrayList<>();

		list2.insertFront(3);
		assertEquals(3, list2.get(0));
		assertEquals(1, list2.size());

		list2.insertFront(2);
		list2.insertFront(1);

		assertEquals(3, list2.size());
		assertEquals("[1, 2, 3]", list2.toString());

	}

	@Test
	void testGet() {
		assertEquals(list.get(4), list.getLast());

		list.add("Germany");
		list.add("Canada");
		list.add("Argentina");
		list.add("South Africa");
		list.add("Poland");

		Exception e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.get(-1);
		});
		assertEquals(arrayMessage + "-1", e.getMessage());

		Exception e2 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.get(10);
		});
		assertEquals(arrayMessage + "10", e2.getMessage());
	}

	@Test
	void testRemoveIndex() {
		assertEquals(5, list.size());
		assertEquals("[Japan, US, France, Kenya, Brazil]", list.toString());

		list.remove(2);
		assertEquals(4, list.size());
		assertEquals("[Japan, US, Kenya, Brazil]", list.toString());
		assertEquals("Kenya", list.get(2));

		list.remove(3);
		assertEquals(3, list.size());
		assertEquals("[Japan, US, Kenya]", list.toString());
		assertFalse(list.contains("Brazil"));

		list.remove(0);
		assertEquals(2, list.size());
		assertEquals("[US, Kenya]", list.toString());
		assertEquals("US", list.get(0));

		tearDown();
		setUp();

		Exception e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.remove(-1);
		});
		assertEquals(arrayMessage + "-1", e.getMessage());

		Exception e2 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.remove(5);
		});
		assertEquals(arrayMessage + "5", e2.getMessage());

	}

	@Test
	void testRemoveRange() {
		list.add("Germany");
		list.add("Canada");
		list.add("Spain");
		list.add("South Africa");
		list.add("Poland");
		assertEquals(10, list.size());
		assertEquals("[Japan, US, France, Kenya, Brazil" + ", Germany, Canada, Spain, South Africa, Poland]",
				list.toString());
		list.remove(3, 6);
		assertEquals(6, list.size());
		assertEquals("[Japan, US, France, Spain, South Africa, Poland]", list.toString());

		list.remove(3, 5);
		assertEquals(3, list.size());
		assertEquals("[Japan, US, France]", list.toString());

		list.remove(0, 1);
		assertEquals(1, list.size());
		assertEquals("[France]", list.toString());
		assertEquals("France", list.getLast());
		assertEquals("France", list.get(0));

		tearDown();
		setUp();

		Exception e3 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.remove(-1, 5);
		});
		assertEquals(arrayMessage + "-1", e3.getMessage());

		Exception e4 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.remove(0, 5);
		});
		assertEquals(arrayMessage + "5", e4.getMessage());
	}

	@Test
	void testRemoveItem() {
		list.remove("Brazil");
		assertEquals("Kenya", list.getLast());
		assertFalse(list.contains("Brazil"));
		list.remove("Japan");
		assertEquals("US", list.get(0));
		assertFalse(list.contains("Japan"));
	}

	@Test
	void testRemoveAll() {
		list.add("Ecuador");
		list.add("Angola");
		list.add("Ecuador");
		list.add("China");
		list.add("Ecuador");
		list.add("Austrailia");
		list.add("Ecuador");
		list.add("Niger");
		assertEquals(4, list.numberOf("Ecuador"));

		list.removeAll("Ecuador");

		assertEquals(0, list.numberOf("Ecuador"));
		assertEquals(9, list.size());
	}

	@Test
	void testTake() {
		assertEquals(list.get(4), list.take(4));
		assertEquals("Japan", list.take(0));
		assertEquals(3, list.size());

		Exception e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.take(-1);
		});
		assertEquals(arrayMessage + "-1", e.getMessage());

		Exception e2 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.take(3);
		});
		assertEquals(arrayMessage + "3", e2.getMessage());
	}

	@Test
	void testIndexOf() {
		assertEquals(2, list.indexOf("France"));
		assertEquals(2, list.lastIndexOf("France"));

		assertEquals(4, list.indexOf("Brazil"));
		assertEquals(4, list.lastIndexOf("Brazil"));

		assertEquals(0, list.indexOf("Japan"));
		assertEquals(0, list.lastIndexOf("Japan"));

		list.add("France");
		assertEquals(2, list.indexOf("France"));
		assertEquals(5, list.lastIndexOf("France"));

		list.remove("France");
		assertEquals(4, list.indexOf("France"));
		assertEquals(4, list.lastIndexOf("France"));

		list.remove("France");
		assertEquals(-1, list.indexOf("France"));
		assertEquals(-1, list.lastIndexOf("France"));
	}

	@Test
	void testClear() {
		assertFalse(list.isEmpty());
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	void testNumberOf() {
		assertEquals(0, list.numberOf("Ecuador"));
		list.add("Ecuador");
		list.add("Angola");
		list.add("Ecuador");
		list.add("China");
		list.add("Ecuador");
		list.add("Austrailia");
		list.add("Ecuador");
		list.add("Niger");

		assertEquals(4, list.numberOf("Ecuador"));

		list.removeAll("Ecuador");

		assertEquals(0, list.numberOf("Ecuador"));
	}

	// [Japan, US, France, Kenya, Brazil]
	@Test
	void testSubList() {
		Exception e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.subList(-1, 5);
		});
		assertEquals(arrayMessage + "-1", e.getMessage());

		Exception e2 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.subList(3, 5);
		});
		assertEquals(arrayMessage + "5", e2.getMessage());

		MyArrayList<String> subList1 = list.subList(1, 3);
		MyArrayList<String> subList2 = list.subList(2, 4);
		MyArrayList<String> subList3 = list.subList(0, 2);

		assertEquals(3, subList1.size());
		assertEquals(3, subList2.size());
		assertEquals(3, subList3.size());
		assertEquals("[US, France, Kenya]", subList1.toString());
		assertEquals("[France, Kenya, Brazil]", subList2.toString());
		assertEquals("[Japan, US, France]", subList3.toString());
	}


	@Test
	void testToFile() {
	}
	
	/*
	 * Screen extender
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */


}
