package yeah.o.punch.bst;

import java.util.ArrayList;
import java.util.Stack;

public class BalancedBinaryTree {

	/**
	 * a height-balanced binary tree is defined as a binary tree in which the depth of 
	 * the two subtrees of every node never differ by more than 1.
	 * 
	 * At first, My understand:
	 * (Wrong) We can find two paths from root to leaf node with depth1 and depth2 such that depth1 - depth2 > 1
	 * 
	 * e.g. Below is a balanced tree
	 * 
	 			 	 1
	 			   /   \
				  2	    2
	 	         / \   / \
				3  3   3 3
 			   / \/ \ / \
			  4  44 4 4 4
			 / \
			 5  5
	 */
	
    public static boolean isBalanced(TreeNode root) {
    	if (root == null) return true;
    	
    	int l_depth = depth(root.left);
    	int r_depth = depth(root.right);

    	if (Math.abs(l_depth-r_depth) > 1)
    		return false;
    	
    	return isBalanced(root.left) && isBalanced(root.right);
    }
    
    public static int depth(TreeNode node) {
    	if(node == null) return 0;

    	int l_depth = depth(node.left);
    	int r_depth = depth(node.right);
    	
    	return 1+ (l_depth>=r_depth ? l_depth : r_depth);
    }
    
	public static void main(String[] args) {
		TreeNode root1 = new TreeNode(1);
		root1.left = new TreeNode(2);
		root1.right = new TreeNode(2);
		root1.left.left = new TreeNode(3);
		root1.right.right = new TreeNode(3);
		root1.left.right = new TreeNode(4);
		root1.right.left = new TreeNode(4);
		TreeNode.printFromNode(root1);
		System.out.println("\n"+isBalanced(root1));
		
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(2);
		root2.right.right = new TreeNode(3);
		TreeNode.printFromNode(root2);
		System.out.println("\n"+isBalanced(root2));
		
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(2);
		root3.right = new TreeNode(3);
		root3.right.left = new TreeNode(4);
		root3.right.left.right = new TreeNode(5);
		TreeNode.printFromNode(root3);
		System.out.println("\n"+isBalanced(root3));
		
		// answer : false
		TreeNode root4 = new TreeNode(1);
		root4.right = new TreeNode(2);
		root4.right.right = new TreeNode(3);
		TreeNode.printFromNode(root4);
		System.out.println("\n"+isBalanced(root4));
			
		// answer : true
		TreeNode root5 = new TreeNode(1);
		root5.left = new TreeNode(2);
		root5.right = new TreeNode(2);
		
		root5.left.left = new TreeNode(3);
		root5.left.right = new TreeNode(3);
		root5.right.left = new TreeNode(3);
		root5.right.right = new TreeNode(3);
		
		root5.left.left.left = new TreeNode(4);
		root5.left.left.right = new TreeNode(4);
		root5.left.right.left = new TreeNode(4);
		root5.left.right.right = new TreeNode(4);
		root5.right.left.left = new TreeNode(4);
		root5.right.left.right = new TreeNode(4);
		
		root5.left.left.left.left = new TreeNode(5);
		root5.left.left.left.right = new TreeNode(5);
		
		TreeNode.printFromNode(root5);
		System.out.println("\n"+isBalanced(root5));
		
		
		// answer : false
		TreeNode root6 = new TreeNode(1);
		root6.left = new TreeNode(2);
		root6.right = new TreeNode(2);
		
		root6.left.left = new TreeNode(3);
		root6.left.right = new TreeNode(3);
	
		root6.left.left.left = new TreeNode(4);
		root6.left.left.right = new TreeNode(4);
		
		TreeNode.printFromNode(root6);
		System.out.println("\n"+isBalanced(root6));
	}

}
