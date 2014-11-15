package yeah.o.punch.bst;

import java.util.ArrayList;
import java.util.List;

public class BTLevelOrderTraversalII {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<TreeNode> prevLevel = new ArrayList<TreeNode>();
        List<TreeNode> currLevel = new ArrayList<TreeNode>();

        if (root != null)
            currLevel.add(root);

        while (!currLevel.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            for (TreeNode node : currLevel) {
                level.add(node.val);
            }
            result.add(level);

            prevLevel.clear();
            prevLevel.addAll(currLevel);
            currLevel.clear();

            for (TreeNode node : prevLevel) {
                if (node.left != null)
                    currLevel.add(node.left);
                if (node.right != null)
                    currLevel.add(node.right);
            }
        }

        return result;
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<TreeNode> prevLevel = new ArrayList<TreeNode>();
        List<TreeNode> currLevel = new ArrayList<TreeNode>();

        if (root != null)
            currLevel.add(root);

        while (!currLevel.isEmpty()) {
            List<Integer> curr = new ArrayList<Integer>();
            for (TreeNode node : currLevel) {
                curr.add(node.val);
            }
            result.add(0, curr);

            prevLevel.clear();
            prevLevel.addAll(currLevel);
            currLevel.clear();

            for (TreeNode node : prevLevel) {
                if (node.left != null)
                    currLevel.add(node.left);
                if (node.right != null)
                    currLevel.add(node.right);
            }
        }

        return result;
    }

    public static void printResult(List<List<Integer>> list, String type) {
        System.out.println("\n======" + type + "======");
        System.out.println("[");
        for (List<Integer> level : list) {
            System.out.print("	[");
            for (int val : level) {
                System.out.print(val + ",");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        TreeNode.printFromNode(root1);
        List<List<Integer>> res1_1 = levelOrderBottom(root1);
        printResult(res1_1, "LevelOrderBottom");
        List<List<Integer>> res1_2 = levelOrder(root1);
        printResult(res1_2, "LevelOrder");

        TreeNode root2 = new TreeNode(11);
        TreeNode.printFromNode(root2);
        List<List<Integer>> res2_1 = levelOrderBottom(root2);
        printResult(res2_1, "LevelOrderBottom");
        List<List<Integer>> res2_2 = levelOrder(root2);
        printResult(res2_2, "LevelOrder");

        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(7);
        TreeNode.printFromNode(root3);
        List<List<Integer>> res3_1 = levelOrderBottom(root3);
        printResult(res3_1, "LevelOrderBottom");
        List<List<Integer>> res3_2 = levelOrder(root3);
        printResult(res3_2, "LevelOrder");

        TreeNode root4 = null;
        TreeNode.printFromNode(root4);
        List<List<Integer>> res4_1 = levelOrderBottom(root4);
        printResult(res4_1, "LevelOrderBottom");
        List<List<Integer>> res4_2 = levelOrder(root4);
        printResult(res4_2, "LevelOrder");
    }

}
