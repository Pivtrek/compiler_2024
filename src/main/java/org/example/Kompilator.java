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

public class Kompilator {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Wrong number of argument");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        try {
            String code = Files.readString(Paths.get(inputFile));
            GrammarLexer lexer = new GrammarLexer(CharStreams.fromString(code));
            CodeGenerator codeGen = getCodeGenerator(lexer);
            codeGen.getInstructionList().writeToFile(outputFile);


        } catch (ErrorColector.SemanticErrorException | IOException e) {

        }
    }

    private static CodeGenerator getCodeGenerator(GrammarLexer lexer) {
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
        return codeGen;
    }
}
