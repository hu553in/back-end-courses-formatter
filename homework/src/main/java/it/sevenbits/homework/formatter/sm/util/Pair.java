package it.sevenbits.homework.formatter.sm.util;

import java.util.Objects;

/**
 * Utility class that allows to combine different objects into pairs.
 *
 * @param <T> First object type.
 * @param <U> Second object type.
 */
public final class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * Class constructor that initializes {@link Pair#first} and {@link Pair#second} fields with passed objects.
     *
     * @param first First object.
     * @param second Second object.
     */
    public Pair(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Override of {@link Object#equals(Object)} method.
     *
     * @param other Object to compare with.
     *
     * @return Boolean value that represents comparison result.
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Pair<?, ?> otherPair = (Pair<?, ?>) other;
        return Objects.equals(first, otherPair.first) && Objects.equals(second, otherPair.second);
    }

    /**
     * Override of {@link Object#hashCode()} method.
     *
     * @return Hash code of {@link Pair} instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
