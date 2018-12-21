package it.sevenbits.homework.formatter.sm.command.factory;

import it.sevenbits.homework.formatter.sm.FormatterState;
import it.sevenbits.homework.formatter.sm.command.IFormatterCommand;
import it.sevenbits.homework.lexer.token.IToken;

public interface IFormatterCommandFactory {
    IFormatterCommand getCommand(
            FormatterState currentFormatterState,
            IToken token
    ) throws FormatterCommandFactoryException;
}
