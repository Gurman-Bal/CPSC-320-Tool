package GUI.Components.SolveManager;

import GUI.Components.Edge;
import GUI.Components.Node;

import javax.swing.*;
import java.util.List;

public interface AlgorithmHandler {
    void execute(List<Node> nodes, List<Edge> edges, JPanel graphPanel);
}
