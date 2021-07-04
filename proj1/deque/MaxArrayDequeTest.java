package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class MaxArrayDequeTest {

    private static class intComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return x - y;
        }
    }

    private static class stringComparator implements Comparator<String> {
        public int compare(String x, String y) {
            return -1; // Passed
        }
    }

    private static class stringLengthComparator implements Comparator<String> {
        public int compare(String x, String y) {
            return x.length() - y.length();
        }
    }

    @Test
    /* Test MaxArrayDeque using comparator in constructor */
    public void TestMax() {
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<Integer>(new intComparator());
        for (int i = 0; i < 10; i++) {
            mad1.addFirst(i);
        }

        assertEquals("Should be 9", 9, mad1.max().intValue());
    }

    @Test
    /* Test MaxArrayDeque using passing comparator */
    public void TestMaxWithComparator() {
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<String>(new stringComparator());
        mad1.addFirst("Java");
        mad1.addFirst("Jav");
        mad1.addFirst("JavaSE");
        assertEquals("Should be \"JavaSE\"", "JavaSE", mad1.max(new stringLengthComparator()));
    }
}
