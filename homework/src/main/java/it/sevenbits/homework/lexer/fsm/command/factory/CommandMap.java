package it.sevenbits.homework.lexer.fsm.command.factory;

import it.sevenbits.homework.lexer.fsm.command.ICommand;
import it.sevenbits.homework.lexer.fsm.command.StayIdleCommand;
import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.lexer.fsm.command.AddCharacterToTokenBuilderCommand;
import it.sevenbits.homework.lexer.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.util.Pair;
import java.util.HashMap;
import java.util.Map;

class CommandMap {
    private final Map<Pair<State, Character>, ICommand> commandMap;
    private final Map<State, ICommand> defaultCommandMap;
    private final ICommand addCharacterToTokenBuilder;

    CommandMap(final ICommandArgs commandArgs) {
        commandMap = new HashMap<>();
        defaultCommandMap = new HashMap<>();
        addCharacterToTokenBuilder = new AddCharacterToTokenBuilderCommand(commandArgs);

        final State otherState = new State("OTHER");
        final State singleLineCommentState = new State("SINGLE_LINE_COMMENT");
        final State singleCharacterState = new State("SINGLE_CHARACTER");

        final ICommand stayIdle = new StayIdleCommand();

        commandMap.put(new Pair<>(singleLineCommentState, '\n'), stayIdle);

        commandMap.put(new Pair<>(otherState, '{'), stayIdle);
        commandMap.put(new Pair<>(otherState, '}'), stayIdle);
        commandMap.put(new Pair<>(otherState, ';'), stayIdle);
        commandMap.put(new Pair<>(otherState, '\n'), stayIdle);
        commandMap.put(new Pair<>(otherState, '\t'), stayIdle);
        commandMap.put(new Pair<>(otherState, ' '), stayIdle);
        commandMap.put(new Pair<>(otherState, '\''), stayIdle);
        commandMap.put(new Pair<>(otherState, '"'), stayIdle);
        commandMap.put(new Pair<>(otherState, '/'), stayIdle);

        final State endOfMultilineCommentState = new State("END_OF_MULTILINE_COMMENT");
        final State endOfStringLiteralState = new State("END_OF_STRING_LITERAL");
        final State endOfCharacterLiteralState = new State("END_OF_CHARACTER_LITERAL");

        defaultCommandMap.put(endOfMultilineCommentState, stayIdle);
        defaultCommandMap.put(endOfStringLiteralState, stayIdle);
        defaultCommandMap.put(endOfCharacterLiteralState, stayIdle);
        defaultCommandMap.put(singleCharacterState, stayIdle);
    }

    ICommand getCommand(final State currentState, final char character) {
        return commandMap.getOrDefault(
                new Pair<>(currentState, character),
                defaultCommandMap.getOrDefault(currentState, addCharacterToTokenBuilder)
        );
    }
}
