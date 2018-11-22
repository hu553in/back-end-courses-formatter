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

    @Before
    public void setUp() {
        formatter = new Formatter();
    }

    @Test
    public void shouldFormatCorrectlyAtFirst() throws FormatterException, ReaderException, WriterException {
        IReader reader = new StringReader(
                "{{{}}}"
        );

        IWriter writer = new StringWriter();

        formatter.format(reader, writer);

        Assert.assertEquals("{\n    {\n        {\n        }\n    }\n}", writer.toString());

        reader.close();
        writer.close();
    }

    @Test
    public void shouldFormatCorrectlyAtSecond() throws FormatterException, ReaderException, WriterException {
        IReader reader = new StringReader(
                "public class HelloWorld { public static void main(String[] " +
                        "args) { System.out.println(\"Hello, World\"); } }"
        );

        IWriter writer = new StringWriter();

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
}
