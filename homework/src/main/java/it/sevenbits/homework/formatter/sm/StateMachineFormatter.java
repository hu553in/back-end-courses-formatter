package it.sevenbits.homework.formatter.sm;

import it.sevenbits.homework.formatter.FormatterException;
import it.sevenbits.homework.formatter.IFormatter;
import it.sevenbits.homework.formatter.sm.command.factory.FormatterCommandFactory;
import it.sevenbits.homework.formatter.sm.command.factory.FormatterCommandFactoryException;
import it.sevenbits.homework.formatter.sm.command.factory.IFormatterCommandFactory;
import it.sevenbits.homework.formatter.sm.command.args.FormatterCommandArgs;
import it.sevenbits.homework.formatter.sm.command.args.IFormatterCommandArgs;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.lexer.ILexer;
import it.sevenbits.homework.lexer.LexerException;
import it.sevenbits.homework.lexer.factory.ILexerFactory;
import it.sevenbits.homework.lexer.factory.LexerFactory;
import it.sevenbits.homework.lexer.factory.LexerFactoryException;
import it.sevenbits.homework.lexer.token.IToken;

// TODO(hu553in): remove warning suppression
@SuppressWarnings("Duplicates")
public class StateMachineFormatter implements IFormatter {
    private static final String SINGLE_INDENT = "    ";
    private final ILexerFactory lexerFactory;
    private final FormatterStateTransitions formatterStateTransitions;
    private final IFormatterCommandFactory commandFactory;
    private final IFormatterCommandArgs commandArgs;

    public StateMachineFormatter() {
        lexerFactory = new LexerFactory();
        formatterStateTransitions = new FormatterStateTransitions();
        commandArgs = new FormatterCommandArgs();
        commandFactory = new FormatterCommandFactory(commandArgs);
    }

    private String getIndent(final short nestingLevel) {
        if (nestingLevel == 0) {
            return "";
        }

        final StringBuilder resultingIndent = new StringBuilder();

        for (short i = 0; i < nestingLevel; i++) {
            resultingIndent.append(SINGLE_INDENT);
        }

        return resultingIndent.toString();
    }

    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        if (reader == null) {
            throw new FormatterException("\"reader\" argument is null");
        }

        if (writer == null) {
            throw new FormatterException("\"writer\" argument is null");
        }

        final ILexer lexer;

        try {
            lexer = lexerFactory.createLexer(reader);
        } catch (LexerFactoryException e) {
            throw new FormatterException("Unable to create ILexer instance", e);
        }

        FormatterState currentFormatterState = formatterStateTransitions.getStartState();

        while (lexer.hasMoreTokens()) {
            final IToken currentToken;

            try {
                currentToken = lexer.readToken();
            } catch (LexerException e) {
                throw new FormatterException("Unable to read token from ILexer instance", e);
            }

            try {
                commandFactory.getCommand(currentFormatterState, currentToken).execute();
            } catch (FormatterCommandFactoryException e) {
                throw new FormatterException("Unable to get command from factory", e);
            }

            currentFormatterState = formatterStateTransitions.nextState(currentFormatterState, currentToken);
        }
    }
}
