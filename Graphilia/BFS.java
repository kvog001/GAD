package Graphilia;

import java.util.*;

public class BFS {

	private Queue<Graph.Node> queue;
	private Map<Graph.Node,Integer> depths;
	private Map<Graph.Node,Graph.Node> parents;

	//variables for depth first search
	private Map<Graph.Node, Boolean> visited;
	private Map<Graph.Node, Integer> dfsNum;
	private Map<Graph.Node, Integer> finishNum;
	private int dfsCount, finishCount;

	public BFS() {
		// TODO
		// Sie dürfen die Signatur dieser Funktion verändern
		// (z.B. im Parameter im Konstruktor zu übergeben),
		// müssen das dann aber in Graphilia.java entsprechend übernehmen.
		queue = new LinkedList<>();
		depths = new HashMap<>();
		parents = new HashMap<>();

		visited = new HashMap<>();
		dfsNum = new HashMap<>();
		finishNum = new HashMap<>();
	}

	/**
	 * Führt eine Breitensuche vom Startknoten "start" aus, um das SSSP-Problem zu lösen.
	 */
	public void sssp(Graph.Node start) {
		depths.put(start,0);
		parents.put(start,start);
		queue.add(start); //if this was to be a stack, we would have depth first search
		while (!queue.isEmpty()){
			Graph.Node u = queue.poll();
			for (Graph.Node v : u.getEdges()) {
				if (parents.get(v) == null && noCycle(v,start)){
					queue.add(v);
					int depthOfU = depths.get(u);
					depths.put(v , depthOfU + 1);
					parents.put(v,u);
				}
			}
		}
	}

	/**
	 * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion die Tiefe des Knotens n
	 * vom Startknoten überprüft werden.
	 */
	public Integer getDepth(Graph.Node n) {
		if (depths.get(n) == null)
			throw new RuntimeException("There is no path between the start node and this node!");
		return depths.get(n);
	}

	/**
	 * Nachdem SSSP ausgeführt wurde, kann mit dieser Funktion der Vaterknoten
	 * des Knotens n in Richtung Startknoten abgefragt werden. 
	 */
	public Graph.Node getParent(Graph.Node n) {
		if (parents.get(n) == null)
			throw new RuntimeException("There is no path between the start node and this node!");
		return parents.get(n);
	}

	private boolean noCycle(Graph.Node u, Graph.Node v){
		return u.getId() != v.getId();
	}

	public Map<Graph.Node, Integer> getDepths() {
		return depths;
	}

	public Map<Graph.Node, Graph.Node> getParents() {
		return parents;
	}

	public boolean hasBackwardEdge(Graph g){
		try{
			for (Graph.Node v: g.getNodes()) {
				for (Graph.Node w: v.getEdges()) {
					if (dfsNum.get(v) >= dfsNum.get(w) || finishNum.get(v) <= finishNum.get(w))
						return false;
				}
			}
		}catch (NullPointerException npe){
			return false;
		}
		return true;
	}



//	public void dfs(Graph.Node start){
//		depths.put(start,0);
//		parents.put(start,start);
//		stack.add(start);
//		while (!stack.isEmpty()){
//			Graph.Node u = stack.pop();
//			for (Graph.Node v : u.getEdges()) {
//				if (parents.get(v) == null && noCycle(v,start)){
//					stack.add(v);
//					int depthOfU = depths.get(u);
//					depths.put(v , depthOfU + 1);
//					parents.put(v,u);
//				}
//			}
//		}
//	}

	public void dfs(Graph g, Graph.Node start){
		for (Graph.Node v : g.getNodes()){
			visited.put(v,false);
		}
		init();
		//foreach (s € V)
		//if(s nicht markiert){}
		visited.put(start,true);
		root(start);
		depthFirstSearch(start,start);
	}

	private void depthFirstSearch(Graph.Node u, Graph.Node v){
		for (Graph.Node w : v.getEdges()) {
			if (visited.get(w))
				traverseNonTreeEdge(v,w);
			else{
				traverseTreeEdge(v,w);
				visited.put(w,true);
				depthFirstSearch(v,w);
			}
		}
		backtrack(u,v);
	}

	private void init(){ dfsCount = 1; finishCount = 1; }
	private void root(Graph.Node s){ dfsNum.put(s,dfsCount); dfsCount++; }
	private void traverseTreeEdge(Graph.Node v, Graph.Node w){
		dfsNum.put(w,dfsCount); dfsCount++;
	}

	private void traverseNonTreeEdge(Graph.Node v, Graph.Node w){}

	private void backtrack(Graph.Node u, Graph.Node v){
		finishNum.put(v,finishCount);
		finishCount++;
	}

	public Map<Graph.Node, Integer> getDfsNum() { return dfsNum; }

	public Map<Graph.Node, Integer> getFinishNum() { return finishNum; }

}
