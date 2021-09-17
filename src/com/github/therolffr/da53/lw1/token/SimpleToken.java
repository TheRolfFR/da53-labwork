package com.github.therolffr.da53.lw1.token;

public class SimpleToken extends Token {
	public SimpleToken(String value, String lexeme) {
		super(value, lexeme);
	}

	@Override
	public String toString() {
		return "<" + lexeme.toUpperCase() + ">";
	}
}
