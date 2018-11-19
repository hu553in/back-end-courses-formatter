package it.sevenbits.homework.lexer;

import it.sevenbits.homework.lexer.token.IToken;

/**
 * Interface that declares a functionality for providing lexical analysis of Java source code.
 */
public interface ILexer {
    /**
     * Method that reports whether single {@link IToken} instance is available for reading.
     *
     * @return Boolean value that indicates the result of method work.
     */
    boolean hasMoreTokens();

    /**
     * Method that returns a single {@link IToken} instance.
     *
     * @return Single {@link IToken} instance.
     *
     * @throws LexerException Exception that can be thrown during the method work.
     */
    IToken readToken() throws LexerException;
}
