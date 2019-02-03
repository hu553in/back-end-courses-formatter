package it.sevenbits.homework.lexer.fsm.tokenbuilder;

import it.sevenbits.homework.lexer.token.IToken;
import it.sevenbits.homework.lexer.token.Token;

public class TokenBuilder implements ITokenBuilder {
    private final StringBuilder nameBuilder, lexemeBuilder;

    public TokenBuilder() {
        nameBuilder = new StringBuilder();
        lexemeBuilder = new StringBuilder();
    }

    @Override
    public void setName(final String name) {
        nameBuilder.setLength(0);
        nameBuilder.append(name);
    }

    @Override
    public void appendToLexeme(final char data) {
        lexemeBuilder.append(data);
    }

    @Override
    public String getLexeme() {
        return lexemeBuilder.toString();
    }

    @Override
    public IToken getToken() {
        return new Token(nameBuilder.toString(), lexemeBuilder.toString());
    }
}
