package it.sevenbits.homework.lexer.fsm.command;

import it.sevenbits.homework.fsm.command.CommandException;
import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.lexer.fsm.command.args.ICommandArgs;

public class AddCharacterToTokenBuilderCommand implements ICommand {
    private final ICommandArgs commandArgs;

    public AddCharacterToTokenBuilderCommand(final ICommandArgs commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute() throws CommandException {
        commandArgs.getTokenBuilder().appendToLexeme(commandArgs.getCharBuffer());
    }
}
