package yeah.o.punch.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostorderTraversal {

	public static List<Integer> postorderTraversal_recur(TreeNode root) {
		List<Integer> path = new ArrayList<Integer>();
		
		if(root == null) return path;
		
		if (root.left != null) {
			List<Integer> l_path = postorderTraversal_recur(root.left);
			path.addAll(l_path);
		} 
		if (root.right != null) {
			List<Integer> r_path = postorderTraversal_recur(root.right);
			path.addAll(r_path);
		} 
		path.add(root.val);
		
		return path;
	}
	
	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> path = new ArrayList<Integer>();
		List<TreeNode> helper = new ArrayList<TreeNode>();
		
		if(root == null) return path;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			TreeNode top = stack.peek();
			
			if(	(top.left == null && top.right == null) || 
				(top.left != null && top.right != null && helper.contains(top.left) && helper.contains(top.right)) || 
				(top.left == null && top.right != null && helper.contains(top.right)) ||
				(top.left != null && top.right == null && helper.contains(top.left)) ){
				helper.add(stack.pop());
			} else if (top.left != null && !helper.contains(top.left)) {
				stack.push(top.left);				
			} else if (top.right != null && !stack.contains(top.right)) {
				stack.push(top.right);
			}		
		}
		
		for(TreeNode n : helper) {
			path.add(n.val);
		}
		
		return path;
	}
	
	public static List<Integer> postorderTraversal2(TreeNode root) {
		List<Integer> path = new ArrayList<Integer>();
		
		if(root == null) return path;
		
		List<TreeNode> helper = new ArrayList<TreeNode>();
		Stack<TreeNode> s = new Stack<TreeNode>();

		s.push(root);
		while(!s.isEmpty()) {	
			TreeNode top = s.peek();
			
			if((top.left == null && top.right == null) ||
			   (top.left!=null && top.right != null && helper.contains(top.left) && helper.contains(top.right)) ||
			   (top.left!=null && helper.contains(top.left)) ||
			   (top.right!=null && helper.contains(top.right)) ) {
				helper.add(s.pop());
				continue;
			}
			
			if(top.right != null && !helper.contains(top.right)) {
				s.push(top.right);
			} 
			
			if(top.left != null && !helper.contains(top.left)) {
				s.push(top.left);
			}
		}
		for(TreeNode n : helper) {
			path.add(n.val);
		}
		return path;
	}
	
	public static void main(String[] args) {
		String[] input1 = new String[]{"1","#","2","3"};		
		TreeNode root1 = TreeNode.buildViaPreorderInput(input1);
		TreeNode.printFromNode(root1);
		List<Integer> p1 = postorderTraversal(root1);
		System.out.print("\n[Case 1]: ");
		TreeNode.printTraversalList(p1);
		System.out.println();
		
		String[] input2 = new String[]{"1","2","#","#","3","4","5"};		
		TreeNode root2 = TreeNode.buildViaPreorderInput(input2);
		TreeNode.printFromNode(root2);
		List<Integer> p2 = postorderTraversal(root2);
		System.out.print("\n[Case 2]: ");
		TreeNode.printTraversalList(p2);
		System.out.println();
		
		String[] input3 = new String[]{"37","-34","-48","#","-100","-100","48","#","#","#","#","-54","#","-71","-22","#","#","#","8"};	
		TreeNode root3 = TreeNode.buildViaPreorderInput(input3);
		TreeNode.printFromNode(root3);
		List<Integer> p3 = postorderTraversal(root3);
		System.out.print("\n[Case 3_1]: ");
		TreeNode.printTraversalList(p3);
		
		List<Integer> p3_2 = postorderTraversal_recur(root3);
		System.out.print("\n[Case 3_2]: ");
		TreeNode.printTraversalList(p3_2);
		
		List<Integer> p3_3 = postorderTraversal2(root3);
		System.out.print("\n[Case 3_3]: ");
		TreeNode.printTraversalList(p3_3);
		
		// Input:	{37,-34,-48,#,-100,-100,48,#,#,#,#,-54,#,-71,-22,#,#,#,8}
		// Output:	[-100,-34,-71,8,-22,-54,48,-48,37]
		// Expected:[-100,-34,-100,-71,8,-22,-54,48,-48,37]
		
	}

}
