package Avella;

/**
 * Diese Klasse implementiert einen Knoten in einem AVL-Baum.
 */
public class AVLTreeNode {
	/**
	 * Diese Variable enthält den Schlüssel, den der Knoten speichert.
	 */
	private int key;

	/**
	 * Diese Variable speichert die Balancierung des Knotens - wie in der
	 * Vorlesung erklärt - ab. Ein Wert von -1 bedeutet, dass der linke Teilbaum
	 * um eins tiefer ist als der rechte Teilbaum. Ein Wert von 0 bedeutet, dass
	 * die beiden Teilbäume gleich hoch sind. Ein Wert von 1 bedeutet, dass der
	 * rechte Teilbaum tiefer ist.
	 */

	private int balance = 0;

	private AVLTreeNode left = null;
	private AVLTreeNode right = null;
	private AVLTreeNode parent = null;

	public AVLTreeNode(int key) {
		this.key = key;
	}

	/**
	 * Diese Methode ermittelt die Höhe des Teilbaums unter diesem Knoten.
	 *
	 * @return die ermittelte Höhe
	 */
	public int height() {

		if (left == null && right == null){
			return 0; //returns height 0 if it has no children

		} else { // calls recurisve height() and returns its result - 1 (per Definition of Height of a Tree)
			return height(this) -1;
		}

	}

	//returns height of a treeNode based on the number of successors, the number of children that make
	//the largest path from root to the last child
	public int height(AVLTreeNode treeNode){
		int leftHeight , rightHeight ;

		if (treeNode == null){
			return 0;
		} else{

			leftHeight = height(treeNode.left);
			rightHeight = height(treeNode.right);

			return rightHeight < leftHeight ? (leftHeight + 1) : (rightHeight + 1);
		}
	}

	//returns true if the node has no children, meaning is valid AVL-Tree, otherwise calls recursive validAVL(AVLTreeNode)
	public boolean validAVL() {

		if (left == null && right == null)
			return true;

		return validAVL(this);

	}

	//returns true if the parameter @treeNode is a valid AVL-Tree, otherwise false
	private boolean validAVL(AVLTreeNode treeNode){
		if (treeNode == null)
			return true;
		if (heightDifference(treeNode) < -1)
			return false;
		if (heightDifference(treeNode) > 1 )
			return false;
		return validAVL(treeNode.left) && validAVL(treeNode.right);
	}

	//returns the height difference of subtrees of a treeNode
	private int heightDifference(AVLTreeNode treeNode){
		int rightHeight = height(treeNode.right);
		int leftHeight = height(treeNode.left);
		return rightHeight - leftHeight;
	}

	/**
	 * Diese Methode sucht einen Schlüssel im Baum.
	 *
	 * @param key der zu suchende Schlüssel
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {

		if (this.key == key)
			return true;

		if (left == null && right == null)
			return false;

		if (key > this.key){
			return right == null ? false : right.find(key);
		}

		if (key < this.key){
			return left == null ? false : left.find(key);
		}

		return false;
	}

	 public AVLTreeNode insert(int key) {
		return insertIterative(key,this);
	}

	 //returns the node in which the new child was added
	 private AVLTreeNode insertIterative(int key, AVLTreeNode treeNode){

		AVLTreeNode tempTreeNode = null;
		while (treeNode != null){

			tempTreeNode = treeNode;
			if (key > treeNode.getKey()){
				treeNode = treeNode.right;
			}else{
				treeNode = treeNode.left;
			}
		}

		if (key > tempTreeNode.getKey()){
			tempTreeNode.setRight(new AVLTreeNode(key));
			tempTreeNode.calculateBalance();
			tempTreeNode.getRight().setParent(tempTreeNode);
		} else {
			tempTreeNode.setLeft(new AVLTreeNode(key));
			tempTreeNode.calculateBalance();
			tempTreeNode.getLeft().setParent(tempTreeNode);
		}

		return tempTreeNode;
	 }

	//insert recursive
	private AVLTreeNode insert(int key, AVLTreeNode treeNode){
		if (key > treeNode.key){
			if (treeNode.right != null)
				return insert(key,treeNode.right); //a return in front was missing

			else{
				treeNode.right = new AVLTreeNode(key); // add node as right child
				treeNode.calculateBalance(); // calculate new balance of the node in which we added the new child
				treeNode.right.setParent(treeNode); // make the node in which was added parent of its new child node

			}

		} else{

			//key1 <= key2
			if (treeNode.left != null)
				return insert(key,treeNode.left);
			else{
				treeNode.left = new AVLTreeNode(key);
				treeNode.calculateBalance();
				treeNode.left.setParent(treeNode);
			}

		}

		return treeNode;
	}

	 public void calculateBalance(){
		balance = heightDifference(this);
	 }

	 public int getKey(){ return this.key; }
	 public int getBalance(){ return this.balance; }


	//setters and getters for children right, left and the parent
	public AVLTreeNode getParent() {
		return parent;
	}

	public void setParent(AVLTreeNode parent) {
		this.parent = parent;
	}

	public boolean hasParent(){
		return parent != null;
	}

	public AVLTreeNode getLeft() {
		return left;
	}

	public AVLTreeNode getRight() {
		return right;
	}

	public void setLeft(AVLTreeNode left) {
		this.left = left;
	}

	public void setRight(AVLTreeNode right) {
		this.right = right;
	}



	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @param sb
	 *          der StringBuilder für die Ausgabe
	 */
	public void dot(StringBuilder sb) {
		dotNode(sb, 0);
	}

	private int dotNode(StringBuilder sb, int idx) {
		sb.append(String.format("\t%d [label=\"%d, b=%d\"];\n", idx, key, balance));
		int next = idx + 1;
		if (left != null)
			next = left.dotLink(sb, idx, next, "l");
		if (right != null)
			next = right.dotLink(sb, idx, next, "r");
		return next;
	}

	private int dotLink(StringBuilder sb, int idx, int next, String label) {
		sb.append(String.format("\t%d -> %d [label=\"%s\"];\n", idx, next, label));
		return dotNode(sb, next);
	};

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	public String dot() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph {\n");
		if (this != null)
			this.dot(sb);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	@Override
	public String toString() {
		return dot();
	}
}
