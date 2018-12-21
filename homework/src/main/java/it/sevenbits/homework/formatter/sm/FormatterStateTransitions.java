package it.sevenbits.homework.formatter.sm;

import it.sevenbits.homework.lexer.token.IToken;

public final class FormatterStateTransitions {
    private final FormatterStateMap formatterStateMap;

    public FormatterStateTransitions() {
        formatterStateMap = new FormatterStateMap();
    }

    public FormatterState nextState(final FormatterState currentFormatterState, final IToken token) {
        return formatterStateMap.getNextState(currentFormatterState, token.getName());
    }

    public FormatterState getStartState() {
        return formatterStateMap.getStartState();
    }
}
