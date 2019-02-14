package it.sevenbits.formatter.io.writer;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementation of {@link IWriter} interface that performs writing to file in UTF-8 encoding.
 */
public class FileWriter implements IWriter, Closeable {
    private final BufferedWriter bufferedWriter;

    /**
     * Overload of constructor that initializes {@link #bufferedWriter} using {@link Path} instance.
     *
     * @param filePath {@link Path} instance that represents a path to output file.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    public FileWriter(final Path filePath) throws WriterException {
        try {
            bufferedWriter = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new WriterException("Unable to open stream", e);
        }
    }

    /**
     * Overload of constructor that initializes {@link #bufferedWriter}
     * using return value of {@link Paths#get(String, String...)} method.
     *
     * @param filePath {@link String} instance that represents a path to input file.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    public FileWriter(final String filePath) throws WriterException {
        this(Paths.get(filePath));
    }

    /**
     * Private method that performs ensuring that stream is open.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    private void ensureOpen() throws WriterException {
        if (bufferedWriter == null) {
            throw new WriterException("Stream is closed");
        }
    }

    /**
     * Overload of method that writes to {@link #bufferedWriter} a single character represented by Unicode code.
     *
     * @param character Unicode character code to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    @Override
    public void write(final int character) throws WriterException {
        ensureOpen();

        try {
            bufferedWriter.write(character);
        } catch (IOException e) {
            throw new WriterException("Unable to write to stream", e);
        }
    }

    /**
     * Overload of method that writes to {@link #bufferedWriter} a {@link String} instance.
     *
     * @param string {@link String} instance to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    @Override
    public void write(final String string) throws WriterException {
        ensureOpen();

        try {
            bufferedWriter.write(string);
        } catch (IOException e) {
            throw new WriterException("Unable to write to stream", e);
        }
    }

    /**
     * Overload of method that writes to {@link #bufferedWriter} an array of characters.
     *
     * @param charArray Array of characters to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    @Override
    public void write(final char[] charArray) throws WriterException {
        ensureOpen();

        try {
            bufferedWriter.write(charArray);
        } catch (IOException e) {
            throw new WriterException("Unable to write to stream", e);
        }
    }

    /**
     * Method that performs {@link FileWriter} closing.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    @Override
    public void close() throws WriterException {
        if (bufferedWriter != null) {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new WriterException("Unable to close the stream.", e);
            }
        }
    }
}
