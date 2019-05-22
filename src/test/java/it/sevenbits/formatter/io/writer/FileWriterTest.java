package it.sevenbits.formatter.io.writer;

import it.sevenbits.formatter.io.reader.FileReader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Path;

public class FileWriterTest {
    private TemporaryFolder temporaryFolder;

    @Before
    public void setUp() throws IOException {
        temporaryFolder = new TemporaryFolder();
        temporaryFolder.create();
    }

    @Test
    public void shouldWriteDataCorrectly() throws IOException {
        final char character = 'a';
        final String text = "TEST_TEXT";

        final char[] charArray = new char[] {
                'a', 'b', 'c'
        };

        final Path filePath = temporaryFolder.newFile().toPath();

        try (FileWriter fileWriter = new FileWriter(filePath.toString())) {
            fileWriter.write(text);
            fileWriter.write((int) character);
            fileWriter.write(charArray);
        }

        try (FileReader fileReader = new FileReader(filePath)) {
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
    }

    @Test(expected = WriterException.class)
    public void shouldThrowException() throws IOException {
        final FileWriter fileWriter = new FileWriter(temporaryFolder.newFile().toPath().toString());
        fileWriter.close();
        fileWriter.write("");
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();
    }
}
