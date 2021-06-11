package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE

    @Test
    public void testAddThreeRemoveThree() {
        // Initialize the two classes
        AListNoResizing<Integer> testAListNoResizing = new AListNoResizing<>();
        BuggyAList<Integer> testBuggyAList = new BuggyAList<>();
        int[] testValues = new int[]{4, 5, 6};

        // Add three then remove three
        for (int currentV : testValues) {
            testAListNoResizing.addLast(currentV);
            testBuggyAList.addLast(currentV);
        }

        assertEquals(testAListNoResizing.size(), testBuggyAList.size());

        for (int currentV : testValues) {
            assertEquals(testAListNoResizing.removeLast(), testBuggyAList.removeLast());
        }
    }

    @Test
    public void randomizeTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> AL = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                AL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeAL = AL.size();
                assertEquals(size, sizeAL);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    int lastNum = L.getLast();
                    int lastNumAL = AL.getLast();
                    assertEquals(lastNum, lastNumAL);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    int lastNumRemoved = L.removeLast();
                    int lastNumRemovedAL = AL.removeLast();
                    assertEquals(lastNumRemoved, lastNumRemovedAL);
                }
            }
        }
    }
}
