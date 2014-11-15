package yeah.o.punch.bst;

import java.util.ArrayList;
import java.util.Stack;

public class MinDepth {

    public static int minDepth_recur(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 1;
        else if (root.left == null)
            return 1 + minDepth_recur(root.right);
        else if (root.right == null)
            return 1 + minDepth_recur(root.left);

        int l_depth = minDepth_recur(root.left);
        int r_depth = minDepth_recur(root.right);

        return 1 + (l_depth < r_depth ? l_depth : r_depth);
    }

    public static int minDepth(TreeNode root) {

        if (root == null)
            return 0;

        int min = Integer.MAX_VALUE;
        int depth = 0;

        ArrayList<TreeNode> visited = new ArrayList<TreeNode>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        visited.add(root);
        depth += 1;

        while (!stack.isEmpty()) {
            TreeNode current = stack.peek();

            if (current.left != null && !visited.contains(current.left)) {
                stack.push(current.left);
                visited.add(current.left);
                depth += 1;
            } else if (current.right != null && !visited.contains(current.right)) {
                stack.push(current.right);
                visited.add(current.right);
                depth += 1;
            } else {
                if (current.left == null && current.right == null) {
                    if (depth < min) {
                        min = depth;
                    }
                }

                stack.pop();
                depth -= 1;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.right.left = new TreeNode(4);
        root3.right.left.right = new TreeNode(5);
        TreeNode.printFromNode(root3);
        System.out.println("\n" + minDepth_recur(root3));
        System.out.println(minDepth(root3) + "\n");

        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        TreeNode.printFromNode(root4);
        System.out.println("\n" + minDepth_recur(root4));
        System.out.println(minDepth(root4) + "\n");

        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.left.left = new TreeNode(3);
        root5.left.left.left = new TreeNode(4);
        root5.left.left.left.left = new TreeNode(5);
        TreeNode.printFromNode(root5);
        System.out.println("\n" + minDepth_recur(root5));
        System.out.println(minDepth(root5) + "\n");
    }

}
