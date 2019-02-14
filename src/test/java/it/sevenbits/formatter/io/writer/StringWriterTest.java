package it.sevenbits.formatter.io.writer;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;

public class StringWriterTest {
    private String text;
    private char character;
    private char[] charArray;
    private StringWriter stringWriter;

    @Before
    public void setUp() {
        text = "HelloWorldAndThenGoodbyeWorld";
        character = 'a';

        charArray = new char[] {
                'a', 'b', 'c'
        };

        stringWriter = new StringWriter();
    }

    @Test
    public void shouldWriteDataCorrectly() throws IOException {
        stringWriter.write(text);
        stringWriter.write((int) character);
        stringWriter.write(charArray);

        final StringBuilder expectedStringBuilder = new StringBuilder();
        expectedStringBuilder.append(text).append(character);

        for (char c : charArray) {
            expectedStringBuilder.append(c);
        }

        Assert.assertEquals(
                expectedStringBuilder.toString(),
                stringWriter.toString()
        );
    }
}
