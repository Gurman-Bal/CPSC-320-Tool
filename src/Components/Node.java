package Components;

public class Node {
    public int x;
    public int y;

    private int id;

    public Node(int x, int y,int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
