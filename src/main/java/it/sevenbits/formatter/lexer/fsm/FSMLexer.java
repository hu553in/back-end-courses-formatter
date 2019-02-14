package it.sevenbits.formatter.lexer.fsm;

import it.sevenbits.formatter.lexer.fsm.state.State;
import it.sevenbits.formatter.io.reader.IReader;
import it.sevenbits.formatter.io.reader.ReaderException;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.LexerException;
import it.sevenbits.formatter.lexer.fsm.command.args.CommandArgs;
import it.sevenbits.formatter.lexer.fsm.command.args.ICommandArgs;
import it.sevenbits.formatter.lexer.fsm.state.IStateTransitions;
import it.sevenbits.formatter.lexer.fsm.tokenbuilder.ITokenBuilder;
import it.sevenbits.formatter.lexer.fsm.tokenbuilder.TokenBuilder;
import it.sevenbits.formatter.lexer.fsm.command.factory.CommandFactory;
import it.sevenbits.formatter.lexer.fsm.command.factory.CommandFactoryException;
import it.sevenbits.formatter.lexer.fsm.command.factory.ICommandFactory;
import it.sevenbits.formatter.lexer.fsm.state.StateTransitions;
import it.sevenbits.formatter.lexer.token.IToken;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * {@link ILexer} interface implementation that provides lexical analysis of Java source code.
 *
 * This {@link ILexer} implementation is based on finite-state machine and "Command" design pattern.
 */
public class FSMLexer implements ILexer {
    private final IReader reader;
    private int charBuffer;

    /**
     * Class constructor that initializes private {@link #reader} field with passed
     * {@link IReader} instance.
     *
     * Also this method performs initial filling of private {@link #charBuffer} field.
     *
     * @param reader {@link IReader} instance that provides data input process.
     *
     * @throws LexerException Exception that can be thrown during the method work.
     */
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

    /**
     * Private method that performs naming of formed lexeme and then puts formed name
     * into {@link ITokenBuilder} instance.
     *
     * Formed lexeme is passed in {@link ITokenBuilder} instance.
     *
     * Naming process is based on regular expressions. If there are no patterns
     * that match passed lexeme, token receives name "OTHER".
     *
     * @param tokenBuilder {@link ITokenBuilder} instance which contains token name and lexeme.
     */
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

    /**
     * Method that reports whether single {@link IToken} instance is available for reading.
     *
     * @return Boolean value that indicates the result of method work.
     */
    @Override
    public boolean hasMoreTokens() {
        return charBuffer != -1;
    }

    /**
     * Method that returns a single {@link IToken} instance.
     *
     * @return Single {@link IToken} instance.
     *
     * @throws LexerException Exception that can be thrown during the method work.
     */
    @Override
    public IToken readToken() throws LexerException {
        if (!hasMoreTokens()) {
            throw new LexerException("No tokens available for reading");
        }

        final IStateTransitions stateTransitions = new StateTransitions();
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
