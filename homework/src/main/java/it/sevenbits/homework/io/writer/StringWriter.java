package it.sevenbits.homework.io.writer;

/**
 * String implementation of IWriter interface that performs writing to external StringBuilder instance.
 */
public class StringWriter implements IWriter {
    private StringBuffer stringBuffer;

    /**
     * Class constructor that initializes private StringBuffer field with a new instance of StringBuffer.
     */
    public StringWriter() {
        this.stringBuffer = new StringBuffer();
    }

    /**
     * Overload of method that writes a single character represented by Unicode code to StringBuffer instance.
     *
     * @param character Unicode character code to be written to private StringBuffer instance.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final int character) throws WriterException {
        stringBuffer.append((char) character);
    }

    /**
     * Overload of method that writes String instance to private StringBuffer instance.
     *
     * @param string String to be written to destination.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final String string) throws WriterException {
        stringBuffer.append(string);
    }

    /**
     * Method that returns a result of class work.
     *
     * @return String that represents a result of class work.
     */
    @Override
    public String toString() {
        return stringBuffer.toString();
    }

    /**
     * Method that performs closing of StringWriter.
     * It's a simple implementation of Closeable interface.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void close() throws WriterException {
        stringBuffer = null;
    }
}
