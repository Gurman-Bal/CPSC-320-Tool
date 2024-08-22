package GUI.Components.SolveManager;

import Algorithms.DFS;
import GUI.Components.Edge;
import GUI.Components.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DFSHandler implements AlgorithmHandler {
    private DFS dfs;

    public DFSHandler(DFS dfs) {
        this.dfs = dfs;
    }

    @Override
    public void execute(List<Node> nodes, List<Edge> edges, JPanel graphPanel) {
        if (!nodes.isEmpty()) {
            Graphics g = graphPanel.getGraphics();
            dfs.performDFS(nodes.get(0), nodes, edges, g);
        } else {
            JOptionPane.showMessageDialog(graphPanel, "No nodes available to start DFS.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
