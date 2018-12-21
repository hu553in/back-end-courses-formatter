package it.sevenbits.homework.formatter.sm;

import java.util.Objects;

public class FormatterState {
    private final String currentState;

    public FormatterState(final String currentState) {
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

        final FormatterState otherFormatterState = (FormatterState) otherObject;
        return Objects.equals(currentState, otherFormatterState.currentState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState);
    }
}
