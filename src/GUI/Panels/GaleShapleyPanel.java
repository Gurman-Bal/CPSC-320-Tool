package GUI.Panels;

import Algorithms.GaleShapley.OffereeNode;
import Algorithms.GaleShapley.OffererNode;
import GUI.Components.Node;
import GUI.Components.NodeList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GaleShapleyPanel {
    private JTextField offerField;
    private JTextField offereeField;
    private JPanel panel;
    private NodeList nodeList;

    public GaleShapleyPanel(NodeList nodeList) {
        this.nodeList = nodeList;

        // Initialize the input fields
        offerField = new JTextField(10);
        offereeField = new JTextField(10);

        // Initialize the panel and add components
        panel = new JPanel();
        panel.add(new JLabel("Offer:"));
        panel.add(offerField);
        panel.add(new JLabel("Offeree:"));
        panel.add(offereeField);

        JButton addOffererButton = new JButton("Add Offerer Node");
        addOffererButton.addActionListener(e -> addOffererNode());

        JButton addOffereeButton = new JButton("Add Offeree Node");
        addOffereeButton.addActionListener(e -> addOffereeNode());

        panel.add(addOffererButton);
        panel.add(addOffereeButton);

        // Initially set the panel to be invisible
        panel.setVisible(false);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getOffer() {
        return offerField.getText();
    }

    public String getOfferee() {
        return offereeField.getText();
    }

    public void setVisible(boolean visible) {
        panel.setVisible(visible);
    }

    private void addOffererNode() {
        // Implement node addition logic
    }

    private void addOffereeNode() {
        // Implement node addition logic
    }
}
