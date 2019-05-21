package it.sevenbits.formatter.formatter.fsm;

import it.sevenbits.formatter.formatter.FormatterException;
import it.sevenbits.formatter.formatter.IFormatter;
import it.sevenbits.formatter.formatter.fsm.command.CommandException;
import it.sevenbits.formatter.formatter.fsm.command.args.CommandArgs;
import it.sevenbits.formatter.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.formatter.formatter.fsm.command.factory.CommandFactory;
import it.sevenbits.formatter.formatter.fsm.command.factory.CommandFactoryException;
import it.sevenbits.formatter.formatter.fsm.command.factory.ICommandFactory;
import it.sevenbits.formatter.formatter.fsm.state.IStateTransitions;
import it.sevenbits.formatter.formatter.fsm.state.State;
import it.sevenbits.formatter.formatter.fsm.state.StateTransitions;
import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.io.writer.IWriter;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.LexerException;
import it.sevenbits.formatter.lexer.factory.ILexerFactory;
import it.sevenbits.formatter.lexer.factory.LexerFactory;
import it.sevenbits.formatter.lexer.factory.LexerFactoryException;
import it.sevenbits.formatter.lexer.token.IToken;

/**
 * Class that formats Java source code. Input is performed using {@link IWriter} and {@link ILexer} instances,
 * output is performed using {@link IWriter} instance.
 * <p>
 * This {@link IFormatter} implementation is based on finite-state machine and "Command" design pattern.
 */
public class FSMFormatter implements IFormatter {
    private final ILexerFactory lexerFactory;

    /**
     * Class constructor that initializes {@link #lexerFactory} with new {@link LexerFactory} instance.
     */
    public FSMFormatter() {
        lexerFactory = new LexerFactory();
    }

    /**
     * Method that performs formatting of Java source code that is stored in lexical tokens
     * which are provided by {@link ILexer} instance.
     *
     * @param reader {@link IReader} instance that provides data reading.
     * @param writer {@link IWriter} instance that provides data writing.
     * @throws FormatterException Exception that can be thrown during the method work.
     */
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
            throw new FormatterException("Unable to create lexer instance", e);
        }

        final IStateTransitions stateTransitions = new StateTransitions();
        final State errorState = stateTransitions.getErrorState();
        final ICommandArgs commandArgs = new CommandArgs();
        final ICommandFactory commandFactory = new CommandFactory(commandArgs);

        commandArgs.setWriter(writer);
        commandArgs.setNestingLevel(0);
        State currentState = stateTransitions.getStartState();

        while (lexer.hasMoreTokens()) {
            final IToken currentToken;

            try {
                currentToken = lexer.readToken();
            } catch (LexerException e) {
                throw new FormatterException("Unable to read token from lexer", e);
            }

            commandArgs.setCurrentLexeme(currentToken.getLexeme());

            try {
                commandFactory.getCommand(currentState, currentToken).execute();
            } catch (CommandFactoryException e) {
                throw new FormatterException("Unable to get command from factory", e);
            } catch (CommandException e) {
                throw new FormatterException("Unable to execute command", e);
            }

            currentState = stateTransitions.nextState(currentState, currentToken);

            if (currentState.equals(errorState)) {
                throw new FormatterException("Unable to get next state basing on current state and token");
            }
        }
    }
}
