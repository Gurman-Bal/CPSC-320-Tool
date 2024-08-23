package Components;

import java.util.List;

public class EdgeList {
    private List<Edge> edges;

    public EdgeList(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Node start, Node end) {
        edges.add(new Edge(start, end));
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    public void removeEdgesConnectedToNode(Node node) {
        edges.removeIf(edge -> edge.start.equals(node) || edge.end.equals(node));
    }

    public Edge getEdgeAt(int x, int y) {
        for (Edge edge : edges) {
            if (isPointOnLine(x, y, edge.start.x, edge.start.y, edge.end.x, edge.end.y)) {
                return edge;
            }
        }
        return null;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private boolean isPointOnLine(int px, int py, int x1, int y1, int x2, int y2) {
        double distance = Math.abs((y2 - y1) * px - (x2 - x1) * py + x2 * y1 - y2 * x1)
                / Math.hypot(y2 - y1, x2 - x1);
        return distance < 5.0;
    }
}
