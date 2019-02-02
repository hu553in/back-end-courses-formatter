package it.sevenbits.homework.formatter.fsm.command.factory;

import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.lexer.token.IToken;

public interface ICommandFactory {
    ICommand getCommand(
            State currentState,
            IToken token
    ) throws CommandFactoryException;
}
