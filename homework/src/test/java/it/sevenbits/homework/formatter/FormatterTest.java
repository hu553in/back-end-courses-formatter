package it.sevenbits.homework.formatter;

import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.reader.ReaderException;
import it.sevenbits.homework.io.reader.StringReader;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.writer.StringWriter;
import it.sevenbits.homework.io.writer.WriterException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class FormatterTest {
    private IFormatter formatter;
    private IReader reader;
    private IWriter writer;

    @Before
    public void setUp() {
        formatter = new Formatter();
    }

    @Test
    public void shouldFormatCorrectlyAtFirst() throws FormatterException, ReaderException, WriterException {
        reader = new StringReader("{{{}}}");
        writer = new StringWriter();

        formatter.format(reader, writer);

        Assert.assertEquals("{\n    {\n        {\n        }\n    }\n}", writer.toString());

        reader.close();
        writer.close();
    }

    @Test
    public void shouldFormatCorrectlyAtSecond() throws FormatterException, ReaderException, WriterException {
        reader = new StringReader(
                "public class     HelloWorld{public static void main(String[] " +
                        "args){System.out.println(\"Hello, World\");}}"
        );

        writer = new StringWriter();
        formatter.format(reader, writer);

        Assert.assertEquals(
            "public class HelloWorld {\n" +
                    "    public static void main(String[] args) {\n" +
                    "        System.out.println(\"Hello, World\");\n" +
                    "    }\n" +
                    "}",
                writer.toString()
        );

        reader.close();
        writer.close();
    }

    @Test (expected = FormatterException.class)
    public void shouldThrowException() throws FormatterException {
        formatter.format(null, null);
    }
}
