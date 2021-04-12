import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LinkedDequeTest {

    @Test
    void testSize() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for (int i = 0; i < 10; ++i)
            s.addFirst(i);
        assertEquals(10, s.size());
    }

    @Test
    void testIsEmpty() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for (int i = 0; i < 10; ++i)
            s.addFirst(i);
        for (int i = 0; i < 10; ++i)
            s.removeLast();

        assertTrue(s.isEmpty());
    }

    @Test
    void testFirst() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for (int i = 0; i < 10; ++i)
            s.addLast(i);
        assertEquals(0, s.first());
    }

    @Test
    void testLast() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for (int i = 0; i < 10; ++i)
            s.addLast(i);
        assertEquals(9, s.last());
    }

    @Test
    void removeFirst() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for (int i = 0; i < 10; ++i)
            s.addLast(i);

        s.removeFirst();
        assertEquals(1, s.first());
    }

    @Test
    void removeLast() {
        LinkedDeque<Integer> s = new LinkedDeque<>();
        for (int i = 0; i < 10; ++i)
            s.addLast(i);

        s.removeLast();
        assertEquals(8, s.last());
    }

}


