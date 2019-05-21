package it.sevenbits.formatter.lexer.fsm.tokenbuilder;

import it.sevenbits.formatter.lexer.token.IToken;

/**
 * Interface that describes functionality for building {@link IToken} instances.
 */
public interface ITokenBuilder {
    /**
     * Method that performs setting of token name.
     *
     * @param name {@link String} instance that presents token name.
     */
    void setName(String name);

    /**
     * Method that performs appending to lexeme.
     *
     * @param data Character to be appended to lexeme.
     */
    void appendToLexeme(char data);

    /**
     * Method that returns {@link String} instance that presents lexeme.
     *
     * @return {@link String} instance that presents lexeme.
     */
    String getLexeme();

    /**
     * Method that performs instantiation of {@link IToken} instance (pre-passed name and lexeme
     * are passed to the constructor) and then returns it.
     * <p>
     * Note that if name or lexeme is not passed to builder in advance, you will receive
     * corresponding empty fields in {@link IToken} instance.
     *
     * @return New {@link IToken} instance initialized with a pre-passed name and lexeme.
     */
    IToken getToken();
}
