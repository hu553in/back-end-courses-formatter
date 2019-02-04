package it.sevenbits.homework.formatter.fsm.command;

/**
 * Interface that describes functionality for command that may be executed during FSM work.
 */
@FunctionalInterface
public interface ICommand {
    /**
     * Method that performs command execution.
     *
     * @throws CommandException Exception that can be thrown during the method work.
     */
    void execute() throws CommandException;
}
