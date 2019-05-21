package it.sevenbits.formatter.lexer.fsm.state;

import java.util.Objects;

/**
 * Class that presents FSM state.
 */
public class State {
    private final String name;

    /**
     * Class constructor that initializes private {@link #name} field with passed
     * {@link String} instance that presents FSM state name.
     *
     * @param name {@link String} instance that presents FSM state name.
     */
    public State(final String name) {
        this.name = name;
    }

    /**
     * Override of {@link Object#toString()} method.
     *
     * @return {@link String} instance that presents FSM state name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Override of {@link Object#equals(Object)} method.
     *
     * @param otherObject {@link Object} instance to check equality with.
     * @return Boolean value that presents result of checking.
     */
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

    /**
     * Override of {@link Object#hashCode()} method.
     *
     * @return Integer value that presents hash code of {@link State} instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
