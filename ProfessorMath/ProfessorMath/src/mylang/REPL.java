package mylang;

import java.util.*;

public class REPL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Evaluator evaluator = new Evaluator();

        System.out.println("Welcome to ProfessorMath REPL");
        System.out.println("Type your code below. Press ENTER twice to execute.");

        while (true) {
            System.out.print(">>> ");
            StringBuilder inputBuilder = new StringBuilder();
            String line;

            // Collect multiline input
            while (true) {
                line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    break; // ENTER twice ends input
                }

                inputBuilder.append(line).append("\n");
                System.out.print("... ");
            }

            String input = inputBuilder.toString().trim();

            if (input.isEmpty()) {
                continue; // skip if empty
            }

            try {
                // Tokenize
                Lexer lexer = new Lexer(input);
                List<Token> tokens = lexer.tokenize();

                // Parse all expressions
                Parser parser = new Parser(tokens);
                List<Node> astList = parser.parseAll();

                // Evaluate each statement
                for (Node node : astList) {
                    Object result = evaluator.evaluate(node);

                    // ✅ Only print if it's a show statement
                    if (node instanceof PrintNode) {
                        System.out.println("Output: " + result);
                    }
                }

            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}