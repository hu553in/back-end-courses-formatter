package it.sevenbits.homework.lexer;

import java.io.IOException;

/**
 * Exception which is used by ILexer interface and classes derived from it.
 */
public class LexerException extends IOException {
    /**
     * LexerException class constructor without any arguments.
     */
    public LexerException() {
        super();
    }

    /**
     * LexerException class constructor with specifying of an error message.
     *
     * @param message Text that will be contained in the thrown instance of exception.
     */
    public LexerException(final String message) {
        super(message);
    }

    /**
     * LexerException class constructor with specifying of an error message and Throwable cause
     * of LexerException throwing.
     *
     * @param message Text that will be contained in the thrown instance of exception.
     * @param cause An instance of Throwable that caused the situation in which LexerException was thrown.
     */
    public LexerException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
