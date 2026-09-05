package trees;

import utils.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================================
 * Problem: Invert Binary Tree
 * LeetCode #226 | Difficulty: Easy
 * Company: Google Interview Question
 * Link: https://leetcode.com/problems/invert-binary-tree/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Inverting a tree means that for every node, its left and right children are swapped.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: root = [4, 2, 7, 1, 3, 6, 9]
 *        4                  4
 *      /   \              /   \
 *     2     7     =>     7     2
 *    / \   / \          / \   / \
 *   1   3 6   9        9   6 3   1
 *   Output: [4, 7, 2, 9, 6, 3, 1]
 *
 * Example 2:
 *   Input: root = [2, 1, 3]
 *       2                 2
 *      / \       =>      / \
 *     1   3             3   1
 *   Output: [2, 3, 1]
 *
 * Example 3:
 *   Input: root = []
 *   Output: []
 *
 * ⚙️ CONSTRAINTS:
 *  - The number of nodes in the tree is in the range [0, 100].
 *  - -100 <= Node.val <= 100
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - How can you solve this recursively using post-order or pre-order traversal?
 *  - What is your base case when root is null?
 *  - Follow-up Google question: "How would you solve this iteratively using a Queue (BFS)
 *    to prevent StackOverflowError if the tree is deeply skewed (depth 100,000)?"
 */
public class InvertBinaryTree {

    /**
     * Inverts the given binary tree and returns its root.
     *
     * @param root root node of the binary tree
     * @return inverted tree root
     */
    public TreeNode invertTree(TreeNode root) {
        // TODO: Implement your solution here
        return null;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        InvertBinaryTree solver = new InvertBinaryTree();

        System.out.println("=== Testing: LeetCode 226 - Invert Binary Tree ===");

        // Test 1: [4, 2, 7, 1, 3, 6, 9]
        TreeNode root1 = new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9))
        );
        System.out.println("Test 1 Input:    " + treeToString(root1));
        TreeNode result1 = solver.invertTree(root1);
        System.out.println("Test 1 Output:   " + treeToString(result1));
        System.out.println("Test 1 Expected: [4, 7, 2, 9, 6, 3, 1]");

        if (result1 != null && result1.left != null && result1.left.val == 7 && result1.right != null && result1.right.val == 2) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [2, 1, 3]
        TreeNode root2 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode result2 = solver.invertTree(root2);
        if (result2 != null && result2.left != null && result2.left.val == 3 && result2.right != null && result2.right.val == 1) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Null root
        TreeNode result3 = solver.invertTree(null);
        if (result3 == null) {
            System.out.println("  [PASS] Test 3 (Null root)");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }

    private static String treeToString(TreeNode root) {
        if (root == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr != null) {
                sb.append(curr.val).append(", ");
                q.offer(curr.left);
                q.offer(curr.right);
            } else {
                sb.append("null, ");
            }
        }
        String res = sb.toString().replaceAll("(, null)*, $", "");
        return res + "]";
    }
}
