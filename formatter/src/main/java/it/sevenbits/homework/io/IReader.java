package it.sevenbits.homework.io;

import java.io.IOException;

/**
 * Interface that describes a functionality for reading data from an abstract data source.
 */
public interface IReader {
    /**
     * Method that reports whether data is available for reading.
     *
     * @return Boolean value that indicates whether data is available for reading.
     */
    boolean hasNext();

    /**
     * A method that reads a single character represented by Unicode code.
     *
     * @return Unicode code of read character.
     *
     * @throws IOException Input/output exception that may be thrown during the method work.
     */
    int read() throws IOException;
}
