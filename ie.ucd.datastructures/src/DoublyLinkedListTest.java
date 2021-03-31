import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoublyLinkedListTest {

	@Test
	public void testSize() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		assertEquals(0, ll.size());
		ll.addFirst(0);
		assertEquals(1, ll.size());
	}

	@Test
	public void testIsEmpty() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		assertEquals(true, ll.isEmpty());
		ll.addFirst(0);
		assertEquals(false, ll.isEmpty());
		ll.removeFirst();
		assertEquals(true, ll.isEmpty());
	}

	@Test
	public void testFirst() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		ll.addFirst(-1);
		assertEquals(-1, ll.first());
		
		ll.removeFirst();
		assertEquals(null, ll.first());
		
		
	}

	@Test
	public void testLast() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		ll.addFirst(-1);

		assertEquals(-1, ll.last());

		ll.addFirst(-2);
		assertEquals(-1, ll.last());
		
		ll.addLast(-3);
		assertEquals(-3, ll.last());
	}


	@Test
	public void testRemoveLast() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		ll.addFirst(-1);
		ll.addFirst(-2);
		assertEquals(-1, ll.removeLast());
	}

	@Test
	public void testGet() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		assertEquals(1, ll.get(1));
	}

	@Test
	public void testRemove() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		ll.remove(1);
		assertEquals("[0, 2, 3, 4]", ll.toString());
	}

	@Test
	public void testAdd() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		ll.add(2, -1);
		assertEquals("[0, 1, -1, 2, 3, 4]", ll.toString());
	}

	@Test
	public void testToString() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);

		assertEquals("[0, 1, 2, 3, 4]", ll.toString());
	}

	@Test
	public void testIterator() {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
		for(int i = 0; i < 5; ++i) ll.addLast(i);
		
		ArrayList<Integer> buf = new ArrayList<>();
		for(Integer i : ll) {
			buf.add(i);
		}
		assertEquals("[0, 1, 2, 3, 4]", buf.toString());
	}

}
