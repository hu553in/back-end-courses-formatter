package it.sevenbits.formatter.lexer.token;

/**
 * Interface that declares a functionality for lexical token.
 */
public interface IToken {
    /**
     * Method that returns token name.
     *
     * @return {@link String} instance that represents name of token.
     */
    String getName();

    /**
     * Method that returns lexeme.
     *
     * @return {@link String} instance that represents lexeme.
     */
    String getLexeme();
}
