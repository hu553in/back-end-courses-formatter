package it.sevenbits.homework.io;

import java.io.EOFException;

/**
 * String implementation of IReader interface that performs reading from String instance.
 */
public class StringReader implements IReader {
    private String string;
    private int currentIndex;

    /**
     * Class constructor that initializes private String field with a String instance passed as argument.
     *
     * @param string String that is the data source for reading.
     */
    public StringReader(final String string) {
        this.string = string;
        currentIndex = 0;
    }

    /**
     * Method that reports whether data is available for reading.
     *
     * @return Boolean value that indicates whether data is available for reading.
     */
    @Override
    public boolean hasNext() {
        return currentIndex < string.length();
    }

    /**
     * A method that reads a single character represented by Unicode code.
     *
     * @return Unicode code of read character.
     *
     * @throws EOFException End-of-file exception that can be thrown if a read attempt is made
     *         despite the lack of data available for reading.
     */
    @Override
    public int read() throws EOFException {
        if (!hasNext()) {
            throw new EOFException("There are no more characters to read in the input line.");
        }

        char result = string.charAt(currentIndex);
        currentIndex++;
        return result;
    }
}
