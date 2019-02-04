package it.sevenbits.homework.lexer.fsm.command;

/**
 * Interface that describes functionality for command that may be executed during FSM work.
 */
@FunctionalInterface
public interface ICommand {
    /**
     * Method that performs command execution.
     */
    void execute();
}
