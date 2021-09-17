package com.github.therolffr.da53.lw1.token;

import java.util.Objects;

public class TokenOccurence implements Comparable<TokenOccurence> {
    private final Token token;
    private final int line;
    private final int col;

    public TokenOccurence(Token token, int[] lineAndColumn) {
        this.token = token;
        this.line = lineAndColumn[0];
        this.col = lineAndColumn[1];
    }

    public Token getToken() {
        return token;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    @SuppressWarnings("unused")
    public int[] getLineAndColumn() {
        return new int[]{line, col};
    }

    @Override
    public int compareTo(TokenOccurence o) {
        int lineCompare = Integer.compare(line, o.line);
        return lineCompare != 0 ? lineCompare : Integer.compare(col, o.col);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof TokenOccurence) ? token.equals(((TokenOccurence) obj).token) && ((TokenOccurence) obj).line == line && ((TokenOccurence) obj).col == col : super.equals(obj);
    }

    @Override
    public String toString() {
        return token.toString() + " -> " + line + ":" + col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, line, col);
    }
}
