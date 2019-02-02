package it.sevenbits.homework.formatter.fsm.command.factory;

import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.formatter.fsm.command.IncreaseNestingAfterNewlineOrInStartCommand;
import it.sevenbits.homework.formatter.fsm.command.IncreaseNestingAfterOtherCommand;
import it.sevenbits.homework.formatter.fsm.command.IncreaseNestingAfterWhitespaceCommand;
import it.sevenbits.homework.formatter.fsm.command.ReduceNestingAfterNewlineOrInStartCommand;
import it.sevenbits.homework.formatter.fsm.command.ReduceNestingAfterWhitespaceOrOtherCommand;
import it.sevenbits.homework.formatter.fsm.command.StayIdleCommand;
import it.sevenbits.homework.formatter.fsm.command.WriteLexemeAfterIndentCommand;
import it.sevenbits.homework.formatter.fsm.command.WriteLexemeBeforeNewlineCommand;
import it.sevenbits.homework.formatter.fsm.command.WriteLexemeCommand;
import it.sevenbits.homework.formatter.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.lexer.token.IToken;
import it.sevenbits.homework.util.Pair;
import java.util.HashMap;
import java.util.Map;

class CommandMap {
    private final Map<Pair<State, String>, ICommand> commandMap;

    CommandMap(final ICommandArgs commandArgs) {
        commandMap = new HashMap<>();

        final State startState = new State("START");
        final State otherState = new State("OTHER");
        final State newLineState = new State("NEWLINE");
        final State whitespaceState = new State("WHITESPACE");

        final ICommand increaseNestingAfterNewlineOrInStartCommand = new IncreaseNestingAfterNewlineOrInStartCommand(
                commandArgs
        );

        final ICommand increaseNestingAfterOtherCommand = new IncreaseNestingAfterOtherCommand(commandArgs);
        final ICommand increaseNestingAfterWhitespaceCommand = new IncreaseNestingAfterWhitespaceCommand(commandArgs);

        final ICommand reduceNestingAfterNewlineOrInStartCommand = new ReduceNestingAfterNewlineOrInStartCommand(
                commandArgs
        );

        final ICommand reduceNestingAfterWhitespaceOrOtherCommand = new ReduceNestingAfterWhitespaceOrOtherCommand(
                commandArgs
        );

        final ICommand stayIdleCommand = new StayIdleCommand();
        final ICommand writeLexemeAfterIndentCommand = new WriteLexemeAfterIndentCommand(commandArgs);
        final ICommand writeLexemeBeforeNewlineCommand = new WriteLexemeBeforeNewlineCommand(commandArgs);
        final ICommand writeLexemeCommand = new WriteLexemeCommand(commandArgs);

        commandMap.put(new Pair<>(startState, "OPENING_CURLY_BRACE"), increaseNestingAfterNewlineOrInStartCommand);
        commandMap.put(new Pair<>(startState, "CLOSING_CURLY_BRACE"), reduceNestingAfterNewlineOrInStartCommand);
        commandMap.put(new Pair<>(startState, "SEMICOLON"), writeLexemeBeforeNewlineCommand);
        commandMap.put(new Pair<>(startState, "NEWLINE"), stayIdleCommand);
        commandMap.put(new Pair<>(startState, "TAB"), stayIdleCommand);
        commandMap.put(new Pair<>(startState, "WHITESPACE"), stayIdleCommand);
        commandMap.put(new Pair<>(startState, "OTHER"), writeLexemeAfterIndentCommand);

        commandMap.put(new Pair<>(otherState, "OPENING_CURLY_BRACE"), increaseNestingAfterOtherCommand);
        commandMap.put(new Pair<>(otherState, "CLOSING_CURLY_BRACE"), reduceNestingAfterWhitespaceOrOtherCommand);
        commandMap.put(new Pair<>(otherState, "SEMICOLON"), writeLexemeBeforeNewlineCommand);
        commandMap.put(new Pair<>(otherState, "NEWLINE"), writeLexemeCommand);
        commandMap.put(new Pair<>(otherState, "TAB"), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, "WHITESPACE"), writeLexemeCommand);
        commandMap.put(new Pair<>(otherState, "OTHER"), writeLexemeCommand);

        commandMap.put(new Pair<>(newLineState, "OPENING_CURLY_BRACE"), increaseNestingAfterNewlineOrInStartCommand);
        commandMap.put(new Pair<>(newLineState, "CLOSING_CURLY_BRACE"), reduceNestingAfterNewlineOrInStartCommand);
        commandMap.put(new Pair<>(newLineState, "SEMICOLON"), writeLexemeBeforeNewlineCommand);
        commandMap.put(new Pair<>(newLineState, "NEWLINE"), stayIdleCommand);
        commandMap.put(new Pair<>(newLineState, "TAB"), stayIdleCommand);
        commandMap.put(new Pair<>(newLineState, "WHITESPACE"), stayIdleCommand);
        commandMap.put(new Pair<>(newLineState, "OTHER"), writeLexemeAfterIndentCommand);

        commandMap.put(new Pair<>(whitespaceState, "OPENING_CURLY_BRACE"), increaseNestingAfterWhitespaceCommand);
        commandMap.put(new Pair<>(whitespaceState, "CLOSING_CURLY_BRACE"), reduceNestingAfterWhitespaceOrOtherCommand);
        commandMap.put(new Pair<>(whitespaceState, "SEMICOLON"), writeLexemeBeforeNewlineCommand);
        commandMap.put(new Pair<>(whitespaceState, "NEWLINE"), writeLexemeCommand);
        commandMap.put(new Pair<>(whitespaceState, "TAB"), stayIdleCommand);
        commandMap.put(new Pair<>(whitespaceState, "WHITESPACE"), stayIdleCommand);
        commandMap.put(new Pair<>(whitespaceState, "OTHER"), writeLexemeCommand);
    }

    ICommand getCommand(final State currentState, final IToken token) {
        return commandMap.get(new Pair<>(currentState, token.getName()));
    }
}
