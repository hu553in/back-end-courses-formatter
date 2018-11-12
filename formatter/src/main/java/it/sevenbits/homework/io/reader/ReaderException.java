package it.sevenbits.homework.io.reader;

import java.io.IOException;

/**
 * Exception which is used by IReader interface and classes derived from it.
 */
public class ReaderException extends IOException {
    /**
     * ReaderException class constructor without any arguments.
     */
    public ReaderException() {
        super();
    }

    /**
     * ReaderException class constructor with specifying of an error message.
     *
     * @param message Text that will be contained in the thrown instance of exception.
     */
    public ReaderException(final String message) {
        super(message);
    }

    /**
     * ReaderException class constructor with specifying of an error message and Throwable cause
     * of ReaderException throwing.
     *
     * @param message Text that will be contained in the thrown instance of exception.
     * @param cause An instance of Throwable that caused the situation in which ReaderException was thrown.
     */
    public ReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
