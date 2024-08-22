package GUI.Components.SolveManager;

import GUI.Panels.GaleShapleyPanel;

import javax.swing.*;

public class DropDownHandler {
    private JComboBox<String> algorithmDropdown;
    private GaleShapleyPanel gsPanel;
    private JPanel graphPanel;

    public DropDownHandler(JComboBox<String> algorithmDropdown, GaleShapleyPanel gsPanel,JPanel graphPanel) {
        this.algorithmDropdown = algorithmDropdown;
        this.gsPanel = gsPanel;
        this.graphPanel = graphPanel;

        this.algorithmDropdown.addItemListener(e -> updatePanelVisibility());
    }

    public String getSelectedAlgorithm() {
        return (String) algorithmDropdown.getSelectedItem();
    }

    private void updatePanelVisibility() {
        String selectedAlgorithm = (String) algorithmDropdown.getSelectedItem();
        if ("Gale-Shapley".equals(selectedAlgorithm)) {
            gsPanel.setVisible(true);
        } else {
            gsPanel.setVisible(false);
        }
        graphPanel.revalidate();
        graphPanel.repaint();
    }
}
