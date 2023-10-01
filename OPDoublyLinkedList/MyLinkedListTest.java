import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {
	MyLinkedList<String> list;
	String noex = "No Exception was thrown";
	String woex = "Wrong Exception thrown";
	// Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie

	@BeforeEach
	void setUp() {
		list = new MyLinkedList<>();
		list.add("Milkshakes");
		list.add("Brownies");
		list.add("Ice Cream");
		list.add("Cake");
		list.add("Cupcakes");
		list.add("Cookies");
		list.add("Pie");
	}

	@AfterEach
	void tearDown() {
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

		MyLinkedList<Integer> newList = new MyLinkedList<Integer>();

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
	void testInsert() throws Exception {
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

		tearDown();
		setUp();
		list.insertFront("Gumdrops");
		assertEquals("Gumdrops", list.get(0));
		assertEquals(8, list.size());

		tearDown();
		setUp();
		list.clear();
		list.insertFront("Last fry in the bag");
		assertEquals("Last fry in the bag", list.get(0));
		assertEquals(list.get(0), list.getLast());
		assertEquals(1, list.size());

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
		assertEquals("[Brownies, Ice Cream, Cake,"
				+ " Cupcakes, Cookies, Pie, Milkshakes]", list.toString());

		list.add("Cake");
		list.remove("Cake");
		assertEquals(7, list.size());
		assertEquals("Cupcakes", list.get(2));
		assertEquals("Cake", list.get(6));
		assertEquals("[Brownies, Ice Cream, Cupcakes,"
				+ " Cookies, Pie, Milkshakes, Cake]", list.toString());

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

	// Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie
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

		list.remove(2, 2);
		assertFalse(list.contains("Ice Cream"));
		assertEquals("Cake", list.get(2));

		tearDown();
		setUp();

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
		assertEquals("[Milkshakes, Brownies, Ice Cream,"
				+ " Cake, Cupcakes, Cookies, Pie]", list.toString());

		list.add("Gumdrops");
		assertEquals("[Milkshakes, Brownies, Ice Cream,"
				+ " Cake, Cupcakes, Cookies, Pie, Gumdrops]", list.toString());

		list.replace(0, "Milkshakes suck");
		assertEquals("[Milkshakes suck, Brownies, Ice Cream,"
				+ " Cake, Cupcakes, Cookies, Pie, Gumdrops]", list.toString());

		list.remove(0);
		assertEquals("[Brownies, Ice Cream, Cake,"
				+ " Cupcakes, Cookies, Pie, Gumdrops]", list.toString());

		list.remove("Gumdrops");
		assertEquals("[Brownies, Ice Cream, Cake,"
				+ " Cupcakes, Cookies, Pie]", list.toString());
	}

	@Test
	void testListToFile() {
		try {
			list.listToFile("dessert.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner sin = null;
		try {
			sin = new Scanner(new File("dessert.txt"));
			for (int i = 0; sin.hasNext(); i++) {
				assertEquals(sin.nextLine(), list.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sin.close();
		}
	}

	@Test
	void testToArrayList() {
		ArrayList<String> aList = list.toArrayList();
		assertEquals(list.size(), aList.size());

		for (int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i), list.get(i));
		}
	}

	@Test
	void testAddList() {
		ArrayList<String> aList = new ArrayList<>();
		aList.add("Broccoli");
		aList.add("Spinach");
		aList.add("Carrots");
		aList.add("Fries");
		
		list.addList(aList);
		assertEquals(11, list.size());
		for(int i = 0; i < aList.size(); i++) {
			assertEquals(aList.get(i), list.get(i + 7));
		}
		
		tearDown();
		setUp();
		
		list.insertList(aList, 1);
		assertEquals("[Milkshakes, Broccoli, Spinach, Carrots, Fries, "
				+ "Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie]", list.toString());
		
		tearDown();
		setUp();
		
		list.insertList(aList, 0);
		assertEquals("[Broccoli, Spinach, Carrots, Fries, Milkshakes,"
				+ " Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie]", list.toString());
		
		try {
			list.insertList(aList, -1);
			fail(noex);
		}
		catch(ArrayIndexOutOfBoundsException e) {
			try {
				list.insertList(aList, 90);
				fail(noex);
			}
			catch(ArrayIndexOutOfBoundsException e2) {
				assertTrue(true);
			}
			catch (Exception e2) {
				fail(woex);
			}
		}
		catch(Exception e) {
			fail(woex);
		}
	}

	// "[Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie]"
	@Test
	void testSubList() {
		MyLinkedList<String> subList = list.subList(2, 5);
		assertEquals("[Ice Cream, Cake, Cupcakes, Cookies]", subList.toString());

		subList = list.subList(0, 2);
		assertEquals("[Milkshakes, Brownies, Ice Cream]", subList.toString());

		subList = list.subList(5, list.size() - 1);
		assertEquals("[Cookies, Pie]", subList.toString());

		subList = list.subList(0, 6);
		assertEquals("[Milkshakes, Brownies,"
				+ " Ice Cream, Cake, Cupcakes, Cookies, Pie]", subList.toString());

		try {
			subList = list.subList(-1, 3);
			fail(noex);
		} catch (ArrayIndexOutOfBoundsException e) {
			try {
				subList = list.subList(4, 8);
				fail(noex);
			} catch (ArrayIndexOutOfBoundsException e2) {
				assertTrue(list.equals(subList));
			} catch (Exception e2) {
				fail(woex);
			}
		} catch (Exception e) {
			fail(woex);
		}
	}

	@Test
	void testEquals() throws Exception {
		MyLinkedList<String> newList = new MyLinkedList<>();
		newList.add("Milkshakes");
		newList.add("Brownies");
		newList.add("Ice Cream");
		newList.add("Cake");
		newList.add("Cupcakes");
		newList.add("Cookies");
		newList.add("Pie");

		assertTrue(list.equals(newList) && newList.equals(list));

		MyLinkedList<String> refList = newList;
		assertTrue(refList.equals(newList) && newList.equals(refList));
		assertTrue(list.equals(refList) && refList.equals(list));
		assertTrue(list.equals(list));

		newList.remove(0);
		assertFalse(list.equals(newList) && newList.equals(list));

		MyLinkedList<Integer> numList = new MyLinkedList<>();
		numList.add(1);
		numList.add(2);
		numList.add(3);
		numList.add(4);
		numList.add(5);
		MyLinkedList<String> sList = new MyLinkedList<>();
		sList.add("1");
		sList.add("2");
		sList.add("3");
		sList.add("4");
		sList.add("5");
		assertFalse(sList.equals(newList));

		tearDown();
		setUp();

		MyLinkedList<String> testList = new MyLinkedList<>();
		testList.add("Milkshakes");
		testList.add("Brownies");
		testList.add("Ice Cream");
		testList.add("Cake");
		testList.add("Cupcakes");
		testList.add("Cookies");
		testList.add("Pie");

		assertTrue(testList.equals(list));

		list.replace(0, "Milkshake");

		assertFalse(testList.equals(list));

	}

}
