package Binomilia;

import java.util.ArrayList;
import java.util.List;

public class BinomialHeap {
	public ArrayList<BinomialTreeNode> trees;
	/**
	 * Dieser Konstruktor baut einen leeren Haufen.
	 */
	public BinomialHeap() {
		trees = new ArrayList<>();
	}

	/**
	 * Diese Methode ermittelt das minimale Element im binomialen Haufen.
	 * Wenn es dieses nicht gibt (bei leerem Haufen), soll eine RuntimeException geworfen werden.
	 *
	 * @return das minimale Element
	 */
	public int min() {

		if (trees.size() == 0)
			throw new RuntimeException("Heap is empty!");

		int min = trees.get(0).min();

		for (BinomialTreeNode treeNode:trees){
			if (treeNode.min() < min)
				min = treeNode.min();
		}

		return min;
	}

	//returns node with smallest element
	public BinomialTreeNode getMinNode(){
		if (trees.size() == 0)
			throw new RuntimeException();

		int min = trees.get(0).min();
		BinomialTreeNode result = trees.get(0);

		for (BinomialTreeNode treeNode:trees){
			if (treeNode.min() < min){
				min = treeNode.min();
				result = treeNode;
			}
		}
		return result;
	}

	/**
	 * Diese Methode fügt einen Schlüssel in den Haufen ein.
	 *
	 * @param key der einzufügende Schlüssel
	 */
	public void insert(int key) {

		BinomialTreeNode binomialTree = new BinomialTreeNode(key);
		merge(binomialTree);

	}

	private void merge(BinomialTreeNode binomialTreeNode ){

		if (trees.isEmpty()){
			trees.add(0,binomialTreeNode);

		} else {

			boolean sameRankFound = false;
			for (int i=0; i < trees.size(); i++){
				if (trees.get(i).rank() == binomialTreeNode.rank()){
					BinomialTreeNode result = binomialTreeNode.merge(binomialTreeNode,trees.get(i));
					sameRankFound = true;
					if (result == binomialTreeNode){
						trees.set(i,binomialTreeNode);
					}
					break;

				}
			}

			if (!sameRankFound){
				if (binomialTreeNode.rank() < trees.get(0).rank())
					trees.add(0,binomialTreeNode);
				else
					trees.add(binomialTreeNode);
				return;
			}

			boolean maxOneRankPerNode = false;

			outer:while (!maxOneRankPerNode){
				for (int i=0; i<trees.size()-1; i++){
					if (trees.get(i).rank() == trees.get(i+1).rank()){
						BinomialTreeNode result = trees.get(i).merge(trees.get(i),trees.get(i+1));

						if (result == trees.get(i+1)){
							trees.set(trees.indexOf(trees.get(i)),trees.get(i+1));
							trees.remove(trees.get(i+1));

						} else {
							trees.remove(trees.get(i+1));
						}
						continue outer;
					}
				}
				maxOneRankPerNode = true;
			}

		}
	}


	/**
	 * Diese Methode entfernt das minimale Element aus dem binomialen
	 * Haufen und gibt es zurück.
	 *
	 * @return das minimale Element
	 */


	public int deleteMin() {

		if (trees.isEmpty())
			throw new RuntimeException();

		BinomialTreeNode minNode = this.getMinNode();
		int result = minNode.min();

		List<BinomialTreeNode> childrenOfMinNode = minNode.getChildrens();

		trees.remove(minNode);

		//it we remove only one node left
		if (trees.size() == 0){
			for (BinomialTreeNode treeNode : childrenOfMinNode){
				trees.add(treeNode);
			}
			return result;
		}

		int start = 0;
		if (childrenOfMinNode.size() > 0){
			outer: for (BinomialTreeNode treeNode : childrenOfMinNode){
				if (start == trees.size()){
					trees.add(treeNode);
					start++;
					continue outer;
				}
				for (int i=start; i < trees.size(); i++){
					if (treeNode.rank() == trees.get(i).rank()){
						BinomialTreeNode resultNode = treeNode.merge(treeNode,trees.get(i));
						if (resultNode == treeNode){
							trees.set(i,treeNode);
						}
						start = i+1;
						continue outer;
					}

					if(treeNode.rank() < trees.get(i).rank()){
						trees.add(i,treeNode);
						start = i+1;
						break;
					}

					if (i==trees.size()-1 && treeNode.rank() > trees.get(i).rank()){
						trees.add(i,treeNode);
						break;
					} else {
						trees.add(i,treeNode);
						break;
					}
				}
			}



			boolean maxOneRankPerNode = false;

			outer:while (!maxOneRankPerNode){
				for (int i=0; i<trees.size()-1; i++){
					if (trees.get(i).rank() == trees.get(i+1).rank()){
						BinomialTreeNode resultTreeNode = trees.get(i).merge(trees.get(i),trees.get(i+1));

						if (resultTreeNode == trees.get(i+1)){
							trees.set(trees.indexOf(trees.get(i)),trees.get(i+1));
							trees.remove(trees.get(i+1));

						} else {
							trees.remove(trees.get(i+1));
						}
						continue outer;
					}
				}
				maxOneRankPerNode = true;
			}

		}

		return result;
	}



	/**
	 * Diese Methode gibt die Binomialbäume aus.
	 */
	public void printTree() {
		for(int i = 0; i < trees.size(); i++) {
			System.out.println("\nTree " + i + ":");
			trees.get(i).print();
		}
	}
}
