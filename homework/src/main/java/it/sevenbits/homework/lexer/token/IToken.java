package it.sevenbits.homework.lexer.token;

/**
 * Interface that describes a functionality for lexical token.
 */
public interface IToken {
    /**
     * Method that returns token name.
     *
     * @return String that represents name of token.
     */
    String getName();

    /**
     * Method that returns lexeme.
     *
     * @return String that represents lexeme.
     */
    String getLexeme();
}
