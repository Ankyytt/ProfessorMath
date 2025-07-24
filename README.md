
🧮 ProfessorMath

**A MATLAB-inspired interpreted language for beginners — built in Java.**

ProfessorMath is a beginner-friendly, expression-oriented language designed for learning how programming languages, interpreters, and ASTs work. It supports arithmetic, variables, matrix operations, and includes both a REPL and a JavaFX GUI.

Whether you're a CS student, hobbyist, or aspiring compiler nerd — this project is for you!

---

## 📖 Table of Contents

- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the REPL](#running-the-repl)
  - [Running the GUI](#running-the-gui)
- [Usage Examples](#usage-examples)
- [Architecture](#architecture)
- [GUI Showcase](#gui-showcase)
- [Built With](#built-with)
- [Roadmap / Future Features](#roadmap--future-features)
- [Contributors](#contributors)
- [License](#license)

---

## 📖 About

ProfessorMath is a lightweight interpreted language built to simulate core features of MATLAB and other mathematical scripting environments. Its goal is to help beginners understand how programming languages are parsed, interpreted, and executed — while enabling basic numerical and matrix computations.

---

## 📦 Features

- ✅ Arithmetic: `+`, `-`, `*`, `/`, `^`
- ✅ Operator precedence and parentheses
- ✅ Variable assignments using `let`
- ✅ Built-in math functions: `sin`, `cos`, `log`, `sqrt`, etc.
- ✅ Matrix literals and operations
- ✅ Scalar-matrix operations
- ✅ `show` keyword for output display
- ✅ Command-line REPL
- ✅ JavaFX-powered GUI
- ✅ JSON-based expression serialization

---

## 🚀 Getting Started

### 🔧 Prerequisites

- Java 11 or higher
- JavaFX SDK (for running the GUI)
- Git (for cloning the repository)

> 💡 JavaFX must be properly configured in your IDE (e.g., IntelliJ or Eclipse).

---

### 📦 Installation

```bash
# Clone the repository
git clone https://github.com/your-username/professormath.git
cd professormath

# Compile the source files
javac -d bin src/**/*.java
````

---

### 🧑‍💻 Running the REPL

```bash
java -cp bin repl.Repl
```

---

### 🖥️ Running the GUI

Make sure JavaFX is correctly set up in your IDE, then run:

```bash
# In your IDE, run:
gui/Main.java
```

---

## 💡 Usage Examples

```plaintext
# Variable arithmetic
let x = 5
let y = 3
x + y           => 8
(2 + 3) * 4 - 1 => 19

# Built-in functions
sin(0.5)        => 0.479...
log(10)         => 2.302...
sqrt(16)        => 4.0

# Matrix creation and math
let A = [[1, 2], [3, 4]]
let B = [[5, 6], [7, 8]]
A + B           => [[6, 8], [10, 12]]
A * B           => Matrix multiplication
show A          => Prints matrix
```

---

## 🏗️ Architecture

Here’s a high-level view of how ProfessorMath works:

```
  Input → Lexer → Parser → AST → Evaluator → Output
                             ↓
                           (REPL or GUI)
```

### 🔍 Components

* **Lexer:** Tokenizes raw input.
* **Parser:** Builds the abstract syntax tree (AST).
* **AST Nodes:** Represent expressions like binary ops, function calls, variables, etc.
* **Evaluator:** Executes AST nodes and returns results.
* **REPL:** For command-line testing and debugging.
* **GUI:** A JavaFX-powered visual playground for input and output.

---

## 🎨 GUI Showcase

*(Add screenshots or gifs here once available)*

> GUI built using JavaFX with modern layout and interactive evaluation support.

---

## 🧰 Built With

* 🟦 Java 17
* 🎨 JavaFX
* 🔁 Custom recursive descent parser
* 🔧 JSON (for expression serialization)
* 🧠 Core interpreter principles: AST, evaluation, environment

---

## 🧠 Roadmap / Future Features

* [ ] Matrix inverse & determinant
* [ ] Custom user-defined functions
* [ ] Control flow: `if`, `while`
* [ ] File import/export for variable storage
* [ ] Enhanced error reporting with line/column tracking
* [ ] Built-in constants like `pi`, `e`, etc.
* [ ] JavaFX code editor with syntax highlighting

---

## 👥 Contributors

Made with 💻 and ☕ by:

* **Ankit Das**
* **Aaryan Brar**
* **Deepanshu Mehra**
* **Anjali**

> Built as an educational project to explore the inner workings of interpreters and compilers.

---

## 📜 License

This project is licensed under the **MIT License**.
Feel free to fork, learn, and contribute!

```

---

Let me know if you'd like:
- A `LICENSE` file for MIT
- A `CONTRIBUTING.md`
- GitHub badge markup
- Syntax-highlighted code editor recommendations for your GUI

You're all set to publish this project like a pro 🚀
```
Here's an improved and cleaner version of your `README.md`. It refines the language, polishes formatting, reduces redundancy, and adds a more professional yet friendly tone. Suggestions have been incorporated for clarity, structure, and polish:

---

````markdown
# 🧮 ProfessorMath

**A MATLAB-inspired interpreted programming language for beginners — built with Java.**

ProfessorMath is a minimalist, expression-based language designed to help you learn how interpreters work. Featuring arithmetic expressions, matrix support, and a graphical interface, this project is ideal for students, curious developers, and compiler enthusiasts exploring parsing, ASTs, and evaluation.

---

## 📚 Table of Contents

- [About](#about)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Run the REPL](#run-the-repl)
  - [Run the GUI](#run-the-gui)
- [Usage Examples](#usage-examples)
- [Architecture](#architecture)
- [GUI Preview](#gui-preview)
- [Built With](#built-with)
- [Roadmap](#roadmap)
- [Contributors](#contributors)
- [License](#license)

---

## 📖 About

ProfessorMath is a simple interpreted programming language designed for educational purposes. It supports basic math operations, variables, matrices, and comes with both a command-line REPL and a JavaFX-based graphical interface.

The main goal is to help learners understand the core components of language design: lexical analysis, parsing, AST construction, and evaluation.

---

## 📦 Features

- ✅ Arithmetic operations: `+`, `-`, `*`, `/`, `^`
- ✅ Operator precedence & parentheses
- ✅ Variable declaration with `let`
- ✅ Built-in functions: `sin`, `cos`, `log`, `sqrt`, etc.
- ✅ Matrix literals & matrix math
- ✅ Scalar operations on matrices
- ✅ Output via `show` keyword
- ✅ Interactive REPL (Read-Eval-Print Loop)
- ✅ JavaFX GUI interface
- ✅ JSON serialization of expressions

---

## 🚀 Getting Started

### 🔧 Prerequisites

- Java 11 or later
- JavaFX SDK (for GUI mode)
- Git (optional, for cloning)

> 💡 You’ll need to set up JavaFX in your IDE (like IntelliJ or Eclipse) for the GUI to work.

---

### 📥 Installation

```bash
# Clone the repository
git clone https://github.com/your-username/professormath.git
cd professormath

# Compile the code
javac -d bin src/**/*.java
````

---

### 🧑‍💻 Run the REPL

```bash
java -cp bin repl.Repl
```

---

### 🖥️ Run the GUI

Make sure JavaFX is configured in your IDE, then run:

```
gui/Main.java
```

---

## 💡 Usage Examples

```plaintext
# Variable assignment & math
let x = 5
let y = 3
x + y            => 8
(2 + 3) * 4 - 1  => 19

# Functions
sin(0.5)         => ~0.479
log(10)          => ~2.302
sqrt(16)         => 4

# Matrices
let A = [[1, 2], [3, 4]]
let B = [[5, 6], [7, 8]]
A + B            => [[6, 8], [10, 12]]
A * B            => Matrix multiplication
show A           => Prints matrix
```

---

## 🏗️ Architecture Overview

ProfessorMath follows a standard interpreted language pipeline:

```
Input → Lexer → Parser → AST → Evaluator → Output
                               ↓
                            (REPL / GUI)
```

### 🧱 Key Components

* **Lexer:** Converts raw text into tokens
* **Parser:** Builds the AST from tokens
* **AST Nodes:** Represent code structure (binary ops, function calls, etc.)
* **Evaluator:** Executes AST nodes in a scoped environment
* **REPL:** Command-line interaction for fast prototyping
* **GUI:** Interactive, styled JavaFX frontend

---

## 🎨 GUI Preview

*(Insert screenshots or GIFs here)*

The JavaFX GUI allows interactive programming with:

* Realtime evaluation
* User-friendly layout
* Easy-to-use input/output

---

## 🧰 Built With

* 💻 Java 17
* 🎨 JavaFX
* ⚙️ Custom recursive descent parser
* 🧠 Expression evaluator + scoped environment
* 🔗 JSON (for serialization)

---

## 🧭 Roadmap

Planned enhancements and upcoming features:

* [ ] Matrix inverse and determinant
* [ ] User-defined functions
* [ ] Control flow: `if`, `while`, etc.
* [ ] File import/export for variable persistence
* [ ] Line/column-based error diagnostics
* [ ] Built-in constants: `pi`, `e`, etc.
* [ ] JavaFX code editor with syntax highlighting

---

## 👥 Contributors

Made with 💻, ☕, and occasional debugging meltdowns by:

* **Ankit Das**
* **Aaryan Brar**
* **Deepanshu Mehra**
* **Anjali**

> Built as an academic and learning project to explore compiler and interpreter design from scratch.

---

## 📜 License

This project is licensed under the **MIT License** — free to use, modify, and share!

---

## 🙌 Want to Contribute?

Pull requests are welcome. For major changes, please open an issue first to discuss what you'd like to change.

Consider adding:

* Test cases
* GUI enhancements
* New language features

Happy hacking!
 😎
```
