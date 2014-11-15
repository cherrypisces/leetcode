package yeah.o.punch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class PathCost<T> {
	T vertice;
	int cost;
	
	public PathCost(T v, int c) {
		vertice = v;
		cost = c;
	}
}

// Java PriorityQueue : smaller number == higher priority
class PathComparator<T> implements Comparator<PathCost<T>> {
	public<T> PathComparator() {
		
	}
	
	public int compare(PathCost<T> o1, PathCost<T> o2) {
		if(o1==null && o2==null)
			return 0;
		else if (o1==null || o1.cost<o2.cost)
			return -1;
		else if (o2==null || o1.cost>o2.cost)
			return 1;
		else
			return 0;				
	}
	
	public boolean equals(PathComparator<T> obj) {
		return this == obj;
	}
}

public class GraphAlgos {
	
	private static int MAX_WEIGHT = Integer.MAX_VALUE;
	
	//========================= Traversal =========================//
	/**
	 * DFS recursive version
	 * 
	 * @param graph			edge weight is expressed in integer
	 * @param startIndex  	node index of the traversal starting point	
	 * @param nodes  		nodes identification array, used for print only
	 * 
	 */
	public static <T> void DFS_Recursive_Matrix(int graph[][], int startIndex, T[] nodes) {
		if (graph==null || startIndex<0 || startIndex>=graph.length)
			return;
		
		boolean[] visited = new boolean[graph.length];
		
		System.out.print("[DFS_Recursive_Matrix]: ");		
		DFS_Recursive_Matrix_Helper(graph, startIndex, nodes, visited);
		System.out.println("&");
	}
	
	private static <T> void DFS_Recursive_Matrix_Helper(
			int graph[][], 
			int startIndex, 
			T[] nodes, 
			boolean[] visited) {
		
		visited[startIndex] = true;
		System.out.print(nodes[startIndex] + " -> ");
		
		for(int i=0; i<graph[startIndex].length; i++) {
			
			if(graph[startIndex][i]!=MAX_WEIGHT && !visited[i]) {
				DFS_Recursive_Matrix_Helper(graph, i, nodes, visited);
			}
		}
	}
	
	/**
	 * Recursive DFS via AdjencyList
	 * 
	 * @param graph
	 * @param start
	 */
	public static <T> void DFS_Recursive_AdjencyList(AdjencyList<T, Integer> graph, T start) {
		if (graph==null || !graph.nodeExists(start)) 
			return;
		
		HashMap<T, Boolean> visited = new HashMap<T, Boolean>();
		
		System.out.print("[DFS_Recursive_AdjencyList]: ");		
		DFS_Recursive_AdjencyList_Helper(graph, start, visited);
		System.out.println("&");
	}
	
	
	private static <T> void DFS_Recursive_AdjencyList_Helper(
			AdjencyList<T, Integer> graph, 
			T start,
			HashMap<T, Boolean> visited) {
		
		visited.put(start, true);
		System.out.print(start + " -> ");
		
		List<Edge<T,Integer>> neighbors = graph.getNeighbors(start);
		
		if (neighbors == null)
			return;
		
		for(Edge<T, Integer> edge : neighbors) {
			if (!visited.keySet().contains(edge.id)) {
				DFS_Recursive_AdjencyList_Helper(graph, edge.id, visited);
			}
		}
	}
	
	/**
	 * DFS Iterative version
	 * 
	 * @param graph			edge weight is expressed in integer
	 * @param start  		identification of starting node
	 * 
	 */
	public static <T> void DFS_Iterative_Matrix(int[][] graph, int startIndex, T[] nodes) {
		if (graph==null || startIndex<0 || startIndex>=graph.length)
			return;
		
		System.out.print("[DFS_Iterative_Matrix]: ");		
		
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[graph.length];
		
		int currIndex = startIndex;
		
		stack.push(currIndex);
		visited[currIndex] = true;
		System.out.print(nodes[currIndex] + " -> ");
		
		while(!stack.isEmpty()) {
			
			boolean found = false;
			for(int i=0; i<graph[currIndex].length; i++) {
				if (graph[currIndex][i]!=MAX_WEIGHT && !visited[i]) {
					currIndex = i;
					found = true;
					break;
				}
			}
			
			if(!found) {
				stack.pop();
				if(stack.isEmpty()) 
					break;
				
				currIndex = stack.peek();
			} else {
				stack.push(currIndex);
				visited[currIndex] = true;
				System.out.print(nodes[currIndex] + " -> ");
			}
			
		} while(!stack.isEmpty());
		
		System.out.println("&");
	}
	
	public static <T> void DFS_Iterative_AdjencyList(AdjencyList<T, Integer> graph, T start) {
		if (graph==null || !graph.nodeExists(start))
			return;
		
		System.out.print("[DFS_Iterative_AdjencyList]: ");
		
		Stack<T> stack = new Stack<T>();
		HashMap<T, Boolean> visited = new HashMap<T, Boolean>();

		T currNode = start;
		
		stack.push(currNode);
		visited.put(currNode, true);
		System.out.print(currNode + " -> ");
		
		while(!stack.isEmpty()) {
			
			boolean found = false;
			List<Edge<T, Integer>> edges = graph.getNeighbors(currNode);
			for (Edge<T, Integer> edge : edges) {
				if (!visited.keySet().contains(edge.id)) {
					currNode = edge.id;
					found=true;
					break;
				}
			}
			
			if (!found) {
				stack.pop();
				if(stack.isEmpty())
					break;
				
				currNode = stack.peek();
			} else {
				stack.push(currNode);
				visited.put(currNode, true);
				System.out.print(currNode + " -> ");
			}
		}
		
		System.out.println("&");
	}
	
	
	/**
	 * BFS Recursive version
	 * 
	 * @param graph			edge weight is expressed in integer
	 * @param start  		identification of starting node
	 * 
	 */
	public static <T> void BFS_Recursive_Matrix(int graph[][], int startIndex, T[] nodes) {
		if (graph==null || startIndex<0 || startIndex>=graph.length) 
			return;
		
		boolean[] visited = new boolean[graph.length];
		
		System.out.print("[BFS_Recursive_Matrix]: ");
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(startIndex);
		visited[startIndex] = true;
		
		BFS_Recursive_Matrix_helper(graph, nodes, queue, visited);
		
		System.out.println("&");
	}
	
	
	public static <T> void BFS_Recursive_Matrix_helper(
			int graph[][], 
			T[] nodes,
			Queue<Integer> queue,
			boolean[] visited) {
		
		if(queue.isEmpty())
			return;		
		
		int currNode = queue.poll();
		
		System.out.print(nodes[currNode] + " -> ");
		
		for(int i=0; i<graph[currNode].length; i++) {
			
			if (graph[currNode][i] != MAX_WEIGHT && !visited[i]) {
				visited[i] = true;
				queue.offer(i);
			}
		}
		
		BFS_Recursive_Matrix_helper(graph, nodes, queue, visited);
	}
	
	/*
	 *
	 */
	public static <T> void BFS_Recursive_AdjencyList(AdjencyList<T, Integer> graph, T start) {
		if (graph==null || !graph.nodeExists(start))
			return;
		
		HashMap<T, Boolean> visited = new HashMap<T, Boolean>();

		System.out.print("[BFS_Recursive_AdjencyList]: ");
		
		Queue<T> queue = new LinkedList<T>();
		queue.offer(start);
		visited.put(start, true);
		
		BFS_Recursive_AdjencyList_Helper(graph, queue, visited);
		
		System.out.println("&");
	}
	
	private static <T> void BFS_Recursive_AdjencyList_Helper(
			AdjencyList<T, Integer> graph,
			Queue<T> queue,
			HashMap<T, Boolean> visited) {
		if(queue.isEmpty())
			return;
		
		T currNode = queue.poll();
		
		System.out.print(currNode + " -> ");
		
		List<Edge<T,Integer>> edges = graph.getNeighbors(currNode);
		if (edges != null) {
			for(Edge<T,Integer> edge : edges) {
				if (!visited.containsKey(edge.id)) {
					queue.offer(edge.id);
					visited.put(edge.id, true);
				}
			}
		}
		
		BFS_Recursive_AdjencyList_Helper(graph, queue, visited);
	}
	
	/**
	 * BFS Iterative version
	 * 
	 * @param graph			edge weight is expressed in integer
	 * @param start  		identification of starting node
	 * 
	 */
	public static <T> void BFS_Iterative_Matrix(int graph[][], int startIndex, T[] nodes) {
		if (graph==null || startIndex<0 || startIndex>=graph.length) 
			return;
		
		System.out.print("[BFS_Iterative_Matrix]: ");
		
		boolean[] visited = new boolean[graph.length];
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(startIndex);
		visited[startIndex] = true;
		
		while(!queue.isEmpty()) {
			int currIndex = queue.poll();
						
			System.out.print(nodes[currIndex] + " -> ");
			
			for(int i=0; i<graph[currIndex].length; i++) {
				if (graph[currIndex][i]!=MAX_WEIGHT && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}

		}
		
		System.out.println("&");
	}
	
	public static <T> void BFS_Iterative_AdjencyList(AdjencyList<T, Integer> graph, T start) {
		if (graph==null || !graph.nodeExists(start))
			return;
			
		System.out.print("[BFS_Iterative_AdjencyList]: ");
		
		HashMap<T, Boolean> visited = new HashMap<T, Boolean>();
		
		Queue<T> queue = new LinkedList<T>();
		queue.offer(start);
		visited.put(start, true);
		
		while(!queue.isEmpty()) {
			T currNode = queue.poll();
			
			System.out.print(currNode + " -> ");
			
			List<Edge<T,Integer>> edges = graph.getNeighbors(currNode);
			if (edges != null) {
				for(Edge<T,Integer> edge: edges) {
					if (!visited.containsKey(edge.id))
						queue.offer(edge.id);
						visited.put(edge.id, true);
				}
			}
		}
		
		System.out.println("&");
	}
	
	//======================= Shortest Path =======================//
	// If a graph contains a "negative cycle" 
	// (i.e. a cycle whose edges sum to a negative value) that is reachable from the source, 
	// then there is no cheapest path: 
	// any path can be made cheaper by one more walk around the negative cycle.
	
	/**
	 * single source --> single destination
	 * 
	 * Essentially a BFS approach 
	 * 
	 */
	public static int ShortestPath_mitDP_Matrix(int graph[][], int src, int dest) {
		if (graph==null || graph.length==0 || 
			src<0 || src>=graph.length || dest<0 || dest>=graph.length)
			return MAX_WEIGHT;
		
		System.out.print("[ShortestPath_mitDP_Matrix]: ");
		
		// dist[k] : shortest path from src to k
		int[] dist = new int[graph.length];
		Arrays.fill(dist, MAX_WEIGHT);
		
		boolean[] visited = new boolean[graph.length];
		
		dist[src] = 0;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(src);
		visited[src] = true;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int i=0; i<graph[curr].length; i++) {				
				if (graph[curr][i] != MAX_WEIGHT) {					
					if (!visited[i]) {
						queue.offer(i);
						visited[i] = true;
					}

					if (dist[curr] + graph[curr][i] < dist[i]) {
						dist[i] = dist[curr] + graph[curr][i];
					}
				}				
			}
		}
 		
		System.out.println(dist[dest]);
		
		return dist[dest];
	}
	
	public static <T> int ShortestPath_mitDP_AdjencyList(AdjencyList<T, Integer> graph, T src, T dest) {
		if (graph==null || !graph.nodeExists(src) || !graph.nodeExists(dest))
			return MAX_WEIGHT;
		
		System.out.print("[ShortestPath_mitDP_AdjencyList]: ");
		
		// dist[k] : shortest path from src to k
		HashMap<T, Integer> dist = new HashMap<T, Integer>();		
		dist.put(src, 0);
		
		Queue<T> queue = new LinkedList<T>();
		queue.offer(src);
		
		while(!queue.isEmpty()) {
			T currNode = queue.poll();
			
			List<Edge<T, Integer>> edges = graph.getNeighbors(currNode);
			
			if (edges == null) 
				continue;
			
			for(Edge<T, Integer> edge: edges) {
				
				if (!dist.containsKey(edge.id)) {
					queue.offer(edge.id);
				}
				
				if (!dist.containsKey(edge.id) || 
					 dist.get(currNode) + edge.weight < dist.get(edge.id) ) {
					dist.put(edge.id, dist.get(currNode) + edge.weight);
				}
			}
		}
		
		System.out.println(dist.get(dest));
		
		return dist.get(dest);
	}
	
	/**
	 *  Shortest paths between <i>EACH</i> pair of vertices <br>
	 *  
	 *  May contain negative weights, but <b><i>*NO*</i> Negative Cycles</b>
	 * <p>
	 * <tt>dist[i,j](k) = min{dist[i,j](k-1), dist[i,k](k-1)+dist[k,j](k-1)}</tt>
	 * <p>
	 * In theory, (irrelevant of data structure) the time complexity would be
	 *  жи(|V|^3)
	 *  <br>
	 *  And it's better implemented in matrix to express its idea explicitly!
	 *  
	 */
	public static int ShortestPath_Floyed_Matrix(int graph[][], int src, int dest) {
		
		if (graph==null || graph.length==0 || 
				src<0 || src>=graph.length || dest<0 || dest>=graph.length)
				return MAX_WEIGHT;
		
		System.out.print("[ShortestPath_Floyed_Matrix]: ");
		
		int DIMENSION = graph.length;
		
		int[][] dist = new int[DIMENSION][DIMENSION];
		for(int i=0; i<DIMENSION; i++){
			Arrays.fill(dist[i], MAX_WEIGHT);
		}
		
		for(int i=0; i<DIMENSION; i++) {
			dist[i][i] = 0;
		}
		
		for(int i=0; i<DIMENSION; i++) 
			for(int j=0; j<DIMENSION; j++) {
				if (graph[i][j] != MAX_WEIGHT) {
					dist[i][j] = graph[i][j];
				}
		}

		for(int k=0; k<DIMENSION; k++) {
			for(int i=0; i<DIMENSION; i++) {
				for(int j=0; j<DIMENSION; j++) {
					if (dist[i][k]==MAX_WEIGHT || dist[k][j]==MAX_WEIGHT)
						continue;
					
					if (dist[i][k]+dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k]+dist[k][j] ;
					}
				}
			}
		}

		System.out.println(dist[src][dest]);
		
		return dist[src][dest];
	}

	/*
	public static <T> void ShortestPath_Floyed_AdjencyList(
			AdjencyList<T, Integer> graph, T src, T dest) {
		
		System.out.print("[ShortestPath_Floyed_Matrix]: ");

		System.out.println();
	}*/
	
	/**
	 * Single source --> all destinations
	 *  
	 * Based on Relaxation <i>|V|-1</i> times
	 * 
	 * In theory, (irrelevant of data structure) the time complexity would be
	 * O(|V|*|E|)
	 * 
	 * However if it's implemented on top of Matrix, the would need жи(|V|^3)
	 */
	public static void ShortestPath_BellmanFord_Matrix(int graph[][], int src, int dest) {		
		if (graph==null || graph.length==0 || src<0 || src>=graph.length)
			return;
			
			System.out.print("[ShortestPath_BellmanFord_Matrix]: ");
			
			// dist[k] : shortest path from src to k
			int[] dist = new int[graph.length];
			Arrays.fill(dist, MAX_WEIGHT);
			dist[src] = 0;
						
			for(int r=1; r<graph.length; r++) {
				// iterate through all edges
				for(int i=0; i<graph.length; i++) {
					for(int j=0; j<graph.length; j++) {
						// got an edge(i,j)
						if (graph[i][j] != MAX_WEIGHT) {
							// implementation specific : to prevent overflow
							if (dist[i] != MAX_WEIGHT) {
								if (dist[i] + graph[i][j] < dist[j]) {
									dist[j] = dist[i] + graph[i][j];
								}
							}							
						}						
					}
				}				
			}
		
		System.out.println(dist[dest]);			
	}
	
	/**
	 * Better implement in AdjencyList :  O(|V|*|E|)
	 * 
	 * My thinking: O(|V|*(|V|+|E|))
	 */
	public static <T> void ShortestPath_BellmanFord_AdjencyList(
			AdjencyList<T, Integer> graph, T start, T dest) {
		
		System.out.print("[ShortestPath_BellmanFord_AdjencyList]: ");
		
		final int vertices = graph.getNumOfNodes();
		HashMap<T,Integer> dist = new HashMap<T,Integer>();
		dist.put(start, 0);
		
		for(int r=1; r<vertices; r++) {
			
			Iterator<Map.Entry<T, List<Edge<T, Integer>>>> it = 
					graph.getGraph().entrySet().iterator();
			
			while(it.hasNext()) {
				Map.Entry<T, List<Edge<T, Integer>>> entry = it.next();
				
				T curr = entry.getKey();
				
				List<Edge<T, Integer>> edges = entry.getValue();				
				for(Edge<T, Integer> edge : edges) {
					if (!dist.containsKey(curr))
						continue;
					
					if (!dist.containsKey(edge.id) ||
						dist.get(curr)+edge.weight<dist.get(edge.id)) {
						dist.put(edge.id, dist.get(curr)+edge.weight);
					}
				}
			}
		}
	
		System.out.println(dist.get(dest));
	}
	
	/**
	 * Single source -> single/multiple destinations<br>
	 * 
	 * <i>Not</i> applicable to graphs with <b>Negative</b> weight edges<br>
	 * This is because Dijkstra is a Greedy approach
	 * Once a vertice is marked as visited for this loop, it would never be reevaluated again
	 * Even if there's another path with less cost to reach it later on. 
	 * And such issue could only happen when negative edges exist in the graph.
	 * 
	 * <p>
	 * Main idea:
	 * <blockquote>
	 * 		Choose node with minimum path value from Unvisited set. <br>
	 * 		And update path value for all neighbors of that node. <br>
	 * 		Repeat above until Unvisited set is empty.
	 * 
	 *  	The iteration could stop once the target node is visited.
	 *</blockquote>
	 *<p> 
	 * Time complexity:	<b>O(|V|*|V|+|E|)</b> <br>		
	 * 		"minimum value chosen" can be optimized with Priority Queue: O(|V|*log|V|+|E|)
	 */
	public static void ShortestPath_Dijkstra_Matrix(int graph[][], int src, int dest) {
		
		System.out.print("[ShortestPath_Dijkstra_Matrix]: ");
		
		final int V = graph.length;
		
		int[] dist = new int[V];
		Arrays.fill(dist, MAX_WEIGHT);
		
		HashSet<Integer> unvisited = new HashSet<Integer>();
		for(int i=0; i<V; i++)
			unvisited.add(i);
		
		int curr = src;
		dist[src]=0;
		while(!unvisited.isEmpty()) {
			// update all neighbours path cost of curr vertice
			for(int n=0; n<V; n++) {
				if (n==curr || graph[curr][n]==MAX_WEIGHT)
					continue;
				
				int c = dist[curr] + graph[curr][n];
				if(c < dist[n])
					dist[n] = c;
			}
			// mark it as "visited"
			unvisited.remove(curr);
			// choose minimum cost unvisited vertice
			int min = MAX_WEIGHT;
			for(Integer index : unvisited) {
				if (dist[index] < min) {
					min = dist[index];
					curr = index;
				}
			}
		}
		
		System.out.println(dist[dest]);
	}
	
	/**
	 * Try with PriorityQueue
	 */
	public static <T> void ShortestPath_Dijkstra_AdjencyList(AdjencyList<T, Integer> graph, T start, T dest) {
		
		System.out.print("[ShortestPath_Dijkstra_AdjencyList]: ");
		
		HashMap<T,Integer> dist = new HashMap<T,Integer>();
		PriorityQueue<PathCost<T>> unvisited = new PriorityQueue<PathCost<T>>(
				new PathComparator<T>());
		unvisited.offer(new PathCost<T>(start, 0));
		for(T v : graph.getGraph().keySet()) {
			if (v != start)
				unvisited.offer(new PathCost<T>(v, MAX_WEIGHT));
		}
		
		T curr = start;
		dist.put(start, 0);
		while(!unvisited.isEmpty()) {
			// choose minimum cost
			curr = unvisited.peek().vertice;
			
			// update costs of neighbors
			int currCost = dist.get(curr);
			List<Edge<T, Integer>> edges = graph.getNeighbors(curr);
			for(Edge<T, Integer> neighbor : edges) {
				int c = currCost+neighbor.weight;
				if(!dist.containsKey(neighbor.id) ||
				   c < dist.get(neighbor.id)) {
					dist.put(neighbor.id, c);
					
					for(PathCost<T> pathcost : unvisited) {
						if (pathcost.vertice == neighbor.id) {
							unvisited.remove(pathcost);
							break;
						}
					}
					unvisited.offer(new PathCost<T>(neighbor.id, c));
				}
			}
			// mark curr as "visited"
			unvisited.poll();
		}
		
		System.out.println(dist.get(dest));
	}
	
	//================================== Test =================================
	public static void main(String[] args) {
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	
		int[][] graph = { {1,MAX_WEIGHT,1,MAX_WEIGHT,MAX_WEIGHT}, 
						  {MAX_WEIGHT,1,1,1,MAX_WEIGHT}, 
						  {1,1,1,1,MAX_WEIGHT},
						  {MAX_WEIGHT,1,1,1,1}, 
						  {MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,1,1} };
		int startIndex = 0;
		String[] nodes = {"hit","dot","hot","lot","log"};
		
		DFS_Recursive_Matrix(graph, startIndex, nodes);
		DFS_Iterative_Matrix(graph, startIndex, nodes);			
		BFS_Recursive_Matrix(graph, startIndex, nodes);
		BFS_Iterative_Matrix(graph, startIndex, nodes);
		
		System.out.println("----------------------------------------------------------------------------------");

		int endIndex = nodes.length-1;
		ShortestPath_mitDP_Matrix(graph, startIndex, endIndex);
		ShortestPath_Floyed_Matrix(graph, startIndex, endIndex);
		ShortestPath_BellmanFord_Matrix(graph, startIndex, endIndex);
		ShortestPath_Dijkstra_Matrix(graph, startIndex, endIndex);
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		AdjencyList<String, Integer> graph1 = new AdjencyList<String, Integer>();
		graph1.insertEdge("hit", "hot", 1);
		graph1.insertEdge("hot", "dot", 3);
		graph1.insertEdge("hot", "lot", 2);
		graph1.insertEdge("dot", "lot", 1);
		graph1.insertEdge("dot", "dog", 1);
		graph1.insertEdge("lot", "log", 1);
		graph1.insertEdge("dog", "log", 1);
		graph1.insertEdge("dog", "cog", 1);
		graph1.insertEdge("log", "cog", 1);
	//	graph1.printGraph();
		
		DFS_Recursive_AdjencyList(graph1, "hit");
		DFS_Iterative_AdjencyList(graph1, "hit");
		BFS_Recursive_AdjencyList(graph1, "hit");
		BFS_Iterative_AdjencyList(graph1, "hit");
				
		System.out.println("----------------------------------------------------------------------------------");
		
		ShortestPath_mitDP_AdjencyList(graph1, "hit", "cog");
		ShortestPath_BellmanFord_AdjencyList(graph1, "hit", "cog");
		ShortestPath_Dijkstra_AdjencyList(graph1, "hit", "cog");
			
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	}
	
}
