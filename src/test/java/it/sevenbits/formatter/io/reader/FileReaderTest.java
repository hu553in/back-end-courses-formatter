package it.sevenbits.formatter.io.reader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderTest {
    private TemporaryFolder temporaryFolder;

    @Before
    public void setUp() throws IOException {
        temporaryFolder = new TemporaryFolder();
        temporaryFolder.create();
    }

    @Test
    public void shouldReadTextCorrectly() throws IOException {
        final String text = "TEST_TEXT";

        final Path filePath = temporaryFolder.newFile().toPath();
        Files.write(filePath, text.getBytes(StandardCharsets.UTF_8));
        final StringBuilder stringBuilder = new StringBuilder();

        try (FileReader fileReader = new FileReader(filePath)) {
            while (fileReader.hasNext()) {
                int read = fileReader.read();
                stringBuilder.append((char) read);
            }
        }

        Assert.assertEquals(text, stringBuilder.toString());
    }

    @Test(expected = ReaderException.class)
    public void shouldThrowExceptionAtFirst() throws ReaderException {
        new FileReader(Paths.get("$$$"));
    }

    @After
    public void tearDown() {
        temporaryFolder.delete();
    }
}
