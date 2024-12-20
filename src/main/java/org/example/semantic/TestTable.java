package org.example.semantic;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.example.parser.GrammarLexer;
import org.example.parser.GrammarParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestTable {
    public static void main(String[] args) throws IOException {

        String filePath = "examples/errors/error4.imp";

        try {
            // 1. Odczyt kodu z pliku
            String code = Files.readString(Paths.get(filePath));
            System.out.println("Kod wejściowy:\n" + code);

            // 2. Stwórz Lexer i Parser
            GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(code));
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            GrammarParser parser = new GrammarParser(tokens);

            // 3. Wygeneruj drzewo składniowe
            ParseTree tree = parser.program_all(); // Dopasowanie reguły 'declarations'

            // 4. Inicjalizacja SymbolTable i SymbolTableBuilder
            SymbolTable symbolTable = new SymbolTable();
            ErrorColector errorColector = new ErrorColector();
            SymbolTableBuilderVisitor visitor = new SymbolTableBuilderVisitor(symbolTable, errorColector);

            // 5. Przejdź po drzewie składniowym
            visitor.visit(tree);

            // 6. Wypisz zawartość tablicy symboli
            System.out.println("Tablica symboli:");
            symbolTable.printSymbols();

            System.out.println(symbolTable);

        } catch (ErrorColector.SemanticErrorException | IOException e) {
            //Purposely left empty, because Semantic error is printing error itself
        }

    }
}
