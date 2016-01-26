/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		// Test empty list
		try {
			emptyList.remove(0);
			fail("Remove: check empty list throws exception");
		} catch (IndexOutOfBoundsException e) {
		}
		
		// Test short list,
		String b = shortList.remove(1);
		assertEquals("Remove: check b is correct", "B", b);
		assertEquals("Remove: check element 0 is correct", "A", shortList.get(0));
		assertEquals("Remove: check size is corret", 1, shortList.size());
		
		// Test long list
		int c = longerList.remove(5);
		assertEquals("Remove: check c is correct", 5, c);
		assertEquals("Remove: check element 5 is correct", (Integer)6, longerList.get(5));
		assertEquals("Remove: check size is corret", 9, longerList.size());
		
		//Test corner cases
		try {
			shortList.remove(1);
			fail("Remove: check short list corner case");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			shortList.remove(-1);
			fail("Remove: check short list corner case");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			longerList.remove(9);
			fail("Remove: check longer list corner case");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		try {
			longerList.remove(-1);
			fail("Remove: check longer list corner case");
		} catch (IndexOutOfBoundsException e) {
			// TODO: handle exception
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		// test empty list,
		assertEquals("Add: check item is added to empty list",true, emptyList.add(5));
		assertEquals("Add: check last element is oorrect",(Integer)5, emptyList.get(0));
		assertEquals("Add: check size is correct ",1, emptyList.size());
		
		// Test Null Insertion
		try {
			emptyList.add(null);
			fail("Add: check null insertion to list");
		} catch (NullPointerException e) {
			
		}
		
		//test shorter list,
		assertEquals("Add: check item is added to shorter list", true, shortList.add("C"));
		assertEquals("Add: check last element is correct", "C", shortList.get(2));
		assertEquals("Add: check size is correct", 3, shortList.size());
		
		// Test Null Insertion
		try {
			shortList.add(null);
			fail("Add: check null insertion to list");
		} catch (NullPointerException e) {

		}
		
		// test longer list,
		for (int i = 10; i < 20; i++) {
			assertEquals("Add: check insertion of  " + i + " element",true, longerList.add(i));
			assertEquals("Add: check last element is correct", (Integer)i, longerList.get(i));
			assertEquals("Add: check size is correct", (i + 1), longerList.size());
		}
		
		// Test Null Insertion
		try {
			longerList.add(null);
			fail("Add: check null insertion to list");
		} catch (NullPointerException e) {
			
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		
		//Test empty  list size.
		assertEquals("check size", 0, emptyList.size());
		
		// Test short list size.
		assertEquals("check size", 2, shortList.size());
		
		// Test long list size.
		assertEquals("check size", 10, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		
		// Test empty list, first contents, then out of bounds
		emptyList.add(0, 77);
		assertEquals("check insertion to empty list", (Integer)77, emptyList.get(0));
		
		try {
			emptyList.add(2, 88);
			fail("check out of upper bounds");
		} catch (IndexOutOfBoundsException ex) {
			
		}
		
		try {
			emptyList.add(-1, 99);
			fail("check out of lower bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		// Test Null Insertion
		try {
			emptyList.add(0, null);
			fail("Add: check null insertion to list");
		} catch (NullPointerException e) {

		}
		
		// Test short list, first contents , then out of bounds
		shortList.add(1, "a");
		assertEquals("check insertion in short list", "a", shortList.get(1));
		
		try {
			shortList.add(10, "C");
			fail("check out of upper bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.add(-1, "D");
			fail("check out of lower bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		// Test Null Insertion
		try {
			shortList.add(1, null);
			fail("Add: check null insertion to list");
		} catch (NullPointerException e) {

		}
		
		// Test longer list, first contents, then out of bounds
		longerList.add(5, 77);
		longerList.add(8, 88);
		assertEquals("check insertion in long list", (Integer)77, longerList.get(5));
		assertEquals("check insertion in long list", (Integer)88, longerList.get(8));
		
		try {
			longerList.add(13, 99);
			fail("check out of upper bounds");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			longerList.add(-1, 100);
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		// Test Null Insertion
		try {
			longerList.add(3, null);
			fail("Add: check null insertion to list");
		} catch (NullPointerException e) {

		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		
		//Test empty list
		try {
			emptyList.set(0, 77);
			fail("Set: Check bounds of empty list");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			emptyList.set(0, null);
			fail("Set: Check Null insertion to list");
		} catch (NullPointerException e) {
			
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		//Test short list
		String a = shortList.set(1, "C");
		assertEquals("Set: check item a is correct", a, "B");
		assertEquals("Set: check element 1 is correct", "C", shortList.get(1));
		assertEquals("Set: check size is correct", 2, shortList.size());
		
		try {
			shortList.set(2, "D");
			fail("Set: Check uppper bounds of list");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.set(-1, "D");
			fail("Set: Check lower bounds of list");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.set(1, null);
			fail("Set: Check Null insertion to list");
		} catch (NullPointerException e) {
			
		}
		
		//Test long list
		Integer b = null;
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			b = longerList.set(i, (i + LONG_LIST_LENGTH));
			assertEquals("Set: check item b is correct", b, (Integer)i);
			assertEquals("Set: check element " + i + " is correct", (Integer)(i + LONG_LIST_LENGTH), longerList.get(i));
			assertEquals("Set: check size is correct", 10, longerList.size());
			
		}
		
		// Test corner cases
		try {
			longerList.set(10, 20);
			fail("Set: Check uppper bounds of list");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			longerList.set(-1, 30);
			fail("Set: Check lower bounds of list");
		} catch (IndexOutOfBoundsException e) {
			
		}
		try {
			longerList.set(4, null);
			fail("Set: Check Null insertion to list");
		} catch (NullPointerException e) {
			
		}
		
	}
	
	
	// TODO: Optionally add more test methods.
	
}
