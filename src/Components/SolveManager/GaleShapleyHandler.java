package Components.SolveManager;

import Algorithms.GaleShapley.GaleShapley;
import Components.Edge;
import Components.Node;
import GUI.Panels.GaleShapleyPanel;

import javax.swing.*;
import java.util.List;

public class GaleShapleyHandler implements AlgorithmHandler {
    private GaleShapley galeShapley;
    private GaleShapleyPanel gsPanel;

    public GaleShapleyHandler(GaleShapley galeShapley, GaleShapleyPanel gsPanel) {
        this.galeShapley = galeShapley;
        this.gsPanel = gsPanel;
    }

    @Override
    public void execute(List<Node> nodes, List<Edge> edges, JPanel graphPanel) {
        if (!nodes.isEmpty()) {
            galeShapley.preformGaleShapley();
        } else {
            JOptionPane.showMessageDialog(graphPanel, "No nodes available to start Gale-Shapley.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
