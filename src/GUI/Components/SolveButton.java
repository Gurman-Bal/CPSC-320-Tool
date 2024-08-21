package GUI.Components;

import Algorithms.BFS;
import Algorithms.DFS;
import GUI.Panels.GaleShapleyPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SolveButton {
    private JComboBox<String> algorithmDropdown;
    private NodeList nodeList;
    private EdgeList edgeList;
    private JPanel graphPanel;
    private BFS bfs;
    private DFS dfs;
    private GaleShapleyPanel gsPanel;

    public SolveButton(JComboBox<String> algorithmDropdown, NodeList nodeManager, EdgeList edgeManager, JPanel graphPanel, BFS bfs, DFS dfs, GaleShapleyPanel gsPanel) {
        this.algorithmDropdown = algorithmDropdown;
        this.nodeList = nodeManager;
        this.edgeList = edgeManager;
        this.graphPanel = graphPanel;
        this.bfs = bfs;
        this.dfs = dfs;
        this.gsPanel = gsPanel;

        this.algorithmDropdown.addItemListener(e -> updatePanelVisibility());
    }

    public JButton createSolveButton() {
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> handleSolveButtonClick());
        return solveButton;
    }

    private void updatePanelVisibility() {
        String selectedAlgorithm = (String) algorithmDropdown.getSelectedItem();
        if ("Gale-Shapley".equals(selectedAlgorithm)) {
            gsPanel.setVisible(true);
        } else {
            gsPanel.setVisible(false);
        }
        graphPanel.revalidate(); // Revalidate and repaint the panel to update the layout
        graphPanel.repaint();
    }

    private void handleSolveButtonClick() {
        String selectedAlgorithm = (String) algorithmDropdown.getSelectedItem();
        List<Node> nodes = nodeList.getNodes();
        List<Edge> edges = edgeList.getEdges();

        if (selectedAlgorithm != null) {
            switch (selectedAlgorithm) {
                case "BFS":
                    if (!nodes.isEmpty()) {
                        Graphics g = graphPanel.getGraphics();
                        bfs.performBFS(nodes.get(0), nodes, edges, g);
                    } else {
                        showError("No nodes available to start BFS.");
                    }
                    break;
                case "DFS":
                    if (!nodes.isEmpty()) {
                        Graphics g = graphPanel.getGraphics();
                        dfs.performDFS(nodes.get(0), nodes, edges, g);
                    } else {
                        showError("No nodes available to start DFS.");
                    }
                    break;
                case "Gale-Shapley":
                    String offer = gsPanel.getOffer();
                    String offeree = gsPanel.getOfferee();
                    runGaleShapleyAlgorithm(offer, offeree);
                    break;
                // Add more cases for other algorithms
                default:
                    showError("Algorithm not implemented.");
                    break;
            }
        } else {
            showError("Please select an algorithm first.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(graphPanel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void runGaleShapleyAlgorithm(String offer, String offeree) {
        // Implement the Gale-Shapley algorithm logic here
    }
}
