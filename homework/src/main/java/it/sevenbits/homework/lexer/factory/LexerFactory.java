package it.sevenbits.homework.lexer.factory;

import it.sevenbits.homework.io.reader.FileReader;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.reader.StringReader;
import it.sevenbits.homework.lexer.ILexer;
import it.sevenbits.homework.lexer.fsm.FSMLexer;
import java.lang.reflect.Constructor;
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
     * {@link IReader} and {@link ILexer} implementations.
     */
    public LexerFactory() {
        lexerForReader = new HashMap<>();
        lexerForReader.put(StringReader.class, FSMLexer.class);
        lexerForReader.put(FileReader.class, FSMLexer.class);
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
        if (reader == null) {
            throw new LexerFactoryException("Null passed as argument");
        }

        final Class<? extends ILexer> lexerClass = lexerForReader.get(reader.getClass());
        if (lexerClass == null) {
            throw new LexerFactoryException(
                    "There are no ILexer interface implementations that " +
                    "match the passed IReader interface implementation"
            );
        }

        final Constructor<? extends ILexer> lexerConstructor;
        try {
            lexerConstructor = lexerClass.getDeclaredConstructor(IReader.class);
        } catch (NoSuchMethodException e) {
            throw new LexerFactoryException(
                    "Invoked " +
                    lexerClass.getSimpleName() +
                    " constructor does not exist",
                    e
            );
        }

        try {
            return lexerConstructor.newInstance(reader);
        } catch (InvocationTargetException e) {
            throw new LexerFactoryException(
                    "An exception was thrown during the creation of " +
                    lexerClass.getSimpleName() +
                    " instance",
                    e.getCause()
            );
        } catch (InstantiationException e) {
            throw new LexerFactoryException("Unable to create " + lexerClass.getSimpleName() + " instance", e);
        } catch (IllegalAccessException e) {
            throw new LexerFactoryException(
                    "No access to the definition of " +
                    lexerClass.getSimpleName() +
                    " implementation or constructor",
                    e
            );
        }
    }
}
