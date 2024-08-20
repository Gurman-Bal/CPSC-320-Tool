package GUI;

import Algorithms.BFS;
import Algorithms.DFS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class gui extends JFrame {
    private List<Node> nodes;
    private List<Edge> edges;
    private JPanel graphPanel;

    private Node startNode;
    private int dragEndX, dragEndY;

    public gui() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        startNode = null;
        dragEndX = dragEndY = 0;
        BFS bfs = new BFS();
        DFS dfs = new DFS();


        // Set up the main window
        setTitle("Graph Algorithms App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up the graph panel
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g);
                drawDraggingEdge(g);
            }
        };
        graphPanel.setBackground(Color.WHITE);
        add(graphPanel, BorderLayout.CENTER);

        // Setup mouse listeners for creating nodes and edges
        graphPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseRelease(e);
            }
        });

        graphPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDrag(e);
            }
        });

        // Define Buttons for algorithms
        JPanel controlPanel = new JPanel();
        JButton bfsButton = new JButton("BFS");
        bfsButton.addActionListener(e -> {
            if (!nodes.isEmpty()) {
                // Assuming you want to start BFS from the first node in the list
                Graphics g = graphPanel.getGraphics();
                bfs.performBFS(nodes.get(0), nodes, edges, g);
            } else {
                JOptionPane.showMessageDialog(this, "No nodes available to start BFS.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        controlPanel.add(bfsButton);

        JButton dfsButton = new JButton("DFS");
        dfsButton.addActionListener(e ->{
            if (!nodes.isEmpty()) {
                Graphics g = graphPanel.getGraphics();
                dfs.performDFS(nodes.get(0), nodes, edges, g);
            } else {
                JOptionPane.showMessageDialog(this, "No nodes available to start DFS.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        controlPanel.add(dfsButton);

        // Add more buttons for other algorithms
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void handleMousePress(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Node clickedNode = getNodeAt(e.getX(), e.getY());
            if (clickedNode != null) {
                // Start drawing an edge from this node
                startNode = clickedNode;
                dragEndX = e.getX();
                dragEndY = e.getY();
            } else {
                // Add a new node if no node is clicked
                addNode(e.getX(), e.getY());
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            // Right-click to remove a node or an edge
            Node clickedNode = getNodeAt(e.getX(), e.getY());
            if (clickedNode != null) {
                removeNode(clickedNode);
            } else {
                Edge clickedEdge = getEdgeAt(e.getX(), e.getY());
                if (clickedEdge != null) {
                    removeEdge(clickedEdge);
                }
            }
        }
        repaint();
    }

    private void handleMouseDrag(MouseEvent e) {
        if (startNode != null) {
            // Update the end position of the edge while dragging
            dragEndX = e.getX();
            dragEndY = e.getY();
            repaint();
        }
    }

    private void handleMouseRelease(MouseEvent e) {
        if (startNode != null && SwingUtilities.isLeftMouseButton(e)) {
            Node endNode = getNodeAt(e.getX(), e.getY());
            if (endNode != null && startNode != endNode) {
                // Create an edge between startNode and endNode
                addEdge(startNode, endNode);
            }
            startNode = null; // Reset after edge creation
            repaint();
        }
    }

    private void addNode(int x, int y) {
        nodes.add(new Node(x, y));
    }

    private void removeNode(Node node) {
        nodes.remove(node);
        removeEdgesConnectedToNode(node);
    }

    private Node getNodeAt(int x, int y) {
        for (Node node : nodes) {
            if (Math.hypot(node.x - x, node.y - y) < 15) { // within 15 pixels
                return node;
            }
        }
        return null;
    }

    private void addEdge(Node start, Node end) {
        edges.add(new Edge(start, end));
    }

    private void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    private Edge getEdgeAt(int x, int y) {
        for (Edge edge : edges) {
            if (isPointOnLine(x, y, edge.start.x, edge.start.y, edge.end.x, edge.end.y)) {
                return edge;
            }
        }
        return null;
    }

    private boolean isPointOnLine(int px, int py, int x1, int y1, int x2, int y2) {
        double distance = Math.abs((y2 - y1) * px - (x2 - x1) * py + x2 * y1 - y2 * x1)
                / Math.hypot(y2 - y1, x2 - x1);
        return distance < 5.0; // Adjust this threshold as needed
    }

    private void removeEdgesConnectedToNode(Node node) {
        edges.removeIf(edge -> edge.start.equals(node) || edge.end.equals(node));
    }

    private void drawGraph(Graphics g) {
        // Draw edges
        g.setColor(Color.BLACK);
        for (Edge edge : edges) {
            g.drawLine(edge.start.x, edge.start.y, edge.end.x, edge.end.y);
        }

        // Draw nodes
        g.setColor(Color.BLUE);
        for (Node node : nodes) {
            g.fillOval(node.x - 15, node.y - 15, 30, 30);
        }
    }

    private void drawDraggingEdge(Graphics g) {
        if (startNode != null) {
            g.setColor(Color.RED);
            g.drawLine(startNode.x, startNode.y, dragEndX, dragEndY);
        }
    }



}
