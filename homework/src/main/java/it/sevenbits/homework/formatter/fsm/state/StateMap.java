package it.sevenbits.homework.formatter.fsm.state;

import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.util.Pair;
import it.sevenbits.homework.lexer.token.IToken;

import java.util.HashMap;
import java.util.Map;

class StateMap {
    private final State startState, errorState;
    private final Map<Pair<State, String>, State> stateMap;

    StateMap() {
        stateMap = new HashMap<>();

        startState = new State("START");
        errorState = new State("ERROR");

        final State otherState = new State("OTHER");
        final State newLineState = new State("NEWLINE");
        final State whitespaceState = new State("WHITESPACE");

        stateMap.put(new Pair<>(startState, "OPENING_CURLY_BRACE"), newLineState);
        stateMap.put(new Pair<>(startState, "CLOSING_CURLY_BRACE"), newLineState);
        stateMap.put(new Pair<>(startState, "SEMICOLON"), newLineState);
        stateMap.put(new Pair<>(startState, "NEWLINE"), startState);
        stateMap.put(new Pair<>(startState, "TAB"), startState);
        stateMap.put(new Pair<>(startState, "WHITESPACE"), startState);
        stateMap.put(new Pair<>(startState, "OTHER"), otherState);

        stateMap.put(new Pair<>(otherState, "OPENING_CURLY_BRACE"), newLineState);
        stateMap.put(new Pair<>(otherState, "CLOSING_CURLY_BRACE"), newLineState);
        stateMap.put(new Pair<>(otherState, "SEMICOLON"), newLineState);
        stateMap.put(new Pair<>(otherState, "NEWLINE"), newLineState);
        stateMap.put(new Pair<>(otherState, "TAB"), otherState);
        stateMap.put(new Pair<>(otherState, "WHITESPACE"), whitespaceState);
        stateMap.put(new Pair<>(otherState, "OTHER"), otherState);

        stateMap.put(new Pair<>(newLineState, "OPENING_CURLY_BRACE"), newLineState);
        stateMap.put(new Pair<>(newLineState, "CLOSING_CURLY_BRACE"), newLineState);
        stateMap.put(new Pair<>(newLineState, "SEMICOLON"), newLineState);
        stateMap.put(new Pair<>(newLineState, "NEWLINE"), newLineState);
        stateMap.put(new Pair<>(newLineState, "TAB"), newLineState);
        stateMap.put(new Pair<>(newLineState, "WHITESPACE"), newLineState);
        stateMap.put(new Pair<>(newLineState, "OTHER"), otherState);

        stateMap.put(new Pair<>(whitespaceState, "OPENING_CURLY_BRACE"), newLineState);
        stateMap.put(new Pair<>(whitespaceState, "CLOSING_CURLY_BRACE"), newLineState);
        stateMap.put(new Pair<>(whitespaceState, "SEMICOLON"), newLineState);
        stateMap.put(new Pair<>(whitespaceState, "NEWLINE"), newLineState);
        stateMap.put(new Pair<>(whitespaceState, "TAB"), whitespaceState);
        stateMap.put(new Pair<>(whitespaceState, "WHITESPACE"), whitespaceState);
        stateMap.put(new Pair<>(whitespaceState, "OTHER"), otherState);
    }

    State getStartState() {
        return startState;
    }

    State getErrorState() {
        return errorState;
    }

    State getNextState(final State state, final IToken token) {
        return stateMap.getOrDefault(new Pair<>(state, token.getName()), errorState);
    }
}
