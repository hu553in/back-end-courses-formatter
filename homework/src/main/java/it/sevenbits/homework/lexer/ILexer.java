package it.sevenbits.homework.lexer;

import it.sevenbits.homework.lexer.token.IToken;

/**
 * Interface that declares a functionality for providing lexical analysis of some data.
 */
public interface ILexer {
    /**
     * Method that reports whether tokens is available for reading.
     *
     * @return Boolean value that indicates whether tokens is available for reading.
     */
    boolean hasMoreTokens();

    /**
     * Method that reads a token.
     *
     * @return Read token.
     *
     * @throws LexerException Exception that may be thrown during the method work.
     */
    IToken readToken() throws LexerException;
}
