package yeah.o.punch.bst;

import java.util.Stack;

public class SymmetricTree {
	
    public static boolean isSymmetric(TreeNode root) {
        if((root == null) ||
           (root.left == null) && (root.right == null)) 
        	return true;        
        
        if(((root.left == null) && (root.right != null)) ||
           ((root.left != null) && (root.right == null)) ||
           (root.left.val != root.right.val))
        	return false;
        
        return isSymmetric(root.left.left, root.right.right) && 
        	   isSymmetric(root.left.right, root.right.left);
    }
    
    public static boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left == null && right == null)
        	return true;
        
        if((left == null && right != null) ||
           (left != null && right == null) ||
           (left.val != right.val))
        	return false;
        
        return isSymmetric(left.left, right.right) && 
         	   isSymmetric(left.right,right.left);
    }
    
    public static boolean isSymmetricIterative(TreeNode root) {
    	if(root == null) return true;
  	
    	Stack<TreeNode> l_stack = new Stack<TreeNode>();
    	l_stack.push(root.left);   	
    	Stack<TreeNode> r_stack = new Stack<TreeNode>(); 
    	r_stack.push(root.right); 
    
    	while(!l_stack.isEmpty() && !r_stack.isEmpty()) {
    		TreeNode leftPtr 	= l_stack.peek();
    		TreeNode rightPtr 	= r_stack.peek(); 
    		
    		if(leftPtr == null && rightPtr == null)	{
    			l_stack.pop();
    			r_stack.pop();
    			continue;
    		} 
 	
	        if((leftPtr == null && rightPtr != null) ||
	           (leftPtr != null && rightPtr == null) ||
	           (leftPtr.val != rightPtr.val))
	        	return false;

    		//leftPtr != null && rightPtr != null && leftPtr.val == rightPtr.val	        
	        l_stack.pop();
	        r_stack.pop();
	        
    		l_stack.push(leftPtr.right);
    		l_stack.push(leftPtr.left);
    		
    		r_stack.push(rightPtr.left);
    		r_stack.push(rightPtr.right);
    	}
    	
    	return true;    	
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
		System.out.println("\n"+isSymmetric(root1));
		System.out.println(isSymmetricIterative(root1)+"\n");
		
		TreeNode root2 = new TreeNode(1);
		root2.left = new TreeNode(2);
		root2.right = new TreeNode(2);
		root2.right.right = new TreeNode(3);
		root2.left.right = new TreeNode(3);
		TreeNode.printFromNode(root2);
		System.out.println("\n"+isSymmetric(root2));
		System.out.println(isSymmetricIterative(root2)+"\n");
		
		TreeNode root3 = new TreeNode(1);
		TreeNode.printFromNode(root3);
		System.out.println("\n"+isSymmetric(root3));
		System.out.println(isSymmetricIterative(root3)+"\n");

	}

}
