package yeah.o.punch.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathSum {

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        if (root.left == null && root.right == null)
            return sum == root.val;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static boolean hasPathSum_Iterative(TreeNode root, int sum) {
        if (root == null)
            return false;

        ArrayList<TreeNode> visited = new ArrayList<TreeNode>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        visited.add(root);

        int tmpSum = root.val;

        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();

            if (curr.left != null && !visited.contains(curr.left)) {
                stack.push(curr.left);
                visited.add(curr.left);
                tmpSum += curr.left.val;
            } else if (curr.right != null && !visited.contains(curr.right)) {
                stack.push(curr.right);
                visited.add(curr.right);
                tmpSum += curr.right.val;
            } else if (curr.left == null && curr.right == null) {
                if (tmpSum == sum)
                    return true;

                stack.pop();
                tmpSum -= curr.val;
            } else if ((curr.left == null && visited.contains(curr.right))
                    || (curr.right == null && visited.contains(curr.left))
                    || (visited.contains(curr.left) && visited.contains(curr.right))) {
                stack.pop();
                tmpSum -= curr.val;
            }
        }

        return false;
    }

    // //////////////////////////////////////////////////////////////////////////////////////

    public static List<List<Integer>> hasPathSum_Recur(TreeNode root, int sum) {
        List<List<Integer>> all = new ArrayList<List<Integer>>();

        if (root == null)
            return all;

        List<Integer> path = new ArrayList<Integer>();

        hasPathSum_helper(all, path, root, sum);

        return all;
    }

    public static void hasPathSum_helper(List<List<Integer>> all, List<Integer> path,
            TreeNode root, int sum) {
        if (root == null)
            return;

        path.add(root.val);

        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                List<Integer> p = new ArrayList<Integer>();
                p.addAll(path);
                all.add(p);
            }

            path.remove(path.size() - 1);
            return;
        }

        hasPathSum_helper(all, path, root.left, sum - root.val);
        hasPathSum_helper(all, path, root.right, sum - root.val);
        // has process all path from this node
        path.remove(path.size() - 1);
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static List<List<Integer>> hasPathSum_All(TreeNode root, int sum) {
        List<List<Integer>> all = new ArrayList<List<Integer>>();

        if (root == null)
            return all;

        List<Integer> path = new ArrayList<Integer>();

        ArrayList<TreeNode> visited = new ArrayList<TreeNode>();

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        visited.add(root);
        path.add(root.val);

        int tmpSum = root.val;

        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();

            if (curr.left != null && !visited.contains(curr.left)) {
                stack.push(curr.left);
                visited.add(curr.left);
                path.add(curr.left.val);
                tmpSum += curr.left.val;
            } else if (curr.right != null && !visited.contains(curr.right)) {
                stack.push(curr.right);
                visited.add(curr.right);
                path.add(curr.right.val);
                tmpSum += curr.right.val;
            } else if (curr.left == null && curr.right == null) {
                if (tmpSum == sum) {
                    List<Integer> p = new ArrayList<Integer>();
                    p.addAll(path);
                    all.add(p);
                }

                stack.pop();
                path.remove(path.size() - 1);
                tmpSum -= curr.val;
            } else if ((curr.left == null && visited.contains(curr.right))
                    || (curr.right == null && visited.contains(curr.left))
                    || (visited.contains(curr.left) && visited.contains(curr.right))) {
                stack.pop();
                path.remove(path.size() - 1);
                tmpSum -= curr.val;
            }
        }

        return all;
    }

    public static void printAllPaths(List<List<Integer>> all) {
        System.out.println("[");

        if (all != null) {
            for (List<Integer> p : all) {
                System.out.print("  [");
                for (Integer n : p) {
                    System.out.print(n + ",");
                }
                System.out.println("],");
            }
        }

        System.out.println("]");
    }

    public static void main(String[] args) {
        // answer : true
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);

        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);

        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.left = new TreeNode(5);
        root1.right.right.right = new TreeNode(1);

        TreeNode.printFromNode(root1);
        System.out.println("\n" + hasPathSum(root1, 22));
        System.out.println("\n" + hasPathSum_Iterative(root1, 22));
        printAllPaths(hasPathSum_All(root1, 22));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printAllPaths(hasPathSum_Recur(root1, 22));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // answer : true
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(2);
        TreeNode.printFromNode(root2);
        System.out.println("\n" + hasPathSum(root2, 5));
        System.out.println("\n" + hasPathSum_Iterative(root2, 5));
        printAllPaths(hasPathSum_All(root2, 5));

        // answer : false
        TreeNode root3 = null;
        TreeNode.printFromNode(root3);
        System.out.println("\n" + hasPathSum(root3, 0));
        System.out.println("\n" + hasPathSum_Iterative(root3, 0));
        printAllPaths(hasPathSum_All(root3, 0));
    }

}
