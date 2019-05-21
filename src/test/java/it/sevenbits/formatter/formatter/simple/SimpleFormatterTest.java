package it.sevenbits.formatter.formatter.simple;

import it.sevenbits.formatter.formatter.FormatterException;
import it.sevenbits.formatter.formatter.IFormatter;
import it.sevenbits.formatter.formatter.SimpleFormatter;
import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.io.reader.ReaderException;
import it.sevenbits.formatter.io.reader.StringReader;
import it.sevenbits.formatter.io.writer.IWriter;
import it.sevenbits.formatter.io.writer.StringWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleFormatterTest {
    private IFormatter formatter;
    private IReader reader;
    private IWriter writer;

    @Before
    public void setUp() {
        formatter = new SimpleFormatter();
    }

    @Test
    public void shouldFormatCorrectlyAtFirst() throws FormatterException {
        reader = new StringReader("{{{}}}");
        writer = new StringWriter();
        formatter.format(reader, writer);
        Assert.assertEquals("{\n    {\n        {\n        }\n    }\n}", writer.toString());
    }

    @Test
    public void shouldFormatCorrectlyAtSecond() throws FormatterException {
        reader = new StringReader(
                "public class     HelloWorld{public static void main(final String[] " +
                        "args){System.out.println(\"Hello, World!\");}}"
        );

        writer = new StringWriter();
        formatter.format(reader, writer);

        Assert.assertEquals(
                "public class HelloWorld {\n" +
                        "    public static void main(final String[] args) {\n" +
                        "        System.out.println(\"Hello, World!\");\n" +
                        "    }\n" +
                        "}",
                writer.toString()
        );
    }

    @Test(expected = FormatterException.class)
    public void shouldThrowExceptionAtFirst() throws FormatterException {
        formatter.format(null, mock(StringWriter.class));
    }

    @Test(expected = FormatterException.class)
    public void shouldThrowExceptionAtSecond() throws FormatterException {
        formatter.format(mock(StringReader.class), null);
    }

    @Test(expected = FormatterException.class)
    public void shouldThrowExceptionAtThird() throws FormatterException, ReaderException {
        final StringReader mockedReader = mock(StringReader.class);
        when(mockedReader.hasNext()).thenReturn(true);
        when(mockedReader.read()).thenThrow(new ReaderException(""));
        formatter.format(mockedReader, mock(StringWriter.class));
    }
}
