package Algorithms.GaleShapley;

import java.util.Objects;

public class Pair {
    private final OffererNode offerer;
    private final OffereeNode offeree;

    public Pair(OffererNode offerer, OffereeNode offeree) {
        this.offerer = offerer;
        this.offeree = offeree;
    }
    public OffererNode getOfferer() {
        return offerer;
    }
    public OffereeNode getOfferee() {
        return offeree;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(offerer, pair.offerer) && Objects.equals(offeree, pair.offeree);
    }

    public int hashCode() {
        return Objects.hash(offerer, offeree);
    }

}
