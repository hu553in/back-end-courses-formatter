package it.sevenbits.homework.formatter.util;

public final class IndentProvider {
    private static final String FOUR_SPACES_INDENT = "    ";

    public static String getFourSpacesIndent(final int nestingLevel) {
        final StringBuilder indentBuilder = new StringBuilder();

        for (int i = 0; i < nestingLevel; i++) {
            indentBuilder.append(FOUR_SPACES_INDENT);
        }

        return indentBuilder.toString();
    }

    private IndentProvider() {
    }
}
