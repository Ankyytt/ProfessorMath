# ProfessorMath
A Matlab inspired Coding Language for beginners
🧮
A minimalistic, expression-oriented interpreted programming language built in Java. MyLang supports arithmetic, variable bindings, matrix operations, functions, and a REPL interface, along with a JavaFX GUI.

This project was created for learning how to build interpreters, abstract syntax trees (ASTs), parsing, evaluation, and integrating a frontend.

📦 Features
✅ Arithmetic operations: +, -, *, /, ^

✅ Parentheses and operator precedence

✅ Built-in math functions: sin, cos, log, sqrt, etc.

✅ Variable assignment using let: let x = 5

✅ Matrix support: [[1, 2], [3, 4]]

✅ Matrix operations: addition, multiplication, scalar ops

✅ Display values using show keyword

✅ REPL (command-line interface) and JavaFX GUI

✅ JSON serialization of expressions

✨ Demo Commands
Try the following in the REPL or GUI:

Basic Arithmetic:

let x = 5
let y = 3
x + y
(2 + 3) * 4 - 1
Math Functions:


sin(0.5)
log(10) + sqrt(16)
Matrices:
    
🎨 Why JavaFX?
JavaFX provides a modern GUI framework for Java with:

Rich UI controls

CSS-style styling

Tight integration with Java logic

Easy event handling

Alternatives like Swing or web were avoided to keep integration simple and native.

🚀 Running the Project
Clone the repository:

Without GUI (REPL):

javac -d bin src/**/*.java
java -cp bin repl.Repl
With GUI (JavaFX):

Make sure JavaFX is configured in your IDE

Run gui/Main.java

🧠 Future Improvements
Matrix inverse and determinant

Custom functions and user-defined operators

Control flow (if, while)

File import/export for variables

Advanced error reporting

Code editor with syntax highlighting

👨‍💻 Author
Developed by Ankit Das, Aaryan Brar, Deepanshu Mehra, Anjali

Built as an educational project to understand compilers, interpreters, and language tooling.


