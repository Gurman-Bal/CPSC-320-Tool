package Components.SolveManager;

import Algorithms.BFS;
import Components.Edge;
import Components.Node;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class BFSHandler implements AlgorithmHandler {
    private BFS bfs;

    public BFSHandler(BFS bfs) {
        this.bfs = bfs;
    }

    @Override
    public void execute(List<Node> nodes, List<Edge> edges, JPanel graphPanel) {
        if (!nodes.isEmpty()) {
            Graphics g = graphPanel.getGraphics();
            bfs.performBFS(nodes.get(0), nodes, edges, g);
        } else {
            JOptionPane.showMessageDialog(graphPanel, "No nodes available to start BFS.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
