package trees;

import utils.TreeNode;

/**
 * ============================================================================
 * Problem: Validate Binary Search Tree
 * LeetCode #98 | Difficulty: Medium
 * Company: Google Interview Question (BST Invariant Classic)
 * Link: https://leetcode.com/problems/validate-binary-search-tree/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *  1. The left subtree of a node contains only nodes with keys strictly less than the node's key.
 *  2. The right subtree of a node contains only nodes with keys strictly greater than the node's key.
 *  3. Both the left and right subtrees must also be binary search trees.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: root = [2, 1, 3]
 *       2
 *      / \
 *     1   3
 *   Output: true
 *
 * Example 2 (Classic Ancestor Violation Trap):
 *   Input: root = [5, 1, 4, null, null, 3, 6]
 *         5
 *        / \
 *       1   4
 *          / \
 *         3   6
 *   Output: false
 *   Explanation: The root node's value is 5 but its right child's value is 4 (4 < 5).
 *
 * Example 3 (Deep Invariant Violation):
 *   Input: root = [5, 4, 6, null, null, 3, 7]
 *         5
 *        / \
 *       4   6
 *          / \
 *         3   7
 *   Output: false
 *   Explanation: Node 3 is in the right subtree of 5, but 3 < 5!
 *                Comparing only a node with its immediate children is NOT enough.
 *
 * ⚙️ CONSTRAINTS:
 *  - The number of nodes in the tree is in the range [1, 10^4].
 *  - -2^31 <= Node.val <= 2^31 - 1
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - Why is `node.left.val < node.val && node.right.val > node.val` insufficient?
 *  - Approach A (Range Boundaries): Maintain valid range `(minVal, maxVal)` for each subtree.
 *    For left child: range becomes `(minVal, node.val)`.
 *    For right child: range becomes `(node.val, maxVal)`.
 *    Watch out for 32-bit integer limits: use `Long.MIN_VALUE` and `Long.MAX_VALUE`!
 *  - Approach B (In-order Traversal): An in-order traversal of a valid BST must produce
 *    a strictly monotonically increasing sequence.
 */
public class ValidateBinarySearchTree {

    /**
     * Determines whether the binary tree is a valid Binary Search Tree.
     *
     * @param root root node of the binary tree
     * @return true if valid BST; false otherwise
     */
    public boolean isValidBST(TreeNode root) {
        // TODO: Implement your solution here
        return false;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        ValidateBinarySearchTree solver = new ValidateBinarySearchTree();

        System.out.println("=== Testing: LeetCode 98 - Validate Binary Search Tree ===");

        // Test 1: Valid BST [2, 1, 3] -> true
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        boolean res1 = solver.isValidBST(root1);
        System.out.println("Test 1 Result: " + res1 + " | Expected: true");
        if (res1) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: Invalid BST [5, 1, 4, null, null, 3, 6] -> false
        TreeNode root2 = new TreeNode(5,
                new TreeNode(1),
                new TreeNode(4, new TreeNode(3), new TreeNode(6))
        );
        boolean res2 = solver.isValidBST(root2);
        System.out.println("Test 2 Result: " + res2 + " | Expected: false");
        if (!res2) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Deep violation [5, 4, 6, null, null, 3, 7] -> false
        TreeNode root3 = new TreeNode(5,
                new TreeNode(4),
                new TreeNode(6, new TreeNode(3), new TreeNode(7))
        );
        boolean res3 = solver.isValidBST(root3);
        System.out.println("Test 3 Result: " + res3 + " | Expected: false");
        if (!res3) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
