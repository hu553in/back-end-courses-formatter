package it.sevenbits.formatter.lexer.factory;

import it.sevenbits.formatter.io.reader.FileReader;
import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.io.reader.ReaderException;
import it.sevenbits.formatter.io.reader.StringReader;
import it.sevenbits.formatter.lexer.fsm.FSMLexer;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class LexerFactoryTest {
    private ILexerFactory lexerFactory;

    @Before
    public void setUp() {
        lexerFactory = new LexerFactory();
    }

    @Test
    public void shouldReturnCommonLexerAtFirst() throws LexerFactoryException {
        Assert.assertEquals(
                FSMLexer.class,
                lexerFactory.createLexer(new StringReader("")).getClass()
        );
    }

    @Test
    public void shouldReturnCommonLexerAtSecond() throws LexerFactoryException, ReaderException {
        Assert.assertEquals(
                FSMLexer.class,
                lexerFactory.createLexer(new FileReader("/dev/null")).getClass()
        );
    }

    @Test (expected = LexerFactoryException.class)
    public void shouldThrowExceptionAtFirst() throws LexerFactoryException {
        lexerFactory.createLexer(new IReader() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public int read() {
                return -1;
            }
        });
    }

    @Test (expected = LexerFactoryException.class)
    public void shouldThrowExceptionAtSecond() throws LexerFactoryException {
        lexerFactory.createLexer(null);
    }
}
