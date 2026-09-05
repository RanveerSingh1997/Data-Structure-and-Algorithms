package trees;

import utils.TreeNode;

/**
 * ============================================================================
 * Problem: Lowest Common Ancestor of a Binary Tree
 * LeetCode #236 | Difficulty: Medium
 * Company: Google Interview Question (Extremely High Frequency)
 * Link: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
 * in the tree (p and q).
 *
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor is defined between two nodes p and q as the lowest node
 * in T that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: root = [3, 5, 1, 6, 2, 0, 8, null, null, 7, 4], p = 5, q = 1
 *             3
 *           /   \
 *          5     1
 *         / \   / \
 *        6   2 0   8
 *           / \
 *          7   4
 *   Output: 3
 *   Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 *   Input: root = [3, 5, 1, 6, 2, 0, 8, null, null, 7, 4], p = 5, q = 4
 *   Output: 5
 *   Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself.
 *
 * Example 3:
 *   Input: p = 7, q = 4
 *   Output: 2
 *   Explanation: Both 7 and 4 are children of 2.
 *
 * ⚙️ CONSTRAINTS:
 *  - The number of nodes in the tree is in the range [2, 10^5].
 *  - -10^9 <= Node.val <= 10^9
 *  - All Node.val are unique.
 *  - p != q
 *  - p and q are guaranteed to exist in the tree.
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - Can you use bottom-up recursion (post-order DFS)?
 *  - Base cases: If `root == null`, `root == p`, or `root == q`, what do you return?
 *  - If searching in left subtree returns non-null AND searching in right subtree returns non-null,
 *    what does that tell you about the current `root`?
 *  - What if only one subtree returns non-null?
 */
public class LowestCommonAncestor {

    /**
     * Finds the lowest common ancestor of nodes p and q.
     *
     * @param root root of the binary tree
     * @param p    target node p
     * @param q    target node q
     * @return lowest common ancestor node
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // TODO: Implement your solution here
        return null;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        LowestCommonAncestor solver = new LowestCommonAncestor();

        System.out.println("=== Testing: LeetCode 236 - Lowest Common Ancestor ===");

        // Construct tree:
        //             3
        //           /   \
        //          5     1
        //         / \   / \
        //        6   2 0   8
        //           / \
        //          7   4
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2, n7, n4);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n5 = new TreeNode(5, n6, n2);
        TreeNode n1 = new TreeNode(1, n0, n8);
        TreeNode root = new TreeNode(3, n5, n1);

        // Test 1: LCA(5, 1) -> 3
        TreeNode res1 = solver.lowestCommonAncestor(root, n5, n1);
        System.out.println("Test 1 Result: " + (res1 != null ? res1.val : "null") + " | Expected: 3");
        if (res1 != null && res1.val == 3) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: LCA(5, 4) -> 5
        TreeNode res2 = solver.lowestCommonAncestor(root, n5, n4);
        System.out.println("Test 2 Result: " + (res2 != null ? res2.val : "null") + " | Expected: 5");
        if (res2 != null && res2.val == 5) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: LCA(7, 4) -> 2
        TreeNode res3 = solver.lowestCommonAncestor(root, n7, n4);
        System.out.println("Test 3 Result: " + (res3 != null ? res3.val : "null") + " | Expected: 2");
        if (res3 != null && res3.val == 2) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
