//package Mauba;
//
//import java.util.ArrayList;
//
///**
// * Diese Klasse implementiert einen (a,b)-Baum.
// */
//public class ABTree {
//
//    /**
//     * Diese Variable speichert die untere Schranke des Knotengrades.
//     */
//    private final int a;
//
//    /**
//     * Diese Variable speichert die obere Schranke des Knotengrades.
//     */
//    private final int b;
//
//    /**
//     * Diese Objektvariable speichert die Wurzel des Baumes.
//     */
//    private ABTreeInnerNode root;
//
//    public ABTree(int a, int b) {
//        this.a = a;
//        this.b = b;
//    }
//
//    public boolean validAB() {
//        return true;
////        return root.validAB(true);
//    }
//
//    /**
//     * Diese Methode ermittelt die Höhe des Baumes.
//     *
//     * @return die ermittelte Höhe
//     */
//    public int height() {
//        return root.height();
//    }
//
//    /**
//     * Diese Methode sucht einen Schlüssel im (a,b)-Baum.
//     *
//     * @param key der Schlüssel, der gesucht werden soll
//     * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
//     */
//    public boolean find(int key) {
//        return root.find(key);
//    }
//
//    /**
//     * Diese Methode fügt einen neuen Schlüssel in den (a,b)-Baum ein.
//     *
//     * @param key der einzufügende Schlüssel
//     */
//    public void insert(int key) {
//        if (root == null){
//            root = new ABTreeInnerNode(key,a,b);
//            return;
//        }
//
//        ABTreeInnerNode actualNode = root.insert(key,true);
//        //split nodes
//        //until d(v) <= b
//        while (actualNode.getKeys().size() > (b-1)){
//            actualNode = actualNode.splitAndMerge(actualNode.getParent());
//
//            if (actualNode.getParent() == null)
//                root = actualNode;
//        }
//
//    }
//
//    public int rightMost(int key){
//        return root.getRightMostKey(key);
//    }
//
//    public int leftMost(int key){
//        return root.getLeftMostKey(key);
//    }
//
//
//    /**
//     * Diese Methode löscht einen Schlüssel aus dem (a,b)-Baum.
//     *
//     * @param key der zu löschende Schlüssel
//     * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false' sonst
//     */
//    public boolean remove(int key) {
//        //find node which contains the key to be deleted
//        ABTreeInnerNode locatedNode = root.locateKey(key);
//
//        if (locatedNode == null){ //key not found
//            return false;
//
//        }
//
//        else{ //key found, to be removed
//
//            //deletion on a leaf node
//            if (locatedNode.getChildren().get(0) instanceof ABTreeLeaf){
//
//                //case 0: tree remains balanced after deletion
//                if (locatedNode.getKeys().size() >= a){
//                    locatedNode.getKeys().remove((Integer) key);
//                    locatedNode.getChildren().remove(0);
//
//                } else if(locatedNode.getKeys().size() < a){ //case 2: Subcase1
//                        if (locatedNode.getParent().getKeys().size() >= a){
//                            if (locatedNode.canStealLeft()){
//                                locatedNode.stealLeft(key);
//
//                            } else if(locatedNode.canStealRight()){
//                                locatedNode.stealRight(key); //todo inspect/test this method
//
//                            } else if (!locatedNode.canSteal()){ //Szzbcase 2
//                                //cannot steal left or right
//                                //merge with right neighbour if it exists, otherwise left neighbour
//
//                                //todo remove and replace with key from parent
//                                int keyPosition = locatedNode.getKeys().indexOf(key);
//                                int childPosition = locatedNode.getParent().getChildren().indexOf(locatedNode);
//                                int parentKey = locatedNode.getParent().getKeys().get(childPosition-1);
//                                locatedNode.getParent().getKeys().remove(parentKey);
//                                //parentKeyPos--;
//                                locatedNode.getKeys().add(keyPosition,parentKey);
//                                locatedNode.getKeys().remove(key);
//                                //int parentKeyPos = childPosition-2;
//
//                                if (locatedNode.hasRightNeighbour()){
//                                    ABTreeInnerNode rightNeighbour = (ABTreeInnerNode) locatedNode.getParent().getChildren().get(childPosition+1);
//
//                                    ABTreeInnerNode mergedNode = mergeWithRightNeigbour(locatedNode,rightNeighbour);
//                                    //todo remove two children and add the result of merge as new one
//                                    //todo add new child to parentKeyPos+1 ?? check if right but idea is correct i think
//                                    locatedNode.getParent().getChildren().add(childPosition,mergedNode);
//                                    mergedNode.setParent(locatedNode.getParent());
//
//                                    locatedNode.getParent().getChildren().remove(mergedNode.getPosition()+1);
//                                    locatedNode.getParent().getChildren().remove(mergedNode.getPosition()-1);
//
//                                }
//
//                                else{
//                                    ABTreeInnerNode leftNeighbour = (ABTreeInnerNode) locatedNode.getParent().getChildren().get(childPosition-1);
//                                    ABTreeInnerNode mergedNode = mergeWithLeftNeigbour(locatedNode,leftNeighbour);
//                                    //todo the same like above
//                                    locatedNode.getParent().getChildren().add(childPosition,mergedNode);
//                                    mergedNode.setParent(locatedNode.getParent());
//
//                                    locatedNode.getParent().getChildren().remove(mergedNode.getPosition()+1);
//                                    locatedNode.getParent().getChildren().remove(mergedNode.getPosition()-1);
//
//                                }
//
//                            }
//
//                        }
//
//                        else /* parent could also get unbalanced with a > keysSize */{ //CASE 3
//                            //what if canSteal from Neighbour is true
//                            //take into consideration both cases
//
//                                if (locatedNode.canStealLeft()){
//                                    locatedNode.stealLeft(key);
//                                } else if (locatedNode.canStealRight()){
//                                    locatedNode.stealRight(key);
//                                }
//
//                                else {
//                                    int childPos = locatedNode.getPosition();
//                                    int keyPos = locatedNode.getKeyPosition(key);
//
//                                    //careful: this case child is leftChild otherwise use get(childPos + 1)
//                                    //resolve this problem in other use cases
//
//                                    //bring down parent key
//                                    int parentKey = 0;
//                                    if (locatedNode.hasRightNeighbour()){
//                                        parentKey = locatedNode.getParent().getKeys().get(childPos);
//                                    } else{
//                                        parentKey = locatedNode.getParent().getKeys().get(childPos-1);
//                                    }
//                                    locatedNode.getParent().getKeys().remove((Integer)parentKey);
//                                    locatedNode.getKeys().add(keyPos,parentKey);
//                                    locatedNode.getKeys().remove((Integer)key);
//
//
//                                    //merge left or right : leaf level
//                                    int childPosition = locatedNode.getPosition();
//                                    ABTreeInnerNode mergedNode = null;
//                                    if (locatedNode.hasRightNeighbour()){
//                                        ABTreeInnerNode rightNeighbour = (ABTreeInnerNode) locatedNode.getParent().getChildren().get(childPosition+1);
//                                        mergedNode = mergeWithRightNeigbour(locatedNode,rightNeighbour);
//                                    }else{
//                                        ABTreeInnerNode leftNeighbour = (ABTreeInnerNode) locatedNode.getParent().getChildren().get(childPosition-1);
//                                        mergedNode = mergeWithRightNeigbour(locatedNode,leftNeighbour);
//
//                                    }
//                                    locatedNode.getParent().getChildren().add(childPosition,mergedNode);
//                                    mergedNode.setParent(locatedNode.getParent());
//
//
//                                    //if parent becomes disbalanced
//                                    ABTreeInnerNode disbalancedParent = mergedNode.getParent();
//
//                                    locatedNode.getParent().getChildren().remove(mergedNode.getPosition()+1);
//                                    locatedNode.getParent().getChildren().remove(mergedNode.getPosition()-1);
//
//                                    //todo revise; did i delete the locatedNode, since i merged it and returned the merged one
//                                    //and only that exists in the parent node
//                                    while (disbalancedParent.getKeys().size() < a){
//
//                                        if (disbalancedParent.canStealLeft()){
//                                            disbalancedParent.stealLeft(key);
//                                        } else if (disbalancedParent.canStealRight()){
//                                            disbalancedParent.stealRight(key);
//
//                                        } else {//can only merge
//                                            int parentKeyOfParent = disbalancedParent.getParent().getKeys().get(disbalancedParent.getPosition()-1);//todo
//                                            disbalancedParent.getParent().getKeys().remove((Integer)parentKeyOfParent);
//
//
//                                            disbalancedParent.insert(parentKeyOfParent,false);
//                                            ABTreeInnerNode mergedInnerNode = null, rightNeighbour = null, leftNeighbour = null;
//
//                                            if (disbalancedParent.hasRightNeighbour()){
//                                                rightNeighbour = (ABTreeInnerNode) disbalancedParent.getParent().getChildren().get(disbalancedParent.getPosition()+1);
//                                                mergedInnerNode = mergeTwoInnerNodesR(disbalancedParent,rightNeighbour);
//                                            }else{
//                                                leftNeighbour = (ABTreeInnerNode) disbalancedParent.getParent().getChildren().get(disbalancedParent.getPosition()-1);
//                                                mergedInnerNode = mergeTwoInnerNodesL(disbalancedParent,leftNeighbour);
//
//                                            }
//
//                                            if (disbalancedParent.getParent().getKeys().size() == 0){ //reached root
//                                                root = mergedInnerNode;
//                                                break;
//                                            } else { //can go further up
//                                                int nodePos = disbalancedParent.getPosition();
//
//                                                if (disbalancedParent.hasRightNeighbour()){
//                                                    disbalancedParent.getParent().getChildren().remove(rightNeighbour);
//                                                    disbalancedParent.getParent().getChildren().remove(disbalancedParent);
//
//                                                }else{
//                                                    disbalancedParent.getParent().getChildren().remove(leftNeighbour);
//                                                    disbalancedParent.getParent().getChildren().remove(disbalancedParent);
//                                                }
//                                                disbalancedParent = disbalancedParent.getParent();
//
//                                                disbalancedParent.getChildren().add(nodePos-1,mergedInnerNode);
//
//                                                mergedInnerNode.setParent(disbalancedParent);
//
//                                                if (disbalancedParent.getParent() == null && disbalancedParent.getKeys().size() < a){
//                                                    //todo do smth
//                                                    throw new RuntimeException("this case isn´t handled!!");
//                                                }
//                                            }
//
//
//
//                                        }
//
//
//                                    }
//                                }
//
//                        }
//                }
//
//
//            } else { //located node is not a leaf node
//
//                    ABTreeInnerNode rightMostNode = locatedNode.getRightMostNode(key); //left Subtree
//                    ABTreeInnerNode leftMostNode = locatedNode.getLeftMostNode(key); //right Subtree
//                    if (locatedNode.getKeys().size() >= a){ //inner node where deletion can happen easily
//
//                        //case 1: we can easily swap and delete
//                        if (rightMostNode.getKeys().size() >= a){ //swap rightMostKey with Parent key we want to delete
//                            int rightMostKey = locatedNode.getRightMostKey(key);
//
//                            int parentKeyLocation = locatedNode.getKeys().indexOf(key);
//                            locatedNode.getKeys().add(parentKeyLocation,rightMostKey);
//                            locatedNode.getKeys().remove(parentKeyLocation+1);
//
////                            locatedNode.getKeys().remove((Integer)key);
////                            locatedNode.insert(rightMostKey,false);
//                            rightMostNode.getKeys().remove((Integer)rightMostKey);
//                            rightMostNode.getChildren().remove(0);
//                        }
//                        else if(leftMostNode.getKeys().size() >= a){ //swap leftMostKey with parent
//                            int leftMostKey = locatedNode.getLeftMostKey(key);
//                            int parentKeyLocation = locatedNode.getKeys().indexOf(key);
//                            locatedNode.getKeys().add(parentKeyLocation,leftMostKey);
//                            locatedNode.getKeys().remove(parentKeyLocation+1); //remove shifted @key
//
//                            leftMostNode.getKeys().remove((Integer)leftMostKey);
//                            leftMostNode.getChildren().remove(0);
//                        }
//                        else {
////                            throw new RuntimeException("error not handled");
//                            //merge children and check parent
//
////                            int pos = locatedNode.getPosition();
////                            ABTreeInnerNode leftChild = (ABTreeInnerNode) locatedNode.getChildren().get(pos);
////                            ABTreeInnerNode rightChild = (ABTreeInnerNode)locatedNode.getChildren().get(pos+1);
//
//                            ABTreeInnerNode leftSubtree = locatedNode.getRightMostNode(key); //leftSubtree
//                            ABTreeInnerNode rightSubtree = locatedNode.getLeftMostNode(key); //rightSubtree
//                            //todo delete keys and children at these subtrees !
//
//                            locatedNode.getKeys().remove((Integer)key);
//
//                            ABTreeInnerNode mergedChildren = mergeChildrenIntoOne(leftSubtree, rightSubtree);
//
//
//                        }
//                        //todo borrow and merge???
//                    }
//
//                    else {
//                        //TODO handle this shi+
//                        //BEispiel GAD seite 387 ...remove(19)
//                        if (rightMostNode.getKeys().size() >= a){ //swap rightMostKey with Parent key we want to delete
//                            int rightMostKey = locatedNode.getRightMostKey(key);
//                            int parentKeyLocation = locatedNode.getKeys().indexOf(key);
//                            locatedNode.getKeys().add(parentKeyLocation,rightMostKey);
//                            //todo replace 304-305 with: locatedNode.insert(rightMostKey);
//                            locatedNode.getKeys().remove(parentKeyLocation+1);
//
//                        } else if(leftMostNode.getKeys().size() >= a){ //swap leftMostKey with parent
//                            int leftMostKey = locatedNode.getLeftMostKey(key);
//                            int parentKeyLocation = locatedNode.getKeys().indexOf(key);
//                            locatedNode.getKeys().add(parentKeyLocation,leftMostKey);
//                            locatedNode.getKeys().remove(parentKeyLocation+1); //remove shifted @key
//                        }
//
//                        else{
//                            //let the magic happen
//                            //borrow and merge motherfuckers
//
////                            ABTreeInnerNode mergedKids = mergeWithLeftNeigbour();
////                            locatedNode.remove();
////                            locatedNode.remove();
//                            throw new RuntimeException("case not handled!!!!!!!!!!");
//
//                        }
//
//                    }
//
//
//            }
//
//
//
//        }
//        return true;
//    }
//
//    private ABTreeInnerNode mergeWithRightNeigbour(ABTreeInnerNode locatedNode, ABTreeInnerNode rightNeighbour){
//
//        if (locatedNode.getChildren().get(0) instanceof ABTreeInnerNode || rightNeighbour.getChildren().get(0) instanceof ABTreeInnerNode)
//            throw new RuntimeException("this merge operation is designed for leaf nodes only");
//
//        ArrayList<Integer> neighbourKeys = rightNeighbour.getKeys();
//        ArrayList<Integer> locatedNodeKeys = locatedNode.getKeys();
//        ArrayList<Integer> mergedKeys =  locatedNodeKeys;
//        mergedKeys.addAll(locatedNodeKeys.size(),neighbourKeys);
//
//        ArrayList<ABTreeNode> mergedChildren = new ArrayList<>();
//        for (int i = 0; i < mergedKeys.size()+1; i++) {
//            mergedChildren.add(i,new ABTreeLeaf(a,b));
//        }
//
//        return new ABTreeInnerNode(mergedKeys,mergedChildren,a,b);
//    }
//
//    private ABTreeInnerNode mergeWithLeftNeigbour(ABTreeInnerNode locatedNode, ABTreeInnerNode leftNeighbour){
//
//        if (locatedNode.getChildren().get(0) instanceof ABTreeInnerNode || leftNeighbour.getChildren().get(0) instanceof ABTreeInnerNode)
//            throw new RuntimeException("this merge operation is designed for leaf nodes only");
//
//
//        //todo remember: the new merged will have one leaf less as a child, so remove one child
//        ArrayList<Integer> neighbourKeys = leftNeighbour.getKeys();
//        ArrayList<Integer> locatedNodeKeys = locatedNode.getKeys();
//        ArrayList<Integer> mergedKeys =  neighbourKeys;
//        mergedKeys.addAll(mergedKeys.size(),locatedNodeKeys); //TODO check index of adding and order !!
//        ArrayList<ABTreeNode> mergedChildren = new ArrayList<>();
//        for (int i = 0; i < mergedKeys.size()+1; i++) {
//            mergedChildren.add(i,new ABTreeLeaf(a,b));
//        }
//
//        return new ABTreeInnerNode(mergedKeys,mergedChildren,a,b);
//    }
//
//    private ABTreeInnerNode mergeTwoInnerNodesR(ABTreeInnerNode disbalancedParent, ABTreeInnerNode right){
//        ArrayList<Integer> rightNeighbourKeys = right.getKeys();
//        ArrayList<Integer> disbalancedParentKeys = disbalancedParent.getKeys();
//        ArrayList<Integer> mergedKeys = disbalancedParentKeys;
//        mergedKeys.addAll(disbalancedParentKeys.size(),rightNeighbourKeys);
//
//        ArrayList<ABTreeNode> rightNeighboursChildren = right.getChildren();
//        ArrayList<ABTreeNode> disbalancedParentsChildren = disbalancedParent.getChildren();
//        ArrayList<ABTreeNode> mergedChildren = disbalancedParentsChildren;
//        mergedChildren.addAll(mergedChildren.size(), rightNeighboursChildren);
//
//        return new ABTreeInnerNode(mergedKeys,mergedChildren,a,b);
//    }
//
//    private ABTreeInnerNode mergeTwoInnerNodesL(ABTreeInnerNode disbalancedParent, ABTreeInnerNode left){
//        ArrayList<Integer> leftNeighbourKeys = left.getKeys();
//        ArrayList<Integer> disbalancedParentKeys = disbalancedParent.getKeys();
//        ArrayList<Integer> mergedKeys = leftNeighbourKeys;
//        mergedKeys.addAll(mergedKeys.size(),disbalancedParentKeys);
//
//        ArrayList<ABTreeNode> leftNeighboursChildren = left.getChildren();
//        ArrayList<ABTreeNode> disbalancedParentsChildren = disbalancedParent.getChildren();
//        ArrayList<ABTreeNode> mergedChildren = leftNeighboursChildren;
//        mergedChildren.addAll(mergedChildren.size(), disbalancedParentsChildren);
//
//        return new ABTreeInnerNode(mergedKeys,mergedChildren,a,b);
//    }
//
//    private ABTreeInnerNode mergeChildrenIntoOne(ABTreeInnerNode leftChild, ABTreeInnerNode rightChild){
//
//        return null;
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
//        if (root != null)
//            root.dot(sb, 0);
//        sb.append("}");
//        return sb.toString();
//    }
//}
