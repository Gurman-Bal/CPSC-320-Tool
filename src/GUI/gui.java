package GUI;

import Algorithms.BFS;
import Algorithms.DFS;
import GUI.Components.*;
import GUI.Listeners.MouseListeners;
import GUI.Panels.GaleShapleyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class gui extends JFrame {
    private NodeList nodeList;
    private EdgeList edgeList;
    private JPanel graphPanel;
    private GaleShapleyPanel gsPanel;

    private MouseListeners mouseListener;

    public gui() {

        // Set up the main window
        setTitle("CPSC320 Algorithms App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //define nodes, edges and the draw edge variables
        List<Node> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();
        nodeList = new NodeList(nodes);
        edgeList = new EdgeList(edges);

        //define the algorithms
        BFS bfs = new BFS();
        DFS dfs = new DFS();

        //create the special panels for each algorithm
        gsPanel = new GaleShapleyPanel();

        // Set up the graph panel
        graphPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGraph(g,edgeList.getEdges(),nodeList.getNodes());
                drawDraggingEdge(g);
            }
        };
        graphPanel.setBackground(Color.WHITE);
        add(graphPanel, BorderLayout.CENTER);

        //add each algorithm panel to the parent panel
        graphPanel.add(gsPanel.getPanel(), BorderLayout.NORTH);

        // Setup mouse listeners for creating nodes and edges
        mouseListener = new MouseListeners(nodeList, edgeList, graphPanel::repaint);
        graphPanel.addMouseListener(mouseListener);
        graphPanel.addMouseMotionListener(mouseListener);

        // Define the dropdown menu for algorithm selection
        JComboBox<String> algorithmDropdown = new JComboBox<>();
        algorithmDropdown.addItem("BFS");
        algorithmDropdown.addItem("DFS");
        algorithmDropdown.addItem("Gale-Shapley");

        //Create A solve button for each algorithm
        SolveButton solveButtonHandler = new SolveButton(algorithmDropdown, nodeList, edgeList, graphPanel, bfs, dfs, gsPanel);
        JButton solveButton = solveButtonHandler.createSolveButton();

        //create the panel to house all the controls
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Algorithm:"));
        controlPanel.add(algorithmDropdown);
        controlPanel.add(solveButton);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void drawGraph(Graphics g, List<Edge> edges, List<Node> nodes) {
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
        Node startNode = mouseListener.getStartNode();
        int dragEndX = mouseListener.getDragEndX();
        int dragEndY = mouseListener.getDragEndY();
        if (startNode != null) {
            g.setColor(Color.RED);
            g.drawLine(startNode.x, startNode.y, dragEndX, dragEndY);
        }
    }
}
