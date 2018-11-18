package it.sevenbits.homework.io.reader;

/**
 * Implementation of {@link it.sevenbits.homework.io.reader.IReader} interface
 * that performs reading from String instance.
 */
public class StringReader implements IReader {
    private String sourceString;
    private int currentIndex;

    /**
     * Class constructor that initializes private String field with an external String instance passed as argument.
     *
     * @param sourceString String that represents the data source for reading.
     */
    public StringReader(final String sourceString) {
        this.sourceString = sourceString;
        currentIndex = 0;
    }

    /**
     * Method that reports whether data is available for reading.
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
     * Method that reads a single character represented by Unicode code from String instance.
     *
     * @return Unicode code of read character.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public int read() throws ReaderException {
        if (sourceString == null) {
            throw new ReaderException("IReader instance is closed or null is passed to constructor as an argument.");
        }

        if (!hasNext()) {
            return -1;
        }

        char result = sourceString.charAt(currentIndex);
        currentIndex++;
        return result;
    }

    /**
     * Method that performs closing of stream.
     *
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    @Override
    public void close() throws ReaderException {
        sourceString = null;
        currentIndex = 0;
    }
}
