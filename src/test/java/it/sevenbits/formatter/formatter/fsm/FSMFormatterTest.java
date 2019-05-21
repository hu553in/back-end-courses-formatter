package it.sevenbits.formatter.formatter.fsm;

import it.sevenbits.formatter.formatter.FormatterException;
import it.sevenbits.formatter.formatter.IFormatter;
import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.io.reader.StringReader;
import it.sevenbits.formatter.io.writer.IWriter;
import it.sevenbits.formatter.io.writer.StringWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class FSMFormatterTest {
    private IFormatter formatter;
    private IReader reader;
    private IWriter writer;

    @Before
    public void setUp() {
        formatter = new FSMFormatter();
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
                "public         class     HelloWorld{     \n" +
                        "// just a single line comment\n" +
                        "public static void main(final String[] " +
                        "args){System.out.println(\"Hello, World!\");\n\n\n\n" +
                        "/*\n * just\n * a\n * multiline\n * comment\n */}}\n\n'c''l'"
        );

        writer = new StringWriter();
        formatter.format(reader, writer);

        Assert.assertEquals(
                "public class HelloWorld {\n" +
                        "// just a single line comment\n" +
                        "    public static void main(final String[] args) {\n" +
                        "        System.out.println(\"Hello, World!\");\n" +
                        "/*\n * just\n * a\n * multiline\n * comment\n */\n" +
                        "    }\n" +
                        "}\n" +
                        "'c' 'l'",
                writer.toString()
        );
    }

    @Test
    public void shouldFormatCorrectlyAtThird() throws FormatterException {
        reader = new StringReader(
                "public         class     HelloWorld {     \n" +
                        "// just a single line comment\n" +
                        "public static void main(final String[] " +
                        "args){System.out.println(\"Hello, World!\");\n\n\n\n" +
                        "/*\n * just\n * a\n * multiline\n * comment\n */}}\n\n'c''l'"
        );

        writer = new StringWriter();
        formatter.format(reader, writer);

        Assert.assertEquals(
                "public class HelloWorld {\n" +
                        "// just a single line comment\n" +
                        "    public static void main(final String[] args) {\n" +
                        "        System.out.println(\"Hello, World!\");\n" +
                        "/*\n * just\n * a\n * multiline\n * comment\n */\n" +
                        "    }\n" +
                        "}\n" +
                        "'c' 'l'",
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
    public void shouldThrowExceptionAtThird() throws FormatterException {
        formatter.format(mock(StringReader.class), mock(StringWriter.class));
    }
}
