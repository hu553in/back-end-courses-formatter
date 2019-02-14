package it.sevenbits.formatter.lexer.fsm.command.args;

import it.sevenbits.formatter.lexer.fsm.tokenbuilder.ITokenBuilder;

/**
 * Interface that describes functionality for command arguments container.
 */
public interface ICommandArgs {
    /**
     * Method that performs containing of {@link ITokenBuilder} instance.
     *
     * @param tokenBuilder {@link ITokenBuilder} instance.
     */
    void setTokenBuilder(ITokenBuilder tokenBuilder);

    /**
     * Method that performs extraction of {@link ITokenBuilder} instance.
     *
     * @return {@link ITokenBuilder} instance.
     */
    ITokenBuilder getTokenBuilder();

    /**
     * Method that performs extraction of a character that is stored in a char buffer.
     *
     * @return Character that is stored in a buffer.
     */
    char getCharBuffer();

    /**
     * Method that performs containing of a character into char buffer.
     *
     * @param character Character to be contained in a char buffer.
     */
    void setCharBuffer(char character);
}
