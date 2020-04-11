package Graphilia;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private List<Node> nodes;
	private int nodeIndexer = -1;

	public class Node {
		private List<Node> edges;
		private int id;

		public Node(int id){
			this.id = id;
			edges = new ArrayList<>();
		}

		public void addEdge(Node neighbour){
			edges.add(neighbour);
		}

		public List<Node> getEdges() {
			return edges;
		}

		public int getId() {
			return id;
		}
	}

	public Graph() {
		nodes = new ArrayList<>();
	}

	/**
	 * Erstellt einen neuen Knoten, und gibt den Index dieses Knotens zurück.
	 * Der erste erstellte Knoten sollte Index 0 haben.
	 * Knoten, die direkt nacheinander erstellt werden, sollten aufsteigende Indices haben.
	 */
	public Integer addNode() {
		nodeIndexer++;
		nodes.add(new Node(nodeIndexer));
		return nodeIndexer;
	}

	/**
	 * Liefert den Knoten zum angegebenen Index zurück
	 */
	public Node getNode(Integer id) {
		return nodes.get(id);
	}

	/**
	 * Fügt zwischen den beiden angegebenen Knoten eine (ungerichtete) Kante hinzu.
	 */
	public void addEdge(Node a, Node b) {
		a.addEdge(b);
		b.addEdge(a);
	}

	public List<Node> getNodes() {
		return nodes;
	}
}
