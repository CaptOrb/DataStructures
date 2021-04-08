import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CircularlyLinkedListTest {

    @Test
    void testSize() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        assertEquals(0, ll.size());
        ll.addFirst(0);
        assertEquals(1, ll.size());
    }

    @Test
    void testIsEmpty() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        assertTrue(ll.isEmpty());
        ll.addFirst(0);
        assertFalse(ll.isEmpty());
        ll.removeFirst();
        assertTrue(ll.isEmpty());
    }

    @Test
    void testFirst() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        ll.addFirst(-1);
        assertEquals(-1, ll.first());
    }

    @Test
    void testLast() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        ll.addFirst(-1);

        assertEquals(-1, ll.last());

        ll.addFirst(-2);
        assertEquals(-1, ll.last());

        ll.addLast(-3);
        assertEquals(-3, ll.last());
    }


    @Test
    void testGet() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        assertEquals(1, ll.get(1));
    }

    @Test
    void testRemoveFirst() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        ll.removeFirst();
        assertEquals("[1, 2, 3, 4]", ll.toString());
    }
    @Test
    void testRemove() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        ll.remove(1);
        assertEquals("[0, 2, 3, 4]", ll.toString());
    }

    @Test
    void testAdd() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
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
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        //0, 1, 2,3, 4
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
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        assertEquals("[0, 1, 2, 3, 4]", ll.toString());
    }

    @Test
    void testRotate() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        ll.rotate();

        System.out.println(ll);

        assertEquals(ll.toString(), "[1, 2, 3, 4, 0]");
    }

    @Test
    void testIterator() {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<>();
        for(int i = 0; i < 5; ++i) ll.addLast(i);

        ArrayList<Integer> buf = new ArrayList<>();
        for(Integer i : ll) {
            buf.add(i);
        }
        assertEquals("[0, 1, 2, 3, 4]", buf.toString());
    }

}
