package it.sevenbits.formatter.io.reader;

/**
 * Interface that declares a functionality for input character stream.
 */
public interface IReader {
    /**
     * Method that reports whether character is available for reading.
     *
     * @return Boolean value that indicates result of the method work.
     */
    boolean hasNext();

    /**
     * Method that reads from stream a single character represented by Unicode code.
     *
     * @return Unicode code of read character.
     * @throws ReaderException Exception that can be thrown during the method work.
     */
    int read() throws ReaderException;
}
