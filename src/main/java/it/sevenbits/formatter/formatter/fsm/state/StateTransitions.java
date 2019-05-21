package it.sevenbits.formatter.formatter.fsm.state;

import it.sevenbits.formatter.lexer.token.IToken;

/**
 * {@link IStateTransitions} interface implementation that provides transitions between FSM states.
 * Transition choice performs using information about current FSM state and other input signals.
 * <p>
 * Also this class can return start and error FSM states.
 */
public final class StateTransitions implements IStateTransitions {
    private final StateMap stateMap;

    /**
     * Class constructor that initializes private {@link #stateMap} field with new {@link StateMap} instance.
     * That field contains information about mapping between FSM states and input signals.
     */
    public StateTransitions() {
        stateMap = new StateMap();
    }

    /**
     * Method that returns {@link State} instance that presents target FSM state
     * corresponding to current FSM state and currently being processed lexical token
     * (or default if there are no matches).
     *
     * @param currentState {@link State} instance that presents current FSM state.
     * @param token        {@link IToken} instance that presents currently being processed lexical token.
     * @return {@link State} instance that presents target FSM state
     * (or default if there are no matches with passed args).
     */
    @Override
    public State nextState(final State currentState, final IToken token) {
        return stateMap.getNextState(currentState, token);
    }

    /**
     * Method that returns {@link State} instance that presents start FSM state.
     *
     * @return {@link State} instance that presents start FSM state.
     */
    @Override
    public State getStartState() {
        return stateMap.getStartState();
    }

    /**
     * Method that returns {@link State} instance that presents error FSM state.
     *
     * @return {@link State} instance that presents error FSM state.
     */
    @Override
    public State getErrorState() {
        return stateMap.getErrorState();
    }
}
