package it.sevenbits.homework.formatter.fsm.command;

import it.sevenbits.homework.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.formatter.fsm.util.IndentProvider;
import it.sevenbits.homework.fsm.command.CommandException;
import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.writer.WriterException;

public class IncreaseNestingAfterNewlineOrInStartCommand implements ICommand {
    private final ICommandArgs commandArgs;

    public IncreaseNestingAfterNewlineOrInStartCommand(final ICommandArgs commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute() throws CommandException {
        final IWriter writer = commandArgs.getWriter();
        final String currentLexeme = commandArgs.getCurrentLexeme();

        try {
            writer.write(IndentProvider.getFourSpacesIndent(commandArgs.getNestingLevel()));
            commandArgs.setNestingLevel(commandArgs.getNestingLevel() + 1);
            writer.write(String.format("%s\n", currentLexeme));
        } catch (WriterException e) {
            throw new CommandException("Unable to write to writer", e);
        }
    }
}
