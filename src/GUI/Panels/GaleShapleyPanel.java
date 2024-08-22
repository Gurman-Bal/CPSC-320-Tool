package GUI.Panels;

import Algorithms.GaleShapley.OffererNode;
import Algorithms.GaleShapley.OffereeNode;
import GUI.Components.NodeList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GaleShapleyPanel {
    private JTextField slotField;
    private JTextField nameField;
    private JTextField preferenceField; // For entering preferences
    private JPanel panel;
    private NodeList nodeList;
    private boolean isOfferer; // Track if the current mode is for Offerer or Offeree

    public GaleShapleyPanel(NodeList nodeList) {
        this.nodeList = nodeList;
        this.isOfferer = true; // Default to Offerer mode

        // Initialize the input fields
        slotField = new JTextField(5);
        nameField = new JTextField(10);
        preferenceField = new JTextField(30); // Larger field for preferences

        // Initialize the panel
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2)); // Use GridLayout for better alignment

        // Add components to the panel
        panel.add(new JLabel("Slots:"));
        panel.add(slotField);
        panel.add(new JLabel("Node Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Preferences (comma-separated):"));
        panel.add(preferenceField);

        JButton toggleModeButton = new JButton("Toggle Node Type");
        toggleModeButton.addActionListener(e -> toggleNodeType());

        JButton addButton = new JButton("Add Node");
        addButton.addActionListener(e -> addNode());

        panel.add(toggleModeButton);
        panel.add(addButton);

        // Initially set the panel to be visible
        panel.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isOffererMode() {
        return isOfferer;
    }

    public String getSlotField() {
        return slotField.getText();
    }

    public String getNodeName() {
        return nameField.getText();
    }

    public List<String> getPreferences() {
        String[] prefs = preferenceField.getText().split(",");
        List<String> preferences = new ArrayList<>();
        for (String pref : prefs) {
            preferences.add(pref.trim());
        }
        return preferences;
    }

    public void setVisible(boolean visible) {
        panel.setVisible(visible);
    }

    private void toggleNodeType() {
        isOfferer = !isOfferer;
        slotField.setEnabled(isOfferer); // Only offerer nodes use slots
    }

    private void addNode() {
        // Logic for adding nodes is handled in MouseListeners
    }
}
