package com.github.therolffr.da53.lw1.symbol;
import com.github.therolffr.da53.lw1.token.Token;

public class SymbolItem {
	private final Token token;
	private int firstOccurenceLine;
	private int firstOccurenceColumn;
	private final boolean isReservedWord;
	
	public SymbolItem(Token token, int firstOccurenceLine, int firstOccurenceColumn, boolean isReservedWord) {
		assert(token != null);
		assert(firstOccurenceLine > 0);
		assert(firstOccurenceColumn > 0);
		
		this.token = token;
		this.firstOccurenceLine = firstOccurenceLine;
		this.firstOccurenceColumn = firstOccurenceColumn;
		this.isReservedWord = isReservedWord;
	}

	public void updateLineAndColumn(int line, int col) {
		assert(line > 0);
		assert(col > 0);


		if(line < firstOccurenceLine) {
			firstOccurenceLine = line;
			firstOccurenceColumn = col;
		}
		else if(line == firstOccurenceLine && col < firstOccurenceColumn) {
			firstOccurenceColumn = col;
		}
	}

	@Override
	public String toString() {
		return "SymbolItem{" +
				"token=" + token +
				", firstOccurenceLine=" + firstOccurenceLine +
				", firstOccurenceColumn=" + firstOccurenceColumn +
				", isReservedWord=" + isReservedWord +
				'}';
	}
}
