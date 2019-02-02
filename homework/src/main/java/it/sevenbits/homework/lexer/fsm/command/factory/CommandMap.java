package it.sevenbits.homework.lexer.fsm.command.factory;

import it.sevenbits.homework.fsm.command.ICommand;
import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.lexer.fsm.command.AddCharacterToTokenBuilderCommand;
import it.sevenbits.homework.lexer.fsm.command.StayIdleCommand;
import it.sevenbits.homework.lexer.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.util.Pair;
import java.util.HashMap;
import java.util.Map;

class CommandMap {
    private final Map<Pair<State, Character>, ICommand> commandMap;
    private final Map<State, ICommand> defaultCommandMap;
    private final ICommand addCharacterToTokenBuilderCommand;

    CommandMap(final ICommandArgs commandArgs) {
        commandMap = new HashMap<>();

        final State otherState = new State("OTHER");
        final State singleLineCommentState = new State("SINGLE_LINE_COMMENT");
        final State singleCharacterState = new State("SINGLE_CHARACTER");

        addCharacterToTokenBuilderCommand = new AddCharacterToTokenBuilderCommand(commandArgs);
        final ICommand stayIdleCommand = new StayIdleCommand();

        commandMap.put(new Pair<>(singleLineCommentState, '\n'), stayIdleCommand);

        commandMap.put(new Pair<>(otherState, '{'), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, '}'), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, ';'), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, '\n'), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, '\t'), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, ' '), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, '\''), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, '"'), stayIdleCommand);
        commandMap.put(new Pair<>(otherState, '/'), stayIdleCommand);

        defaultCommandMap = new HashMap<>();

        final State endOfMultilineCommentState = new State("END_OF_MULTILINE_COMMENT");
        final State endOfStringLiteralState = new State("END_OF_STRING_LITERAL");
        final State endOfCharacterLiteralState = new State("END_OF_CHARACTER_LITERAL");

        defaultCommandMap.put(endOfMultilineCommentState, stayIdleCommand);
        defaultCommandMap.put(endOfStringLiteralState, stayIdleCommand);
        defaultCommandMap.put(endOfCharacterLiteralState, stayIdleCommand);
        defaultCommandMap.put(singleCharacterState, stayIdleCommand);
    }

    ICommand getCommand(final State currentState, final char character) {
        return commandMap.getOrDefault(
                new Pair<>(currentState, character),
                defaultCommandMap.getOrDefault(
                        currentState,
                        addCharacterToTokenBuilderCommand
                )
        );
    }
}
