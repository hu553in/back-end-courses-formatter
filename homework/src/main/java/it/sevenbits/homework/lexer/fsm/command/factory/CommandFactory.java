package it.sevenbits.homework.lexer.fsm.command.factory;

import it.sevenbits.homework.lexer.fsm.command.ICommand;
import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.lexer.fsm.command.args.ICommandArgs;

public class CommandFactory implements ICommandFactory {
    private final CommandMap commandMap;

    public CommandFactory(final ICommandArgs commandArgs) {
        commandMap = new CommandMap(commandArgs);
    }

    @Override
    public ICommand getCommand(final State currentState, final char character) throws CommandFactoryException {
        if (currentState == null) {
            throw new CommandFactoryException("\"currentState\" is null");
        }

        return commandMap.getCommand(currentState, character);
    }
}
