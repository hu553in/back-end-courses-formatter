package it.sevenbits.formatter.lexer.fsm.command;

import it.sevenbits.formatter.lexer.fsm.command.args.ICommandArgs;

/**
 * {@link ICommand} interface implementation that presents one of the commands.
 */
public class AddCharacterToTokenBuilderCommand implements ICommand {
    private final ICommandArgs commandArgs;

    /**
     * Class constructor that initializes private {@link #commandArgs} field with passed
     * {@link ICommandArgs} instance that presents command arguments container.
     *
     * @param commandArgs {@link ICommandArgs} instance that presents command arguments container.
     */
    public AddCharacterToTokenBuilderCommand(final ICommandArgs commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Method that performs command execution.
     */
    @Override
    public void execute() {
        commandArgs.getTokenBuilder().appendToLexeme(commandArgs.getCharBuffer());
    }
}
