package it.sevenbits.homework.formatter.fsm.command.factory;

import it.sevenbits.homework.formatter.fsm.state.State;
import it.sevenbits.homework.formatter.fsm.command.ICommand;
import it.sevenbits.homework.lexer.token.IToken;

/**
 * Interface that describes functionality for command factory.
 */
public interface ICommandFactory {
    /**
     * Method that performs issue of {@link ICommand} instance corresponding to passed arguments.
     *
     * @param currentState {@link State} instance that presents current FSM state.
     * @param token {@link IToken} instance that presents a lexical token.
     *
     * @return {@link ICommand} instance that presents a command to execute.
     *
     * @throws CommandFactoryException Exception that can be thrown during the method work.
     */
    ICommand getCommand(State currentState, IToken token) throws CommandFactoryException;
}
