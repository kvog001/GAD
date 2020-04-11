package Graphilia;
//
public class ConnectedComponents {
	private BFS search;

	public ConnectedComponents() {
		search = new BFS();
	}

	/**
	 * Zählt alle Zusammenhangskomponenten im gegebenen Graphen g
	 */
	public int countConnectedComponents(Graph g) {
		int counter = 0;
		BFS bfs = new BFS();
		for (Graph.Node v: g.getNodes()) {
			if (!bfs.getParents().keySet().contains(v)){
				counter++;
				bfs.sssp(v);
			}
		}
		return counter;
	}
}