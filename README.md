# **Simple Imperative Language Compiler**

## **Project Overview**
This project is a **compiler** for a simple imperative language, built using **Java and ANTLR4**. The compiler translates source code into an **assembly-like language** for a **custom virtual machine**. It supports **variable management, arithmetic operations, control structures, loops, procedures, and memory handling**.

## **Features**
✅ **Full Compilation Pipeline**  
- Lexical and syntactic analysis using **ANTLR4**  
- Semantic analysis with **symbol table management**  
- Code generation for a custom **stack-based virtual machine**  

✅ **Memory Management**  
- Stack-based variable allocation  
- Handling **global, local, and procedural variables**  
- Array indexing and iterator support  

✅ **Control Structures & Loops**  
- **IF, IF-ELSE, WHILE, REPEAT-UNTIL, FOR** loops  
- **Efficient condition evaluation**  

✅ **Arithmetic & Optimizations**  
- **Optimized multiplication and division** using **binary algorithms**  
- **Strength reduction** (replacing expensive operations with simpler ones)  
- **Loop invariant code motion** (reducing redundant calculations)  

✅ **Procedure Handling**  
- **Nested procedure calls**  
- **Parameter passing (by reference)**  
- **Stack-based return address management**  

✅ **Error Handling**  
- Detection of **uninitialized variables**  
- Prevention of **undeclared identifiers**  
- **No recursion allowed** (per language specification)  

---

### **Prerequisites**
- **Java 19+**  
- **Maven**  

### **Build Instructions**
Clone the repository:  
```bash
git clone <repository-url>
cd <project-folder>
```
Compile the project:
```bash
mvn build
```

## **Usage**
```bash
java -jar target/kompilator.jar <input_file> <output_file>
```



