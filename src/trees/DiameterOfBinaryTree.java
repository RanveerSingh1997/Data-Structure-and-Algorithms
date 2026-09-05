package trees;

import utils.TreeNode;

/**
 * ============================================================================
 * Problem: Diameter of Binary Tree
 * LeetCode #543 | Difficulty: Easy
 * Company: Google Interview Question (Extremely High Frequency)
 * Link: https://leetcode.com/problems/diameter-of-binary-tree/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two
 * nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 * (Edges = Nodes along path - 1).
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: root = [1, 2, 3, 4, 5]
 *          1
 *         / \
 *        2   3
 *       / \
 *      4   5
 *   Output: 3
 *   Explanation: The longest path is [4, 2, 1, 3] or [5, 2, 1, 3], which has length 3 edges.
 *
 * Example 2:
 *   Input: root = [1, 2]
 *   Output: 1
 *
 * Example 3 (Path does NOT pass through root):
 *   Input: A tree where left subtree has deep branches on both its children.
 *   Explanation: The longest path can exist entirely inside a subtree without crossing root.
 *
 * ⚙️ CONSTRAINTS:
 *  - The number of nodes in the tree is in the range [1, 10^4].
 *  - -100 <= Node.val <= 100
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - At any given node, what is the longest path that has this node as its highest point?
 *    (leftSubtreeDepth + rightSubtreeDepth).
 *  - What should the recursive helper function return to its parent?
 *    (Height of current subtree: 1 + max(leftDepth, rightDepth)).
 *  - How can you maintain the global maximum diameter during a single post-order traversal?
 */
public class DiameterOfBinaryTree {

    /**
     * Calculates the diameter (longest edge-path) of a binary tree.
     *
     * @param root root node of the binary tree
     * @return maximum number of edges in any path
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // TODO: Implement your solution here
        return 0;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        DiameterOfBinaryTree solver = new DiameterOfBinaryTree();

        System.out.println("=== Testing: LeetCode 543 - Diameter of Binary Tree ===");

        // Test 1: Standard tree [1, 2, 3, 4, 5] -> 3
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3)
        );
        int res1 = solver.diameterOfBinaryTree(root1);
        System.out.println("Test 1 Result: " + res1 + " | Expected: 3");
        if (res1 == 3) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [1, 2] -> 1
        TreeNode root2 = new TreeNode(1, new TreeNode(2), null);
        int res2 = solver.diameterOfBinaryTree(root2);
        System.out.println("Test 2 Result: " + res2 + " | Expected: 1");
        if (res2 == 1) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Path does not pass through root -> 4
        //         1
        //        /
        //       2
        //      / \
        //     3   4
        //    /     \
        //   5       6
        TreeNode root3 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3, new TreeNode(5), null),
                        new TreeNode(4, null, new TreeNode(6))
                ),
                null
        );
        int res3 = solver.diameterOfBinaryTree(root3);
        System.out.println("Test 3 Result: " + res3 + " | Expected: 4");
        if (res3 == 4) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
