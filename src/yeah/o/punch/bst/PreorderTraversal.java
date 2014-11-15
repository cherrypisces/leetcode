package yeah.o.punch.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PreorderTraversal {
	
	public static List<Integer> preorderTraversal_recur(TreeNode root) {
        List<Integer> path = new ArrayList<Integer>();
		
		if(root == null) return path;
	
		path.add(root.val);	
		if (root.left != null) {
			List<Integer> l_path = preorderTraversal(root.left);
			path.addAll(l_path);
		} 
		if (root.right != null) {
			List<Integer> r_path = preorderTraversal(root.right);
			path.addAll(r_path);
		} 
		
		return path;	
	}

	public static List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> path = new ArrayList<Integer>();
		
		if(root == null) return path;
		
		Stack<TreeNode> s = new Stack<TreeNode>();

		s.push(root);		
		while(!s.isEmpty()) {			
			TreeNode top = s.peek();
			
			path.add(s.pop().val);
			
			if(top.right != null) {
				s.push(top.right);
			} 
			
			if(top.left != null) {
				s.push(top.left);
			}
		}
		
		return path;
	}
	
	public static void main(String[] args) {
		
		String[] input1 = new String[]{"1","#","2","3"};		
		TreeNode root1 = TreeNode.buildViaPreorderInput(input1);
		TreeNode.printFromNode(root1);
		List<Integer> p1_1 = preorderTraversal(root1);
		System.out.print("\n[Case 1_Iter]: ");
		TreeNode.printTraversalList(p1_1);
		List<Integer> p1_2 = preorderTraversal_recur(root1);
		System.out.print("[Case 1_Recur]: ");
		TreeNode.printTraversalList(p1_2);
		System.out.println();
		
		String[] input2 = new String[]{"1","2","#","#","3","4","5"};		
		TreeNode root2 = TreeNode.buildViaPreorderInput(input2);
		TreeNode.printFromNode(root2);
		List<Integer> p2_1 = preorderTraversal(root2);
		System.out.print("\n[Case 2_Iter]: ");
		TreeNode.printTraversalList(p2_1);
	//	List<Integer> p2_2 = preorderTraversal_recur(root2);
		System.out.print("[Case 2_Recur]: ");
	//	TreeNode.printTraversalList(p2_2);
		System.out.println();
		
		String[] input3 = new String[]{"37","-34","-48","#","-100","-100","48","#","#","#","#","-54","#","-71","-22","#","#","#","8"};	
		TreeNode root3 = TreeNode.buildViaPreorderInput(input3);
		TreeNode.printFromNode(root3);
		List<Integer> p3_1 = preorderTraversal(root3);
		System.out.print("\n[Case 3_Iter]: ");
		TreeNode.printTraversalList(p3_1);
//		List<Integer> p3_2 = preorderTraversal_recur(root3);
		System.out.print("[Case 3_Recur]: ");
//		TreeNode.printTraversalList(p3_2);
//		System.out.println();

	}

}
