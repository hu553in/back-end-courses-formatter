package it.sevenbits.homework.io.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementation of {@link it.sevenbits.homework.io.reader.IReader} interface
 * that performs reading from file that is encoded with UTF-8.
 */
public class FileReader implements IReader {
    private BufferedReader bufferedReader;
    private int charBuffer;

    /**
     * Overload of constructor that initializes {@link FileReader#bufferedReader} using
     * {@link java.nio.file.Path} instance and then calls {@link FileReader#prepareCharBuffer()} method.
     *
     * @param filePath {@link java.nio.file.Path} instance that represents a path to input file.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    public FileReader(final Path filePath) throws ReaderException {
        try {
            bufferedReader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ReaderException("Unable to open the stream.", e);
        }

        prepareCharBuffer();
    }

    /**
     * Overload of constructor that initializes {@link FileReader#bufferedReader}
     * using return value of {@link java.nio.file.Paths#get(String, String...)} method.
     *
     * @param filePath {@link java.lang.String} instance that represents a path to input file.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    public FileReader(final String filePath) throws ReaderException {
        this(Paths.get(filePath));
    }

    /**
     * Method that reads Unicode code of character from {@link FileReader#bufferedReader}
     * and then puts it to {@link FileReader#charBuffer}.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    private void prepareCharBuffer() throws ReaderException {
        try {
            charBuffer = bufferedReader.read();
        } catch (IOException e) {
            throw new ReaderException("Unable to read from the stream.", e);
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
     * Method that remembers a single character represented by Unicode code from {@link FileReader#charBuffer},
     * calls {@link FileReader#prepareCharBuffer()} method and then returns memorized character code.
     *
     * @return Unicode code of read character.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public int read() throws ReaderException {
        if (bufferedReader == null) {
            throw new ReaderException("Stream is closed.");
        }

        int currentChar = charBuffer;

        if (hasNext()) {
            prepareCharBuffer();
        }

        return currentChar;
    }

    /**
     * Method that performs closing of {@link FileReader}.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public void close() throws ReaderException {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new ReaderException("Unable to close the stream.", e);
            } finally {
                bufferedReader = null;
                charBuffer = -1;
            }
        }
    }
}
