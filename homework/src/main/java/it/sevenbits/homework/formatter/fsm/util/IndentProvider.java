package it.sevenbits.homework.formatter.fsm.util;

public final class IndentProvider {
    private static final String FOUR_SPACES_INDENT = "    ";
    private static final String TWO_SPACES_INDENT = "  ";
    private static final String TABS_INDENT = "\t";

    public static String getFourSpacesIndent(final int nestingLevel) {
        return getIndent(FOUR_SPACES_INDENT, nestingLevel);
    }

    public static String getTwoSpacesIndent(final int nestingLevel) {
        return getIndent(TWO_SPACES_INDENT, nestingLevel);
    }

    public static String getTabsIndent(final int nestingLevel) {
        return getIndent(TABS_INDENT, nestingLevel);
    }

    private static String getIndent(final String singleIndent, final int nestingLevel) {
        final StringBuilder indentBuilder = new StringBuilder();

        for (int i = 0; i < nestingLevel; i++) {
            indentBuilder.append(singleIndent);
        }

        return indentBuilder.toString();
    }

    private IndentProvider() {
    }
}
