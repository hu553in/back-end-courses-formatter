package it.sevenbits.homework.io.writer;

/**
 * Interface that describes a functionality for writing data to an abstract destination.
 */
public interface IWriter {
    /**
     * Overload of method that writes a single character represented by Unicode code to an abstract destination.
     *
     * @param character Unicode character code to be written to destination.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    void write(int character) throws WriterException;

    /**
     * Overload of method that writes String instance to a destination.
     *
     * @param string String to be written to destination.
     *
     * @throws WriterException Exception that may be thrown during the method work.
     */
    void write(String string) throws WriterException;
}
