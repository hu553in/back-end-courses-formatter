package it.sevenbits.homework.formatter.fsm.command.args;

import it.sevenbits.homework.io.writer.IWriter;

/**
 * Interface that describes functionality for command arguments container.
 */
public interface ICommandArgs {
    /**
     * Method that performs containing of integer value that presents code nesting level.
     *
     * @param nestingLevel Integer value that presents code nesting level.
     */
    void setNestingLevel(int nestingLevel);

    /**
     * Method that performs containing of {@link IWriter} instance.
     *
     * @param writer {@link IWriter} instance.
     */
    void setWriter(IWriter writer);

    /**
     * Method that performs containing of {@link String} value that presents current lexeme.
     *
     * @param currentLexeme {@link String} value that presents current lexeme.
     */
    void setCurrentLexeme(String currentLexeme);

    /**
     * Method that performs extraction of an integer value that presents code nesting level.
     *
     * @return Integer value that presents code nesting level.
     */
    int getNestingLevel();

    /**
     * Method that performs extraction of {@link IWriter} instance.
     *
     * @return {@link IWriter} instance.
     */
    IWriter getWriter();

    /**
     * Method that performs extraction of {@link String} value that presents current lexeme.
     *
     * @return {@link String} value that presents current lexeme.
     */
    String getCurrentLexeme();
}
