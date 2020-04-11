package Avella;

/**
 * Diese Klasse implementiert einen AVL-Baum.
 */
public class AVLTree {
	/**
	 * Diese Variable speichert die Wurzel des Baumes.
	 */
	AVLTreeNode root = null;

	public AVLTree() {
	}

	/**
	 * Diese Methode ist zum Debuggen gedacht und prüft, ob es sich um einen
	 * validen AVL-Baum handelt. Dabei werden die folgenden Eigenschaften geprüft:
	 *
	 * - Die Höhe des linken Teilbaumes eines Knotens unterscheidet sich von der
	 * Höhe des rechten Teilbaumes um höchstens eins. - Die Schlüssel im linken
	 * Teilbaum eines Knotens sind kleiner als der oder gleich dem Schlüssel des
	 * Knotens. - Die Schlüssel im rechten Teilbaum eines Knotens sind größer als
	 * der Schlüssel des Knotens. - Die Balancierung jedes Knoten entspricht der
	 * Höhendifferenz der Teilbäume entsprechend der Erklärung in der Vorlesung.
	 *
	 * @return 'true' falls der Baum ein valider AVL-Baum ist, 'false' sonst
	 */
	public boolean validAVL() {
//		return true;
		return root.validAVL();
	}

	/**
	 * Diese Methode fügt einen neuen Schlüssel in den AVL-Baum ein.
	 *
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {
		if(root == null){
			root = new AVLTreeNode(key);
			return;
		}
		AVLTreeNode treeNode = root.insert(key);

		//todo root = treeNode; ??

		//traverse back to the root and rebalance along the way if necessary
		while (treeNode.hasParent()) /* until we reach root, which has no parent */{
			root = treeNode = treeNode.getParent();
			treeNode.calculateBalance();
			int updatedBalance = treeNode.getBalance();

			if (updatedBalance > 1){
				root = treeNode = rotateLeft(treeNode);
				if(treeNode.getBalance()<-1){
					root = treeNode = rotateRight(treeNode);
					root = treeNode = rotateLeftRight(treeNode);
				}

			}else if(updatedBalance < -1){
				root = treeNode = rotateRight(treeNode);
				if (treeNode.getBalance()>1){
					root = treeNode = rotateLeft(treeNode);
					root = treeNode = rotateRightLeft(treeNode);//todo
				}
			}

		}
	}


	private AVLTreeNode rotateLeft(AVLTreeNode treeNode){
		AVLTreeNode newRoot = treeNode.getRight(); //right child becomes new root

		if (treeNode.hasParent()){
			newRoot.setParent(treeNode.getParent()); // make old parent of old root new parent of new root
			//check if tree node was left or right child of its parent
			if (treeNode.getParent().getRight() == treeNode)
				treeNode.getParent().setRight(newRoot); //point new root as right child to parent of old root
			else
				treeNode.getParent().setLeft(newRoot);

		}else //newRoot becomes root of the whole Tree
			newRoot.setParent(null);

		treeNode.setRight(null); //remove old root´s right child

		if (newRoot.getLeft() != null){
			treeNode.setRight(newRoot.getLeft());
			newRoot.getLeft().setParent(treeNode);
		}

		newRoot.setLeft(treeNode);
		treeNode.setParent(newRoot);

		//recalculate balance
		treeNode.calculateBalance();
		newRoot.calculateBalance();

		return newRoot;
	}

	private AVLTreeNode rotateRight(AVLTreeNode treeNode) {

		AVLTreeNode newRoot = treeNode.getLeft(); //left child becomes new root

		if (treeNode.hasParent()){
			newRoot.setParent(treeNode.getParent()); // make old parent of old root new parent of new root
			//check if old root was left or right child so we know where to add newRoot
			if (treeNode.getParent().getLeft() == treeNode)
				treeNode.getParent().setLeft(newRoot);
			else
				treeNode.getParent().setRight(newRoot);
		}else
			newRoot.setParent(null);

		treeNode.setLeft(null); //remove old root´ left child

		if (newRoot.getRight() != null){
			treeNode.setLeft(newRoot.getRight());
			newRoot.getRight().setParent(treeNode);
		}

		newRoot.setRight(treeNode);
		treeNode.setParent(newRoot);

		//recalculate balance
		treeNode.calculateBalance();
		newRoot.calculateBalance();

		return newRoot;
	}


	private AVLTreeNode rotateLeftRight(AVLTreeNode treeNode){
		treeNode = rotateRight(treeNode.getRight());
		treeNode = rotateLeft(treeNode.getParent());
		treeNode.calculateBalance();
		return treeNode;
	}

	private AVLTreeNode rotateRightLeft(AVLTreeNode treeNode){
		treeNode = rotateLeft(treeNode.getLeft());
		treeNode = rotateRight(treeNode.getParent());
		treeNode.calculateBalance();
		return treeNode;
	}

	/**
	 * Diese Methode sucht einen Schlüssel im AVL-Baum.
	 *
	 * @param key der Schlüssel, der gesucht werden soll
	 * @return 'true', falls der Schlüssel gefunden wurde, 'false' sonst
	 */
	public boolean find(int key) {
		return root.find(key);
	}

	/**
	 * Diese Methode wandelt den Baum in das Graphviz-Format um.
	 *
	 * @return der Baum im Graphiz-Format
	 */
	public String dot() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph {\n");
		if (root != null)
			root.dot(sb);
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
