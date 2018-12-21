package it.sevenbits.homework.lexer.token;

import java.util.Objects;

/**
 * Implementation of {@link IToken} interface that represents a lexical token.
 */
public class Token implements IToken {
    private final String name;
    private final String lexeme;

    /**
     * Overload of constructor that initializes {@link #lexeme} with passed {@link String} instance.
     *
     * @param name {@link String} instance that represents name of token.
     * @param lexeme {@link String} instance that represents lexeme.
     */
    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    /**
     * Overload of constructor that initializes {@link #lexeme} with passed single character
     * using {@link Character#toString()} method call.
     *
     * @param name {@link String} instance that represents name of token.
     * @param lexeme Single character that represents lexeme.
     */
    public Token(final String name, final char lexeme) {
        this(name, Character.toString(lexeme));
    }

    /**
     * Method that returns {@link #name}.
     *
     * @return {@link String} instance that represents name of token.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method that returns {@link #lexeme}.
     *
     * @return {@link String} instance that represents lexeme.
     */
    @Override
    public String getLexeme() {
        return lexeme;
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

        final Token otherToken = (Token) other;
        return Objects.equals(name, otherToken.name) && Objects.equals(lexeme, otherToken.lexeme);
    }

    /**
     * Override of {@link Object#hashCode()} method.
     *
     * @return Hash code of {@link Token} instance.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, lexeme);
    }
}
