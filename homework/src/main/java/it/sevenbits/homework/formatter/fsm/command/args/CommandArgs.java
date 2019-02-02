package it.sevenbits.homework.formatter.fsm.command.args;

import it.sevenbits.homework.io.writer.IWriter;

public class CommandArgs implements ICommandArgs {
    private int nestingLevel;
    private IWriter writer;
    private String currentLexeme;

    @Override
    public void setNestingLevel(final int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    @Override
    public void setWriter(final IWriter writer) {
        this.writer = writer;
    }

    @Override
    public void setCurrentLexeme(final String currentLexeme) {
        this.currentLexeme = currentLexeme;
    }

    @Override
    public int getNestingLevel() {
        return nestingLevel;
    }

    @Override
    public IWriter getWriter() {
        return writer;
    }

    @Override
    public String getCurrentLexeme() {
        return currentLexeme;
    }
}
