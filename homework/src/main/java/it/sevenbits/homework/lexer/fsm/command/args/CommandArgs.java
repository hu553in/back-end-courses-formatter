package it.sevenbits.homework.lexer.fsm.command.args;

import it.sevenbits.homework.lexer.fsm.tokenbuilder.ITokenBuilder;

public class CommandArgs implements ICommandArgs {
    private ITokenBuilder tokenBuilder;
    private char charBuffer;

    @Override
    public void setTokenBuilder(final ITokenBuilder tokenBuilder) {
        this.tokenBuilder = tokenBuilder;
    }

    @Override
    public ITokenBuilder getTokenBuilder() {
        return tokenBuilder;
    }

    @Override
    public char getCharBuffer() {
        return charBuffer;
    }

    @Override
    public void setCharBuffer(final char character) {
        charBuffer = character;
    }
}
