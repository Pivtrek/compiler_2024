Simple Imperative Language Compiler
Project Overview

This project is a compiler for a simple imperative language, built using Java and ANTLR4. The compiler translates source code into an assembly-like language for a custom virtual machine. It supports variable management, arithmetic operations, control structures, loops, procedures, and memory handling.
Features

✅ Full Compilation Pipeline

    Lexical and syntactic analysis using ANTLR4
    Semantic analysis with symbol table management
    Code generation for a custom stack-based virtual machine

✅ Memory Management

    Stack-based variable allocation
    Handling global, local, and procedural variables
    Array indexing and iterator support

✅ Control Structures & Loops

    IF, IF-ELSE, WHILE, REPEAT-UNTIL, FOR loops
    Efficient condition evaluation

✅ Arithmetic & Optimizations

    Optimized multiplication and division using binary algorithms
    Strength reduction (replacing expensive operations with simpler ones)
    Loop invariant code motion (reducing redundant calculations)

✅ Procedure Handling

    Nested procedure calls
    Parameter passing (by reference)
    Stack-based return address management

✅ Error Handling

    Detection of uninitialized variables
    Prevention of undeclared identifiers
    No recursion allowed (per language specification)

Installation & Setup
Prerequisites

    Java 19+
    Maven

Build Instructions

Clone the repository:

git clone <repository-url>
cd <project-folder>

Compile the project:

mvn clean package

This generates a standalone JAR file in the target/ directory.
Usage

To compile a program written in this language, use:

java -jar target/kompilator.jar <input_file> <output_file>

Example:

java -jar target/kompilator.jar examples/program.imp output.mr

This will generate an assembly-like output file (output.mr) that can be run on the virtual machine.

Project Structure

├── src/main/java/org/example
│   ├── Compiler.java          # Main entry point
│   ├── CodeGenerator.java     # Code generation logic
│   ├── Memory.java            # Memory and register management
│   ├── Instruction.java       # Virtual machine instruction representation
│   ├── SemanticAnalysis.java  # Semantic checks
│   ├── SymbolTable.java       # Variable and scope management
│   ├── Grammar.g4             # ANTLR grammar file
│   └── VirtualMachine.java    # VM simulation (if applicable)
├── target/                    # Compiled JAR output
├── examples/                   # Example programs
├── pom.xml                     # Maven build file
└── README.md                   # Project documentation

Known Issues & Improvements

🔹 Handling large numbers: Current implementation is limited to standard integer operations.
🔹 Further optimizations: Additional instruction merging and register reuse could improve efficiency.
🔹 Better debugging tools: Improved error reporting and debugging support could be added.
License

This project is released under the MIT License.
