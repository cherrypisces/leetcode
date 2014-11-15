package yeah.o.punch.bst;

public class SameTree {
	
    public static boolean isSameTree(TreeNode p, TreeNode q) {
    	if (p==null && q==null)
    		return true;
    	else if ((p==null && q!=null) ||
    			 (p!=null && q==null) ||
    			 (p.val != q.val))
        	return false;

        return isSameTree(p.left, q.left) && 
        	   isSameTree(p.right, q.right);    	
    }
    
	public static void main(String[] args) {
		System.out.print("[Case 1]: ");
		String[] input1 = new String[]{"1","#","2","3"};
		TreeNode root1 = TreeNode.buildViaPreorderInput(input1);
		String[] input2 = new String[]{"1","2","#","4"};
		TreeNode root2 = TreeNode.buildViaPreorderInput(input2);		
		System.out.println(isSameTree(root1,root2));

	}

}
