package it.sevenbits.homework.formatter.sm.command.args;

import it.sevenbits.homework.io.writer.IWriter;

public interface IFormatterCommandArgs {
    void setNestingLevel(int nestingLevel);

    void setWriter(IWriter writer);

    void setLastWrittenLexeme(String lastWrittenLexeme);

    void setCurrentLexeme(String currentLexeme);

    int getNestingLevel();

    IWriter getWriter();

    String getLastWrittenLexeme();

    String getCurrentLexeme();
}
