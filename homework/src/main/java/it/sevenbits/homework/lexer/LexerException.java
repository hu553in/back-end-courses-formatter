package it.sevenbits.homework.lexer;

/**
 * This exception is used by ILexer interface and classes that implement it.
 */
public class LexerException extends Exception {
    /**
     * Class constructor with specifying of an error message.
     *
     * @param message Message that will be contained in the thrown instance of exception.
     */
    public LexerException(final String message) {
        super(message);
    }

    /**
     * Class constructor with specifying of an error message and Throwable cause of exception throwing.
     *
     * @param message Message that will be contained in the thrown instance of exception.
     * @param cause An instance of Throwable that caused the situation in which exception was thrown.
     */
    public LexerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
