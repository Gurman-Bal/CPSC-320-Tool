package Algorithms.GaleShapley;

import GUI.Components.Node;

import java.util.List;

public class OffereeNode extends Node {
    private List<OffererNode> preferences;

    public OffereeNode(int id, int x, int y, List<OffererNode> preferences) {
        super(id, x, y);
        this.preferences = preferences;
    }

    public List<OffererNode> getPreferences() {
        return preferences;
    }
}
