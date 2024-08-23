package Algorithms.GaleShapley;

import Components.Node;

import java.util.List;

public class OffereeNode extends Node {
    private List<String> preferences;

    public OffereeNode(int id, int x, int y, List<String> preferences) {
        super(x, y, id);
        this.preferences = preferences;
    }

    public List<String> getPreferences() {
        return preferences;
    }
}
