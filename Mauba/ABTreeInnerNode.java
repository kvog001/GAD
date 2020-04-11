//package Mauba;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Diese Klasse repräsentiert einen inneren Knoten des Baumes.
// */
//public class ABTreeInnerNode extends ABTreeNode {
//    private ArrayList<Integer> keys;
//    private ArrayList<ABTreeNode> children;
//
//    private ABTreeInnerNode parent;
//    private int location = 0;
//
//    public ABTreeInnerNode(ArrayList<Integer> keys, ArrayList<ABTreeNode> children, int a, int b) {
//        super(a, b);
//        this.keys = keys;
//        this.children = children;
//    }
//
//    public ABTreeInnerNode(int key, ABTreeNode left, ABTreeNode right, int a, int b) {
//        super(a, b);
//        keys = new ArrayList<>();
//        children = new ArrayList<>();
//        keys.add(key);
//        children.add(left);
//        children.add(right);
//    }
//
//    public ABTreeInnerNode(int key, int a, int b) {
//        this(key, new ABTreeLeaf(a, b), new ABTreeLeaf(a, b), a, b);
//    }
//
//    /**
//     * for the moment: locates the node and adds key to existing keys
//     * @param key der Schlüssel, der eingefügt wird
//     * @return den Knoten, in dem der Schlüssel eingefügt wurde
//     */
//    @Override
//    public ABTreeInnerNode insert(int key,boolean addChild) {
//        if (this.children.get(0) instanceof ABTreeLeaf){
//
//            if (key <= this.min()){ //if key already smallest, add it in the beginning
//                keys.add(0,key);
//                if (addChild){
//                    children.add(0,new ABTreeLeaf(a,b));
//                }
//
//            } else if( key > this.max()){ //if key already biggest add it in the end
//                keys.add(key);
//                if (addChild){
//                    children.add(new ABTreeLeaf(a,b));
//                }
//
//            } else{ //otherwise add between min and max of all keys
//                for (int i = 0; i < this.keys.size(); i++) {
//                    if (key <= keys.get(i)){
//                        keys.add(i,key);
//                        if (addChild){
//                            children.add(i,new ABTreeLeaf(a,b));
//                        }
//                        break;
//                    }
//                }
//            }
//
//        } else{
//            //locate and
//            //call insert on the child tree
//            if (key <= this.min()){ //if key already smallest, call insert on first child
//                  return this.getChildren().get(0).insert(key,addChild);
//
//            } else if( key > this.max()){ //if key already biggest call insert on last child
//                 return this.getChildren().get(children.size()-1).insert(key,addChild);
//
//            } else{ //otherwise add between min and max of all keys
//                for (int i = 0; i < this.keys.size(); i++) {
//                    if (key <= keys.get(i)){
//                        return this.getChildren().get(i).insert(key,addChild);
//                    }
//                }
//            }
//        }
//
//        return this;
//    }
//
//    private ABTreeNode insertIterative(int key){
////TODO
//        return new ABTreeInnerNode(key,a,b);
//    }
//
//    public ABTreeInnerNode splitAndMerge(ABTreeInnerNode parent) {
//        int splitterIndex = (int) Math.floor(b/2);
//        int splitter = keys.get(splitterIndex);
//
//        ArrayList<Integer> leftKeys = new ArrayList<>(keys.subList(0,splitterIndex));
//        ArrayList<ABTreeNode> leftChildren = new ArrayList<>(children.subList(0,splitterIndex+1));
//        ABTreeInnerNode leftNode = new ABTreeInnerNode( leftKeys, leftChildren, a, b);
//
//        ArrayList<Integer> rightKeys = new ArrayList<>(keys.subList(splitterIndex+1,keys.size()));
//        ArrayList<ABTreeNode> rightChildren = new ArrayList<>(children.subList(splitterIndex+1,children.size()));
//        ABTreeInnerNode rightNode = new ABTreeInnerNode( rightKeys, rightChildren, a, b);
//
//        if (parent != null){
//            //merge with parent
//            int index = parent.getChildren().indexOf(this);
//            parent.getKeys().add(index,splitter);
//            parent.getChildren().add(index,rightNode);
//            parent.getChildren().add(index,leftNode);
//            parent.getChildren().remove(this);
//            rightNode.setParent(parent);
//            leftNode.setParent(parent);
//
//            return parent;
//
//        } else {
//            ABTreeInnerNode newNode = new ABTreeInnerNode(splitter,leftNode,rightNode, a, b);
//            leftNode.setParent(newNode);
//            rightNode.setParent(newNode);
//            return newNode;
//        }
//    }
//
//    @Override
//    public boolean canSteal() {
//        location = parent.getChildren().indexOf(this);
//        if (location == 0){
//            //can only steal from right neighbour
//            if (parent.getChildren().get(1) instanceof ABTreeInnerNode){
//                if (( (ABTreeInnerNode) parent.getChildren().get(1)).getKeys().size() >= a){
//                    return true;
//                }
//            }
//
//        } else if(location > 0){
//            //first check left then right
//            if (( (ABTreeInnerNode) parent.getChildren().get(location-1)).getKeys().size() >= a){
//                return true;
//            }
//
//            //check right
//            if (( (ABTreeInnerNode) parent.getChildren().get(1)).getKeys().size() >= a){
//                return true;
//            }
//
//        }
//
//        return false;
//    }
//
//    public boolean canStealLeft(){
//        location = parent.getChildren().indexOf(this);
//        if (location > 0 && ( (ABTreeInnerNode) parent.getChildren().get(location-1)).getKeys().size() >= a){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean canStealRight(){
//        location = parent.getChildren().indexOf(this);
//        if (( (ABTreeInnerNode) parent.getChildren().get(1)).getKeys().size() >= a){
//            return true;
//        }
//        return false;
//    }
//
//    public void stealLeft(int key){
//        int pos = parent.getChildren().indexOf(this);
//        ABTreeInnerNode leftNeighbour = (ABTreeInnerNode) parent.getChildren().get(pos-1);
//        int stolenKey = leftNeighbour.getKeys().get(leftNeighbour.getKeys().size()-1);
//
//        leftNeighbour.getKeys().remove((Integer) stolenKey);
//        leftNeighbour.getChildren().remove(0);
//        int parentKey = parent.getKeys().get(pos-1);
//
//        parent.getKeys().remove((Integer)parentKey);
//        parent.getKeys().add(pos-1,stolenKey);
//
//        this.getKeys().add(keys.indexOf(key),parentKey);
//        this.getKeys().remove((Integer) key);
//    }
//
//    public void stealRight(int key){
//        int pos = parent.getChildren().indexOf(this);
//        ABTreeInnerNode rightNeighbour = (ABTreeInnerNode) parent.getChildren().get(pos+1);
//        int stolenKey = rightNeighbour.getKeys().get(0);
//
//        rightNeighbour.getKeys().remove((Integer) stolenKey);
//        rightNeighbour.getChildren().remove(0);
//
//        int parentKey = parent.getKeys().get(pos);
//
//        parent.getKeys().remove((Integer) parentKey);
//        parent.getKeys().add(pos-1,stolenKey);
//
//        this.getKeys().add(keys.indexOf(key),parentKey);
//        this.getKeys().remove((Integer) key);
//    }
//
//    public boolean hasRightNeighbour(){
//        int pos = parent.getChildren().indexOf(this);
//        if (pos == parent.getChildren().size() -1){
//            return false;
//        } else
//            return true;
//    }
//
//
//
//    @Override
//    public boolean find(int key) {
//        if(this.getKeys().contains(key))
//            return true;
//        if (this.getChildren().get(0) instanceof ABTreeLeaf){
//            return false;
//        }
//
//
//        for (int i = 0; i < keys.size(); i++) {
//            if (key < keys.get(i)){
//                return children.get(i).find(key);
//            }
//
//            if(i == keys.size()-1 && key > keys.get(i)){
//                return children.get(i+1).find(key);
//            }
//        }
//
//        return false;
//    }
//
//    public boolean remove(int key) {
//        if (this.keys.contains(key)){
//            this.keys.remove(key);
//            //todo remove child [LeafNode==true] as well ?
//            return true;
//        } else
//            return false;
//    }
//
//
//    public int getRightMostKey(int key){
//        int result = getRightMostNode(key).getKeys().get(getRightMostNode(key).getKeys().size()-1);
////        getRightMostNode(key).getKeys().remove(getRightMostNode(key).getKeys().size()-1);
////        getRightMostNode(key).getChildren().remove(0);
//        return result;
//    }
//
//    //prerequisite: key(that we want to delete afterwards / replace wth) must exist in the keys list
//    public ABTreeInnerNode getRightMostNode(int key){
//        if (this.children.get(0) instanceof ABTreeLeaf)
//            throw new RuntimeException("can not call RightMostKey on leaf node");
//        int index = this.keys.indexOf(key);
//        ABTreeInnerNode leftSubtree = (ABTreeInnerNode) children.get(index);
//        return getNodeWithRightMostKey(leftSubtree,key);
//    }
//
//    //return Node with Right-most-Key from the left Subtree
//    private ABTreeInnerNode getNodeWithRightMostKey(ABTreeInnerNode child,int key){
//        if (child.children.get(child.children.size()-1) instanceof ABTreeLeaf){
////            child.getKeys().remove((Integer) key);
////            child.getChildren().remove(child.children.size()-1);
//            return child;
//        } else {
//            child.getNodeWithRightMostKey((ABTreeInnerNode) child.getChildren().get(child.children.size()-1),key);
//        }
//        return child;
//    }
//
//
//
//    public int getLeftMostKey(int key){
//        int result = getLeftMostNode(key).getKeys().get(0);
////        getLeftMostNode(key).getKeys().remove(0);
////        getLeftMostNode(key).getChildren().remove(0);
//        return result;
//    }
//
//    public ABTreeInnerNode getLeftMostNode(int key){
//        if (this.children.get(0) instanceof ABTreeLeaf)
//            throw new RuntimeException("can not call RightMostKey on leaf node");
//        int index = this.keys.indexOf(key);
//        ABTreeInnerNode rightSubtree = (ABTreeInnerNode) children.get(index+1);
//        return getNodeWithLeftMostKey(rightSubtree,key);
//    }
//
//    private ABTreeInnerNode getNodeWithLeftMostKey(ABTreeInnerNode child, int key){
//        if (child.children.get(0) instanceof ABTreeLeaf){
////            child.getKeys().remove(key);
////            child.getChildren().remove(child.children.size()-1);
//            return child;
//        } else {
//            child.getNodeWithLeftMostKey((ABTreeInnerNode) child.getChildren().get(0),key);
//        }
//        return child;
//    }
//
//    //returns node which contains key
//    public ABTreeInnerNode locateKey(int key){
//        if(this.getKeys().contains(key))
//            return this;
//        if (this.getChildren().get(0) instanceof ABTreeLeaf){
//            return null;
//        }
//
//        for (int i = 0; i < keys.size(); i++) {
//            if (key < keys.get(i)){
//                return ((ABTreeInnerNode)children.get(i)).locateKey(key);
//            }
//
//            if(i == keys.size()-1 && key > keys.get(i)){
//                return ((ABTreeInnerNode)children.get(i+1)).locateKey(key);
//            }
//        }
//
//        return null;
//    }
//
//
//    @Override
//    public int height() {
//        if (this.getChildren().get(0) instanceof ABTreeLeaf)
//            return 0;
//        else
//            return 1 + this.getChildren().get(0).height();
//    }
//
//    @Override
//    public Integer min() {
//        return this.keys.get(0);
//    }
//
//    @Override
//    public Integer max() {
//        return this.keys.get( keys.size()-1);
//    }
//
//    @Override
//    public boolean validAB(boolean root) {
//        if (a < 2 || b < (2*a)-1)
//            return false;
//        if (children.size() > keys.size())
//            return false;
//        if (children.size() < a || children.size() > b)
//            return false;
//        for (ABTreeNode child : children) {
//            //Todo in ABTree class
//        }
//        return true;
//    }
//
//    //--------getters and setters
//
//    public ArrayList<ABTreeNode> getChildren() {
//        return children;
//    }
//
//    public ArrayList<Integer> getKeys() {
//        return keys;
//    }
//
//    public void setChildren(ArrayList<ABTreeNode> children) {
//        this.children = children;
//    }
//
//    public void setKeys(ArrayList<Integer> keys) {
//        this.keys = keys;
//    }
//
//    public void removeChild(int index){
//        children.remove(index); //returns removed child if needed
//    }
//
//    public void setParent(ABTreeInnerNode parent){
//        this.parent = parent;
//    }
//
//    public ABTreeInnerNode getParent() {
//        return parent;
//    }
//
//    public int getLocation() {
//        return location;
//    }
//
//    public int getPosition(){
//        return this.parent.getChildren().indexOf(this);
//    }
//
//    public int getKeyPosition(int key){
//        return this.keys.indexOf(key);
//    }
//
//    //-------END
//
//
//    @Override
//    public int dot(StringBuilder sb, int from) {
//        int mine = from++;
//        sb.append(String.format("\tstruct%s [label=\"", mine));
//        for (int i = 0; i < 2 * keys.size() + 1; i++) {
//            if (i > 0)
//                sb.append("|");
//            sb.append(String.format("<f%d> ", i));
//            if (i % 2 == 1)
//                sb.append(keys.get(i / 2));
//        }
//        sb.append("\"];\n");
//        for (int i = 0; i < children.size(); i++) {
//            int field = 2 * i;
//            sb.append(String.format("\tstruct%d:<f%d> -> struct%d;\n", mine, field, from));
//            from = children.get(i).dot(sb, from);
//        }
//        return from;
//    }
//
//    /**
//     * Diese Methode wandelt den Baum in das Graphviz-Format um.
//     *
//     * @return der Baum im Graphiz-Format
//     */
//    String dot() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("digraph {\n");
//        sb.append("\tnode [shape=record];\n");
//        if (this != null)
//            this.dot(sb, 0);
//        sb.append("}");
//        return sb.toString();
//    }
//}
