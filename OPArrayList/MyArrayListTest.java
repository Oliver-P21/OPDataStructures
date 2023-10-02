import static org.junit.jupiter.api.Assertions.*;

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
	void testBasicMethods() {
		assertEquals(5, list.size());
		assertEquals("Japan", list.get(0));
		assertEquals("US", list.get(1));
		assertEquals("France", list.get(2));
		assertEquals("Kenya", list.get(3));
		assertEquals("Brazil", list.get(4));
		assertEquals(list.get(4), list.getLast());

		assertFalse(list.isEmpty());
		assertTrue(list.contains("France"));
		assertFalse(list.contains("Germany"));

		list.add("Germany");
		list.add("Canada");
		list.add("Argentina");
		list.add("South Africa");
		list.add("Poland");

		assertEquals(list.size(), list.maxSize());
		assertEquals(10, list.size());
		list.add("Spain");
		assertEquals(11, list.size());
		assertEquals(20, list.maxSize());
		assertTrue(list.contains("Germany"));

		Exception e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.get(-1);
		});
		assertEquals(arrayMessage + "-1", e.getMessage());

		Exception e2 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.get(11);
		});
		assertEquals(arrayMessage + "11", e2.getMessage());
	}

	// [Japan, Egypt, US, France, Kenya, Brazil, New Zealand]
	@Test
	void testInsert() {
		assertEquals(5, list.size());
		assertEquals("US", list.get(1));
		list.insert("Egypt", 0);
		assertEquals(6, list.size());
		assertEquals("Japan", list.get(0));
		assertEquals("US", list.get(2));
		assertEquals("Egypt", list.get(1));

		list.insert("New Zealand", 5);
		assertEquals("Brazil", list.get(5));
		assertEquals("New Zealand", list.getLast());

		list.insert("Mexico", 2);
		assertEquals("Mexico", list.get(3));

		tearDown();
		setUp();

		Exception e = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.insert("Mars", -1);
		});
		assertEquals(arrayMessage + "-1", e.getMessage());

		Exception e2 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
			list.insert("Moon", 5);
		});
		assertEquals(arrayMessage + "5", e2.getMessage());

	}
	
	
}
