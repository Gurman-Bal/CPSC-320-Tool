package Components.SolveManager;

import GUI.Panels.GaleShapleyPanel;

import javax.swing.*;

public class DropDownHandler {
    private JComboBox<String> algorithmDropdown;
    private GaleShapleyPanel gsPanel;
    private JPanel graphPanel;
    public String selectedAlgorithm;

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
        selectedAlgorithm = (String) algorithmDropdown.getSelectedItem();
        if ("Gale-Shapley".equals(selectedAlgorithm)) {
            gsPanel.setVisible(true);
        } else {
            gsPanel.setVisible(false);
        }
        graphPanel.revalidate();
        graphPanel.repaint();
    }

    public boolean isGSPanelVisible() {
        return "Gale-Shapley".equals(selectedAlgorithm);
    }
}
