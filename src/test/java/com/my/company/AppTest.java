package com.my.company;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void checkIp() {
        final boolean b1 = new App().checkIp("sdfsdf");
        final boolean b2 = new App().checkIp("sdfsdf.asaf.afsa.asf");
        final boolean b3 = new App().checkIp("256.123.123.123");
        final boolean b4 = new App().checkIp("qwe.123.123.123");
        final boolean b5 = new App().checkIp("123.123.123.123");
        Assert.assertFalse(b1);
        Assert.assertFalse(b2);
        Assert.assertFalse(b3);
        Assert.assertFalse(b4);
        Assert.assertTrue(b5);
    }

    @Test
    public void ipToLong() {
        final long l1 = new App().ipToLong("123.123.123.123");
        final long l2 = new App().ipToLong("255.255.255.255");
        final long l3 = new App().ipToLong("0.0.0.0");
        long expected1 = 2071690107L;
        long expected2 = 4294967295L;
        long expected3 = 0L;
        Assert.assertEquals(expected1, l1);
        Assert.assertEquals(expected2, l2);
        Assert.assertEquals(expected3, l3);
    }
}
