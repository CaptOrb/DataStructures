import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ArrayQueueTest {

    @Test
    void testSize() {
        ArrayQueue<Integer> s = new ArrayQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals(10, s.size());
    }

    @Test
    void testIsEmpty() {
        ArrayQueue<Integer> s = new ArrayQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        for(int i = 0; i < 10; ++i)
            s.dequeue();
        assertEquals(true, s.isEmpty());
    }

    @Test
    void testEnqueue() {
        ArrayQueue<Integer> s = new ArrayQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", s.toString());

        ArrayQueue<Integer> s2 = new ArrayQueue<>(5);

        try {
            for(int i = 0; i < 10; ++i) {
                s2.enqueue(i);
            }

            fail("Should of thrown exception - queue is full");
        } catch (IllegalStateException ex){}
    }

    @Test
    void testFirst() {
        ArrayQueue<Integer> s = new ArrayQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);
        assertEquals(0, s.first());
    }

    @Test
    void testDequeue() {
        ArrayQueue<Integer> s = new ArrayQueue<>();
        for(int i = 0; i < 10; ++i)
            s.enqueue(i);

        assertEquals(0, s.dequeue());
        assertEquals(9, s.size());
    }

}
