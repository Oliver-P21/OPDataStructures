import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DLinkedListTest {
	DLinkedList<String> list;
	// Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie

	@BeforeEach
	void setUp() throws Exception {
		list = new DLinkedList<>();
		list.add("Milkshakes");
		list.add("Brownies");
		list.add("Ice Cream");
		list.add("Cake");
		list.add("Cupcakes");
		list.add("Cookies");
		list.add("Pie");
	}

	@AfterEach
	void tearDown() throws Exception {
		list = null;
	}

	@Test
	void testSize() {
		assertEquals(7, list.size());
		list.add("Broccoli");
		assertEquals(8, list.size());

		for (int i = list.size() - 1; i >= 0; i--) {
			assertEquals(i + 1, list.size());
			list.remove(0);
		}

		assertEquals(0, list.size());
	}

	@Test
	void testAdd() {
		assertEquals(7, list.size());
		assertEquals("Pie", list.get(list.size() - 1));

		list.add("Gumdrops");
		assertEquals(8, list.size());
		assertEquals("Gumdrops", list.get(list.size() - 1));

		DLinkedList<Integer> newList = new DLinkedList<Integer>();

		assertEquals(0, newList.size());

		for (int i = 1; i < 10; i++) {
			newList.add(34);
			assertEquals(i, newList.size());
		}

		assertEquals(9, newList.size());
	}

	@Test
	void testReplace() throws ArrayIndexOutOfBoundsException {
		assertEquals("Milkshakes", list.get(0));
		assertEquals(7, list.size());
		

		list.replace(0, "Milkshakes suck");
		assertEquals(7, list.size());
		assertEquals("Milkshakes suck", list.get(0));


		list.replace(list.size() - 1, "Brownies are the best");
		assertEquals(7, list.size());
		assertEquals("Brownies are the best", list.getLast());
		
		list.replace(3, "Cake is mid");
		assertEquals(7, list.size());
		assertEquals("Cake is mid", list.get(3));
	

		list.replace(0, "Milkshakes are peak");
		assertEquals(7, list.size());
		assertEquals("Milkshakes are peak", list.get(0));
		
		try {
			list.replace(-1, "Carrots");
			fail("No excpetion thrown");
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				list.replace(list.size(), "Carrots");
				fail("No exception thrown");
			} catch (ArrayIndexOutOfBoundsException e2) {

			} catch (Exception e2) {
				fail("Wrong exception thrown");
			}
		} catch (Exception e) {
			fail("Wrong exception thrown");
		}
	}

	@Test
	void testInsert() throws ArrayIndexOutOfBoundsException {
		assertEquals(7, list.size());

		list.insert(6, "Gumdrops");
		assertEquals(8, list.size());
		assertEquals("Gumdrops", list.getLast());

		list.insert(0, "Smoothie");
		assertEquals(9, list.size());
		assertEquals("Smoothie", list.get(1));

		list.insert(2, "Chocolate");
		assertEquals(10, list.size());
		assertEquals("Chocolate", list.get(3));

		try {
			list.insert(-1, "Carrots");
			fail("No excpetion thrown");
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				list.insert(list.size(), "Carrots");
				fail("No exception thrown");
			} catch (ArrayIndexOutOfBoundsException e2) {

			} catch (Exception e2) {
				fail("Wrong exception thrown");
			}
		} catch (Exception e) {
			fail("Wrong exception thrown");
		}

	}

	@Test
	void testRemoveIndex() throws ArrayIndexOutOfBoundsException {
		assertEquals(7, list.size());
		assertEquals("Milkshakes", list.get(0));

		list.remove(0);
		assertEquals(6, list.size());
		assertEquals("Brownies", list.get(0));

		list.remove(5);
		assertEquals(5, list.size());
		assertEquals("Cookies", list.getLast());

		list.remove(2);
		assertEquals(4, list.size());
		assertEquals("Cupcakes", list.get(2));

		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);

		assertEquals(0, list.size());

		try {
			list.remove(0);
			fail("No excpetion thrown");
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (Exception e) {
			fail("Wrong exception thrown");
		}

	}

	@Test
	void testRemoveItem() {
		list.add("Milkshakes");
		list.remove("Milkshakes");
		assertEquals(7, list.size());
		assertEquals("Brownies", list.get(0));
		assertEquals("Milkshakes", list.get(6));
		assertEquals("[Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie, Milkshakes]", list.toString());

		list.add("Cake");
		list.remove("Cake");
		assertEquals(7, list.size());
		assertEquals("Cupcakes", list.get(2));
		assertEquals("Cake", list.get(6));
		assertEquals("[Brownies, Ice Cream, Cupcakes, Cookies, Pie, Milkshakes, Cake]", list.toString());

		list.add("Ice Cream");
		list.add("Gumdrops");

		list.remove("Ice Cream");

		assertEquals("Cupcakes", list.get(1));

		try {
			list.remove("Ain't here");
			fail("No exception thrown");
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (Exception e) {
			fail("Wrong excpetion thrown");
		}
	}

	@Test
	void testRemoveRange() throws ArrayIndexOutOfBoundsException, Exception {
		list.remove(2, 4);
		assertEquals(4, list.size());
		assertEquals("Cookies", list.get(2));

		list.remove(0, 2);
		assertEquals(1, list.size());
		assertEquals(list.get(0), list.getLast());
		assertEquals("Pie", list.getLast());

		tearDown();
		setUp();

		assertEquals(7, list.size());
		try {
			list.remove(1, 7);
			fail("No Exception thrown");
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				list.remove(-1, 6);
				fail("No Exception thrown");
			} catch (ArrayIndexOutOfBoundsException e2) {
				list.remove(0, 6);
				assertTrue(list.isEmpty());
			} catch (Exception e2) {
				fail("Wrong exception thrown");
			}
		} catch (Exception e) {
			fail("Wrong exception thrown");
		}

	}

	// Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie
	@Test
	void testRemoveAll() {
		assertEquals(7, list.size());
		list.removeAll("Cake");

		assertEquals(6, list.size());
		assertEquals("Cupcakes", list.get(3));

		list.add("Healthy Food");
		list.add("Gumdrops");
		list.add("Healthy Food");
		list.add("Smoothies");
		list.add("Healthy Food");
		list.add("Lollipops");
		list.add("Healthy Food");
		list.add("Cupcakes");

		assertEquals(14, list.size());
		assertEquals("Cupcakes", list.getLast());

		list.removeAll("Healthy Food");

		assertEquals(10, list.size());
		assertEquals("Cupcakes", list.getLast());
	}

	// Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie
	@Test
	void testGet() throws ArrayIndexOutOfBoundsException {
		assertEquals("Milkshakes", list.get(0));
		list.remove(0);
		assertEquals("Brownies", list.get(0));

		assertEquals("Pie", list.getLast());
		list.remove(list.size() - 1);
		assertEquals("Cookies", list.getLast());

		try {
			list.get(list.size());
			fail("No Exception thrown");
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				list.get(-1);
			} catch (ArrayIndexOutOfBoundsException e2) {
				assertEquals(5, list.size());
			} catch (Exception e2) {
				fail("Wrong Exception thrown");
			}
		} catch (Exception e) {
			fail("Wrong Exception thrown");
		}

	}

	// Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie
	@Test
	void testTake() {
		assertEquals(7, list.size());
		assertEquals(list.get(5), list.take(5));
		assertEquals(6, list.size());
		assertFalse(list.contains("Cookies"));
		assertEquals("Pie", list.get(5));
	}

	@Test
	void testClear() {
		assertTrue(!list.isEmpty());
		list.clear();
		assertTrue(list.isEmpty());
	}

	// Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie
	@Test
	void testIndexOf() throws Exception {
		assertEquals(0, list.indexOf("Milkshakes"));
		assertEquals(6, list.indexOf("Pie"));
		assertEquals(0, list.lastIndexOf("Milkshakes"));
		assertEquals(6, list.lastIndexOf("Pie"));

		assertEquals(2, list.indexOf("Ice Cream"));

		list.add("Milkshakes");
		list.add("Pie");
		assertEquals(0, list.indexOf("Milkshakes"));
		assertEquals(6, list.indexOf("Pie"));
		assertEquals(7, list.lastIndexOf("Milkshakes"));
		assertEquals(8, list.lastIndexOf("Pie"));

		list.removeAll("Milkshakes");
		list.removeAll("Pie");
		assertEquals(-1, list.indexOf("Milkshakes"));
		assertEquals(-1, list.indexOf("Pie"));
		assertEquals(-1, list.lastIndexOf("Milkshakes"));
		assertEquals(-1, list.lastIndexOf("Pie"));

		tearDown();
		setUp();

		assertEquals(list.indexOf("Cake"), list.lastIndexOf("Cake"));
	}

	@Test
	void testContains() {
		assertTrue(list.contains("Milkshakes"));
		assertFalse(list.contains("Lettuce"));

		list.add("Lettuce");
		assertTrue(list.contains("Lettuce"));

		list.remove("Milkshakes");
		assertFalse(list.contains("Milkshakes"));

	}

	@Test
	void testIsEmpty() {
		assertFalse(list.isEmpty());
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		assertTrue(list.isEmpty());
	}

	@Test
	void testNumberOf() {
		assertEquals(1, list.numberOf("Cupcakes"));
		assertEquals(0, list.numberOf("Burgers"));

		list.remove("Cupcakes");
		list.add("Burgers");
		assertEquals(0, list.numberOf("Cupcakes"));
		assertEquals(1, list.numberOf("Burgers"));

		list.add("Burgers");
		list.add("Burgers");
		list.add("Burgers");
		assertEquals(4, list.numberOf("Burgers"));

		list.removeAll("Burgers");
	}

	@Test
	void testToString() {
		assertEquals("[Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie]", list.toString());
		
		list.add("Gumdrops");
		assertEquals("[Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie, Gumdrops]", list.toString());
		
		list.replace(0, "Milkshakes suck");
		assertEquals("[Milkshakes suck, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie, Gumdrops]", list.toString());
		
		list.remove(0);
		assertEquals("[Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie, Gumdrops]", list.toString());
		
		list.remove("Gumdrops");
		assertEquals("[Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie]", list.toString());
	}

	@Test
	void testListToFile() {
	}

	@Test
	void testAddList() {
	}

	@Test
	void testToArrayList() {
	}

}
