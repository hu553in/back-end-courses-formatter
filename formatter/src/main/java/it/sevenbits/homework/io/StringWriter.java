package it.sevenbits.homework.io;

/**
 * String implementation of IWriter interface that performs writing to external StringBuilder instance.
 */
public class StringWriter implements IWriter {
    private StringBuilder stringBuilder;

    /**
     * Class constructor that initializes private StringBuilder field with a new instance of StringBuilder.
     */
    public StringWriter() {
        this.stringBuilder = new StringBuilder();
    }

    /**
     * Overload of method that writes a single character represented by Unicode code to StringBuilder instance.
     *
     * @param character Unicode character code to be written to private StringBuilder instance.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final int character) throws WriterException {
        stringBuilder.append((char) character);
    }

    /**
     * Overload of method that writes String instance to private StringBuilder instance.
     *
     * @param string String to be written to destination.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    @Override
    public void write(final String string) throws WriterException {
        stringBuilder.append(string);
    }

    /**
     * Method that returns a result of class work.
     *
     * @return String that represents a result of class work.
     */
    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
