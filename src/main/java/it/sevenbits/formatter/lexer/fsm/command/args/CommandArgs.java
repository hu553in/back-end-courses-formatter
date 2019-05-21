package it.sevenbits.formatter.lexer.fsm.command.args;

import it.sevenbits.formatter.lexer.fsm.tokenbuilder.ITokenBuilder;

/**
 * {@link ICommandArgs} interface implementation that presents a command arguments container.
 */
public class CommandArgs implements ICommandArgs {
    private ITokenBuilder tokenBuilder;
    private char charBuffer;

    /**
     * Method that performs extraction of {@link ITokenBuilder} instance.
     *
     * @return {@link ITokenBuilder} instance.
     */
    @Override
    public ITokenBuilder getTokenBuilder() {
        return tokenBuilder;
    }

    /**
     * Method that performs containing of {@link ITokenBuilder} instance.
     *
     * @param tokenBuilder {@link ITokenBuilder} instance.
     */
    @Override
    public void setTokenBuilder(final ITokenBuilder tokenBuilder) {
        this.tokenBuilder = tokenBuilder;
    }

    /**
     * Method that performs extraction of a character that is stored in a char buffer.
     *
     * @return Character that is stored in a buffer.
     */
    @Override
    public char getCharBuffer() {
        return charBuffer;
    }

    /**
     * Method that performs containing of a character into char buffer.
     *
     * @param character Character to be contained in a char buffer.
     */
    @Override
    public void setCharBuffer(final char character) {
        charBuffer = character;
    }
}
