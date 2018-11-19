package it.sevenbits.homework.io.writer;

/**
 * Implementation of {@link IWriter} interface that performs writing to internal {@link #buffer}
 * represented by {@link StringBuffer} instance.
 */
public class StringWriter implements IWriter {
    private StringBuffer buffer;

    /**
     * Class constructor that initializes buffer with a new {@link StringBuffer} instance.
     */
    public StringWriter() {
        this.buffer = new StringBuffer();
    }

    /**
     * Overload of method that writes a single character represented by Unicode code to {@link #buffer}.
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
     * Overload of method that writes a {@link String} instance to {@link #buffer}.
     *
     * @param string {@link String} instance to be written.
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
     * Overload of method that writes an array of characters to {@link #buffer}.
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
     * Method that returns {@link String} instance which contains all written data.
     *
     * @return {@link String} instance which contains all written data.
     */
    @Override
    public String toString() {
        if (buffer == null) {
            return null;
        }

        return buffer.toString();
    }

    /**
     * Method that performs {@link StringWriter} closing.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void close() throws WriterException {
        if (buffer != null) {
            buffer = null;
        }
    }
}
