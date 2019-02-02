package it.sevenbits.homework.formatter.fsm.command;

import it.sevenbits.homework.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.fsm.command.CommandException;
import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.writer.WriterException;

public class WriteLexemeBeforeNewlineCommand implements ICommand {
    private final ICommandArgs commandArgs;

    public WriteLexemeBeforeNewlineCommand(final ICommandArgs commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute() throws CommandException {
        final IWriter writer = commandArgs.getWriter();
        final String currentLexeme = commandArgs.getCurrentLexeme();

        try {
            writer.write(String.format("%s\n", currentLexeme));
        } catch (WriterException e) {
            throw new CommandException("Unable to write to writer", e);
        }
    }
}
