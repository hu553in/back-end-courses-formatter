package it.sevenbits.homework.formatter.fsm;

import it.sevenbits.homework.formatter.FormatterException;
import it.sevenbits.homework.formatter.IFormatter;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.reader.StringReader;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.writer.StringWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

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

    @Test (expected = FormatterException.class)
    public void shouldThrowException() throws FormatterException {
        formatter.format(null, null);
    }
}
