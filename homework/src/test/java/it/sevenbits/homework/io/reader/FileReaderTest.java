package it.sevenbits.homework.io.reader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

public class FileReaderTest {
    private Path filePath;
    private FileReader fileReader;
    private String text;

    @Before
    public void setUp() throws IOException {
        text = "HelloWorldAndThenGoodbyeWorld";
        filePath = Paths.get("./" + ThreadLocalRandom.current().nextLong());
        Files.write(filePath, text.getBytes(StandardCharsets.UTF_8));
        fileReader = new FileReader(filePath);
    }

    @Test
    public void shouldReadTextCorrectly() throws ReaderException {
        final StringBuilder stringBuilder = new StringBuilder();

        while (fileReader.hasNext()) {
            int read = fileReader.read();
            stringBuilder.append((char) read);
        }

        Assert.assertEquals(text, stringBuilder.toString());
    }

    @After
    public void tearDown() throws IOException {
        Files.delete(filePath);
        fileReader.close();
    }
}
