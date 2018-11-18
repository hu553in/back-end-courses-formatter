package it.sevenbits.homework.io.writer;

import java.io.IOException;

/**
 * This exception is used by {@link it.sevenbits.homework.io.writer.IWriter} interface and classes that implement it.
 */
public class WriterException extends IOException {
    /**
     * Class constructor with specifying of an error message.
     *
     * @param message Message that will be contained in the thrown instance of exception.
     */
    public WriterException(final String message) {
        super(message);
    }

    /**
     * Class constructor with specifying of an error message and Throwable cause of exception throwing.
     *
     * @param message Message that will be contained in the thrown instance of exception.
     * @param cause An instance of Throwable that caused the situation in which exception was thrown.
     */
    public WriterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
