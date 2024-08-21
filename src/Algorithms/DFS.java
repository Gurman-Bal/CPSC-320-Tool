package Algorithms;

import GUI.Components.Node;
import GUI.Components.Edge;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DFS {

    public void performDFS(Node startNode, List<Node> nodes, List<Edge> edges, Graphics g) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                drawVisitedNode(g, current);

                for (Edge edge : edges) {
                    if (edge.start.equals(current) && !visited.contains(edge.end)) {
                        stack.push(edge.end);
                    } else if (edge.end.equals(current) && !visited.contains(edge.start)) {
                        stack.push(edge.start);
                    }
                }
            }
        }
    }

    private void drawVisitedNode(Graphics g, Node node) {
        g.setColor(Color.GREEN); // Or any color you want for visited nodes
        g.fillOval(node.x - 15, node.y - 15, 30, 30);
        try {
            Thread.sleep(500); // Delay for visualization
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

