package it.sevenbits.homework.lexer.fsm.tokenbuilder;

import it.sevenbits.homework.lexer.token.IToken;

public interface ITokenBuilder {
    void setName(String name);

    void appendToLexeme(char data);

    String getLexeme();

    IToken getToken();
}
