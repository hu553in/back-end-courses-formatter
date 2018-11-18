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
     * @throws WriterException Exception that may be thrown during the method work.
     */
    void write(int character) throws WriterException;

    /**
     * Overload of method that writes to stream a String instance.
     *
     * @param string String to be written.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    void write(String string) throws WriterException;

    /**
     * Method that performs closing of stream.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    void close() throws WriterException;
}
