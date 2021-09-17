package com.github.therolffr.da53.lw1.token;

public class OperatorToken extends Token {

	public OperatorToken(String value, String lexeme) {
		super(value, lexeme);
	}

	@Override
	public String toString() {
		return "<OP," + lexeme + ">";
	}
}
