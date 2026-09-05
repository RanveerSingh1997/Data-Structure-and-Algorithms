package utils;

/**
 * Standard Binary Tree Node used in most LeetCode problems.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Parses a LeetCode level-order string (e.g. "[4, 2, 7, 1, 3, 6, 9]") into a TreeNode.
     */
    public static TreeNode fromLeetCode(String data) {
        return TreeVisualizer.fromLeetCode(data);
    }

    /**
     * Prints this tree as a 2D ASCII diagram.
     */
    public void print() {
        TreeVisualizer.printTree(this);
    }

    @Override
    public String toString() {
        return TreeVisualizer.toLeetCode(this);
    }
}
