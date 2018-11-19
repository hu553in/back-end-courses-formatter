package it.sevenbits.homework.io.writer;

/**
 * Implementation of {@link it.sevenbits.homework.io.writer.IWriter} interface that performs writing
 * to internal {@link StringWriter#buffer} represented by {@link java.lang.StringBuffer} instance.
 */
public class StringWriter implements IWriter {
    private StringBuffer buffer;

    /**
     * Class constructor that initializes buffer with a new {@link java.lang.StringBuffer} instance.
     */
    public StringWriter() {
        this.buffer = new StringBuffer();
    }

    /**
     * Overload of method that writes a single character represented by Unicode code to {@link StringWriter#buffer}.
     *
     * @param character Unicode character code to be written.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final int character) throws WriterException {
        if (buffer == null) {
            throw new WriterException("Stream is closed.");
        }

        buffer.append((char) character);
    }

    /**
     * Overload of method that writes a {@link java.lang.String} instance to {@link StringWriter#buffer}.
     *
     * @param string {@link java.lang.String} instance to be written.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final String string) throws WriterException {
        if (buffer == null) {
            throw new WriterException("Stream is closed.");
        }

        buffer.append(string);
    }

    /**
     * Overload of method that writes an array of characters to {@link StringWriter#buffer}.
     *
     * @param charArray Array of characters to be written.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final char[] charArray) throws WriterException {
        if (buffer == null) {
            throw new WriterException("Stream is closed.");
        }

        buffer.append(charArray);
    }


    /**
     * Method that returns {@link java.lang.String} instance which contains all written data.
     *
     * @return {@link java.lang.String} instance which contains all written data.
     */
    @Override
    public String toString() {
        if (buffer == null) {
            return null;
        }

        return buffer.toString();
    }

    /**
     * Method that performs closing of {@link StringWriter}.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void close() throws WriterException {
        buffer = null;
    }
}
