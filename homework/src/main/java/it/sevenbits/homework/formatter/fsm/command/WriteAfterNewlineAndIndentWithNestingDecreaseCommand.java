package it.sevenbits.homework.formatter.fsm.command;

import it.sevenbits.homework.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.formatter.util.IndentProvider;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.io.writer.WriterException;

/**
 * {@link ICommand} interface implementation that presents one of the commands.
 */
public class WriteAfterNewlineAndIndentWithNestingDecreaseCommand implements ICommand {
    private final ICommandArgs commandArgs;

    /**
     * Class constructor that initializes private {@link #commandArgs} field with passed
     * {@link ICommandArgs} instance that presents command arguments container.
     *
     * @param commandArgs {@link ICommandArgs} instance that presents command arguments container.
     */
    public WriteAfterNewlineAndIndentWithNestingDecreaseCommand(final ICommandArgs commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Method that performs command execution.
     *
     * @throws CommandException Exception that can be thrown during the method work.
     */
    @Override
    public void execute() throws CommandException {
        final IWriter writer = commandArgs.getWriter();
        final String currentLexeme = commandArgs.getCurrentLexeme();
        final int currentNestingLevel = commandArgs.getNestingLevel();

        if (currentNestingLevel > 0) {
            commandArgs.setNestingLevel(currentNestingLevel - 1);
        }

        try {
            writer.write(String.format(
                    "\n%s%s",
                    IndentProvider.getFourSpacesIndent(commandArgs.getNestingLevel()),
                    currentLexeme
            ));
        } catch (WriterException e) {
            throw new CommandException("Unable to write to writer", e);
        }
    }
}
