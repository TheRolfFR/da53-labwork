package com.github.therolffr.da53.lw1.token;

import java.util.regex.Pattern;

public class TokenDefinition {
	private final String regularExpression;
	private final Pattern pattern;
	private final Class<? extends Token> tokenClass;
	
	public TokenDefinition(String regExp, Class<? extends Token> tokenClass) {
		super();
		this.regularExpression = regExp;
		this.pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		this.tokenClass = tokenClass;
	}

	public TokenDefinition(String regExp) {
		super();
		this.regularExpression = regExp;
		this.pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		this.tokenClass = SimpleToken.class;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public Class<? extends Token> getTokenClass() {
		return tokenClass;
	}

	public String getRegex() {
		return regularExpression;
	}
}
