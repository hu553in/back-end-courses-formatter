package it.sevenbits.formatter.formatter.fsm.command.factory;

import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.formatter.formatter.fsm.command.ICommand;
import it.sevenbits.formatter.formatter.fsm.command.StayIdleCommand;
import it.sevenbits.formatter.formatter.fsm.command.WriteAfterNewlineAndIndentCommand;
import it.sevenbits.formatter.formatter.fsm.command.WriteAfterNewlineAndIndentWithNestingDecreaseCommand;
import it.sevenbits.formatter.formatter.fsm.command.WriteAfterNewlineAndIndentWithNestingIncreaseCommand;
import it.sevenbits.formatter.formatter.fsm.command.WriteAfterNewlineCommand;
import it.sevenbits.formatter.formatter.fsm.command.WriteAfterWhitespaceCommand;
import it.sevenbits.formatter.formatter.fsm.command.WriteAfterWhitespaceWithNestingIncreaseCommand;
import it.sevenbits.formatter.formatter.fsm.command.WriteCommand;
import it.sevenbits.formatter.formatter.fsm.command.WriteWithNestingIncreaseCommand;
import it.sevenbits.formatter.formatter.fsm.state.State;
import it.sevenbits.formatter.util.Pair;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that provides a mapping of {@link Pair} instances (that contain {@link State} instance / {@link String}
 * instance pairs) to {@link ICommand} instances.
 */
class CommandMap {
    private final Map<Pair<State, String>, ICommand> commandMap;
    private final ICommand stayIdle;

    /**
     * Class constructor that initializes private {@link #commandMap} field and fills it with a pairs of
     * {@link Pair} instances (that contain {@link State} instance / {@link String} instance pairs)
     * and {@link ICommand} instances.
     *
     * Method creates instances of these commands and then initializing some of them
     * with the passed {@link ICommandArgs} instance.
     *
     * @param commandArgs {@link ICommandArgs} instance that presents a command arguments container.
     */
    CommandMap(final ICommandArgs commandArgs) {
        commandMap = new HashMap<>();
        stayIdle = new StayIdleCommand();

        final State startState = new State("START");
        final State otherState = new State("OTHER");
        final State newLineState = new State("NEWLINE");
        final State whitespaceState = new State("WHITESPACE");
        final State openingCurlyBraceState = new State("OPENING_CURLY_BRACE");
        final State closingCurlyBraceState = new State("CLOSING_CURLY_BRACE");
        final State semicolonState = new State("SEMICOLON");
        final State singleLineCommentState = new State("SINGLE_LINE_COMMENT");
        final State multilineCommentState = new State("MULTILINE_COMMENT");
        final State characterLiteralState = new State("CHARACTER_LITERAL");
        final State stringLiteralState = new State("STRING_LITERAL");

        final ICommand writeAfterNewline = new WriteAfterNewlineCommand(commandArgs);
        final ICommand writeAfterNewlineAndIndent = new WriteAfterNewlineAndIndentCommand(commandArgs);
        final ICommand write = new WriteCommand(commandArgs);
        final ICommand writeWithNestingIncrease = new WriteWithNestingIncreaseCommand(commandArgs);
        final ICommand writeAfterWhitespace = new WriteAfterWhitespaceCommand(commandArgs);

        final ICommand writeAfterNewlineAndIndentWithNestingDecrease = (
                new WriteAfterNewlineAndIndentWithNestingDecreaseCommand(commandArgs)
        );

        final ICommand writeAfterNewlineAndIndentWithNestingIncrease = (
                new WriteAfterNewlineAndIndentWithNestingIncreaseCommand(commandArgs)
        );

        final ICommand writeAfterWhitespaceWithNestingIncrease = (
                new WriteAfterWhitespaceWithNestingIncreaseCommand(commandArgs)
        );

        commandMap.put(new Pair<>(startState, "OPENING_CURLY_BRACE"), writeWithNestingIncrease);
        commandMap.put(new Pair<>(startState, "CLOSING_CURLY_BRACE"), write);
        commandMap.put(new Pair<>(startState, "SEMICOLON"), write);
        commandMap.put(new Pair<>(startState, "OTHER"), write);
        commandMap.put(new Pair<>(startState, "SINGLE_LINE_COMMENT"), write);
        commandMap.put(new Pair<>(startState, "MULTILINE_COMMENT"), write);
        commandMap.put(new Pair<>(startState, "CHARACTER_LITERAL"), write);
        commandMap.put(new Pair<>(startState, "STRING_LITERAL"), write);

        commandMap.put(new Pair<>(otherState, "OPENING_CURLY_BRACE"), writeAfterWhitespaceWithNestingIncrease);
        commandMap.put(new Pair<>(otherState, "CLOSING_CURLY_BRACE"), writeAfterNewlineAndIndentWithNestingDecrease);
        commandMap.put(new Pair<>(otherState, "SEMICOLON"), write);
        commandMap.put(new Pair<>(otherState, "SINGLE_LINE_COMMENT"), write);
        commandMap.put(new Pair<>(otherState, "MULTILINE_COMMENT"), write);
        commandMap.put(new Pair<>(otherState, "CHARACTER_LITERAL"), write);
        commandMap.put(new Pair<>(otherState, "STRING_LITERAL"), write);

        commandMap.put(new Pair<>(newLineState, "OPENING_CURLY_BRACE"), writeAfterNewlineAndIndentWithNestingIncrease);
        commandMap.put(new Pair<>(newLineState, "CLOSING_CURLY_BRACE"), writeAfterNewlineAndIndentWithNestingDecrease);
        commandMap.put(new Pair<>(newLineState, "SEMICOLON"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(newLineState, "OTHER"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(newLineState, "SINGLE_LINE_COMMENT"), writeAfterNewline);
        commandMap.put(new Pair<>(newLineState, "MULTILINE_COMMENT"), writeAfterNewline);
        commandMap.put(new Pair<>(newLineState, "CHARACTER_LITERAL"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(newLineState, "STRING_LITERAL"), writeAfterNewlineAndIndent);

        commandMap.put(new Pair<>(whitespaceState, "OPENING_CURLY_BRACE"), writeAfterWhitespace);

        commandMap.put(
                new Pair<>(whitespaceState, "CLOSING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingDecrease
        );

        commandMap.put(new Pair<>(whitespaceState, "SEMICOLON"), write);
        commandMap.put(new Pair<>(whitespaceState, "OTHER"), writeAfterWhitespace);
        commandMap.put(new Pair<>(whitespaceState, "SINGLE_LINE_COMMENT"), writeAfterWhitespace);
        commandMap.put(new Pair<>(whitespaceState, "MULTILINE_COMMENT"), writeAfterWhitespace);
        commandMap.put(new Pair<>(whitespaceState, "CHARACTER_LITERAL"), writeAfterWhitespace);
        commandMap.put(new Pair<>(whitespaceState, "STRING_LITERAL"), writeAfterWhitespace);

        commandMap.put(
                new Pair<>(openingCurlyBraceState, "OPENING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingIncrease
        );

        commandMap.put(
                new Pair<>(openingCurlyBraceState, "CLOSING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingDecrease
        );

        commandMap.put(new Pair<>(openingCurlyBraceState, "SEMICOLON"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(openingCurlyBraceState, "OTHER"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(openingCurlyBraceState, "SINGLE_LINE_COMMENT"), write);
        commandMap.put(new Pair<>(openingCurlyBraceState, "MULTILINE_COMMENT"), write);
        commandMap.put(new Pair<>(openingCurlyBraceState, "CHARACTER_LITERAL"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(openingCurlyBraceState, "STRING_LITERAL"), writeAfterNewlineAndIndent);

        commandMap.put(
                new Pair<>(closingCurlyBraceState, "OPENING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingIncrease
        );

        commandMap.put(new Pair<>(
                closingCurlyBraceState, "CLOSING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingDecrease
        );

        commandMap.put(new Pair<>(closingCurlyBraceState, "SEMICOLON"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(closingCurlyBraceState, "OTHER"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(closingCurlyBraceState, "SINGLE_LINE_COMMENT"), write);
        commandMap.put(new Pair<>(closingCurlyBraceState, "MULTILINE_COMMENT"), write);
        commandMap.put(new Pair<>(closingCurlyBraceState, "CHARACTER_LITERAL"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(closingCurlyBraceState, "STRING_LITERAL"), writeAfterNewlineAndIndent);

        commandMap.put(
                new Pair<>(semicolonState, "OPENING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingIncrease
        );

        commandMap.put(
                new Pair<>(semicolonState, "CLOSING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingDecrease
        );

        commandMap.put(new Pair<>(semicolonState, "SEMICOLON"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(semicolonState, "OTHER"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(semicolonState, "SINGLE_LINE_COMMENT"), write);
        commandMap.put(new Pair<>(semicolonState, "MULTILINE_COMMENT"), write);
        commandMap.put(new Pair<>(semicolonState, "CHARACTER_LITERAL"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(semicolonState, "STRING_LITERAL"), writeAfterNewlineAndIndent);

        commandMap.put(
                new Pair<>(singleLineCommentState, "OPENING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingIncrease
        );

        commandMap.put(
                new Pair<>(singleLineCommentState, "CLOSING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingDecrease
        );

        commandMap.put(new Pair<>(singleLineCommentState, "SEMICOLON"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(singleLineCommentState, "OTHER"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(singleLineCommentState, "SINGLE_LINE_COMMENT"), writeAfterNewline);
        commandMap.put(new Pair<>(singleLineCommentState, "MULTILINE_COMMENT"), writeAfterNewline);
        commandMap.put(new Pair<>(singleLineCommentState, "CHARACTER_LITERAL"), writeAfterNewlineAndIndent);
        commandMap.put(new Pair<>(singleLineCommentState, "STRING_LITERAL"), writeAfterNewlineAndIndent);

        commandMap.put(
                new Pair<>(multilineCommentState, "OPENING_CURLY_BRACE"),
                writeAfterWhitespaceWithNestingIncrease
        );

        commandMap.put(
                new Pair<>(multilineCommentState, "CLOSING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingDecrease
        );

        commandMap.put(new Pair<>(multilineCommentState, "SEMICOLON"), write);
        commandMap.put(new Pair<>(multilineCommentState, "OTHER"), write);
        commandMap.put(new Pair<>(multilineCommentState, "SINGLE_LINE_COMMENT"), write);
        commandMap.put(new Pair<>(multilineCommentState, "MULTILINE_COMMENT"), write);
        commandMap.put(new Pair<>(multilineCommentState, "CHARACTER_LITERAL"), write);
        commandMap.put(new Pair<>(multilineCommentState, "STRING_LITERAL"), write);

        commandMap.put(
                new Pair<>(characterLiteralState, "OPENING_CURLY_BRACE"),
                writeAfterWhitespaceWithNestingIncrease
        );

        commandMap.put(
                new Pair<>(characterLiteralState, "CLOSING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingDecrease
        );

        commandMap.put(new Pair<>(characterLiteralState, "SEMICOLON"), write);
        commandMap.put(new Pair<>(characterLiteralState, "OTHER"), write);
        commandMap.put(new Pair<>(characterLiteralState, "SINGLE_LINE_COMMENT"), write);
        commandMap.put(new Pair<>(characterLiteralState, "MULTILINE_COMMENT"), write);
        commandMap.put(new Pair<>(characterLiteralState, "CHARACTER_LITERAL"), writeAfterWhitespace);
        commandMap.put(new Pair<>(characterLiteralState, "STRING_LITERAL"), writeAfterWhitespace);

        commandMap.put(
                new Pair<>(stringLiteralState, "OPENING_CURLY_BRACE"),
                writeAfterWhitespaceWithNestingIncrease
        );

        commandMap.put(
                new Pair<>(stringLiteralState, "CLOSING_CURLY_BRACE"),
                writeAfterNewlineAndIndentWithNestingDecrease
        );

        commandMap.put(new Pair<>(stringLiteralState, "SEMICOLON"), write);
        commandMap.put(new Pair<>(stringLiteralState, "OTHER"), write);
        commandMap.put(new Pair<>(stringLiteralState, "SINGLE_LINE_COMMENT"), write);
        commandMap.put(new Pair<>(stringLiteralState, "MULTILINE_COMMENT"), write);
        commandMap.put(new Pair<>(stringLiteralState, "CHARACTER_LITERAL"), writeAfterWhitespace);
        commandMap.put(new Pair<>(stringLiteralState, "STRING_LITERAL"), writeAfterWhitespace);
    }

    /**
     * Method that performs an issuing of {@link ICommand} instance that matches passed args
     * (or default if there are no matches).
     *
     * @param currentState {@link State} instance that presents a current FSM state.
     * @param token {@link IToken} instance that presents currently being processed lexical token.
     *
     * @return {@link ICommand} instance that matches passed args (or default if there are no matches).
     */
    ICommand getCommand(final State currentState, final IToken token) {
        return commandMap.getOrDefault(new Pair<>(currentState, token.getName()), stayIdle);
    }
}
