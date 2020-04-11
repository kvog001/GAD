package Navigatoria;

import java.util.*;

public class Dijkstra {
	private PriorityQueue<Dijkstra.HelpDijkstra> queue = new PriorityQueue<>();
	private Map<Graph.Node, Integer> distances;
	private Map<Graph.Node, Graph.Node> predecessors;

	private Graph.Node start, end, merkeEnd;
	private List<Graph.Node> shortestPath = new ArrayList<>();

	public Dijkstra() {
		distances = new HashMap<>();
		predecessors = new HashMap<>();
	}

	public void findRoute(Graph g, Graph.Node a, Graph.Node b) {
		start = a;
		end = b;
		merkeEnd = b;

		for (Graph.Node v : g.getNodes()) {
			if (v != a)
				distances.put(v,Integer.MAX_VALUE);
		}
		distances.put(a,0);
		predecessors.put(a,null);
		queue.add(new HelpDijkstra(a,0));
		while (!queue.isEmpty()){
			HelpDijkstra v = queue.poll();
			for (Graph.Node w : v.getNode().getEdges().keySet()) {
				int newDist = distances.get(v.getNode()) + v.getNode().getEdges().get(w);
				if (newDist < distances.get(w)){
					predecessors.put(w,v.getNode());
					if (distances.get(w) == Integer.MAX_VALUE) queue.add(new HelpDijkstra(w,newDist));
					else {
						queue.remove(w);
						queue.add(new HelpDijkstra(w,newDist));
					}
					distances.put(w,newDist);
				}
			}
		}

		//todo if path not found throw exception ?
		if (!pathExists())
			throw new RuntimeException("Path doesn´t exist");
	}

	public List<Graph.Node> getShortestPath() {
		// TODO: geben Sie den kürzesten Pfad von Start zu Ziel zurück
		return this.shortestPath;

	}

	private List<Graph.Node> shortestPath(){
		shortestPath.add(end);
		while (predecessors.get(end)!= null){
			Graph.Node pred = predecessors.get(end);
			shortestPath.add(pred);
			end = pred;
		}

		Collections.reverse(shortestPath);
		return shortestPath;
	}

	private boolean pathExists(){
		List<Graph.Node> shortestPath = shortestPath();
		if (shortestPath.get(0) == this.start && shortestPath.get(shortestPath.size()-1)==this.merkeEnd)
			return true;
		return false;
	}

	public Integer getShortestPathLength() {
		// TODO: geben Sie das Gewicht des Pfades zurück
		return distances.get(merkeEnd);
	}

	//getters and setters
	public Map<Graph.Node, Integer> getDistances() {
		return distances;
	}

	public Map<Graph.Node, Graph.Node> getPredecessors() {
		return predecessors;
	}

	//Hilfsklasse
	public class HelpDijkstra implements Comparable<HelpDijkstra>{
		private Graph.Node node;
		private Integer distance;

		public HelpDijkstra(Graph.Node node, Integer distance){
			this.node = node;
			this.distance = distance;
		}

		public Integer getDistance() {
			return distance;
		}

		public Graph.Node getNode() {
			return node;
		}

		@Override
		public int compareTo(HelpDijkstra o) {
			return this.node.getID().compareTo(o.getNode().getID());
		}
	}
}