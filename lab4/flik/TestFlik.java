package flik;

import org.junit.Test;
import org.junit.Assert;

public class TestFlik {

    @Test
    public void testIsSameNumber() {
        int a = 500;
        int b = 500;
        boolean cond = (a == b);
        Assert.assertTrue(cond);

        boolean cond2 = Flik.isSameNumber(a, b);
        Assert.assertTrue(cond2);
    }
}