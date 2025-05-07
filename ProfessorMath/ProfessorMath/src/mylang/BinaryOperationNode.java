package mylang;

public class BinaryOperationNode extends Node {
    private String operator;
    private Node left;
    private Node right;

    public BinaryOperationNode(String operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "BinaryOperationNode(operator=" + operator + ", left=" + left + ", right=" + right + ")";
    }
}