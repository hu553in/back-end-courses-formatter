package it.sevenbits.homework.lexer.fsm.command.factory;

import it.sevenbits.homework.lexer.fsm.command.ICommand;
import it.sevenbits.homework.lexer.fsm.state.State;

/**
 * Interface that describes functionality for command factory.
 */
public interface ICommandFactory {
    /**
     * Method that performs issue of {@link ICommand} instance corresponding to passed arguments.
     *
     * @param currentState {@link State} instance that presents current FSM state.
     * @param character Read character.
     *
     * @return {@link ICommand} instance that presents a command to execute.
     *
     * @throws CommandFactoryException Exception that can be thrown during the method work.
     */
    ICommand getCommand(State currentState, char character) throws CommandFactoryException;
}
