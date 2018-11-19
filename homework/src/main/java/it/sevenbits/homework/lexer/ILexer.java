package it.sevenbits.homework.lexer;

import it.sevenbits.homework.lexer.token.IToken;

/**
 * Interface that declares a functionality for providing lexical analysis of some data.
 */
public interface ILexer {
    /**
     * Method that reports whether single {@link it.sevenbits.homework.lexer.token.IToken} instance
     * is available for reading.
     *
     * @return Boolean value that indicates the result of method work.
     */
    boolean hasMoreTokens();

    /**
     * Method that provides a single {@link it.sevenbits.homework.lexer.token.IToken} instance.
     *
     * @return Single {@link it.sevenbits.homework.lexer.token.IToken} instance.
     *
     * @throws LexerException Exception that can be thrown during the method work.
     */
    IToken readToken() throws LexerException;
}
