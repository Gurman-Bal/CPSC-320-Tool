package Components;

import java.util.List;

public class NodeList {
    private List<Node> nodes;

    public NodeList(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void removeNode(Node node) {
        nodes.remove(node);
    }

    public List<Node> getNodes(){
        return nodes;
    }

    public Node getNodeAt(int x, int y) {
        for (Node node : nodes) {
            if (Math.hypot(node.x - x, node.y - y) < 15) { // within 15 pixels
                return node;
            }
        }
        return null;
    }

    public Node getNodeById(int id) {
        for (Node node : nodes) {
            if (node.getId() == id) {
                return node;
            }
        }
        return null;
    }
}
