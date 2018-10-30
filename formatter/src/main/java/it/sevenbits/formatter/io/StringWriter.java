package it.sevenbits.formatter.io;

import java.io.IOException;

/**
 * String implementation of IWriter interface that performs writing to external StringBuilder instance.
 */
public class StringWriter implements IWriter {
    private StringBuilder stringBuilder;

    /**
     * Class constructor that initializes private StringBuilder field with an external instance of StringBuilder.
     *
     * @param stringBuilder External instance of StringBuilder that serves as the destination of writing.
     */
    public StringWriter(final StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    /**
     * Overload of method that writes a single character represented by Unicode code to StringBuilder instance.
     *
     * @param character Unicode character code to be written to StringBuilder instance.
     *
     * @throws IOException Input/output exception that may be thrown during the method work.
     */
    @Override
    public void write(final int character) throws IOException {
        stringBuilder.append((char) character);
    }

    /**
     * Overload of method that writes String instance to StringBuilder instance.
     *
     * @param string String to be written to destination.
     *
     * @throws IOException Input/output exception that may be thrown during the method work.
     */
    @Override
    public void write(final String string) throws IOException {
        stringBuilder.append(string);
    }
}
