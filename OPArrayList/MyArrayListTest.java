import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyArrayListTest {
	MyArrayList<String> list;
	String woex = "Wrong Exception was thrown";
	String noex = "No Exception was thrown";
	
	@BeforeEach
	void setUp() throws Exception {
		list = new MyArrayList<>();
		list.add("Japan");
		list.add("US");
		list.add("France");
		list.add("Kenya");
		list.add("Brazil");
	}

	@AfterEach
	void tearDown() throws Exception {
		list = null;
	}

	@Test
	void testBasicMethods() {
		assertEquals(5,list.size());
		assertEquals("Japan", list.get(0));
		assertEquals("US", list.get(1));
		assertEquals("France", list.get(2));
		assertEquals("Kenya", list.get(3));
		assertEquals("Brazil", list.get(4));
		assertEquals(list.get(4), list.getLast());
		
		assertFalse(list.isEmpty());
		
	}
	
	

}
