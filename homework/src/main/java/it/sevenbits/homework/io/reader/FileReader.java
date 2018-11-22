package it.sevenbits.homework.io.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementation of {@link IReader} interface that performs reading from file that is encoded with UTF-8.
 */
public class FileReader implements IReader {
    private BufferedReader bufferedReader;
    private int charBuffer;

    /**
     * Overload of constructor that initializes {@link #bufferedReader} using {@link Path} instance
     * and then calls {@link #prepareCharBuffer()} method.
     *
     * @param filePath {@link Path} instance that represents a path to input file.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    public FileReader(final Path filePath) throws ReaderException {
        try {
            bufferedReader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ReaderException("Unable to open stream", e);
        }

        prepareCharBuffer();
    }

    /**
     * Overload of constructor that initializes {@link #bufferedReader}
     * using return value of {@link Paths#get(String, String...)} method.
     *
     * @param filePath {@link String} instance that represents a path to input file.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    public FileReader(final String filePath) throws ReaderException {
        this(Paths.get(filePath));
    }

    /**
     * Method that reads Unicode code of character from {@link #bufferedReader} and then puts it to {@link #charBuffer}.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    private void prepareCharBuffer() throws ReaderException {
        try {
            charBuffer = bufferedReader.read();
        } catch (IOException e) {
            throw new ReaderException("Unable to read from stream", e);
        }
    }

    /**
     * Method that reports whether character is available for reading.
     *
     * @return Boolean value that indicates result of the method work.
     */
    @Override
    public boolean hasNext() {
        if (bufferedReader == null) {
            return false;
        }

        return charBuffer != -1;
    }

    /**
     * Method that remembers a single character represented by Unicode code from {@link #charBuffer},
     * calls {@link #prepareCharBuffer()} method and then returns memorized character code.
     *
     * @return Unicode code of read character.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public int read() throws ReaderException {
        if (bufferedReader == null) {
            throw new ReaderException("Stream is closed");
        }

        int currentChar = charBuffer;

        if (hasNext()) {
            prepareCharBuffer();
        }

        return currentChar;
    }

    /**
     * Method that performs {@link FileReader} closing.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public void close() throws ReaderException {
        if (charBuffer != -1) {
            charBuffer = -1;
        }

        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new ReaderException("Unable to close stream", e);
            } finally {
                bufferedReader = null;
            }
        }
    }
}
