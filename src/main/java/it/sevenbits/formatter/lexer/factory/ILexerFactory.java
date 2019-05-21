package it.sevenbits.formatter.lexer.factory;

import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.lexer.ILexer;

/**
 * Interface that declares a functionality for creating of various {@link ILexer} instances
 * using "Factory method" design pattern.
 */
public interface ILexerFactory {
    /**
     * Method that creates an instance of one of {@link ILexer} interface implementations
     * in accordance with passed {@link IReader} implementation.
     *
     * @param reader Instance of {@link IReader} which is used to select, create, and initialize
     *               one of the implementations of {@link ILexer} interface.
     * @return New {@link ILexer} instance.
     * @throws LexerFactoryException Exception that can be thrown during the method work.
     */
    ILexer createLexer(IReader reader) throws LexerFactoryException;
}
