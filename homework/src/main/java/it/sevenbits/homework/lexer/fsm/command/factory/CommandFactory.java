package it.sevenbits.homework.lexer.fsm.command.factory;

import it.sevenbits.homework.lexer.fsm.state.State;
import it.sevenbits.homework.lexer.fsm.command.ICommand;
import it.sevenbits.homework.lexer.fsm.command.args.ICommandArgs;

/**
 * {@link ICommandFactory} interface implementation that presents command factory.
 */
public class CommandFactory implements ICommandFactory {
    private final CommandMap commandMap;

    /**
     * Class constructor that initializes private {@link #commandMap} field using passed {@link ICommandArgs} instance.
     *
     * @param commandArgs {@link ICommandArgs} instance that presents command arguments container.
     */
    public CommandFactory(final ICommandArgs commandArgs) {
        commandMap = new CommandMap(commandArgs);
    }

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
    @Override
    public ICommand getCommand(final State currentState, final char character) throws CommandFactoryException {
        if (currentState == null) {
            throw new CommandFactoryException("\"currentState\" is null");
        }

        return commandMap.getCommand(currentState, character);
    }
}
