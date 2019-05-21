package it.sevenbits.formatter.formatter.fsm.state;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StateTest {
    private State state;

    @Before
    public void setUp() {
        state = new State("STATE_NAME");
    }

    @Test
    public void shouldReturnStateNameViaToStringMethodCorrectly() {
        Assert.assertEquals("STATE_NAME", state.toString());
    }

    @Test
    public void shouldEqualsReturnFalse() {
        Assert.assertNotEquals(state, new State("ANOTHER_NAME"));
        Assert.assertNotEquals(state, "");
    }

    @Test
    public void shouldEqualsReturnTrue() {
        Assert.assertEquals(state, state);
    }
}
