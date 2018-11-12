package it.sevenbits.homework.formatter;

import it.sevenbits.homework.io.IReader;
import it.sevenbits.homework.io.IWriter;
import it.sevenbits.homework.io.StringReader;
import it.sevenbits.homework.io.StringWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

public class FormatterTest {
    private Formatter formatter;
    private IReader stringReader;
    private IWriter stringWriter;

    @Before
    public void setUp() {
        formatter = new Formatter();
    }

    @Test
    public void shouldFormatCorrectlyAtFirst() throws IOException {
        String testString = "try{\n                formatter.format(stringReader, stringWriter);\n" +
                            "                }                       catch (IOException e) {\n" +
                            "                System.err.println(\"An instance             of IOException was " +
                            "thrown.\");\n                return;\n\n\n\n\n                }";

        stringReader = new StringReader(testString);
        stringWriter = new StringWriter();

        formatter.format(stringReader, stringWriter);

        Assert.assertEquals("try {\n" +
                            "    formatter.format(stringReader, stringWriter);\n" +
                            "}\n" +
                            "catch (IOException e) {\n" +
                            "    System.err.println(\"An instance of IOException was thrown.\");\n" +
                            "    return;\n" +
                            "}",
                            stringWriter.toString());

        stringWriter = null;
        stringReader = null;
    }

    @Test
    public void shouldFormatCorrectlyAtSecond() throws IOException {
        String testString = "{{{{}}}}";

        stringReader = new StringReader(testString);
        stringWriter = new StringWriter();

        formatter.format(stringReader, stringWriter);

        Assert.assertEquals("{\n    {\n        {\n            {\n            }\n        }\n    }\n}",
                            stringWriter.toString());

        stringWriter = null;
        stringReader = null;
    }

    @Test
    public void shouldNotChangeAnythingAtFirst() throws IOException {
        String testString = "try {\n" +
                            "    formatter.format(stringReader, stringWriter);\n" +
                            "}\n" +
                            "catch (IOException e) {\n" +
                            "    System.err.println(\"An instance of IOException was thrown.\");\n" +
                            "    return;\n" +
                            "}";

        stringReader = new StringReader(testString);
        stringWriter = new StringWriter();

        formatter.format(stringReader, stringWriter);

        Assert.assertEquals(testString, stringWriter.toString());

        stringWriter = null;
        stringReader = null;
    }

    @Test
    public void shouldNotChangeAnythingAtSecond() throws IOException {
        String testString = "{\n    {\n        {\n            {\n            }\n        }\n    }\n}";

        stringReader = new StringReader(testString);
        stringWriter = new StringWriter();

        formatter.format(stringReader, stringWriter);

        Assert.assertEquals(testString, stringWriter.toString());

        stringWriter = null;
        stringReader = null;
    }

    @Test
    public void shouldNotChangeAnythingAtThird() throws IOException {
        String testString = "public interface IWriter {\n" +
                            "    void write(int character) throws IOException;\n" +
                            "    void write(String string) throws IOException;\n" +
                            "}";

        stringReader = new StringReader(testString);
        stringWriter = new StringWriter();

        formatter.format(stringReader, stringWriter);

        Assert.assertEquals(testString, stringWriter.toString());

        stringWriter = null;
        stringReader = null;
    }

    @Test
    public void shouldNotChangeAnythingAtFourth() throws IOException {
        String testString = "while (true) {\n" +
                            "    while (!false) {\n" +
                            "        int oldValue = result.get(j + i);\n" +
                            "        int newValue = oldValue + left.get(j) * right.get(i);\n" +
                            "        result.set(j + i, newValue);\n" +
                            "    }\n" +
                            "}";

        stringReader = new StringReader(testString);
        stringWriter = new StringWriter();

        formatter.format(stringReader, stringWriter);

        Assert.assertEquals(testString, stringWriter.toString());

        stringWriter = null;
        stringReader = null;
    }
}
