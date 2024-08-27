package Algorithms.GaleShapley;

import Components.Node;

import java.util.List;

public class OffereeNode extends Node {
    private List<String> preferences;
    private boolean isFree;
    private OffererNode matchedOfferer;

    public OffereeNode(int id, int x, int y, List<String> preferences) {
        super(x, y, id);
        this.preferences = preferences;
        this.isFree = true;
        this.matchedOfferer = null;
    }

    public List<String> getPreferences() {
        return preferences;
    }
    public boolean isFree() {
        return isFree;
    }

    public OffererNode getMatchedOfferer() {
        return matchedOfferer;
    }

    public void setMatchedOfferer(OffererNode matchedOfferer) {
        this.matchedOfferer = matchedOfferer;
    }
}
