package Algorithms.GaleShapley;

import GUI.Components.Node;

import java.util.List;

public class OffererNode extends Node {
    private List<OffereeNode> preferenceList;

    public OffererNode(int id, int x, int y, List<OffereeNode> preferenceList) {
        super(id, x, y);
        this.preferenceList = preferenceList;
    }

    public List<OffereeNode> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(List<OffereeNode> preferenceList) {
        this.preferenceList = preferenceList;
    }
}
