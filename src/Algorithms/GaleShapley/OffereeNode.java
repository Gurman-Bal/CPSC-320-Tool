package Algorithms.GaleShapley;

import GUI.Components.Node;

import java.util.List;

public class OffereeNode extends Node {
    private List<OffererNode> preferenceList;

    public OffereeNode(int id, int x, int y, List<OffererNode> preferenceList) {
        super(id, x, y);
        this.preferenceList = preferenceList;
    }

    public List<OffererNode> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(List<OffererNode> preferenceList) {
        this.preferenceList = preferenceList;
    }

    // Add methods to interact with the preference list
}
