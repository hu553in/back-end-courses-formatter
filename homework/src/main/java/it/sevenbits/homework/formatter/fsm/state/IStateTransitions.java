package it.sevenbits.homework.formatter.fsm.state;

import it.sevenbits.homework.lexer.token.IToken;

/**
 * Interface that describes functionality for class that provides mapping of transitions between FSM states.
 * Transition choice performs using information about current FSM state and other input signals.
 *
 * Also this interface describes functionality for return start and error FSM states.
 */
public interface IStateTransitions {
    /**
     * Method that returns {@link State} instance that presents target FSM state
     * corresponding to current FSM state and currently being processed lexical token
     * (or default if there are no matches).
     *
     * @param currentState {@link State} instance that presents current FSM state.
     * @param token {@link IToken} instance that presents currently being processed lexical token.
     *
     * @return {@link State} instance that presents target FSM state
     *         (or default if there are no matches with passed args).
     */
    State nextState(State currentState, IToken token);

    /**
     * Method that returns {@link State} instance that presents start FSM state.
     *
     * @return {@link State} instance that presents start FSM state.
     */
    State getStartState();

    /**
     * Method that returns {@link State} instance that presents error FSM state.
     *
     * @return {@link State} instance that presents error FSM state.
     */
    State getErrorState();
}
