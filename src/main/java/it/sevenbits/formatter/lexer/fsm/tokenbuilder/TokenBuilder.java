package it.sevenbits.formatter.lexer.fsm.tokenbuilder;

import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.lexer.token.Token;

/**
 * {@link ITokenBuilder} interface implementation that provides {@link IToken} instance building process.
 */
public class TokenBuilder implements ITokenBuilder {
    private final StringBuilder nameBuilder, lexemeBuilder;

    /**
     * Class constructor that initializes private {@link #nameBuilder} and {@link #lexemeBuilder}
     * fields with new {@link StringBuilder} instances.
     */
    public TokenBuilder() {
        nameBuilder = new StringBuilder();
        lexemeBuilder = new StringBuilder();
    }

    /**
     * Method that performs setting of token name.
     *
     * @param name {@link String} instance that presents token name.
     */
    @Override
    public void setName(final String name) {
        nameBuilder.setLength(0);
        nameBuilder.append(name);
    }

    /**
     * Method that performs appending to lexeme.
     *
     * @param data Character to be appended to lexeme.
     */
    @Override
    public void appendToLexeme(final char data) {
        lexemeBuilder.append(data);
    }

    /**
     * Method that returns {@link String} instance that presents lexeme.
     *
     * @return {@link String} instance that presents lexeme.
     */
    @Override
    public String getLexeme() {
        return lexemeBuilder.toString();
    }

    /**
     * Method that performs instantiation of {@link IToken} instance (pre-passed name and lexeme
     * are passed to the constructor) and then returns it.
     * <p>
     * Note that if name or lexeme is not passed to builder in advance, you will receive
     * corresponding empty fields in {@link IToken} instance.
     *
     * @return New {@link IToken} instance initialized with a pre-passed name and lexeme.
     */
    @Override
    public IToken getToken() {
        return new Token(nameBuilder.toString(), lexemeBuilder.toString());
    }
}
