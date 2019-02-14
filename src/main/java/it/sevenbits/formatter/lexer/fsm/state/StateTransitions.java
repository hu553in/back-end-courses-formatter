package it.sevenbits.formatter.lexer.fsm.state;

/**
 * {@link IStateTransitions} interface implementation that provides transitions between FSM states.
 * Transition choice performs using information about current FSM state and other input signals.
 *
 * Also this class can return start and end FSM states.
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
     * @param character Currently being processed character.
     *
     * @return {@link State} instance that presents target FSM state
     *         (or default if there are no matches with passed args).
     */
    @Override
    public State nextState(final State currentState, final char character) {
        return stateMap.getNextState(currentState, character);
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
     * Method that returns {@link State} instance that presents end FSM state.
     *
     * @return {@link State} instance that presents end FSM state.
     */
    @Override
    public State getEndState() {
        return stateMap.getEndState();
    }
}
