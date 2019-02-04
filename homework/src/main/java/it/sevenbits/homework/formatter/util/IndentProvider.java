package it.sevenbits.homework.formatter.util;

/**
 * Utility class that provides necessary indent (based on four spaces)
 * corresponding to passed code nesting level.
 *
 * Indentation is presented by {@link String} instance.
 */
public final class IndentProvider {
    private static final String FOUR_SPACES_INDENT = "    ";

    /**
     * Static method that performs indent formation and then returns it.
     *
     * @param nestingLevel Integer value that presents code nesting level.
     *
     * @return {@link String} instance that presents necessary indent (based on four spaces).
     */
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
