package it.sevenbits.formatter.formatter.fsm.command;

import it.sevenbits.formatter.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.formatter.formatter.util.IndentProvider;
import it.sevenbits.formatter.io.writer.IWriter;
import it.sevenbits.formatter.io.writer.WriterException;

/**
 * {@link ICommand} interface implementation that presents one of the commands.
 */
public class WriteAfterNewlineAndIndentCommand implements ICommand {
    private final ICommandArgs commandArgs;

    /**
     * Class constructor that initializes private {@link #commandArgs} field with passed
     * {@link ICommandArgs} instance that presents command arguments container.
     *
     * @param commandArgs {@link ICommandArgs} instance that presents command arguments container.
     */
    public WriteAfterNewlineAndIndentCommand(final ICommandArgs commandArgs) {
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
