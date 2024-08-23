package GUI;

import Algorithms.BFS;
import Algorithms.DFS;
import Algorithms.GaleShapley.GaleShapley;
import Algorithms.GaleShapley.OffereeNode;
import Components.*;
import Components.SolveManager.*;
import Components.Listeners.MouseListeners;
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
        GaleShapley galeShapley = new GaleShapley();

        //create the special panels for each algorithm
        gsPanel = new GaleShapleyPanel(nodeList);

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

        // Define the dropdown menu for algorithm selection
        JComboBox<String> algorithmDropdown = new JComboBox<>();
        algorithmDropdown.addItem("BFS");
        algorithmDropdown.addItem("DFS");
        algorithmDropdown.addItem("Gale-Shapley");

        // Initialize DropDownHandler
        DropDownHandler dropDownHandler = new DropDownHandler(algorithmDropdown,gsPanel,graphPanel);

        // Setup mouse listeners for creating nodes and edges
        mouseListener = new MouseListeners(nodeList, edgeList, graphPanel::repaint, dropDownHandler,gsPanel);
        graphPanel.addMouseListener(mouseListener);
        graphPanel.addMouseMotionListener(mouseListener);

        // Initialize SolveButton with algorithm handlers
        SolveButton solveButtonHandler = new SolveButton(dropDownHandler, nodeList, edgeList, graphPanel);
        solveButtonHandler.registerAlgorithmHandler("BFS", new BFSHandler(bfs));
        solveButtonHandler.registerAlgorithmHandler("DFS", new DFSHandler(dfs));
        solveButtonHandler.registerAlgorithmHandler("Gale-Shapley", new GaleShapleyHandler(galeShapley, gsPanel));

        JButton solveButton = solveButtonHandler.createSolveButton();

        //create the panel to house all the controls
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Select Algorithm:"));
        controlPanel.add(algorithmDropdown);
        controlPanel.add(solveButton);
        add(controlPanel, BorderLayout.SOUTH);

        // Add the Gale-Shapley panel to the graph panel, initially invisible
        graphPanel.add(gsPanel.getPanel(), BorderLayout.NORTH);
        gsPanel.getPanel().setVisible(false); // Ensure it's hidden by default

        setVisible(true);
    }

    private void drawGraph(Graphics g, List<Edge> edges, List<Node> nodes) {
        // Draw edges
        g.setColor(Color.BLACK);
        for (Edge edge : edges) {
            g.drawLine(edge.start.x, edge.start.y, edge.end.x, edge.end.y);
        }

        // Draw nodes

        for (Node node : nodes) {
            if(node.getClass().equals(OffereeNode.class)){
                g.setColor(Color.CYAN);
                g.fillOval(node.x - 15, node.y - 15, 30, 30);
            } else {
                g.setColor(Color.BLUE);
                g.fillOval(node.x - 15, node.y - 15, 30, 30);
            }
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
