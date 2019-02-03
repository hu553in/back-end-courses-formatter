package it.sevenbits.homework.fsm.state;

import java.util.Objects;

public class State {
    private final String name;

    public State(final String currentState) {
        this.name = currentState;
    }

    @Override
    public String toString() {
        return name;
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
        return Objects.equals(name, otherState.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
