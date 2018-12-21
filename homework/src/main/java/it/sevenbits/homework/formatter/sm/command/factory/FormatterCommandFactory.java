package it.sevenbits.homework.formatter.sm.command.factory;

import it.sevenbits.homework.formatter.sm.command.IFormatterCommand;
import it.sevenbits.homework.formatter.sm.command.args.IFormatterCommandArgs;
import it.sevenbits.homework.formatter.sm.FormatterState;
import it.sevenbits.homework.lexer.token.IToken;

public class FormatterCommandFactory implements IFormatterCommandFactory {
    private final FormatterCommandRepository formatterCommandRepository;

    public FormatterCommandFactory(final IFormatterCommandArgs commandArgs) {
        formatterCommandRepository = new FormatterCommandRepository(commandArgs);
    }

    @Override
    public IFormatterCommand getCommand(
            final FormatterState currentFormatterState,
            final IToken token
    ) throws FormatterCommandFactoryException {
        final IFormatterCommand command = formatterCommandRepository.getCommand(currentFormatterState, token);

        if (command == null) {
            throw new FormatterCommandFactoryException(
                    "There are no commands associated with this state and token name"
            );
        }

        return command;
    }
}
