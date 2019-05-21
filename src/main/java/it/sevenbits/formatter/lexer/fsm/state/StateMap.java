package it.sevenbits.formatter.lexer.fsm.state;

import it.sevenbits.formatter.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that provides a mapping of {@link Pair} instances (that contain {@link State} instance / character pairs)
 * to {@link State} instances.
 * <p>
 * This mapping is used for providing transitions between FSM states.
 * Transition choice performs using information about current FSM state and other input signals.
 * Other input signals are actually presented by characters.
 */
class StateMap {
    private final Map<Pair<State, Character>, State> stateMap;
    private final Map<State, State> defaultStateMap;
    private final State startState, endState;

    /**
     * Class constructor that initializes private {@link #stateMap} field and fills it with a pairs of
     * {@link Pair} instances (that contain {@link State} instance / character pairs) and {@link State} instances.
     * <p>
     * Also this method initializes private {@link #startState} and {@link #endState} fields
     * with new {@link State} instances.
     * <p>
     * Besides all of the above this constructor initializes private {@link #defaultStateMap} field and fills it
     * with a pairs of {@link State} / {@link State} instances. That mapping is required for specifying of
     * different default values for different {@link State} instances.
     * <p>
     * Default value is {@link State} instance that returns only if there are no mapping
     * for passed {@link State} instance / character pair.
     */
    StateMap() {
        stateMap = new HashMap<>();

        startState = new State("START");
        endState = new State("END");

        final State otherState = new State("OTHER");
        final State singleCharacterState = new State("SINGLE_CHARACTER");

        final State probablyStartOfUnknownTypeCommentState = new State(
                "PROBABLY_START_OF_UNKNOWN_TYPE_COMMENT"
        );

        final State singleLineCommentState = new State("SINGLE_LINE_COMMENT");
        final State multilineCommentState = new State("MULTILINE_COMMENT");
        final State probablyEndOfMultilineCommentState = new State("PROBABLY_END_OF_MULTILINE_COMMENT");
        final State endOfMultilineCommentState = new State("END_OF_MULTILINE_COMMENT");
        final State stringLiteralState = new State("STRING_LITERAL");
        final State endOfStringLiteralState = new State("END_OF_STRING_LITERAL");
        final State characterLiteralState = new State("CHARACTER_LITERAL");
        final State endOfCharacterLiteralState = new State("END_OF_CHARACTER_LITERAL");

        stateMap.put(new Pair<>(startState, '{'), singleCharacterState);
        stateMap.put(new Pair<>(startState, '}'), singleCharacterState);
        stateMap.put(new Pair<>(startState, ';'), singleCharacterState);
        stateMap.put(new Pair<>(startState, '\n'), singleCharacterState);
        stateMap.put(new Pair<>(startState, '\t'), singleCharacterState);
        stateMap.put(new Pair<>(startState, ' '), singleCharacterState);
        stateMap.put(new Pair<>(startState, '\''), characterLiteralState);
        stateMap.put(new Pair<>(startState, '"'), stringLiteralState);
        stateMap.put(new Pair<>(startState, '/'), probablyStartOfUnknownTypeCommentState);

        stateMap.put(new Pair<>(singleCharacterState, '{'), endState);
        stateMap.put(new Pair<>(singleCharacterState, '}'), endState);
        stateMap.put(new Pair<>(singleCharacterState, ';'), endState);
        stateMap.put(new Pair<>(singleCharacterState, '\n'), endState);
        stateMap.put(new Pair<>(singleCharacterState, '\t'), endState);
        stateMap.put(new Pair<>(singleCharacterState, ' '), endState);
        stateMap.put(new Pair<>(singleCharacterState, '\''), endState);
        stateMap.put(new Pair<>(singleCharacterState, '"'), endState);
        stateMap.put(new Pair<>(singleCharacterState, '/'), endState);

        stateMap.put(new Pair<>(singleLineCommentState, '{'), singleLineCommentState);
        stateMap.put(new Pair<>(singleLineCommentState, '}'), singleLineCommentState);
        stateMap.put(new Pair<>(singleLineCommentState, ';'), singleLineCommentState);
        stateMap.put(new Pair<>(singleLineCommentState, '\n'), endState);
        stateMap.put(new Pair<>(singleLineCommentState, '\t'), singleLineCommentState);
        stateMap.put(new Pair<>(singleLineCommentState, ' '), singleLineCommentState);
        stateMap.put(new Pair<>(singleLineCommentState, '\''), singleLineCommentState);
        stateMap.put(new Pair<>(singleLineCommentState, '"'), singleLineCommentState);
        stateMap.put(new Pair<>(singleLineCommentState, '/'), singleLineCommentState);

        stateMap.put(new Pair<>(stringLiteralState, '{'), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, '}'), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, ';'), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, '\n'), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, '\t'), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, ' '), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, '\''), stringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, '"'), endOfStringLiteralState);
        stateMap.put(new Pair<>(stringLiteralState, '/'), stringLiteralState);

        stateMap.put(new Pair<>(characterLiteralState, '{'), characterLiteralState);
        stateMap.put(new Pair<>(characterLiteralState, '}'), characterLiteralState);
        stateMap.put(new Pair<>(characterLiteralState, ';'), characterLiteralState);
        stateMap.put(new Pair<>(characterLiteralState, '\n'), characterLiteralState);
        stateMap.put(new Pair<>(characterLiteralState, '\t'), characterLiteralState);
        stateMap.put(new Pair<>(characterLiteralState, ' '), characterLiteralState);
        stateMap.put(new Pair<>(characterLiteralState, '\''), endOfCharacterLiteralState);
        stateMap.put(new Pair<>(characterLiteralState, '"'), characterLiteralState);
        stateMap.put(new Pair<>(characterLiteralState, '/'), characterLiteralState);

        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, '{'), endState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, '}'), endState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, ';'), endState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, '\n'), endState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, '\t'), endState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, ' '), endState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, '\''), endState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, '"'), endState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, '/'), singleLineCommentState);
        stateMap.put(new Pair<>(probablyStartOfUnknownTypeCommentState, '*'), multilineCommentState);

        stateMap.put(new Pair<>(multilineCommentState, '{'), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, '}'), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, ';'), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, '\n'), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, '\t'), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, ' '), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, '\''), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, '"'), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, '/'), multilineCommentState);
        stateMap.put(new Pair<>(multilineCommentState, '*'), probablyEndOfMultilineCommentState);

        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, '{'), multilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, '}'), multilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, ';'), multilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, '\n'), multilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, '\t'), multilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, ' '), multilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, '\''), multilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, '"'), multilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, '/'), endOfMultilineCommentState);
        stateMap.put(new Pair<>(probablyEndOfMultilineCommentState, '*'), multilineCommentState);

        stateMap.put(new Pair<>(otherState, '{'), endState);
        stateMap.put(new Pair<>(otherState, '}'), endState);
        stateMap.put(new Pair<>(otherState, ';'), endState);
        stateMap.put(new Pair<>(otherState, '\n'), endState);
        stateMap.put(new Pair<>(otherState, '\t'), endState);
        stateMap.put(new Pair<>(otherState, ' '), endState);
        stateMap.put(new Pair<>(otherState, '\''), endState);
        stateMap.put(new Pair<>(otherState, '"'), endState);
        stateMap.put(new Pair<>(otherState, '/'), endState);

        defaultStateMap = new HashMap<>();

        defaultStateMap.put(startState, otherState);
        defaultStateMap.put(otherState, otherState);
        defaultStateMap.put(probablyEndOfMultilineCommentState, multilineCommentState);
        defaultStateMap.put(multilineCommentState, multilineCommentState);
        defaultStateMap.put(endOfMultilineCommentState, endState);
        defaultStateMap.put(probablyStartOfUnknownTypeCommentState, otherState);
        defaultStateMap.put(characterLiteralState, characterLiteralState);
        defaultStateMap.put(endOfCharacterLiteralState, endState);
        defaultStateMap.put(stringLiteralState, stringLiteralState);
        defaultStateMap.put(endOfStringLiteralState, endState);
        defaultStateMap.put(singleLineCommentState, singleLineCommentState);
        defaultStateMap.put(singleCharacterState, endState);
    }

    /**
     * Method that returns {@link State} instance that presents start FSM state.
     *
     * @return {@link State} instance that presents start FSM state.
     */
    State getStartState() {
        return startState;
    }

    /**
     * Method that returns {@link State} instance that presents end FSM state.
     *
     * @return {@link State} instance that presents end FSM state.
     */
    State getEndState() {
        return endState;
    }

    /**
     * Method that returns {@link State} instance that presents target FSM state
     * corresponding to current FSM state and currently being processed character
     * (or default if there are no matches).
     *
     * @param state     {@link State} instance that presents current FSM state.
     * @param character Currently being processed character.
     * @return {@link State} instance that presents target FSM state
     * (or default if there are no matches with passed args).
     */
    State getNextState(final State state, final char character) {
        return stateMap.getOrDefault(new Pair<>(state, character), defaultStateMap.get(state));
    }
}
