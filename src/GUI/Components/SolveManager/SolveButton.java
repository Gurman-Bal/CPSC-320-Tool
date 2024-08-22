package GUI.Components.SolveManager;

import GUI.Components.EdgeList;
import GUI.Components.NodeList;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SolveButton {
    private DropDownHandler dropDownHandler;
    private NodeList nodeList;
    private EdgeList edgeList;
    private JPanel graphPanel;

    private Map<String, AlgorithmHandler> algorithmHandlers;

    public SolveButton(DropDownHandler dropDownHandler, NodeList nodeList, EdgeList edgeList, JPanel graphPanel) {
        this.dropDownHandler = dropDownHandler;
        this.nodeList = nodeList;
        this.edgeList = edgeList;
        this.graphPanel = graphPanel;
        this.algorithmHandlers = new HashMap<>();
    }

    // Register different algorithms with their corresponding handlers
    public void registerAlgorithmHandler(String algorithmName, AlgorithmHandler handler) {
        algorithmHandlers.put(algorithmName, handler);
    }

    // Create the Solve button with the correct logic
    public JButton createSolveButton() {
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> handleSolveButtonClick());
        return solveButton;
    }

    // Handle the Solve button click event
    private void handleSolveButtonClick() {
        String selectedAlgorithm = dropDownHandler.getSelectedAlgorithm();

        if (selectedAlgorithm != null) {
            AlgorithmHandler handler = algorithmHandlers.get(selectedAlgorithm);

            if (handler != null) {
                handler.execute(nodeList.getNodes(), edgeList.getEdges(), graphPanel);
            } else {
                showError("Selected algorithm not implemented.");
            }
        } else {
            showError("Please select an algorithm first.");
        }
    }

    // Show an error message
    void showError(String message) {
        JOptionPane.showMessageDialog(graphPanel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }


}
