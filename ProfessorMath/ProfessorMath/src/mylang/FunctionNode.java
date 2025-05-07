package mylang;

import java.util.List;

public class FunctionNode extends Node {
    private String functionName;
    private List<Node> arguments;

    public FunctionNode(String functionName, List<Node> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<Node> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "FunctionNode(functionName=" + functionName + ", arguments=" + arguments + ")";
    }
}
