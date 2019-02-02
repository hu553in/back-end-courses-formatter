package it.sevenbits.homework.lexer.fsm.command.factory;

import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.fsm.state.State;

public interface ICommandFactory {
    ICommand getCommand(
            State currentState,
            char character
    ) throws CommandFactoryException;
}
