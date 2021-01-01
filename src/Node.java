import java.util.ArrayList;

public class Node {
    boolean isRoot;
    boolean isLeaf;
    ArrayList<Integer> keys;
    ArrayList<Node> pointers;
    int order;
    Node parent;
    int level;
    Node nextNode;
    Node prevNode;

    Node(int order){
        this.isRoot = false;
        this.isLeaf = false;
        this.order = order;
        ArrayList<Integer> keys = new ArrayList<Integer>();
        ArrayList<Node> pointers = new ArrayList<Node>();
        this.keys = keys;
        this.pointers = pointers;
        this.parent = null;
    }

}
