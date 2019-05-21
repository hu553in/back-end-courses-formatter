package it.sevenbits.formatter.formatter.fsm.command.factory;

import it.sevenbits.formatter.formatter.fsm.command.ICommand;
import it.sevenbits.formatter.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.formatter.formatter.fsm.state.State;
import it.sevenbits.formatter.lexer.token.IToken;

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
     * @param token        {@link IToken} instance that presents a lexical token.
     * @return {@link ICommand} instance that presents a command to execute.
     * @throws CommandFactoryException Exception that can be thrown during the method work.
     */
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
