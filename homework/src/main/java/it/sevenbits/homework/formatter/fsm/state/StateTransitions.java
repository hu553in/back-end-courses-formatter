package it.sevenbits.homework.formatter.fsm.state;

import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.lexer.token.IToken;

public final class StateTransitions {
    private final StateMap stateMap;

    public StateTransitions() {
        stateMap = new StateMap();
    }

    public State nextState(final State currentState, final IToken token) {
        return stateMap.getNextState(currentState, token);
    }

    public State getStartState() {
        return stateMap.getStartState();
    }

    public State getErrorState() {
        return stateMap.getErrorState();
    }
}
