package Algorithms;

import Components.Edge;
import Components.Node;

import java.awt.*;
import java.util.*;
import java.util.List;


public class BFS {
    public void performBFS(Node start, List<Node> nodes, List<Edge> edges, Graphics g) {
        if (start == null) return;

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            highlightNode(current, g, Color.GREEN);

            // Get all adjacent nodes (connected via edges)
            for (Edge edge : edges) {
                Node neighbor = null;
                if (edge.start.equals(current) && !visited.contains(edge.end)) {
                    neighbor = edge.end;
                } else if (edge.end.equals(current) && !visited.contains(edge.start)) {
                    neighbor = edge.start;
                }

                if (neighbor != null) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    private void highlightNode(Node node, Graphics g, Color color) {
        g.setColor(color);
        g.fillOval(node.x - 15, node.y - 15, 30, 30);
        try {
            Thread.sleep(500); // Pause to visualize the algorithm's progress
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
