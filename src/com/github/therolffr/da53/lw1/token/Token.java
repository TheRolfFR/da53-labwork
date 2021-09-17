package com.github.therolffr.da53.lw1.token;

public abstract class Token {
	
	@SuppressWarnings("unused")
	protected String regex;
	@SuppressWarnings("unused")
	protected String lexeme;
	
	protected Token(String regex, String lexeme) {
		assert(regex != null);
		assert(lexeme != null);
		this.regex = regex;
		this.lexeme = lexeme;
	}

    @SuppressWarnings("unused")
    public String getRegex() {
		return regex;
	}

	public String getLexeme() {
		return lexeme;
	}

	public abstract String toString();
}
