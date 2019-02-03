package it.sevenbits.homework.formatter.fsm;

import it.sevenbits.homework.formatter.FormatterException;
import it.sevenbits.homework.formatter.IFormatter;
import it.sevenbits.homework.formatter.fsm.command.CommandException;
import it.sevenbits.homework.formatter.fsm.command.factory.CommandFactory;
import it.sevenbits.homework.formatter.fsm.command.factory.CommandFactoryException;
import it.sevenbits.homework.formatter.fsm.command.factory.ICommandFactory;
import it.sevenbits.homework.formatter.fsm.command.args.CommandArgs;
import it.sevenbits.homework.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.formatter.fsm.state.StateTransitions;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.writer.IWriter;
import it.sevenbits.homework.lexer.ILexer;
import it.sevenbits.homework.lexer.LexerException;
import it.sevenbits.homework.lexer.factory.ILexerFactory;
import it.sevenbits.homework.lexer.factory.LexerFactory;
import it.sevenbits.homework.lexer.factory.LexerFactoryException;
import it.sevenbits.homework.lexer.token.IToken;

public class FSMFormatter implements IFormatter {
    private final ILexerFactory lexerFactory;

    public FSMFormatter() {
        lexerFactory = new LexerFactory();
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
            throw new FormatterException("Unable to create lexer instance", e);
        }

        final StateTransitions stateTransitions = new StateTransitions();
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
