package it.sevenbits.formatter.formatter.fsm.state;

import org.junit.Assert;
import org.junit.Test;

public class StateTest {
    @Test
    public void shouldReturnStateNameViaToStringMethodCorrectly() {
        final State state = new State("STATE_NAME");
        Assert.assertEquals("STATE_NAME", state.toString());
    }
}
