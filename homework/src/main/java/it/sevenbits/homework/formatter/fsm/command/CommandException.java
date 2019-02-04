package it.sevenbits.homework.formatter.fsm.command;

/**
 * This exception is used by {@link ICommand} interface and classes that implement it.
 */
public class CommandException extends Exception {
    /**
     * Class constructor with specifying of an error message and {@link Throwable} cause of exception throwing.
     *
     * @param message {@link String} instance that will be contained in the thrown instance of exception.
     * @param cause An instance of {@link Throwable} that caused the situation in which exception was thrown.
     */
    public CommandException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
