package it.sevenbits.homework.lexer.fsm.state;

import it.sevenbits.homework.fsm.state.State;

public final class StateTransitions {
    private final StateMap stateMap;

    public StateTransitions() {
        stateMap = new StateMap();
    }

    public State nextState(final State currentState, final char character) {
        return stateMap.getNextState(currentState, character);
    }

    public State getStartState() {
        return stateMap.getStartState();
    }

    public State getEndState() {
        return stateMap.getEndState();
    }
}
