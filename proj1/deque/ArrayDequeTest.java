package deque;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    /** Test the functionality of add, get, size, etc. */
    public void addIsEmptySizeTest() {
        ArrayDeque<Integer> arrayDequeTest = new ArrayDeque<Integer>();
        assertTrue(arrayDequeTest.isEmpty());
        assertFalse(arrayDequeTest.isFull());

        arrayDequeTest.addFirst(1);
        arrayDequeTest.addFirst(2);
        arrayDequeTest.addLast(3);
        arrayDequeTest.addLast(4);
        arrayDequeTest.addLast(5);
        arrayDequeTest.addFirst(6);
        arrayDequeTest.addFirst(7);
        arrayDequeTest.addFirst(8);

        assertTrue(arrayDequeTest.isFull());
        assertEquals(8, arrayDequeTest.size());
        arrayDequeTest.printDeque();
        assertEquals(8, (int) arrayDequeTest.get(0));
        assertEquals(3, (int) arrayDequeTest.get(5));
        assertEquals(5, (int) arrayDequeTest.get(7));
    }

    @Test
    /** Test the functionality of resize */
    public void resizeTest() {
        ArrayDeque<Integer> arrayDequeTest = new ArrayDeque<Integer>();
        for (int i = 0; i < 8; i++) {
            arrayDequeTest.addFirst(i+1);
        }
        arrayDequeTest.printDeque();
        arrayDequeTest.addLast(9);
        assertEquals(9, (int) arrayDequeTest.size());
        arrayDequeTest.printDeque();
    }
}