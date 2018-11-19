package it.sevenbits.homework.io.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementation of {@link it.sevenbits.homework.io.writer.IWriter} interface
 * that performs writing to file in UTF-8 encoding.
 */
public class FileWriter implements IWriter {
    private BufferedWriter bufferedWriter;

    /**
     * Overload of constructor that initializes {@link FileWriter#bufferedWriter} using
     * {@link java.nio.file.Path} instance.
     *
     * @param filePath {@link java.nio.file.Path} instance that represents a path to output file.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    public FileWriter(final Path filePath) throws WriterException {
        try {
            bufferedWriter = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new WriterException("Unable to open the stream.", e);
        }
    }

    /**
     * Overload of constructor that initializes {@link FileWriter#bufferedWriter}
     * using return value of {@link java.nio.file.Paths#get(String, String...)} method.
     *
     * @param filePath {@link java.lang.String} instance that represents a path to input file.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    public FileWriter(final String filePath) throws WriterException {
        this(Paths.get(filePath));
    }

    /**
     * Overload of method that writes to {@link FileWriter#bufferedWriter} a single character
     * represented by Unicode code.
     *
     * @param character Unicode character code to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    @Override
    public void write(final int character) throws WriterException {
        if (bufferedWriter == null) {
            throw new WriterException("Stream is closed.");
        }

        try {
            bufferedWriter.write(character);
        } catch (IOException e) {
            throw new WriterException("Unable to write to the stream.", e);
        }
    }

    /**
     * Overload of method that writes to {@link FileWriter#bufferedWriter} a {@link java.lang.String} instance.
     *
     * @param string {@link java.lang.String} instance to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    @Override
    public void write(final String string) throws WriterException {
        if (bufferedWriter == null) {
            throw new WriterException("Stream is closed.");
        }

        try {
            bufferedWriter.write(string);
        } catch (IOException e) {
            throw new WriterException("Unable to write to the stream.", e);
        }
    }

    /**
     * Overload of method that writes to {@link FileWriter#bufferedWriter} an array of characters.
     *
     * @param charArray Array of characters to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    @Override
    public void write(final char[] charArray) throws WriterException {
        if (bufferedWriter == null) {
            throw new WriterException("Stream is closed.");
        }

        try {
            bufferedWriter.write(charArray);
        } catch (IOException e) {
            throw new WriterException("Unable to write to the stream.", e);
        }
    }

    /**
     * Method that performs closing of {@link FileWriter}.
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
            } finally {
                bufferedWriter = null;
            }
        }
    }
}
