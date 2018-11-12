package it.sevenbits.homework.io;

import java.io.IOException;

/**
 * Exception which is used by IWriter interface and classes derived from it.
 */
public class WriterException extends IOException {
    /**
     * WriterException class constructor without any arguments.
     */
    public WriterException() {
        super();
    }

    /**
     * WriterException class constructor with specifying of an error message.
     *
     * @param message Text that will be contained in the thrown instance of exception.
     */
    public WriterException(final String message) {
        super(message);
    }

    /**
     * WriterException class constructor with specifying of an error message and Throwable cause
     * of WriterException throwing.
     *
     * @param message Text that will be contained in the thrown instance of exception.
     * @param cause An instance of Throwable that caused the situation in which WriterException was thrown.
     */
    public WriterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
