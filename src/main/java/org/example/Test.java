package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.codegen.CodeGenerator;
import org.example.memory.Memory;
import org.example.parser.GrammarLexer;
import org.example.parser.GrammarParser;
import org.example.semantic.ErrorColector;
import org.example.semantic.SemanticAnalysis;
import org.example.semantic.SymbolTable;
import org.example.semantic.SymbolTableBuilderVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        String[] names = {"example1", "example2", "example3", "example4", "example5", "example6", "example7", "example8", "example9", "exampleA-n", "program0", "program1", "program2", "program3"};
        for (String name : names) {
            try {
                //Compilation
                String filePath = "labor4/testy/" + name + ".imp";
                String code = Files.readString(Paths.get(filePath));
                GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(code));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                GrammarParser parser = new GrammarParser(tokens);
                ParseTree tree = parser.program_all();
                SymbolTable symbolTable = new SymbolTable();
                ErrorColector errorColector = new ErrorColector();
                SymbolTableBuilderVisitor visitor = new SymbolTableBuilderVisitor(symbolTable, errorColector);
                visitor.visit(tree);
                SemanticAnalysis semanticAnalysis = new SemanticAnalysis(symbolTable, errorColector);
                semanticAnalysis.analyze(tree);
                Memory memory = new Memory(symbolTable, semanticAnalysis.getProcCallNumber());
                CodeGenerator codeGen = new CodeGenerator(memory, tree, symbolTable);
                codeGen.genereteCode();
                codeGen.getInstructionList().writeToFile("labor4/maszyna_wirtualna/" + name +".mr");
            } catch (ErrorColector.SemanticErrorException | IOException e) {
            }
        }
    }
}
