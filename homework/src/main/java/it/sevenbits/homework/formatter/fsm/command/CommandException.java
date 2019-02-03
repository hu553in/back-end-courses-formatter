package it.sevenbits.homework.formatter.fsm.command;

public class CommandException extends Exception {
    public CommandException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
