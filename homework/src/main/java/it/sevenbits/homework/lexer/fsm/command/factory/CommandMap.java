package it.sevenbits.homework.lexer.fsm.command.factory;

import it.sevenbits.homework.lexer.fsm.state.State;
import it.sevenbits.homework.lexer.fsm.command.AddCharacterToTokenBuilderCommand;
import it.sevenbits.homework.lexer.fsm.command.args.ICommandArgs;
import it.sevenbits.homework.lexer.fsm.command.ICommand;
import it.sevenbits.homework.lexer.fsm.command.StayIdleCommand;
import it.sevenbits.homework.util.Pair;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that provides a mapping of {@link Pair} instances (that contain {@link State} instance / character pairs)
 * to {@link ICommand} instances.
 */
class CommandMap {
    private final Map<Pair<State, Character>, ICommand> commandMap;
    private final Map<State, ICommand> defaultCommandMap;
    private final ICommand addCharacterToTokenBuilder;

    /**
     * Class constructor that initializes private {@link #commandMap} field and fills it with a pairs of
     * {@link Pair} instances (that contain {@link State} instance / character pairs) and {@link ICommand} instances.
     *
     * Method creates instances of these commands and then initializing some of them
     * with the passed {@link ICommandArgs} instance.
     *
     * Also method initializes private {@link #defaultCommandMap} field and fills it with a pairs of
     * {@link State} instances and {@link ICommand} instances. That mapping is required for
     * specifying of different default values for different {@link State} instances.
     *
     * Default value is {@link ICommand} instance that returns only if there are no mapping
     * for passed {@link State} instance / character pair.
     *
     * @param commandArgs {@link ICommandArgs} instance that presents a command arguments container.
     */
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

    /**
     * Method that performs an issuing of {@link ICommand} instance that matches passed args
     * (or default if there are no matches).
     *
     * @param currentState {@link State} instance that presents a current FSM state.
     * @param character Currently being processed character.
     *
     * @return {@link ICommand} instance that matches passed args (or default if there are no matches).
     */
    ICommand getCommand(final State currentState, final char character) {
        return commandMap.getOrDefault(
                new Pair<>(currentState, character),
                defaultCommandMap.getOrDefault(currentState, addCharacterToTokenBuilder)
        );
    }
}
