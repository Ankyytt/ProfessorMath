package mylang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator {
    private Map<String, Object> variables = new HashMap<>();

    public Object evaluate(Node node) {
        if (node instanceof AssignmentNode) {
            AssignmentNode assign = (AssignmentNode) node;
            Object value = evaluate(assign.getValue());
            variables.put(assign.getVariableName(), value);
            return value;
        } else if (node instanceof VariableNode) {
            VariableNode varNode = (VariableNode) node;
            String varName = varNode.getName();
            Object value = variables.get(varName);
            if (value == null) {
                throw new RuntimeException("Undefined Variable: " + varName);
            }
            return value;
        } else if (node instanceof PrintNode) {
            PrintNode printNode = (PrintNode) node;
            Object value = evaluate(printNode.getExpression());
            System.out.println(value);
            return value;
        } else if (node instanceof NumberNode) {
            return ((NumberNode) node).getValue(); // This should return a double

        } else if (node instanceof CharacterNode) {
            return ((CharacterNode) node).getValue();
        } else if (node instanceof StringNode) {
            return ((StringNode) node).getValue();
        } else if (node instanceof BinaryOperationNode) {
            BinaryOperationNode binOp = (BinaryOperationNode) node;
            Object left = evaluate(binOp.getLeft());
            Object right = evaluate(binOp.getRight());
            return performOperation(binOp.getOperator(), left, right);
        } else if (node instanceof FunctionNode) {
            FunctionNode funcNode = (FunctionNode) node;
            List<Node> arguments = funcNode.getArguments();
            Object[] evaluatedArgs = new Object[arguments.size()];
            for (int i = 0; i < arguments.size(); i++) {
                evaluatedArgs[i] = evaluate(arguments.get(i));
            }
            return performFunction(funcNode.getFunctionName(), evaluatedArgs);
        } else if (node instanceof MatrixNode) {
            MatrixNode matrixNode = (MatrixNode) node;
            List<List<Node>> rows = matrixNode.getRows();
            double[][] matrixData = new double[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                List<Node> row = rows.get(i);
                matrixData[i] = new double[row.size()];
                for (int j = 0; j < row.size(); j++) {
                    Object evaluated = evaluate(row.get(j));
                    if (evaluated instanceof Double) {
                        matrixData[i][j] = (Double) evaluated;
                    } else {
                        throw new RuntimeException("Matrix element must evaluate to a number");
                    }
                }
            }
            return new Matrix(matrixData);
        } else {
            throw new RuntimeException("Unknown node type: " + node.getClass().getSimpleName());
        }
    }

    private Object performOperation(String operator, Object left, Object right) {
        if (left instanceof Double && right instanceof Double) {
            double leftVal = (Double) left;
            double rightVal = (Double) right;
            switch (operator) {
                case "+": return leftVal + rightVal;
                case "-": return leftVal - rightVal;
                case "*": return leftVal * rightVal;
                case "/": return leftVal / rightVal;
                default: throw new RuntimeException("Unknown operator: " + operator);
            }
        } else if (left instanceof Integer && right instanceof Integer) {
            int leftVal = (Integer) left;
            int rightVal = (Integer) right;

            switch (operator) {
                case "+": return leftVal + rightVal;
                case "-": return leftVal - rightVal;
                case "*": return leftVal * rightVal;
                case "/": return leftVal / rightVal;
                default: throw new RuntimeException("Unknown operator: " + operator);
            }
        } else if (left instanceof String && right instanceof String && operator.equals("+")) {
            return (String) left + (String) right;
        } else if (left instanceof Matrix && right instanceof Matrix) {
            Matrix leftMatrix = (Matrix) left;
            Matrix rightMatrix = (Matrix) right;
            switch (operator) {
                case "+": return leftMatrix.add(rightMatrix);
                case "*": return leftMatrix.multiply(rightMatrix);
                default: throw new RuntimeException("Unsupported matrix operator: " + operator);
            }
        } else {
            throw new RuntimeException("Unsupported operand types for operator: " + operator);
        }
    }

    private Object performFunction(String functionName, Object[] arguments) {
        switch (functionName) {
            case "sin":
                if (arguments.length != 1 || !(arguments[0] instanceof Double)) {
                    throw new RuntimeException("sin function expects one double argument");
                }
                return Math.sin((Double) arguments[0]);
            case "cos":
                if (arguments.length != 1 || !(arguments[0] instanceof Double)) {
                    throw new RuntimeException("cos function expects one double argument");
                }
                return Math.cos((Double) arguments[0]);
            case "log":
                if (arguments.length != 1 || !(arguments[0] instanceof Double)) {
                    throw new RuntimeException("log function expects one double argument");
                }
                return Math.log((Double) arguments[0]);
            case "sqrt":
                if (arguments.length != 1 || !(arguments[0] instanceof Double)) {
                    throw new RuntimeException("sqrt function expects one double argument");
                }
                return Math.sqrt((Double) arguments[0]);
            default:
                throw new RuntimeException("Unknown function: " + functionName);
        }
    }
}
