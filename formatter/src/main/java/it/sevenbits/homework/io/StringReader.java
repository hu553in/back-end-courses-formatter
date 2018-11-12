package it.sevenbits.homework.io;

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
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public int read() throws ReaderException {
        if (!hasNext()) {
            return -1;
        }

        char result = string.charAt(currentIndex);
        currentIndex++;
        return result;
    }
}
