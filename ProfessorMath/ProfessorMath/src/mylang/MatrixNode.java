package mylang;

import java.util.List;

public class MatrixNode extends Node {
    private List<List<Node>> rows;

    public MatrixNode(List<List<Node>> rows) {
        this.rows = rows;
    }

    public List<List<Node>> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "MatrixNode(rows=" + rows + ")";
    }
}