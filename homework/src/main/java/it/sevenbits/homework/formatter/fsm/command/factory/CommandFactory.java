package it.sevenbits.homework.formatter.fsm.command.factory;

import it.sevenbits.homework.formatter.fsm.command.ICommand;
import it.sevenbits.homework.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.lexer.token.IToken;

public class CommandFactory implements ICommandFactory {
    private final CommandMap commandMap;

    public CommandFactory(final ICommandArgs commandArgs) {
        commandMap = new CommandMap(commandArgs);
    }

    @Override
    public ICommand getCommand(final State currentState, final IToken token) throws CommandFactoryException {
        if (currentState == null) {
            throw new CommandFactoryException("\"currentState\" is null");
        }

        if (token == null) {
            throw new CommandFactoryException("\"token\" is null");
        }

        return commandMap.getCommand(currentState, token);
    }
}
