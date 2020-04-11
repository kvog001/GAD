package Binomilia;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinomialTreeNode {

	private BinomialTreeNode[] children;
	private List<BinomialTreeNode> childrens;
	private int key;

	public BinomialTreeNode(int key) {
		this.key = key;
		childrens = new ArrayList<>();
	}

	/**
	 * Ermittelt das minimale Element im Teilbaum.
	 *
	 * @return das minimale Element
	 */
	public int min() {
		return this.key;
	}

	/**
	 * Gibt den Rang des Teilbaumes zurück.
	 *
	 * @return der Rang des Teilbaumes
	 */
	public int rank() {
		return this.childrens.size();
	}

	/**
	 * Entfernt den minimalen Knoten (= Wurzel) und gibt eine Menge von
	 * Teilbäumen zurück, in die der aktuelle Baum zerfällt, wenn man
	 * den Knoten entfernt.
	 *
	 * @return die Menge von Teilbäumen
	 */
	public BinomialTreeNode[] deleteMin() {
		return (BinomialTreeNode[]) this.childrens.toArray();
	}

	/**
	 * Diese Methode vereint zwei Bäume des gleichen Ranges.
	 *
	 * @param a der erste Baum
	 * @param b der zweite Baum
	 * @return denjenigen der beiden Bäume, an den der andere angehängt wurde
	 */
	public static BinomialTreeNode merge(BinomialTreeNode a, BinomialTreeNode b) {

		if (a.min() >= b.min()){
			b.addChild(a);
			return b;
		} else {
			a.addChild(b);
			return a;
		}
	}

	private void addChild(BinomialTreeNode child){
		this.childrens.add(child);

	}

//getter for childrens
	public List<BinomialTreeNode> getChildrens(){
		return this.childrens;
	}


	private void print(int depth) {
		for(int i = 0; i < depth; i++) {
			System.out.print("\t");
		}
		System.out.print("[rank: " + rank() + " key: " + min() + "]\n");
		for(BinomialTreeNode c : childrens) {
			c.print(depth+1);
		}
	}

	/**
	 * Diese Methode gibt den Binomialbaum aus.
	 */
	public void print() {
		print(0);
	}

//	@Override
//	public int compareTo(BinomialTreeNode o) {
//		int objectKey = o.rank();
//		return this.rank() - objectKey;
//	}
}
