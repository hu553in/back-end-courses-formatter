package it.sevenbits.homework.formatter.fsm.command;

import it.sevenbits.homework.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.formatter.fsm.util.IndentProvider;
import it.sevenbits.homework.fsm.command.CommandException;
import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.writer.WriterException;

public class ReduceNestingAfterNewlineOrInStartCommand implements ICommand {
    private final ICommandArgs commandArgs;

    public ReduceNestingAfterNewlineOrInStartCommand(final ICommandArgs commandArgs) {
        this.commandArgs = commandArgs;
    }

    @Override
    public void execute() throws CommandException {
        final IWriter writer = commandArgs.getWriter();
        final String currentLexeme = commandArgs.getCurrentLexeme();

        commandArgs.setNestingLevel(commandArgs.getNestingLevel() - 1);

        try {
            writer.write(String.format(
                    "%s%s\n",
                    IndentProvider.getFourSpacesIndent(commandArgs.getNestingLevel()),
                    currentLexeme
            ));
        } catch (WriterException e) {
            throw new CommandException("Unable to write to writer", e);
        }
    }
}
