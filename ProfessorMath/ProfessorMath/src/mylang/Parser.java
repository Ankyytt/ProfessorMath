package mylang;

import java.util.*;

public class Parser {
    private List<Token> tokens;
    private int position = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token peek() {
        return tokens.get(position);
    }

    private Token consume(TokenType expectedType) {
        Token token = peek();
        if (token.getType() != expectedType) {
            throw new RuntimeException("Expected " + expectedType + " but got " + token.getType());
        }
        position++;
        return token;
    }

    public Node parse() {
        if (peek().getType() == TokenType.LET) {
            return parseAssignment();
        } else if (peek().getType() == TokenType.SHOW) {
            return parsePrint();
        } else {
            return parseExpression();
        }
    }

    public List<Node> parseAll() {
        List<Node> nodes = new ArrayList<>();
        while (peek().getType() != TokenType.EOF) {
            nodes.add(parse());
        }
        return nodes;
    }

    private Node parseAssignment() {
        consume(TokenType.LET);
        String variableName = consume(TokenType.IDENTIFIER).getValue();
        consume(TokenType.EQUALS);
        Node value = parseExpression();
        return new AssignmentNode(variableName, value);
    }

    private Node parsePrint() {
        consume(TokenType.SHOW);
        Node expression = parseExpression();
        return new PrintNode(expression);
    }

    private Node parseExpression() {
        return parseAdditiveExpression();
    }

    private Node parseAdditiveExpression() {
        Node left = parseMultiplicativeExpression();
        while (peek().getType() == TokenType.PLUS || peek().getType() == TokenType.MINUS) {
            Token operator = consume(peek().getType());
            Node right = parseMultiplicativeExpression();
            left = new BinaryOperationNode(operator.getValue(), left, right);
        }
        return left;
    }

    private Node parseMultiplicativeExpression() {
        Node left = parsePrimaryExpression();
        while (peek().getType() == TokenType.MULTIPLY || peek().getType() == TokenType.DIVIDE) {
            Token operator = consume(peek().getType());
            Node right = parsePrimaryExpression();
            left = new BinaryOperationNode(operator.getValue(), left, right);
        }
        return left;
    }

    private Node parsePrimaryExpression() {
        Token token = peek();

        switch (token.getType()) {
            case NUMBER:
                consume(TokenType.NUMBER);
                return new NumberNode(Double.parseDouble(token.getValue()));

            case IDENTIFIER:
                consume(TokenType.IDENTIFIER);
                if (peek().getType() == TokenType.LPAREN) {
                    return parseFunctionCall(token.getValue());
                } else {
                    return new VariableNode(token.getValue());
                }

            case LPAREN:
                consume(TokenType.LPAREN);
                Node expr = parseExpression();
                consume(TokenType.RPAREN);
                return expr;

            case LBRACKET:
                return parseMatrixLiteral();

            default:
                throw new RuntimeException("Unexpected token: " + token);
        }
    }

    private Node parseFunctionCall(String name) {
        consume(TokenType.LPAREN);
        List<Node> arguments = new ArrayList<>();
        if (peek().getType() != TokenType.RPAREN) {
            arguments.add(parseExpression());
            while (peek().getType() == TokenType.COMMA) {
                consume(TokenType.COMMA);
                arguments.add(parseExpression());
            }
        }
        consume(TokenType.RPAREN);
        return new FunctionNode(name, arguments);
    }

    private Node parseMatrixLiteral() {
        consume(TokenType.LBRACKET);
        List<List<Node>> rows = new ArrayList<>();

        while (peek().getType() != TokenType.RBRACKET) {
            consume(TokenType.LBRACKET);
            List<Node> row = new ArrayList<>();

            row.add(parseExpression());
            while (peek().getType() == TokenType.COMMA) {
                consume(TokenType.COMMA);
                row.add(parseExpression());
            }

            consume(TokenType.RBRACKET);
            rows.add(row);

            if (peek().getType() == TokenType.COMMA) {
                consume(TokenType.COMMA);
            }
        }

        consume(TokenType.RBRACKET);
        return new MatrixNode(rows);
    }
}