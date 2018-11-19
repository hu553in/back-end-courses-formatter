package it.sevenbits.homework.io.reader;

/**
 * Implementation of {@link IReader} interface that performs reading from {@link String} instance.
 */
public class StringReader implements IReader {
    private String sourceString;
    private int currentIndex;

    /**
     * Class constructor that initializes {@link #sourceString}
     * with an external {@link String} instance passed as argument.
     *
     * @param sourceString {@link String} instance that represents the data source for reading.
     */
    public StringReader(final String sourceString) {
        this.sourceString = sourceString;
        currentIndex = 0;
    }

    /**
     * Method that reports whether character is available for reading.
     *
     * @return Boolean value that indicates result of the method work.
     */
    @Override
    public boolean hasNext() {
        if (sourceString == null) {
            return false;
        }

        return currentIndex < sourceString.length();
    }

    /**
     * Method that reads a single character represented by Unicode code from {@link #sourceString}.
     *
     * @return Unicode code of read character.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public int read() throws ReaderException {
        if (sourceString == null) {
            throw new ReaderException("Stream is closed or null is passed to constructor as an argument.");
        }

        if (!hasNext()) {
            return -1;
        }

        final char currentChar = sourceString.charAt(currentIndex);
        currentIndex++;
        return currentChar;
    }

    /**
     * Method that performs {@link StringReader} closing.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public void close() throws ReaderException {
        if (currentIndex != 0) {
            currentIndex = 0;
        }

        if (sourceString != null) {
            sourceString = null;
        }
    }
}
