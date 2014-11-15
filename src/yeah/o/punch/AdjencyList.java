package yeah.o.punch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Edge <T1, T2> {
	T1 id;
	T2 weight;
	
	public Edge(T1 d, T2 w) {
		id = d;
		weight = w;	
	}
};

public class AdjencyList <T1, T2> {
	
	private HashMap<T1, List<Edge<T1, T2>>> graph = new HashMap<T1, List<Edge<T1, T2>>>(); 
	private boolean _directed = false;
	private int numOfEdge = 0;
	private int numOfNodes = 0;
	
	public AdjencyList() {
		this(false);
	}
	
	public AdjencyList(boolean directed) {
		_directed = directed;	
	}
	
	public HashMap<T1, List<Edge<T1, T2>>> getGraph() {
		return graph;
	}
	
	public void insertEdge(T1 from, T1 to, T2 weight) {
		if (!nodeExists(from)) {
			List<Edge<T1,T2>> from_adjList = new LinkedList<Edge<T1,T2>>();
			graph.put(from, from_adjList);
		}
		
		if (!nodeExists(to)) {
			List<Edge<T1,T2>> to_adjList = new LinkedList<Edge<T1,T2>>();
			graph.put(to, to_adjList);
		}
		
		Edge<T1, T2> edge = new Edge<T1, T2>(to, weight);
		graph.get(from).add(edge);
		numOfEdge++;
		
		if (!_directed) {
			Edge<T1, T2> reverseEdge = new Edge<T1, T2>(from, weight);
			graph.get(to).add(reverseEdge);
		}
	}
	
	public List<Edge<T1,T2>> getNeighbors(T1 id) {
		if (!nodeExists(id))
			return null;
		
		return graph.get(id);
	}
	
	public Set<T1> getNodes() {
		if (graph.size() == 0)
			return null;
		
		return graph.keySet();
	}
	
	public int getNumOfNodes() {
		return graph.size(); 
	}
	
	public boolean isDirectedGraph() {		
		return _directed == true;
	}
	
	public boolean nodeExists(T1 id) {
		if (graph.keySet().contains(id))
			return true;
		
		return false;
	}
	
	public boolean edgeExists(T1 from, T1 to) {
		if (!nodeExists(from) || !nodeExists(to))
			return false;
		
		List<Edge<T1,T2>> edges = graph.get(from);
		for (Edge<T1,T2> edge : edges) {
			if (edge.id.equals(to))
				return true;
		}
		
		return false;
	}
	
	
	public void printGraph() {
		System.out.println("/=========================================/");
		
		if (graph==null || graph.size()==0) {
			System.out.println("Empty Graph!");
			return;
		}
		
		String directStr = _directed ? "Directed" : "Undirected";
		int vertices = graph.size();
		System.out.println(vertices + " vertices, " + numOfEdge + " edges " + directStr + " graph");	

		Iterator<Map.Entry<T1, List<Edge<T1, T2>>>> it = graph.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<T1, List<Edge<T1, T2>>> entry = it.next();
			
			System.out.print(entry.getKey());
			
			List<Edge<T1, T2>> edges = entry.getValue();			
			for(Edge<T1, T2> edge : edges) {
				System.out.print(" -> " + edge.id);
			}
			System.out.println(" #");
		}
	}
	
	
	public static void main(String[] args) {
		
		AdjencyList<String, Integer> graph1 = new AdjencyList<String, Integer>();
		graph1.insertEdge("hit", "hot", 1);
		graph1.insertEdge("hot", "dot", 1);
		graph1.insertEdge("hot", "lot", 1);
		graph1.insertEdge("dot", "lot", 1);
		graph1.insertEdge("dot", "dog", 1);
		graph1.insertEdge("lot", "log", 1);
		graph1.insertEdge("dog", "log", 1);
		graph1.insertEdge("dog", "cog", 1);
		graph1.insertEdge("log", "cog", 1);
		graph1.printGraph();
		
		
		
	}
}
