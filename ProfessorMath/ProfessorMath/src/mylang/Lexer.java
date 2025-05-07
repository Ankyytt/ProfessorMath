package mylang;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String input;
    private int position = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        while (position < input.length()) {
            char currentChar = input.charAt(position);

            // Skip whitespace
            if (Character.isWhitespace(currentChar)) {
                position++;
            }
            // Handle multi-digit numbers
            else if (Character.isDigit(currentChar) || currentChar == '.') {
                StringBuilder number = new StringBuilder();
                boolean hasDot = false;
                while (position < input.length() &&
                        (Character.isDigit(input.charAt(position)) || input.charAt(position) == '.')) {
                    if (input.charAt(position) == '.') {
                        if (hasDot) break;
                        hasDot = true;
                    }
                    number.append(input.charAt(position));
                    position++;
                }
                tokens.add(new Token(TokenType.NUMBER, number.toString()));
            }
            // Handle '='
            else if (currentChar == '=') {
                tokens.add(new Token(TokenType.EQUALS, "="));
                position++;
            }
            // Handle '+'
            else if (currentChar == '+') {
                tokens.add(new Token(TokenType.PLUS, "+"));
                position++;
            }
            // Handle '-'
            else if (currentChar == '-') {
                tokens.add(new Token(TokenType.MINUS, "-"));
                position++;
            }
            // Handle '*'
            else if (currentChar == '*') {
                tokens.add(new Token(TokenType.MULTIPLY, "*"));
                position++;
            }
            // Handle '/'
            else if (currentChar == '/') {
                tokens.add(new Token(TokenType.DIVIDE, "/"));
                position++;
            }
            // Handle '('
            else if (currentChar == '(') {
                tokens.add(new Token(TokenType.LPAREN, "("));
                position++;
            }
            // Handle ')'
            else if (currentChar == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")"));
                position++;
            }
            // Handle '['
            else if (currentChar == '[') {
                tokens.add(new Token(TokenType.LBRACKET, "["));
                position++;
            }
            // Handle ']'
            else if (currentChar == ']') {
                tokens.add(new Token(TokenType.RBRACKET, "]"));
                position++;
            }
            // Handle ','
            else if (currentChar == ',') {
                tokens.add(new Token(TokenType.COMMA, ","));
                position++;
            }
            // Handle "let" keyword
            else if (currentChar == 'l' && input.startsWith("let", position)) {
                tokens.add(new Token(TokenType.LET, "let"));
                position += 3;
            }
            // Handle "show" keyword
            else if (currentChar == 's' && input.startsWith("show", position)) {
                tokens.add(new Token(TokenType.SHOW, "show"));
                position += 4;
            }
            // Handle character literals
            else if (currentChar == '\'') {
                position++;
                char charValue = input.charAt(position);
                position++;
                if (input.charAt(position) != '\'') {
                    throw new RuntimeException("Invalid character literal");
                }
                position++;
                tokens.add(new Token(TokenType.CHARACTER, String.valueOf(charValue)));
            }
            // Handle string literals
            else if (currentChar == '"') {
                position++;
                StringBuilder stringValue = new StringBuilder();
                while (position < input.length() && input.charAt(position) != '"') {
                    stringValue.append(input.charAt(position));
                    position++;
                }
                if (position >= input.length()) {
                    throw new RuntimeException("Unterminated string literal");
                }
                position++;
                tokens.add(new Token(TokenType.STRING, stringValue.toString()));
            }
            // Handle identifiers (variable names)
            else if (Character.isLetter(currentChar)) {
                StringBuilder identifier = new StringBuilder();
                while (position < input.length() && Character.isLetterOrDigit(input.charAt(position))) {
                    identifier.append(input.charAt(position));
                    position++;
                }
                tokens.add(new Token(TokenType.IDENTIFIER, identifier.toString()));
            }
            else {
                throw new RuntimeException("Unexpected character: " + currentChar);
            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}