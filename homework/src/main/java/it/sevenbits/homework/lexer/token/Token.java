package it.sevenbits.homework.lexer.token;

/**
 * Implementation of {@link IToken} interface that represents a lexical token.
 */
public class Token implements IToken {
    private String name;
    private String lexeme;

    /**
     * Class constructor.
     *
     * @param name {@link String} instance that represents name of token.
     * @param lexeme {@link String} instance that represents lexeme.
     */
    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
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
}
