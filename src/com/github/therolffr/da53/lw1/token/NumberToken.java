package com.github.therolffr.da53.lw1.token;

public class NumberToken extends Token {

	public NumberToken(String value, String lexeme) {
		super(value, lexeme);
	}

	@SuppressWarnings("unused")
	public double toDouble() throws NumberFormatException {
		return Double.parseDouble(lexeme);
	}


	@SuppressWarnings("unused")
	public int toInt() throws NumberFormatException {
		return Integer.parseInt(lexeme);
	}

	@Override
	public String toString() {
		return "<NUM," + lexeme + ">";
	}
}
