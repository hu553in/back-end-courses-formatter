package it.sevenbits.formatter.io.writer;

import it.sevenbits.formatter.io.reader.FileReader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class FileWriterTest {
    private Path filePath;
    private FileWriter fileWriter;
    private String text;
    private char character;
    private char[] charArray;

    @Before
    public void setUp() throws IOException {
        text = "HelloWorldAndThenGoodbyeWorld";
        character = 'a';

        charArray = new char[]{
                'a', 'b', 'c'
        };

        filePath = Paths.get("./" + +ThreadLocalRandom.current().nextLong());
        Files.createFile(filePath);
        fileWriter = new FileWriter(filePath.toString());
    }

    @Test
    public void shouldWriteDataCorrectly() throws IOException {
        fileWriter.write(text);
        fileWriter.write((int) character);
        fileWriter.write(charArray);
        fileWriter.close();

        final FileReader fileReader = new FileReader(filePath);
        final StringBuilder actualStringBuilder = new StringBuilder();

        while (fileReader.hasNext()) {
            actualStringBuilder.append((char) fileReader.read());
        }

        final StringBuilder expectedStringBuilder = new StringBuilder();
        expectedStringBuilder.append(text).append(character);

        for (char c : charArray) {
            expectedStringBuilder.append(c);
        }

        Assert.assertEquals(
                expectedStringBuilder.toString(),
                actualStringBuilder.toString()
        );
    }

    @Test(expected = WriterException.class)
    public void shouldThrowException() throws WriterException {
        fileWriter.close();
        fileWriter.write("JUST_A_STRING");
    }

    @After
    public void tearDown() throws IOException {
        Files.delete(filePath);
    }
}
