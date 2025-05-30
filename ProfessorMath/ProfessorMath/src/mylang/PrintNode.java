package mylang;

public class PrintNode extends Node {
    private Node expression;

    public PrintNode(Node expression) {
        this.expression = expression;
    }

    public Node getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "PrintNode(expression=" + expression + ")";
    }
}
