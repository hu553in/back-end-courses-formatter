package it.sevenbits.formatter.io.reader;

import java.io.IOException;

/**
 * This exception is used by {@link IReader} interface and classes that implement it.
 */
public class ReaderException extends IOException {
    /**
     * Class constructor with specifying of an error message.
     *
     * @param message {@link String} instance that will be contained in the thrown instance of exception.
     */
    public ReaderException(final String message) {
        super(message);
    }

    /**
     * Class constructor with specifying of an error message and {@link Throwable} cause of exception throwing.
     *
     * @param message {@link String} instance that will be contained in the thrown instance of exception.
     * @param cause An instance of {@link Throwable} that caused the situation in which exception was thrown.
     */
    public ReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
