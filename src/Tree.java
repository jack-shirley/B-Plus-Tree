import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class Tree {
    ArrayList<Integer> inputArrayList;
    String type;
    Node root;
    int order;
    public int numLevels;
    ArrayList<Node> outputArrayList = new ArrayList<Node>();



    Tree(ArrayList<Integer> inputArrayList, String type, int order){
        this.inputArrayList = inputArrayList;
        this.type = type;
        this.order = order;
        this.root = null;
        //ArrayList<Node> outputArrayList = new ArrayList<Node>();

        if(type.equals("dense")){
            createDenseTree();
        }else if(type.equals("sparse")){
            createSparseTree();
        }
    }

    public void createDenseTree(){
        //call insert for every key
        type = "dense";
        for(int i = 0; i < inputArrayList.size(); i++){
            insertKey(inputArrayList.get(i), false);
        }
        //create nodes
    }

    public void createSparseTree(){
        //call insert for every key
        type = "sparse";
        for(int i = 0; i < inputArrayList.size(); i++){
            insertKey(inputArrayList.get(i), false);
        }
        //create nodes
    }

    public void insertKey(int key, boolean print){
        //create node
        //TODO-DEBUGGING
        if(key == 186971){
            int asdf = 0;
        }
        if(root == null) {
            Node root = new Node(order);
            root.isRoot = true;
            root.isLeaf = true;
            root.keys.add(key);
            root.level = 0;
            this.root = root;
            return;
        }
        Node node = root;

        //if root node is only node and not full, add the key
        if(node.isRoot && root.isLeaf && root.keys.size() < order){
            node.keys.add(key);
            Collections.sort(node.keys);
            return;
        }

        //***** program will reach this point once the root node is full/not the only node *****



        if(node.isRoot && node.isLeaf && root.keys.size() >= order){
            splitNode(node);
            node.isLeaf = false;
        }


        //Iterate down the tree to find the correct proposed node for placement of key into a leaf node
        //might need to fix
        int i = 0;
        while(!node.isLeaf) {
                if(key < node.keys.get(i)){
                    node = node.pointers.get(i);
                    i = 0;
                }else if(key >= node.keys.get(i) && i == node.keys.size()-1){
                    node = node.pointers.get(node.keys.size());
                    i = 0;
                }else {
                    i++;
                }
        }

        if(print == true){
            System.out.println("Node prior to insertion:" + node.keys);
        }

        if(node.isLeaf && node.keys.size() >= (int)((double)order/2) && type == "sparse") {
            splitNode(node);
            insertKey(key, false);
            if(print == true){
                System.out.println("Node after insertion:" + node.keys);
                System.out.println();
            }
            return;
        }

        if(node.isLeaf && node.keys.size() >= order && type == "dense") {
            splitNode(node);
            insertKey(key, false);
            if(print == true){
                System.out.println("Node after insertion:" + node.keys);
                System.out.println();
            }
            return;
        }

        if(node.isLeaf && node.keys.size() < order){
            node.keys.add(key);
            Collections.sort(node.keys);
            if(print == true){
                System.out.println("Node after insertion:" + node.keys);
                System.out.println();
            }
            return;
        }

        if(print == true){
            System.out.println("Node after insertion:" + node.keys);
            System.out.println();
        }


    }

    public void deleteKey(int key){
        int i = 0;
        boolean tooEmpty = false;
        Node node = new Node(order);
        node = root;
        while(!node.isLeaf){
            if(key < node.keys.get(i)){
                node = node.pointers.get(i);
                i = 0;
            }else if(key >= node.keys.get(i) && i == node.keys.size()-1){
                node = node.pointers.get(node.keys.size());
                i = 0;
            }else {
                i++;
            }
        }

        System.out.println("Node prior to delete:" + node.keys);

        if(node.keys.contains(key) && node.keys.size() > 1){
            node.keys.remove(Integer.valueOf(key));
            if(node.keys.size() < (int)((double)order/2) && node.prevNode.keys.size() < (int)((double)order/2)){
                tooEmpty = true;
                //attempt merge of siblings
                //first try with previous node
                if(node.prevNode.parent.keys.get(node.prevNode.parent.keys.size()-1) < node.keys.get(0)){
                    for(int x = 0; i < node.keys.size(); i++){
                        node.prevNode.keys.add(node.keys.get(i));
                    }
                    node.keys.clear();
                }
            }

                //then try with next node
                if(node.keys.size() < (int)((double)order/2) && node.nextNode.keys.size() < (int)((double)order/2)){
                    if(node.nextNode.parent.keys.get(0) > node.keys.get(node.keys.size()-1)){
                        for(int x = 0; i < node.keys.size(); i++){
                            node.nextNode.keys.add(node.keys.get(i));
                        }
                        node.keys.clear();
                    }
                }
            System.out.println("Node after delete and/or merge:" + node.keys);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            }else{
                System.out.println("Key does not exist!");
                System.out.println();
                System.out.println();
                System.out.println();
            }
    }


    public void searchNodes(int key, String type){
        System.out.println("Searching for key " + key + " in " + type + " tree of order " + order);
        System.out.println();
        int i = 0;
        Node node = new Node(order);
        node = root;
        while(!node.isLeaf) {
            if (key < node.keys.get(i)) {
                node = node.pointers.get(i);
                i = 0;
                System.out.println(node.keys);
                System.out.println('|');
                System.out.println('v');
            } else if (key >= node.keys.get(i) && i == node.keys.size() - 1) {
                node = node.pointers.get(node.keys.size());
                i = 0;
                System.out.println(node.keys);
                System.out.println('|');
                System.out.println('v');
            } else {
                i++;
            }
        }
            if(node.keys.contains(key)){
                System.out.print("Element found at node: ");
                System.out.print(node.keys);
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
            }else{
                System.out.println("Key does not exist!");
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
            }

    }

    public void rangeSearch(int key1, int key2){
        int i = 0;
        Node node = new Node(order);
        node = root;
        while(!node.isLeaf){
            if(key1 < node.keys.get(i)){
                node = node.pointers.get(i);
                i = 0;
                System.out.println(node.keys);
                System.out.println('|');
                System.out.println('v');
            }else if(key1 >= node.keys.get(i) && i == node.keys.size()-1){
                node = node.pointers.get(node.keys.size());
                i = 0;
                System.out.println(node.keys);
                System.out.println('|');
                System.out.println('v');
            }else {
                i++;
            }
        }
            System.out.println();
            System.out.print("Nodes: ");
            while(node.nextNode.keys.get(node.nextNode.keys.size()-1) < key2) {
                System.out.print(node.keys + "-->");
                node = node.nextNode;
            }
            System.out.print(node.nextNode.keys + "   ");



    }


    public void splitNode(Node node){
        //Create 2 new nodes
        if(node.parent != null){
            if(node.parent.keys.size() == order) {
                splitNode(node.parent);
            }
        }
        Node nodeLeft = new Node(order);
        Node nodeRight = new Node(order);

        //If node to be split has pointers, assign them to new children
        if(!node.pointers.isEmpty()) {
            int tempNum = 0;
            if(order % 2 == 0){
                tempNum = (int)Math.ceil((double)order / 2) + 1;
            }else{
                tempNum = (int)Math.ceil((double)order / 2);
            }

            for (int i = 0; i < tempNum; i++) {
                nodeLeft.pointers.add(node.pointers.get(i));
                nodeLeft.pointers.get(i).parent = nodeLeft;
            }
            int y = 0;

            for (int i = tempNum; i < node.pointers.size(); i++) {
                nodeRight.pointers.add(node.pointers.get(i));
                nodeRight.pointers.get(y).parent = nodeRight;
                y++;
            }
        }

        //Assign node to be splits' keys to new children
        if(type == "dense") {
            for (int i = 0; i < (int) Math.floor((double) order / 2); i++) {
                nodeLeft.keys.add(node.keys.get(i));
            }
            for (int i = (int) Math.floor((double) order / 2); i < node.keys.size(); i++) {
                nodeRight.keys.add(node.keys.get(i));
            }
        }else if(type == "sparse"){
            for (int i = 0; i < (int) Math.floor((double) node.keys.size() / 2); i++) {
                nodeLeft.keys.add(node.keys.get(i));
            }
            for (int i = (int) Math.floor((double) node.keys.size() / 2); i < node.keys.size(); i++) {
                nodeRight.keys.add(node.keys.get(i));
            }
        }


        //Delete extraneous key in non-leaf non-root node


        if(node.isRoot) {
            nodeLeft.parent = node;
            nodeRight.parent = node;
            nodeLeft.level = 1;
            nodeRight.level = 1;
            int temp = node.keys.get((int)Math.floor(order/2));
            node.keys.clear();
            node.keys.add(temp);

            if(!node.pointers.isEmpty()) {
                node.pointers.clear();
            }
            node.pointers.add(nodeLeft);
            node.pointers.add(nodeRight);

            if(nodeLeft.pointers.isEmpty())
                nodeLeft.isLeaf = true;
            if(nodeRight.pointers.isEmpty())
                nodeRight.isLeaf = true;

            if(!nodeRight.isLeaf && nodeRight.parent.keys.contains(nodeRight.keys.get(0))){
                nodeRight.keys.remove(0);
            }
            Collections.sort(nodeRight.keys);
            Collections.sort(nodeLeft.keys);
        }else if(type == "dense") {
            //iterate down to find parent?

            nodeLeft.parent = node.parent;
            nodeRight.parent = node.parent;
            int midKey = node.keys.get((int) Math.floor(order / 2));
            //If there is room in parent, add middle element to it
            if (nodeLeft.parent.keys.size() < order) {
                nodeLeft.parent.keys.add(midKey);
                Collections.sort(nodeLeft.parent.keys);
            }

            //assign parents the correct position pointers to children
            int i = 0;
            while (nodeLeft.parent.keys.get(i) < nodeLeft.keys.get(0)) {
                i++;
            }
            int tempint = nodeLeft.parent.keys.get(i);
            int tempint2 = nodeLeft.keys.get(0);
            if(tempint == tempint2) {
                nodeLeft.parent.pointers.set(i + 1, nodeLeft);
            }else{
                nodeLeft.parent.pointers.set(i, nodeLeft);
            }

            i = 0;
            while(nodeRight.parent.keys.get(i) < nodeRight.keys.get(0)){
                i++;
            }
            //if found spot before end of pointers, add at that spot, otherwise just append
            int nodeRightPos = 0;
            if(i < nodeRight.parent.pointers.size()){
                nodeRight.parent.pointers.add(i+1, nodeRight);
                nodeRightPos = i+1;
            }else{
                nodeRight.parent.pointers.add(nodeRight);
                nodeRightPos = nodeRight.parent.pointers.size()-1;
            }
            if(nodeLeft.pointers.isEmpty())
                nodeLeft.isLeaf = true;
            if(nodeRight.pointers.isEmpty())
                nodeRight.isLeaf = true;
            if(!nodeRight.isLeaf && nodeRight.parent.keys.contains(nodeRight.keys.get(0))){
                nodeRight.keys.remove(0);
            }
            Collections.sort(nodeRight.keys);
            Collections.sort(nodeLeft.keys);

            //set next node?

        }else if(type == "sparse"){
            //iterate down to find parent?

            nodeLeft.parent = node.parent;
            nodeRight.parent = node.parent;
            int midKey = node.keys.get((int) Math.floor((double)node.keys.size() / 2));
            //If there is room in parent, add middle element to it
            if (nodeLeft.parent.keys.size() < order) {
                nodeLeft.parent.keys.add(midKey);
                Collections.sort(nodeLeft.parent.keys);
            }

            //assign parents the correct position pointers to children
            int i = 0;
            while (nodeLeft.parent.keys.get(i) < nodeLeft.keys.get(0)) {
                i++;
            }
            int tempint = nodeLeft.parent.keys.get(i);
            int tempint2 = nodeLeft.keys.get(0);
            if(tempint == tempint2) {
                nodeLeft.parent.pointers.set(i + 1, nodeLeft);
            }else{
                nodeLeft.parent.pointers.set(i, nodeLeft);
            }

            i = 0;
            while(nodeRight.parent.keys.get(i) < nodeRight.keys.get(0)){
                i++;
            }
            //if found spot before end of pointers, add at that spot, otherwise just append
            int nodeRightPos = 0;
            if(i < nodeRight.parent.pointers.size()){
                nodeRight.parent.pointers.add(i+1, nodeRight);
                nodeRightPos = i+1;
            }else{
                nodeRight.parent.pointers.add(nodeRight);
                nodeRightPos = nodeRight.parent.pointers.size()-1;
            }
            if(nodeLeft.pointers.isEmpty())
                nodeLeft.isLeaf = true;
            if(nodeRight.pointers.isEmpty())
                nodeRight.isLeaf = true;
            if(!nodeRight.isLeaf && nodeRight.parent.keys.contains(nodeRight.keys.get(0))){
                nodeRight.keys.remove(0);
            }
            Collections.sort(nodeRight.keys);
            Collections.sort(nodeLeft.keys);

            //set next node?
        }

    }


    public ArrayList<Node> getAllNodesFromLevelIntoList(Node node, int level){
        int x = 0;
        //boolean bool = true;
        while(true){
            if(node.level != level){
                for(int z = 0; z < node.pointers.size(); z++){
                    getAllNodesFromLevelIntoList(node.pointers.get(z), level);
                }
                break;
            }
            if(node.level == level){
                outputArrayList.add(node);
                break;
            }
        }
        return outputArrayList;
    }

    public void getAllNodesFromLevel(Node node, int level){
        int x = 0;
        //boolean bool = true;
        while(true){
            if(node.level != level){
                for(int z = 0; z < node.pointers.size(); z++){
                    getAllNodesFromLevel(node.pointers.get(z), level);
                }
                break;
            }
            if(node.level == level){
                outputArrayList.add(node);
                System.out.print(node.keys);
                if(level == numLevels) {
                    System.out.print("-->");
                }else{
                    System.out.print("  ");
                }
                break;
            }
        }

    }

    public void printTree(Tree tree, String type){
        //assign each level in the tree a number, starting with root at level 0, etc.
        tree.assignLevels(tree.root, 0);
        //return all nodes from given level
        tree.getAllNodesFromLevelIntoList(tree.root, tree.numLevels);
        //assign pointers of each leaf node to next leaf node in graph
        for(int i = 0; i < tree.outputArrayList.size()-1; i++) {
            tree.assignNextNodes(tree.outputArrayList.get(i), tree.outputArrayList.get(i+1));
        }
        System.out.println(type + " tree - Order " + tree.order);
        System.out.println();
        System.out.println();
        System.out.print("Root Level: ");
        tree.getAllNodesFromLevel(tree.root, 0);
        int i = 0;
        for(i = 1; i < tree.numLevels; i++){
            System.out.println();
            System.out.print("Level " + i + ": ");
            tree.getAllNodesFromLevel(tree.root, i);
        }

        System.out.println();
        System.out.print("Level " + i + ": ");
        tree.getAllNodesFromLevel(tree.root, i);
        for(int x = 0; x < 10; x ++){
            System.out.println();
        }

    }

    public void assignNextNodes(Node node1, Node node2){
        node1.nextNode = node2;
        node2.prevNode = node1;
    }

    public void assignLevels(Node node,int level){
        boolean bool = true;
        int x = 0;
        while(bool){
            if (node.pointers == null || node.pointers.size() == 0) {
                bool = false;
                break;
            }
            if(x == node.pointers.size()){
                for(int z = 0; z < node.pointers.size(); z++) {
                    if(level >= numLevels){
                        numLevels = level + 1;
                    }
                    assignLevels(node.pointers.get(z), level + 1);
                }
                break;
            }
            node.pointers.get(x).level = level + 1;
            x++;
        }



    }

}
