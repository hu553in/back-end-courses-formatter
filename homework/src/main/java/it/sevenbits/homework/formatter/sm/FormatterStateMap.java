package it.sevenbits.homework.formatter.sm;

import it.sevenbits.homework.formatter.sm.util.Pair;
import java.util.HashMap;
import java.util.Map;

class FormatterStateMap {
    private final FormatterState defaultState;
    private final FormatterState errorState;
    private final Map<Pair<FormatterState, String>, FormatterState> stateMap;

    FormatterStateMap() {
        stateMap = new HashMap<>();
        defaultState = new FormatterState("DEFAULT");
        errorState = new FormatterState("ERROR");
    }

    FormatterState getStartState() {
        return defaultState;
    }

    FormatterState getErrorState() {
        return errorState;
    }

    FormatterState getNextState(final FormatterState formatterState, final String tokenType) {
        return stateMap.getOrDefault(new Pair<>(formatterState, tokenType), errorState);
    }
}
