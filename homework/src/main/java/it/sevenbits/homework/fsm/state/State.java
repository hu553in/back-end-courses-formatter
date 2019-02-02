package it.sevenbits.homework.fsm.state;

import java.util.Objects;

public class State {
    private final String currentState;

    public State(final String currentState) {
        this.currentState = currentState;
    }

    @Override
    public String toString() {
        return currentState;
    }

    @Override
    public boolean equals(final Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        final State otherState = (State) otherObject;
        return Objects.equals(currentState, otherState.currentState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState);
    }
}
