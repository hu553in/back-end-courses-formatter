package it.sevenbits.homework.formatter;

/**
 * Exception which is used by IFormatter interface and classes derived from it.
 */
public class FormatterException extends Exception {
    /**
     * FormatterException class constructor with specifying of an error message.
     *
     * @param message Text that will be contained in the thrown instance of exception.
     */
    public FormatterException(final String message) {
        super(message);
    }

    /**
     * FormatterException class constructor with specifying of an error message and Throwable cause
     * of FormatterException throwing.
     *
     * @param message Text that will be contained in the thrown instance of exception.
     * @param cause An instance of Throwable that caused the situation in which FormatterException was thrown.
     */
    public FormatterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
