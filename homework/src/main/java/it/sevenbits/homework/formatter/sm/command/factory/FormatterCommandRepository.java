package it.sevenbits.homework.formatter.sm.command.factory;

import it.sevenbits.homework.formatter.sm.command.IFormatterCommand;
import it.sevenbits.homework.formatter.sm.command.args.IFormatterCommandArgs;
import it.sevenbits.homework.formatter.sm.FormatterState;
import it.sevenbits.homework.lexer.token.IToken;
import it.sevenbits.homework.formatter.sm.util.Pair;
import java.util.HashMap;
import java.util.Map;

public class FormatterCommandRepository {
    private final Map<Pair<FormatterState, String>, IFormatterCommand> commandMap;

    FormatterCommandRepository(final IFormatterCommandArgs commandArgs) {
        commandMap = new HashMap<>();
    }

    IFormatterCommand getCommand(final FormatterState currentFormatterState, final IToken token) {
        return commandMap.get(new Pair<>(currentFormatterState, token.getName()));
    }
}
