package Algorithms.GaleShapley;

import Components.Node;

import java.util.List;

public class OffererNode extends Node {
    private int slots;
    private List<String> preferences;


    public OffererNode(int id, int x, int y, int slots, List<String> preferences) {
        super(x, y, id);
        this.slots = slots;
        this.preferences = preferences;
    }

    public int getSlots() {
        return slots;
    }

    public List<String> getPreferences() {
        return preferences;
    }
}
