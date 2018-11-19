package it.sevenbits.homework.lexer;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.lexer.token.IToken;
import it.sevenbits.homework.lexer.token.Token;

/**
 * {@link ILexer} interface implementation that provides lexical analysis of Java source code.
 */
public class CommonLexer implements ILexer {
    private final IReader reader;

    /**
     * Class constructor.
     *
     * @param reader {@link IReader} instance that provides data input process.
     */
    public CommonLexer(final IReader reader) {
        this.reader = reader;
    }

    /**
     * Method that reports whether single {@link IToken} instance is available for reading.
     *
     * @return Boolean value that indicates the result of method work.
     */
    @Override
    public boolean hasMoreTokens() {
        return reader.hasNext();
    }

    /**
     * Method that returns a single {@link IToken} instance.
     *
     * @return Single {@link IToken} instance.
     *
     * @throws LexerException Exception that can be thrown during the method work.
     */
    @Override
    public IToken readToken() throws LexerException {
        return new Token("name", "lexeme");
    }
}
