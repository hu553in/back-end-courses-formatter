package it.sevenbits.homework.formatter.sm;

import it.sevenbits.homework.formatter.sm.util.Pair;
import java.util.HashMap;
import java.util.Map;

class FormatterStateMap {
    private final FormatterState defaultFormatterState;
    private final Map<Pair<FormatterState, String>, FormatterState> stateMap;

    FormatterStateMap() {
        stateMap = new HashMap<>();
        defaultFormatterState = new FormatterState("");
    }

    FormatterState getStartState() {
        return defaultFormatterState;
    }

    FormatterState getNextState(final FormatterState formatterState, final String signal) {
        return stateMap.getOrDefault(new Pair<>(formatterState, signal), defaultFormatterState);
    }
}
