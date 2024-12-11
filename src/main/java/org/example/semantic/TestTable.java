package org.example.semantic;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.example.parser.GrammarLexer;
import org.example.parser.GrammarParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestTable {
    public static void main(String[] args) {

        String filePath = "examples/example1.imp";

        try {
            // 1. Odczyt kodu z pliku
            String code = Files.readString(Paths.get(filePath));
            System.out.println("Kod wejściowy:\n" + code);

            // 2. Stwórz Lexer i Parser
            GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(code));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GrammarParser parser = new GrammarParser(tokens);

            // 3. Wygeneruj drzewo składniowe
            ParseTree tree = parser.declarations(); // Dopasowanie reguły 'declarations'

            // 4. Inicjalizacja SymbolTable i SymbolTableBuilder
            SymbolTable symbolTable = new SymbolTable();
            SymbolTableBuilder builder = new SymbolTableBuilder(symbolTable);

            // 5. Przejdź po drzewie składniowym
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(builder, tree);

            // 6. Wypisz zawartość tablicy symboli
            System.out.println("Tablica symboli:");
            symbolTable.printSymbols();

        } catch (IOException e) {
            System.err.println("Błąd odczytu pliku: " + e.getMessage());
        }

    }
}
