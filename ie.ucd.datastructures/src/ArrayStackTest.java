import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ArrayStackTest {

    @Test
    void testSize() {
        ArrayStack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(10, s.size());
    }

    @Test
    void testIsEmpty() {
        ArrayStack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        for (int i = 0; i < 10; ++i) {
            s.pop();
        }
        assertEquals(true, s.isEmpty());
    }

    @Test
    void testPush() {
        ArrayStack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(10, s.size());
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());

        ArrayStack<Integer> s2 = new ArrayStack<>(5);
        try {
            for(int i = 0; i < 10; ++i) {
                s2.push(i);
            }

            fail("Should of thrown exception - stack is full");
        } catch (IllegalStateException ex){}
    }

    @Test
    void testTop() {
        ArrayStack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, s.top());
    }

    @Test
    void testPop() {
        ArrayStack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals(9, s.pop());
        assertEquals(9, s.size());
    }

    @Test
    void testToString() {
        ArrayStack<Integer> s = new ArrayStack<>();
        for (int i = 0; i < 10; ++i)
            s.push(i);
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", s.toString());
    }
}
