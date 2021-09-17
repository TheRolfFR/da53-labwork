package com.github.therolffr.da53.lw1.token;

public class IdentifierToken extends Token {
    public IdentifierToken(String value, String lexeme) {
        super(value, lexeme);
    }

    @Override
    public String toString() {
        return "<ID," + lexeme + ">";
    }
}
