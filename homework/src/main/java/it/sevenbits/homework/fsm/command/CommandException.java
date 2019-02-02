package it.sevenbits.homework.fsm.command;

public class CommandException extends Exception {
    public CommandException(final String message) {
        super(message);
    }

    public CommandException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
