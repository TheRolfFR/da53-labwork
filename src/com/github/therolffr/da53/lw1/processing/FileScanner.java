package com.github.therolffr.da53.lw1.processing;

import com.github.therolffr.da53.lw1.token.*;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Matcher;

public class FileScanner {
	public static final int INVALID_INDEX = -1;
	private final StringBuilder stringBuilder;

	private final int[] lineAndColumn = new int[2];

	public enum TokenList {
		PRINT,
		IF,
		THEN,
		REL_OP,
		GOTO,
		NUM,
		ID,
		LET,
		INPUT,
		GOSUB,
		RETURN,
		END,
		MATH_OP,
		REM,
	}

	protected static final Map<TokenList, TokenDefinition> TOKEN_DEFS = new EnumMap<>(TokenList.class);

	static {
		TOKEN_DEFS.put(TokenList.PRINT,  new TokenDefinition("print"));
		TOKEN_DEFS.put(TokenList.IF,     new TokenDefinition("if"));
		TOKEN_DEFS.put(TokenList.THEN,   new TokenDefinition("then"));
		TOKEN_DEFS.put(TokenList.REL_OP, new TokenDefinition("(=|<=?|>=?|<>|><)", RelationalOperator.class));
		TOKEN_DEFS.put(TokenList.GOTO,   new TokenDefinition("goto"));
		TOKEN_DEFS.put(TokenList.NUM,    new TokenDefinition("[0-9]+(.[0-9]+(e[0-9]+)?)?", NumberToken.class));
		TOKEN_DEFS.put(TokenList.ID,     new TokenDefinition("[_a-zA-Z0-9][_a-zA-Z0-9]*", IdentifierToken.class));
		TOKEN_DEFS.put(TokenList.LET,    new TokenDefinition("let"));
		TOKEN_DEFS.put(TokenList.INPUT,  new TokenDefinition("input"));
		TOKEN_DEFS.put(TokenList.GOSUB,  new TokenDefinition("gosub"));
		TOKEN_DEFS.put(TokenList.RETURN, new TokenDefinition("return"));
		TOKEN_DEFS.put(TokenList.END,    new TokenDefinition("end"));
		TOKEN_DEFS.put(TokenList.MATH_OP,new TokenDefinition("[+*-/]", OperatorToken.class));
		TOKEN_DEFS.put(TokenList.REM,    new TokenDefinition("rem.*\n"));

		// verify I didn't miss any token
		for(TokenList tokenName : TokenList.values()) {
			assert(!TOKEN_DEFS.containsKey(tokenName));
		}
	}
	
	public FileScanner(Reader reader) throws IOException {
		stringBuilder = new StringBuilder();
		int c;
		while((c = reader.read()) != -1) {
			stringBuilder.append((char) c);
		}
	}

	public Token getNextToken() {
		String bestMatch = null;
		TokenDefinition bestMatchDefinition = null;
		int startIndex = INVALID_INDEX;
		int endIndex = INVALID_INDEX;

		Matcher matcher;
		for(TokenList tk : TokenList.values()) {
			TokenDefinition td = TOKEN_DEFS.get(tk);

			matcher = td.getPattern().matcher(stringBuilder);

			// if there is one match
			if(matcher.find()) {
				String match = matcher.group();

				// we check non-null match because it won't give us any result
				if(match.length() > 0 && (bestMatch == null || match.length() > bestMatch.length())) {
					startIndex = matcher.start();
					endIndex = matcher.end();
					bestMatch = match;
					bestMatchDefinition = td;
				}
			}
		}

		// return null if no match (end of analysis?)
		if(bestMatchDefinition == null)
			return null;

		Token t = null;
		try {
			// create instance
			lineAndColumnFinder(startIndex, endIndex);
			t = bestMatchDefinition.getTokenClass().getDeclaredConstructor(String.class, String.class).newInstance(bestMatchDefinition.getRegex(), bestMatch);
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * Finds line and column and replaces match with spaces
	 * @param start Start index in file
	 * @param end End of index file in file
	 */
	private void lineAndColumnFinder(int start, int end) {
		assert(start != INVALID_INDEX);
		assert(end != INVALID_INDEX);

		// subs is used in line and columns calculations
		String subs = stringBuilder.substring(0, end);
		int line = subs.split("\n").length; // there is a - 1 + 1 because if no line before the split gives one (one group because no split) so line 1
		int lastLine = subs.lastIndexOf("\n");
		int column;
		if(lastLine == -1) column = start + 1; // no line yet, so first time so column is the offset in the line
		else column = start - lastLine;// else we count from the start of the line to the start

		lineAndColumn[0] = line;
		lineAndColumn[1] = column;

		// we replace all characters from start to end with spaces
		for(int i = start; i < end; ++i) {
			stringBuilder.setCharAt(i, ' ');
		}
	}

	public int[] getLineAndColumn() {
		return lineAndColumn;
	}
}
