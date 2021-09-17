package com.github.therolffr.da53.lw1.symbol;

import com.github.therolffr.da53.lw1.token.SimpleToken;
import com.github.therolffr.da53.lw1.token.Token;
import com.github.therolffr.da53.lw1.token.TokenOccurence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class SymbolTable extends HashMap<String, SymbolItem> {

	private static final long serialVersionUID = 1395405105109648987L;

	private final ArrayList<TokenOccurence> occurences = new ArrayList<>();

	public void include(Token token, int[] lineAndCol) {
		assert(lineAndCol.length > 1); // line + col = 2
		String lexeme = token.getLexeme();

		SymbolItem item;
		if(containsKey(lexeme)) {
			item = get(lexeme);
			item.updateLineAndColumn(lineAndCol[0], lineAndCol[1]);
		} else {
			item = new SymbolItem(token, lineAndCol[0], lineAndCol[1], token instanceof SimpleToken);
			put(lexeme, item);
		}

		occurences.add(new TokenOccurence(token, lineAndCol));
	}

	public String getOccurencesResult() {
		Collections.sort(occurences);
		StringBuilder builder = new StringBuilder();

		int lastLine = 1;
		for(TokenOccurence oc : occurences) {
			if(oc.getLine() > lastLine) builder.append('\n');
			if(oc.getLine() == lastLine && oc.getCol() > 1) builder.append(' ');

			builder.append(oc.getToken());

			lastLine = oc.getLine();
		}

		return builder.toString();
	}

	public String getItemsToString() {
		return keySet().stream().map(key -> "'" + key + "' -> " + get(key)).reduce((acc, curr) -> acc + '\n' + curr).orElse("");
	}

	@Override
	public String toString() {
		return getItemsToString();
	}

	@Override
	public boolean equals(Object o) {
		boolean result = super.equals(o);
		if(o instanceof SymbolTable) result |= occurences.equals(((SymbolTable) o).occurences);
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(serialVersionUID, occurences);
	}
}
