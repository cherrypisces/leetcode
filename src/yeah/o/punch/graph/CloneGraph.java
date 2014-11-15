package yeah.o.punch.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * http://oj.leetcode.com/problems/clone-graph/
 * 
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * OJ's undirected graph serialization:
 * 
 * Nodes are labeled uniquely.
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * 
 * Visually, the graph looks like the following:
 * 
 *    1 
 *   / \
 *  /   \
 * 0 --- 2
 *	    / \
 *	    \_/
 * 
 * @author CherryPisces
 *
 */

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;
	
	UndirectedGraphNode(int x) { 
		label = x; 
		neighbors = new ArrayList<UndirectedGraphNode>(); 
	}
};


public class CloneGraph {
	
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node == null) return null;
		
		HashMap<Integer, UndirectedGraphNode> _nodesMap = new HashMap<Integer, UndirectedGraphNode>();		
		
		Queue<UndirectedGraphNode> _queue = new LinkedList<UndirectedGraphNode>();
		_queue.offer(node);
		
		while(!_queue.isEmpty()) {
			UndirectedGraphNode _currNode = _queue.poll();
			
			if (!_nodesMap.containsKey(_currNode.label)) {
				_nodesMap.put(_currNode.label, new UndirectedGraphNode(_currNode.label));
			} 

			if(_currNode.neighbors != null && _currNode.neighbors.size()>0) {
				
				for(UndirectedGraphNode neighbor : _currNode.neighbors) {
					
					if (!_nodesMap.containsKey(neighbor.label)) {
						_queue.offer(neighbor);
						_nodesMap.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
					}					
					_nodesMap.get(_currNode.label).neighbors.add(_nodesMap.get(neighbor.label));
				}
			}
		}
		
		////////////////////////////////////////////////
		Iterator it = _nodesMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, UndirectedGraphNode> pair = (Map.Entry<Integer, UndirectedGraphNode>)it.next();
			System.out.print("["+pair.getKey()+"]: ");
			for (UndirectedGraphNode neighbor : ((UndirectedGraphNode)(pair.getValue())).neighbors) {
				System.out.print(neighbor.label+", ");
			}
			System.out.println("#");
		}
		////////////////////////////////////////////////
		
		return _nodesMap.get(node.label);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UndirectedGraphNode g1_0 = new UndirectedGraphNode(0);
		UndirectedGraphNode g1_1 = new UndirectedGraphNode(1);
		UndirectedGraphNode g1_2 = new UndirectedGraphNode(2);
		g1_0.neighbors.add(g1_1);
		g1_0.neighbors.add(g1_2);
		g1_1.neighbors.add(g1_2);
		g1_2.neighbors.add(g1_2);
		
		UndirectedGraphNode test1 = cloneGraph(g1_0);
		
		
	}

}
