package it.sevenbits.homework.io.writer;

import java.io.Closeable;

/**
 * Interface that declares a functionality for output character stream.
 */
public interface IWriter extends Closeable {
    /**
     * Overload of method that writes to stream a single character represented by Unicode code.
     *
     * @param character Unicode character code to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    void write(int character) throws WriterException;

    /**
     * Overload of method that writes to stream a {@link java.lang.String} instance.
     *
     * @param string {@link java.lang.String} instance to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    void write(String string) throws WriterException;

    /**
     * Overload of method that writes to stream an array of characters.
     *
     * @param charArray Array of characters to be written.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    void write(char[] charArray) throws WriterException;

    /**
     * Method that performs closing of {@link IWriter}.
     *
     * @throws WriterException Exception that can be thrown during the method work.
     */
    @Override
    void close() throws WriterException;
}
