package com.github.therolffr.da53.lw1.processing;

import com.github.therolffr.da53.lw1.symbol.SymbolTable;
import com.github.therolffr.da53.lw1.token.Token;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lexer {
	public static void main(String[] args) throws IOException {
		System.out.println("Reading file...");

		InputStream inputStream = Lexer.class.getResourceAsStream("/com/github/therolffr/da53/lw1/resources/test2.tb");
		if(inputStream == null) throw new IOException("No test file found.");

		FileScanner scanner = new FileScanner(new InputStreamReader(inputStream));

		System.out.println("Readt file successfully!");

		SymbolTable symbolTable = new SymbolTable();

		Token t;
		while ((t = scanner.getNextToken()) != null) {
			symbolTable.include(t, scanner.getLineAndColumn());
		}

		System.out.println("processing output:\n");
		System.out.println(symbolTable.getOccurencesResult());

		System.out.println("\nSymbol items:");
		System.out.println(symbolTable.getItemsToString());
	}
}
