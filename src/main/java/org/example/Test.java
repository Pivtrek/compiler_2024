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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        String[] names = {"example1.imp", "example2.imp", "example3.imp", "example4.imp", "example5.imp", "example6.imp", "example7.imp", "example8.imp", "example9.imp", "exampleA-n.imp", "program0.imp", "program1.imp", "program2.imp", "program3.imp"};
        for (String name : names) {
            try {
                String filePath = "examples/" + name;
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
                codeGen.getInstructionList().writeToFile("C:/Users/piotr/Desktop/labor4_2/maszyna_wirtualna/" + name +".mr");
            } catch (ErrorColector.SemanticErrorException | IOException e) {
            }
        }
    }
}
