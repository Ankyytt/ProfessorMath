package mylang;

public class AssignmentNode extends Node {
    private String variableName;
    private Node value;

    public AssignmentNode(String variableName, Node value) {
        this.variableName = variableName;
        this.value = value;
    }

    public String getVariableName() {
        return variableName;
    }

    public Node getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AssignmentNode(variableName=" + variableName + ", value=" + value + ")";
    }
}
