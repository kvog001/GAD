package Navigatoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	private List<Node> nodes;
	private int nodeIndexer = -1;

	public class Node {
		private Integer id;
		private Map<Node, Integer> edges;

		public Node(Integer id){
			this.id = id;
			edges = new HashMap<>();
		}

		public Integer getID() {
			return id;
		}

		public void addEdge(Node edge, Integer weight){
			edges.put(edge,weight);
		}

		public Map<Node, Integer> getEdges() {
			return edges;
		}

		@Override
		public String toString(){
			return ""+this.id;
		}
	}

	public Graph() {
		nodes = new ArrayList<>();
	}

	public Integer addNode() {
		nodeIndexer++;
		nodes.add(new Node(nodeIndexer));
		return nodeIndexer;
	}

	public Node getNode(Integer id) {
		return nodes.get(id);
	}

	/**
	 * Fügt eine neue direktionale Kante von Knoten a zu Knoten b
	 * mit Gewicht weight hinzu. Gewicht darf nicht negativ sein.
	 */
	public void addEdge(Node a, Node b, Integer weight) {
		a.addEdge(b,weight);
	}

	public List<Graph.Node> getNodes() {
		return nodes;
	}
}
