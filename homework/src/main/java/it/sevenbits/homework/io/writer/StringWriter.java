package it.sevenbits.homework.io.writer;

/**
 * Implementation of {@link it.sevenbits.homework.io.writer.IWriter} interface
 * that performs writing to internal buffer represented by StringBuffer instance.
 */
public class StringWriter implements IWriter {
    private StringBuffer buffer;

    /**
     * Class constructor that initializes buffer with a new StringBuffer instance.
     */
    public StringWriter() {
        this.buffer = new StringBuffer();
    }

    /**
     * Overload of method that writes a single character represented by Unicode code to buffer.
     *
     * @param character Unicode character code to be written.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final int character) throws WriterException {
        if (buffer == null) {
            throw new WriterException("IWriter instance is closed.");
        }

        buffer.append((char) character);
    }

    /**
     * Overload of method that writes a String instance to buffer.
     *
     * @param string String instance to be written.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final String string) throws WriterException {
        if (buffer == null) {
            throw new WriterException("IWriter instance is closed.");
        }

        buffer.append(string);
    }

    /**
     * Method that returns String instance which contains all written data.
     *
     * @return String instance which contains all written data.
     */
    @Override
    public String toString() {
        if (buffer == null) {
            return null;
        }

        return buffer.toString();
    }

    /**
     * Method that performs closing of stream.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void close() throws WriterException {
        buffer = null;
    }
}
