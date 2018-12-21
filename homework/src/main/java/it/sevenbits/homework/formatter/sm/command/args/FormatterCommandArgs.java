package it.sevenbits.homework.formatter.sm.command.args;

import it.sevenbits.homework.io.writer.IWriter;

public class FormatterCommandArgs implements IFormatterCommandArgs {
    private int nestingLevel;
    private IWriter writer;
    private String lastWrittenLexeme;
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
    public void setLastWrittenLexeme(final String lastWrittenLexeme) {
        this.lastWrittenLexeme = lastWrittenLexeme;
    }

    @Override
    public void setCurrentLexeme(String currentLexeme) {
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
    public String getLastWrittenLexeme() {
        return lastWrittenLexeme;
    }

    @Override
    public String getCurrentLexeme() {
        return currentLexeme;
    }
}
