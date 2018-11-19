package it.sevenbits.homework.lexer.factory;

import it.sevenbits.homework.io.reader.FileReader;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.reader.StringReader;
import it.sevenbits.homework.lexer.ILexer;
import it.sevenbits.homework.lexer.CommonLexer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link ILexerFactory} interface that provides a creating of various {@link ILexer} instances
 * using "Factory method" design pattern.
 */
public class LexerFactory implements ILexerFactory {
    private final Map<Class<? extends IReader>, Class<? extends ILexer>> lexerForReader;

    /**
     * Class constructor that initializes {@link #lexerForReader} and puts in it all known pairs of
     * {@link IReader} - {@link ILexer} implementations.
     */
    public LexerFactory() {
        lexerForReader = new HashMap<>();
        lexerForReader.put(StringReader.class, CommonLexer.class);
        lexerForReader.put(FileReader.class, CommonLexer.class);
    }

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
    @Override
    public ILexer createLexer(final IReader reader) throws LexerFactoryException {
        try {
            return lexerForReader.get(reader.getClass()).getDeclaredConstructor(IReader.class).newInstance(reader);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                 | NoSuchMethodException e) {
            throw new LexerFactoryException("Unable to create ILexer instance.", e);
        }
    }
}
