import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

	@Test
	void testSize() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		assertEquals(0, ll.size());
		ll.addFirst(0);
		assertEquals(1, ll.size());
	}

	@Test
	void testIsEmpty() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		assertTrue(ll.isEmpty());
		ll.addFirst(0);
		assertFalse(ll.isEmpty());
		ll.removeFirst();
		assertTrue(ll.isEmpty());
	}

	@Test
	void testFirst() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		ll.addFirst(-1);
		assertEquals(-1, ll.first());
		
		ll.removeFirst();
		assertNull(ll.first());
		
		
	}

	@Test
	void testLast() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		ll.addFirst(-1);

		assertEquals(-1, ll.last());

		ll.addFirst(-2);
		assertEquals(-1, ll.last());
		
		ll.addLast(-3);
		assertEquals(-3, ll.last());
	}


	@Test
	void testRemoveLast() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		ll.addFirst(-1);
		ll.addFirst(-2);
		assertEquals(-1, ll.removeLast());
		assertEquals(1, ll.size());
	}

	@Test
	void testGet() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		assertEquals(1, ll.get(1));
	}

	@Test
	void testRemoveFirst() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		ll.removeFirst();
		assertEquals("[1, 2, 3, 4]", ll.toString());
	}
	@Test
	void testRemove() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		ll.remove(1);
		assertEquals("[0, 2, 3, 4]", ll.toString());
	}

	@Test
	void testAdd() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		ll.add(2, -1);
		assertEquals("[0, 1, -1, 2, 3, 4]", ll.toString());

		try{
			ll.add(-1, 5);
			fail("cannot put element at a negative index");
		} catch (IndexOutOfBoundsException ex){
			// exception was caught
		}
	}

	@Test
	void testSet() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		assertEquals(2,ll.set(2, 5));
		assertEquals("[0, 1, 5, 3, 4]", ll.toString());

		try{
			ll.set(-1, 5);
			fail();
		} catch (IndexOutOfBoundsException ex){
			// exception was caught
		}

		try{
			ll.set(7, 5);
			fail();
		} catch (IndexOutOfBoundsException ex){
			// exception was caught
		}
	}

	@Test
	void testToString() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		assertEquals("[0, 1, 2, 3, 4]", ll.toString());
	}

	@Test
	void testEquals() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		SinglyLinkedList<Integer> ll2 = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll2.addLast(i);

		assertEquals(ll2, ll)  ;
	}

	@Test
	void testIterator() {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);
		
		ArrayList<Integer> buf = new ArrayList<>();
		for(Integer i : ll) {
			buf.add(i);
		}
		assertEquals("[0, 1, 2, 3, 4]", buf.toString());
	}

}
