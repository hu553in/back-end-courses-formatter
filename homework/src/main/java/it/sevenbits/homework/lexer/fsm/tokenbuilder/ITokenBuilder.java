package it.sevenbits.homework.lexer.fsm.tokenbuilder;

import it.sevenbits.homework.lexer.token.IToken;

public interface ITokenBuilder {
    ITokenBuilder setName(String name);

    String getName();

    ITokenBuilder clearName();

    ITokenBuilder setLexeme(String data);

    ITokenBuilder setLexeme(char data);

    ITokenBuilder setLexeme(char[] data);

    ITokenBuilder appendToLexeme(String data);

    ITokenBuilder appendToLexeme(char data);

    ITokenBuilder appendToLexeme(char[] data);

    String getLexeme();

    ITokenBuilder clearLexeme();

    IToken getToken();
}
