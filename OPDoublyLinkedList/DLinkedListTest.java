
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DLinkedListTest {
	DLinkedList<String> list;
	
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
		assertEquals(7,list.size());
		list.add("Broccoli");
		assertEquals(8,list.size());
		
		for(int i = list.size() - 1; i >= 0; i-- ) {
			assertEquals(i + 1,list.size());
			list.remove(0);
		}
		
		assertEquals(0 , list.size());	
	}

	@Test
	void testAdd() {
		assertEquals(7, list.size());
		assertEquals("[Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie]", list.toString());
		assertEquals("Pie", list.get(list.size() - 1));
		
		list.add("Gumdrops");
		
		assertEquals(8, list.size());
		assertEquals("[Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie, Gumdrops]", list.toString());
		assertEquals("Gumdrops", list.get(list.size() - 1));
		
		DLinkedList<Integer> newList = new DLinkedList<Integer>();
		
		assertEquals(0, newList.size());
		
		for(int i = 1; i < 10; i++) {
			newList.add(34);
			assertEquals(i,newList.size());
		}
		
		assertEquals(9, newList.size());
	}

	@Test
	void testReplace() throws ArrayIndexOutOfBoundsException {
		assertEquals("Milkshakes",list.get(0));
		assertEquals(7, list.size());
		assertEquals("[Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie]", list.toString());
		
		list.replace(0, "Milkshakes suck");
		assertEquals(7, list.size());
		assertEquals("Milkshakes suck", list.get(0));
		assertEquals("[Milkshakes suck, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie]", list.toString());
		
		list.replace(list.size() - 1, "Brownies are the best");
		assertEquals(7, list.size());
		assertEquals("Brownies are the best", list.getLast());
		assertEquals("[Milkshakes suck, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Brownies are the best]", list.toString());
		
		list.replace(3, "Cake is mid");
		assertEquals(7, list.size());
		assertEquals("Cake is mid", list.get(3));
		assertEquals("[Milkshakes suck, Brownies, Ice Cream, Cake is mid, Cupcakes, Cookies, Brownies are the best]", list.toString());
		
		list.replace(0, "Milkshakes are peak");
		assertEquals(7, list.size());
		assertEquals("Milkshakes are peak", list.get(0));
		assertEquals("[Milkshakes are peak, Brownies, Ice Cream, Cake is mid, Cupcakes, Cookies, Brownies are the best]", list.toString());
		
		try {
			list.replace(-1, "Carrots");
			fail("No excpetion thrown");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			try {
				list.replace(list.size(), "Carrots");
				fail("No exception thrown");
			}
			catch(ArrayIndexOutOfBoundsException e2) {
				
			}
			catch(Exception e2) {
				fail("Wrong exception thrown");
			}
		}
		catch(Exception e) {
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
		assertEquals(9 , list.size());
		assertEquals("Smoothie", list.get(1));
		
		list.insert(2, "Chocolate");
		assertEquals(10, list.size());
		assertEquals("Chocolate", list.get(3));
		
		try {
			list.insert(-1, "Carrots");
			fail("No excpetion thrown");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			try {
				list.insert(list.size(), "Carrots");
				fail("No exception thrown");
			}
			catch(ArrayIndexOutOfBoundsException e2) {
				
			}
			catch(Exception e2) {
				fail("Wrong exception thrown");
			}
		}
		catch(Exception e) {
			fail("Wrong exception thrown");
		}
		
	}
	
	//Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie
	@Test
	void testRemoveIndex() throws ArrayIndexOutOfBoundsException {
		assertEquals(7,list.size());
		assertEquals("Milkshakes", list.get(0));
		
		list.remove(0);
		assertEquals(6,list.size());
		assertEquals("Brownies", list.get(0));
		
		list.remove(5);
		assertEquals(5, list.size());
		assertEquals("Cookies", list.getLast());
		
		list.remove(2);
		assertEquals(4,list.size());
		assertEquals("Cupcakes", list.get(2));
		
		list.remove(0);
		list.remove(0);
		list.remove(0);
		list.remove(0);
		
		assertEquals(0, list.size());
		
		try {
			list.remove(0);
			fail("No excpetion thrown");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			
		}
		catch(Exception e) {
			fail("Wrong exception thrown");
		}
		
	}
	//Brownies, Ice Cream, Cupcakes, Cookies, Pie, Milkshakes, Cake,
	
	@Test
	void testRemoveItem() {
		list.add("Milkshakes");
		list.remove("Milkshakes");
		assertEquals(7, list.size());
		assertEquals("Brownies", list.get(0));
		assertEquals("Milkshakes",list.get(6));
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
		}
		catch(ArrayIndexOutOfBoundsException e) {
			
		}
		catch(Exception e) {
			fail("Wrong excpetion thrown");
		}
	}
	
	//Milkshakes, Brownies, Ice Cream, Cake, Cupcakes, Cookies, Pie
	// Milkshakes, Brownies, Cookies, Pie
	@Test
	void testRemoveRange() throws Exception {
		list.remove(2,4);
		assertEquals(4,list.size());
		assertEquals("Cookies", list.get(2));
		
		list.remove(0,2);
		assertEquals(1, list.size());
		assertEquals(list.get(0), list.getLast());
		assertEquals("Pie", list.getLast());
		
		tearDown();
		setUp();
		
		
	}

	@Test
	void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	void testGet() {
		fail("Not yet implemented");
	}

	@Test
	void testTake() {
		fail("Not yet implemented");
	}

	@Test
	void testClear() {
		fail("Not yet implemented");
	}

	@Test
	void testIndexOf() {
		fail("Not yet implemented");
	}

	@Test
	void testLastIndexOf() {
		fail("Not yet implemented");
	}

	@Test
	void testContains() {
		fail("Not yet implemented");
	}

	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	void testNumberOf() {
		fail("Not yet implemented");
	}

	@Test
	void testListToFile() {
		fail("Not yet implemented");
	}

	@Test
	void testAddList() {
		fail("Not yet implemented");
	}

	@Test
	void testToArrayList() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
