package it.sevenbits.homework.lexer.factory;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.lexer.ILexer;

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
     *
     * @throws LexerFactoryException Exception that can be thrown during the method work.
     *
     * @return New {@link ILexer} instance.
     */
    ILexer createLexer(IReader reader) throws LexerFactoryException;
}
