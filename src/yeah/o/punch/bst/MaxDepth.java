package yeah.o.punch.bst;

import java.util.ArrayList;
import java.util.Stack;

public class MaxDepth {
	
	/**
	 * Time Limit Exceeded
	 * @param root
	 * @return
	 */
    public static int maxDepth_Recur(TreeNode root) {
        if(root==null) 
        	return 0;
        
        if(root.left==null && root.right==null) 
        	return 1;
        
        int l_depth = maxDepth_Recur(root.left);
        int r_depth = maxDepth_Recur(root.right);
        
        return 1 + (l_depth >= r_depth ? l_depth : r_depth);
    }
    
    public static int maxDepth(TreeNode root) {
        if(root==null) 
        	return 0;
        
        int max = -1;
        int depth = 0;
        
        ArrayList<TreeNode> visited = new ArrayList<TreeNode>();        
       
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        visited.add(root);
        depth += 1;
       
        while(!stack.isEmpty()) {
        	TreeNode current = stack.peek();
        	
        	if (current.left != null && !visited.contains(current.left)) {
        		stack.push(current.left);
        		visited.add(current.left);        		
        		depth += 1;
        	} else if (current.right != null && !visited.contains(current.right)) {
        		stack.push(current.right);
        		visited.add(current.right);        		
        		depth += 1;
        	} else  {
        		if (current.left==null && current.right==null) {        		
	        		if (depth > max) {
	        			max = depth;
	        		}
        		}
        		
        		stack.pop();
        		depth -= 1;
        	}
        }
        
        return max;
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
		System.out.println("\n"+maxDepth(root1));
		System.out.println("\n"+maxDepth_Recur(root1)+"\n");
		
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(2);
		root2.right.right = new TreeNode(3);
		root2.left.right = new TreeNode(3);
		TreeNode.printFromNode(root2);
		System.out.println("\n"+maxDepth(root2));
		System.out.println("\n"+maxDepth_Recur(root2)+"\n");
				
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(2);
		root3.right = new TreeNode(3);
		root3.right.left = new TreeNode(4);
		root3.right.left.right = new TreeNode(5);
		TreeNode.printFromNode(root3);
		System.out.println("\n"+maxDepth(root3));
		System.out.println("\n"+maxDepth_Recur(root3)+"\n");
	}

}
