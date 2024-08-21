package GUI.Panels;

import javax.swing.*;

public class GaleShapleyPanel {
    private JTextField offerField;
    private JTextField offereeField;
    private JPanel panel;

    public GaleShapleyPanel() {
        // Initialize the input fields
        offerField = new JTextField(10);
        offereeField = new JTextField(10);

        // Initialize the panel and add components
        panel = new JPanel();
        panel.add(new JLabel("Offer:"));
        panel.add(offerField);
        panel.add(new JLabel("Offeree:"));
        panel.add(offereeField);

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
}
