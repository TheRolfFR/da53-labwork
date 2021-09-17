package com.github.therolffr.da53.lw1.token;

public class RelationalOperator extends Token {
    @SuppressWarnings("unused")
    public RelationalOperator(String regex, String lexeme) {
        super(regex, lexeme);
    }

    @Override
    public String toString() {
        return "<RELOP," + lexeme + ">";
    }
}
