package it.sevenbits.formatter.lexer;

/**
 * This exception is used by {@link ILexer} interface and classes that implement it.
 */
public class LexerException extends Exception {
    /**
     * Class constructor with specifying of an error message.
     *
     * @param message {@link String} instance that will be contained in the thrown instance of exception.
     */
    public LexerException(final String message) {
        super(message);
    }

    /**
     * Class constructor with specifying of an error message and {@link Throwable} cause of exception throwing.
     *
     * @param message {@link String} instance that will be contained in the thrown instance of exception.
     * @param cause An instance of {@link Throwable} that caused the situation in which exception was thrown.
     */
    public LexerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
