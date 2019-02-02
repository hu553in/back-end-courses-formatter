package it.sevenbits.homework.lexer.fsm.command.args;

import it.sevenbits.homework.lexer.fsm.tokenbuilder.ITokenBuilder;

public interface ICommandArgs {
    void setTokenBuilder(ITokenBuilder tokenBuilder);

    ITokenBuilder getTokenBuilder();

    char getCharBuffer();

    void setCharBuffer(char character);
}
