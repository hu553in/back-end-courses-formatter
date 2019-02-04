package it.sevenbits.homework.formatter.fsm.command.args;

import it.sevenbits.homework.io.writer.IWriter;

/**
 * {@link ICommandArgs} interface implementation that presents a command arguments container.
 */
public class CommandArgs implements ICommandArgs {
    private int nestingLevel;
    private IWriter writer;
    private String currentLexeme;

    /**
     * Method that performs containing of integer value that presents code nesting level.
     *
     * @param nestingLevel Integer value that presents code nesting level.
     */
    @Override
    public void setNestingLevel(final int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    /**
     * Method that performs containing of {@link IWriter} instance.
     *
     * @param writer {@link IWriter} instance.
     */
    @Override
    public void setWriter(final IWriter writer) {
        this.writer = writer;
    }

    /**
     * Method that performs containing of {@link String} value that presents current lexeme.
     *
     * @param currentLexeme {@link String} value that presents current lexeme.
     */
    @Override
    public void setCurrentLexeme(final String currentLexeme) {
        this.currentLexeme = currentLexeme;
    }

    /**
     * Method that performs extraction of an integer value that presents code nesting level.
     *
     * @return Integer value that presents code nesting level.
     */
    @Override
    public int getNestingLevel() {
        return nestingLevel;
    }

    /**
     * Method that performs extraction of {@link IWriter} instance.
     *
     * @return {@link IWriter} instance.
     */
    @Override
    public IWriter getWriter() {
        return writer;
    }

    /**
     * Method that performs extraction of {@link String} value that presents current lexeme.
     *
     * @return {@link String} value that presents current lexeme.
     */
    @Override
    public String getCurrentLexeme() {
        return currentLexeme;
    }
}
