package it.sevenbits.homework.lexer;

import it.sevenbits.homework.io.reader.IReader;

/**
 * Interface that declares a functionality for creating of {@link it.sevenbits.homework.lexer.ILexer} instances
 * using "Factory method" pattern.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Factory_method_pattern">Factory method pattern - Wikipedia</a>
 */
public interface ILexerFactory {
    /**
     * "Factory" method that creates optional {@link it.sevenbits.homework.lexer.ILexer} instance.
     *
     * @param reader Instance of {@link it.sevenbits.homework.io.reader.IReader} which is used to select, create,
     *               and initialize one of the implementations of {@link it.sevenbits.homework.lexer.ILexer} interface.
     *
     * @return New {@link it.sevenbits.homework.lexer.ILexer} instance.
     */
    ILexer createLexer(IReader reader);
}
