package yeah.o.punch.bst;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(int x) { val = x; left = right = null; }
    
    /** build a tree via input format as a string list */
    public static TreeNode buildViaPreorderInput(String[] values) {
    	TreeNode root = null;
    	Stack<TreeNode> stack = new Stack<TreeNode>();
   
    	for(String val : values) {
    		int value;
    		if(val.equals("#")) {
    			value = Integer.MIN_VALUE;
    		} else {
    			value = Integer.parseInt(val);
    		}    		
    		
    		if(root == null) {
    			root = new TreeNode(value);
    			stack.push(root);
    			continue;
    		}

			TreeNode node = new TreeNode(value);
    		TreeNode parent = stack.peek();
    		if(parent.left == null) {
    			parent.left = node;
    		} else {
    			parent.right = node;
    			stack.pop();
    		}
			if(value != Integer.MIN_VALUE)
				stack.push(node);
    	}
    	
    	removePoundKeyNode(root);
    	
    	return root;
    }
    
    
    /**
     * OJ's Binary Tree Serialization:
     * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
     * 
     * Here's an example:
     * 
     *   	1
     *     / \
     * 	  2   3
     * 		  /
     * 		 4
     * 		  \
     * 		   5
     * 
     * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
     * 
     */
    public static TreeNode buildViaLevelorderInput(String[] values) {
    	TreeNode root = null;
    	
    	for(String val : values) {
    		
    	}
    	
    	return root;
    }
    
    private static void removePoundKeyNode (TreeNode node) {
    	if(node == null) return;
 
    	if(node.left != null) {
    		if(node.left.val == Integer.MIN_VALUE)
    			node.left = null;
    		else
    			removePoundKeyNode(node.left);
    	}
    	
    	if(node.right != null) {
    		if(node.right.val == Integer.MIN_VALUE)
    			node.right = null;
    		else
    			removePoundKeyNode(node.right);
    	}
    }
    
	
	public static void printTraversalList(List<Integer> path) {
		System.out.print("[");
		for(Integer v : path) {
			System.out.print(v + ",");
		}
		System.out.println("]");
	}
    
    /** pre order **/
    public static void printFromNode (TreeNode node) {
    	if(node == null) return;
    	
    	System.out.print(node.val+",");
    	if(node.left != null) { 
    		System.out.print("["+node.val+"->left]");
    		printFromNode(node.left);
    	}
    	if(node.right != null) {
    		System.out.print("["+node.val+"->right]");
    		printFromNode(node.right);
    	}
    }
    
    public static int heightFromNode (TreeNode node) {
    	if(node == null) 
    		return 0;
    	else if (node.left == null && node.right == null) 
    		return 1;
    	
    	int leftHeight 	= heightFromNode(node.left);
    	int rightHeight = heightFromNode(node.right);
    	
    	return 1 + (leftHeight>=rightHeight ? leftHeight : rightHeight);    	
    }
    
    /** Level-order traversal */
    public static Queue<TreeNode> levelOrderTraversal (TreeNode node) {
    	Queue<TreeNode> path	= new LinkedList<TreeNode>();
    	
    	if(node == null) return path;

    	Queue<TreeNode> queue	= new LinkedList<TreeNode>();
    	queue.offer(node);  // add root to queue
 
    	do {
    		TreeNode head = queue.peek();
    		
			if (head.left != null) {
				queue.offer(head.left);
			} else {
				TreeNode NullNode = new TreeNode(Integer.MIN_VALUE);
				queue.offer(NullNode);
			}

			if (head.right != null) {
				queue.offer(head.right);
			} else {
				TreeNode NullNode = new TreeNode(Integer.MIN_VALUE);
				queue.offer(NullNode);
			}

			path.offer(queue.poll());
    		
    	} while (!queue.isEmpty());
    
    	return path;
    }
    
    /** print the tree in a pretty direct way */
    public static void printTreeLevelByLevel(TreeNode node) {
    	int height = heightFromNode(node);
    	Queue<TreeNode> queue = levelOrderTraversal(node);
    	
    	// print from top to bottom
    	int numberOfNodesAtCurrLevel = 1;
    	for(int l=height-1; l>=1; l--) {
    		if(l < height-1)
    			numberOfNodesAtCurrLevel *= 2;
    		
    		for(int n=1; n<=numberOfNodesAtCurrLevel; n++) {
    			TreeNode tmp = queue.poll();
 
    		}
    		
    		// new line for each level
    		System.out.println();
    	}
    }
    
    
	public static void main(String[] args) {
		System.out.print("[Case 1]: ");
		String[] input1 = new String[]{"1","#","2","3"};
		TreeNode root1 = TreeNode.buildViaPreorderInput(input1);		
		TreeNode.printFromNode(root1);		

		System.out.print("\n[Case 2]: ");		
		String[] input2 = new String[]{"1","2","3","#","4"};
		TreeNode root2 = TreeNode.buildViaPreorderInput(input2);		
		TreeNode.printFromNode(root2);
		
		
		String[] input3 = new String[]{"10","5","15"};
		TreeNode root3 = TreeNode.buildViaPreorderInput(input3);
		TreeNode.printTreeLevelByLevel(root3);
	}
}
