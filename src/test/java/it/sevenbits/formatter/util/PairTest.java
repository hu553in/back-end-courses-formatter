package it.sevenbits.formatter.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PairTest {
    private Pair<String, String> pair;

    @Before
    public void setUp() {
        pair = new Pair<>("", "");
    }

    @Test
    public void shouldEqualsReturnFalse() {
        Assert.assertNotEquals(pair, new Pair<>("str", ""));
        Assert.assertNotEquals(pair, "");
    }

    @Test
    public void shouldEqualsReturnTrue() {
        Assert.assertEquals(pair, pair);
    }
}
