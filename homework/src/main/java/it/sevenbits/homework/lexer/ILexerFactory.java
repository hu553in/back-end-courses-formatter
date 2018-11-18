package it.sevenbits.homework.lexer;

import it.sevenbits.homework.io.reader.IReader;

/**
 * Interface that describes a functionality for creating of ILexer instances using "Factory method" pattern.
 */
public interface ILexerFactory {
    /**
     * "Factory" method that creates optional ILexer instance.
     *
     * @param reader Instance of IReader which is used to select, create, and initialize
     *               one of the implementations of ILexer interface.
     *
     * @return New ILexer instance.
     */
    ILexer createLexer(IReader reader);
}
