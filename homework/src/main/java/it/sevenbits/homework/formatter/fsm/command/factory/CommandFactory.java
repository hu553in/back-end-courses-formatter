package it.sevenbits.homework.formatter.fsm.command.factory;

import it.sevenbits.homework.fsm.command.ICommand;
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
        final ICommand command = commandMap.getCommand(currentState, token);

        if (command == null) {
            throw new CommandFactoryException(
                    "There are no commands associated with this state and token name"
            );
        }

        return command;
    }
}
