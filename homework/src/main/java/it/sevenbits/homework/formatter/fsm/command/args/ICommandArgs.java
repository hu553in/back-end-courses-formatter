package it.sevenbits.homework.formatter.fsm.command.args;

import it.sevenbits.homework.io.writer.IWriter;

public interface ICommandArgs {
    void setNestingLevel(int nestingLevel);

    void setWriter(IWriter writer);

    void setCurrentLexeme(String currentLexeme);

    int getNestingLevel();

    IWriter getWriter();

    String getCurrentLexeme();
}
