package it.sevenbits.homework.lexer.fsm.tokenbuilder;

import it.sevenbits.homework.lexer.token.IToken;
import it.sevenbits.homework.lexer.token.Token;

public class TokenBuilder implements ITokenBuilder {
    private StringBuilder nameBuilder, lexemeBuilder;

    public TokenBuilder() {
        nameBuilder = new StringBuilder();
        lexemeBuilder = new StringBuilder();
    }

    @Override
    public ITokenBuilder setName(final String name) {
        nameBuilder.append(name);
        return this;
    }

    @Override
    public String getName() {
        return nameBuilder.toString();
    }

    @Override
    public ITokenBuilder clearName() {
        nameBuilder.setLength(0);
        return this;
    }

    @Override
    public ITokenBuilder setLexeme(final String data) {
        lexemeBuilder.setLength(0);
        lexemeBuilder.append(data);
        return this;
    }

    @Override
    public ITokenBuilder setLexeme(final char data) {
        lexemeBuilder.setLength(0);
        lexemeBuilder.append(data);
        return this;
    }

    @Override
    public ITokenBuilder setLexeme(final char[] data) {
        lexemeBuilder.setLength(0);
        lexemeBuilder.append(data);
        return this;
    }

    @Override
    public ITokenBuilder appendToLexeme(final String data) {
        lexemeBuilder.append(data);
        return this;
    }

    @Override
    public ITokenBuilder appendToLexeme(final char data) {
        lexemeBuilder.append(data);
        return this;
    }

    @Override
    public ITokenBuilder appendToLexeme(final char[] data) {
        lexemeBuilder.append(data);
        return this;
    }

    @Override
    public String getLexeme() {
        return lexemeBuilder.toString();
    }

    @Override
    public ITokenBuilder clearLexeme() {
        lexemeBuilder.setLength(0);
        return this;
    }

    @Override
    public IToken getToken() {
        return new Token(nameBuilder.toString(), lexemeBuilder.toString());
    }
}
