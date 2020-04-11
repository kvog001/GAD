package NeatMauba;

import java.util.ArrayList;

/**
 * Diese Klasse implementiert einen (a,b)-Baum.
 */
public class ABTree {

    /**
     * Diese Variable speichert die untere Schranke des Knotengrades.
     */
    private final int a;

    /**
     * Diese Variable speichert die obere Schranke des Knotengrades.
     */
    private final int b;

    /**
     * Diese Objektvariable speichert die Wurzel des Baumes.
     */
    private ABTreeInnerNode root;

    public ABTree(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public boolean validAB() {
        return true;
//        return root.validAB(true);
    }


    public int leftMostKey(int key){
        return root.getLeftMostKey(key);
    }
    public int rightMostKey(int key){
        return root.getRightMostKey(key);
    }


    /**
     * Diese Methode ermittelt die Höhe des Baumes.
     *
     * @return die ermittelte Höhe
     */
    public int height() {
        return root.height();
    }

    /**
     * Diese Methode sucht einen Schlüssel im (a,b)-Baum.
     *
     * @param key der Schlüssel, der gesucht werden soll
     * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
     */
    public boolean find(int key) {
        return root.find(key);
    }

    /**
     * Diese Methode fügt einen neuen Schlüssel in den (a,b)-Baum ein.
     *
     * @param key der einzufügende Schlüssel
     */
    public void insert(int key) {
        if (root == null){
            root = new ABTreeInnerNode(key,a,b);
            return;
        }

        if ( find(key))
            return;

        ABTreeInnerNode actualNode = root.insert(key,true);

        //split nodes
        //until d(v) <= b
        while (actualNode.getKeys().size() > (b-1)){

            actualNode = actualNode.split();

            if (actualNode.getParent() == null)
                root = actualNode;

        }

    }

    /**
     * Diese Methode löscht einen Schlüssel aus dem (a,b)-Baum.
     *
     * @param key der zu löschende Schlüssel
     * @return 'true' falls der Schlüssel gefunden und gelöscht wurde, 'false' sonst
     */
    public boolean remove(int key) {
        //find node which contains the key to be deleted
        ABTreeInnerNode locatedNode = root.locateKey(key);

        if (locatedNode == null){ //key not found
            return false;
        }

        else if(locatedNode  == root && root.getChildren().get(0) instanceof ABTreeLeaf && root.getKeys().size() == 1){
            root = null;
        }

        else{ //key found, to be removed

            //locatedNode is not a leaf so swap key we want to delete with rightMostKey in left Subtree
            ABTreeInnerNode leafNode = null;
            if ( !locatedNode.isLeaf() ){
                ABTreeInnerNode rightMostNode = locatedNode.getRightMostNode(key);
                leafNode = swap(rightMostNode,locatedNode,key);

            } else{
                leafNode = locatedNode;
            }

            leafNode.deleteKey(key);
            leafNode.decreaseLeafNumber();

            if (leafNode.validAfterDeletion())
                return true;


            //steal left or right if possible
            //otherwise merge and propagate up
            if (leafNode.canStealLeft()){
                leafNode.stealLeft();

            } else if (leafNode.canStealRight()){
                leafNode.stealRight();

            } else /* merge and propagate up */{
                while (!leafNode.validAfterDeletion()){

                    if (leafNode.canStealLeft()){
                        if (leafNode.isLeaf())
                            leafNode.stealLeft();
                        else
                            leafNode.stealSubtreeLeft();

                        return true;

                    } else if(leafNode.canStealRight()){
                        if (leafNode.isLeaf())
                            leafNode.stealRight();
                        else
                            leafNode.stealSubtreeRight();

                        return true;
                    }


                    if (leafNode.getParent() == null || leafNode.getParent().getKeys().size() == 0){
                        root = leafNode;
                        return true;
                    }


                    if (leafNode.hasRightNeighbour()){
                        leafNode = leafNode.mergeWithRightLeaf();
                    }else{
                        leafNode = leafNode.mergeWithLeftLeaf();
                    }

                    if (leafNode.getParent() == null || leafNode.getParent().getKeys().size() == 0){
                        root = leafNode;
                        return true;
                    }

                    leafNode = leafNode.getParent();

                }
            }


        }
        return true;
    }

    private ABTreeInnerNode swap(ABTreeInnerNode predecessor, ABTreeInnerNode locatedNode, int keyToBeDeleted){
        int rightMostKey = locatedNode.getRightMostKey(keyToBeDeleted);
        predecessor.addKey(keyToBeDeleted);
        predecessor.deleteKey(rightMostKey);

        locatedNode.insertInorder(rightMostKey);
        locatedNode.deleteKey(keyToBeDeleted);

        return predecessor;
    }

    /**
     * Diese Methode wandelt den Baum in das Graphviz-Format um.
     *
     * @return der Baum im Graphiz-Format
     */
    String dot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph {\n");
        sb.append("\tnode [shape=record];\n");
        if (root != null)
            root.dot(sb, 0);
        sb.append("}");
        return sb.toString();
    }
}
