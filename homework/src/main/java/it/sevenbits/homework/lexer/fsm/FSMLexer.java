package it.sevenbits.homework.lexer.fsm;

import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.io.reader.IReader;
import it.sevenbits.homework.io.reader.ReaderException;
import it.sevenbits.homework.lexer.ILexer;
import it.sevenbits.homework.lexer.LexerException;
import it.sevenbits.homework.lexer.fsm.command.args.CommandArgs;
import it.sevenbits.homework.lexer.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.lexer.fsm.tokenbuilder.ITokenBuilder;
import it.sevenbits.homework.lexer.fsm.tokenbuilder.TokenBuilder;
import it.sevenbits.homework.lexer.fsm.command.factory.CommandFactory;
import it.sevenbits.homework.lexer.fsm.command.factory.CommandFactoryException;
import it.sevenbits.homework.lexer.fsm.command.factory.ICommandFactory;
import it.sevenbits.homework.lexer.fsm.state.StateTransitions;
import it.sevenbits.homework.lexer.token.IToken;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FSMLexer implements ILexer {
    private final IReader reader;
    private int charBuffer;

    public FSMLexer(final IReader reader) throws LexerException {
        this.reader = reader;

        if (reader.hasNext()) {
            try {
                charBuffer = reader.read();
            } catch (ReaderException e) {
                throw new LexerException("Unable to read from reader", e);
            }
        }
    }

    private void nameToken(final ITokenBuilder tokenBuilder) {
        final String lexeme = tokenBuilder.getLexeme();
        final HashMap<Pattern, String> tokenNamesForPatterns = new HashMap<>();

        tokenNamesForPatterns.put(Pattern.compile("\\{"), "OPENING_CURLY_BRACE");
        tokenNamesForPatterns.put(Pattern.compile("}"), "CLOSING_CURLY_BRACE");
        tokenNamesForPatterns.put(Pattern.compile(";"), "SEMICOLON");
        tokenNamesForPatterns.put(Pattern.compile("\n"), "NEWLINE");
        tokenNamesForPatterns.put(Pattern.compile("\t"), "TAB");
        tokenNamesForPatterns.put(Pattern.compile(" "), "WHITESPACE");
        tokenNamesForPatterns.put(Pattern.compile("//.*"), "SINGLE_LINE_COMMENT");
        tokenNamesForPatterns.put(Pattern.compile("/\\*[\\d\\D]*\\*/"), "MULTILINE_COMMENT");
        tokenNamesForPatterns.put(Pattern.compile("'[\\d\\D]*'"), "CHARACTER_LITERAL");
        tokenNamesForPatterns.put(Pattern.compile("\"[\\d\\D]*\""), "STRING_LITERAL");

        tokenBuilder.setName(
                tokenNamesForPatterns.entrySet().stream().filter(
                        mapEntry -> mapEntry.getKey().matcher(lexeme).matches()
                ).findAny().map(Map.Entry::getValue).orElse("OTHER")
        );
    }

    @Override
    public boolean hasMoreTokens() {
        return charBuffer != -1;
    }

    @Override
    public IToken readToken() throws LexerException {
        if (!hasMoreTokens()) {
            throw new LexerException("No tokens available for reading");
        }

        final StateTransitions stateTransitions = new StateTransitions();
        final State endState = stateTransitions.getEndState();
        final ICommandArgs commandArgs = new CommandArgs();
        final ITokenBuilder tokenBuilder = new TokenBuilder();
        final ICommandFactory commandFactory = new CommandFactory(commandArgs);

        commandArgs.setTokenBuilder(tokenBuilder);
        State currentState = stateTransitions.getStartState();

        while (charBuffer != -1) {
            commandArgs.setCharBuffer((char) charBuffer);

            try {
                commandFactory.getCommand(currentState, (char) charBuffer).execute();
            } catch (CommandFactoryException e) {
                throw new LexerException("Unable to get command from factory", e);
            }

            currentState = stateTransitions.nextState(currentState, (char) charBuffer);

            if (currentState.equals(endState)) {
                break;
            }

            if (reader.hasNext()) {
                try {
                    charBuffer = reader.read();
                } catch (ReaderException e) {
                    throw new LexerException("Unable to read from reader", e);
                }
            } else {
                charBuffer = -1;
            }
        }

        nameToken(tokenBuilder);
        return tokenBuilder.getToken();
    }
}
