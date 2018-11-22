package it.sevenbits.homework.lexer.factory;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.reader.ReaderException;
import it.sevenbits.homework.io.reader.StringReader;
import it.sevenbits.homework.lexer.CommonLexer;
import it.sevenbits.homework.lexer.ILexer;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static org.mockito.Mockito.mock;

public class LexerFactoryTest {
    private ILexerFactory lexerFactory;

    @Before
    public void setUp() {
        lexerFactory = new LexerFactory();
    }

    @Test
    public void shouldReturnCommonLexer() throws LexerFactoryException {
        ILexer lexer = lexerFactory.createLexer(new StringReader(""));
        Assert.assertEquals(lexer.getClass(), CommonLexer.class);
    }

    @Test (expected = LexerFactoryException.class)
    public void shouldThrowLexerFactoryExceptionAtFirst() throws LexerFactoryException {
        lexerFactory.createLexer(mock(IReader.class));
    }

    @Test (expected = LexerFactoryException.class)
    public void shouldThrowLexerFactoryExceptionAtSecond() throws LexerFactoryException {
        lexerFactory.createLexer(new IReader() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public int read() throws ReaderException {
                return 0;
            }

            @Override
            public void close() throws ReaderException {
            }
        });
    }
}
