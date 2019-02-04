package it.sevenbits.homework.lexer.fsm.command.factory;

/**
 * This exception is used by {@link ICommandFactory} interface and classes that implement it.
 */
public class CommandFactoryException extends Exception {
    /**
     * Class constructor with specifying of an error message.
     *
     * @param message {@link String} instance that will be contained in the thrown instance of exception.
     */
    public CommandFactoryException(final String message) {
        super(message);
    }
}
