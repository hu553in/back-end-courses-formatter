package it.sevenbits.homework.lexer.fsm.command.factory;

import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.lexer.fsm.command.args.ICommandArgs;
import org.slf4j.LoggerFactory;

public class CommandFactory implements ICommandFactory {
    private final CommandMap commandMap;

    public CommandFactory(final ICommandArgs commandArgs) {
        commandMap = new CommandMap(commandArgs);
    }

    @Override
    public ICommand getCommand(final State currentState, final char character) throws CommandFactoryException {
        final ICommand command = commandMap.getCommand(currentState, character);

        if (command == null) {
            LoggerFactory.getLogger(CommandFactory.class).info(currentState.toString() + " " + character);
            throw new CommandFactoryException(
                    "There are no commands associated with this state and character"
            );
        }

        return command;
    }
}
