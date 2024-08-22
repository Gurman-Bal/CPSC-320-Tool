package Algorithms.GaleShapley;

import GUI.Components.Node;

import java.util.List;

public class OffererNode extends Node {
    private int slots;
    private List<OffereeNode> preferences;

    public OffererNode(int id, int x, int y, int slots, List<OffereeNode> preferences) {
        super(id, x, y);
        this.slots = slots;
        this.preferences = preferences;
    }

    public int getSlots() {
        return slots;
    }

    public List<OffereeNode> getPreferences() {
        return preferences;
    }
}
