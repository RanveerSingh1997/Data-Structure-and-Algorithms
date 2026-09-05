package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ============================================================================
 * Utility: TreeVisualizer & LeetCode Tree Parser
 * ============================================================================
 *
 * Provides:
 *  1. LeetCode String Parser: Converts strings like "[4, 2, 7, 1, 3, 6, 9]"
 *     or "[1, 2, 3, null, null, 4, 5]" directly into a TreeNode binary tree.
 *  2. LeetCode Serializer: Converts a TreeNode binary tree back to "[...]" string.
 *  3. 2D ASCII Tree Visualizer: Prints a clear visual ASCII representation
 *     with `/` and `\` branch connectors in the console.
 */
public class TreeVisualizer {

    /**
     * Parses a LeetCode-formatted level-order tree string into a TreeNode tree.
     * Supports formats like:
     *   "[4, 2, 7, 1, 3, 6, 9]"
     *   "[1, null, 2, 3]"
     *   "1, 2, 3, null, 4"
     *   "[]" or "null" (returns null)
     *
     * @param data LeetCode serialized tree string
     * @return root TreeNode of the binary tree
     */
    public static TreeNode fromLeetCode(String data) {
        if (data == null) return null;

        String cleaned = data.trim();
        if (cleaned.startsWith("[") && cleaned.endsWith("]")) {
            cleaned = cleaned.substring(1, cleaned.length() - 1).trim();
        }
        if (cleaned.isEmpty() || cleaned.equalsIgnoreCase("null")) {
            return null;
        }

        String[] tokens = cleaned.split("\\s*,\\s*");
        if (tokens.length == 0 || tokens[0].equalsIgnoreCase("null") || tokens[0].isEmpty()) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty() && index < tokens.length) {
            TreeNode current = queue.poll();

            // Process left child
            if (index < tokens.length) {
                String leftVal = tokens[index++];
                if (!leftVal.equalsIgnoreCase("null") && !leftVal.equals("#") && !leftVal.isEmpty()) {
                    current.left = new TreeNode(Integer.parseInt(leftVal));
                    queue.offer(current.left);
                }
            }

            // Process right child
            if (index < tokens.length) {
                String rightVal = tokens[index++];
                if (!rightVal.equalsIgnoreCase("null") && !rightVal.equals("#") && !rightVal.isEmpty()) {
                    current.right = new TreeNode(Integer.parseInt(rightVal));
                    queue.offer(current.right);
                }
            }
        }

        return root;
    }

    /**
     * Serializes a binary tree into standard LeetCode level-order string format.
     * E.g. [4, 2, 7, 1, 3, 6, 9]
     *
     * @param root root node of tree
     * @return LeetCode format string representation
     */
    public static String toLeetCode(TreeNode root) {
        if (root == null) return "[]";

        List<String> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                list.add(String.valueOf(curr.val));
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                list.add("null");
            }
        }

        // Remove trailing nulls for clean LeetCode representation
        int lastNonNull = list.size() - 1;
        while (lastNonNull >= 0 && list.get(lastNonNull).equals("null")) {
            lastNonNull--;
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= lastNonNull; i++) {
            sb.append(list.get(i));
            if (i < lastNonNull) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Prints a 2D ASCII visual representation of the binary tree to standard output.
     *
     * @param root root node of tree
     */
    public static void printTree(TreeNode root) {
        System.out.println(visualize(root));
    }

    /**
     * Generates a 2D ASCII visual representation of the binary tree.
     *
     * @param root root node of tree
     * @return multi-line string containing the 2D tree diagram
     */
    public static String visualize(TreeNode root) {
        if (root == null) return "(empty tree)";

        int maxLevel = maxLevel(root);
        List<TreeNode> initialNodes = Collections.singletonList(root);
        StringBuilder sb = new StringBuilder();
        printNodeInternal(initialNodes, 1, maxLevel, sb);
        return sb.toString();
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel, StringBuilder sb) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces, sb);

        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                sb.append(node.val);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                sb.append(" ");
            }
            printWhitespaces(betweenSpaces, sb);
        }
        sb.append("\n");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i, sb);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1, sb);
                    continue;
                }

                if (nodes.get(j).left != null) {
                    sb.append("/");
                } else {
                    printWhitespaces(1, sb);
                }

                printWhitespaces(i + i - 1, sb);

                if (nodes.get(j).right != null) {
                    sb.append("\\");
                } else {
                    printWhitespaces(1, sb);
                }

                printWhitespaces(endgeLines + endgeLines - i, sb);
            }
            sb.append("\n");
        }

        printNodeInternal(newNodes, level + 1, maxLevel, sb);
    }

    private static void printWhitespaces(int count, StringBuilder sb) {
        for (int i = 0; i < count; i++) {
            sb.append(" ");
        }
    }

    private static int maxLevel(TreeNode node) {
        if (node == null) return 0;
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private static boolean isAllElementsNull(List<TreeNode> list) {
        for (TreeNode node : list) {
            if (node != null) return false;
        }
        return true;
    }

    // ========================================================================
    // 🧪 DEMO RUNNER
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== Demo: TreeVisualizer & LeetCode Parser ===");

        // 1. Parse standard LeetCode string directly
        String input1 = "[4, 2, 7, 1, 3, 6, 9]";
        TreeNode tree1 = fromLeetCode(input1);
        System.out.println("Parsed from: " + input1);
        System.out.println("Serialized back: " + toLeetCode(tree1));
        System.out.println("\n2D Visual Tree Diagram:");
        printTree(tree1);

        // 2. Parse tree with null children
        String input2 = "[1, 2, 3, null, 4, null, 5]";
        TreeNode tree2 = fromLeetCode(input2);
        System.out.println("Parsed from: " + input2);
        System.out.println("Serialized back: " + toLeetCode(tree2));
        System.out.println("\n2D Visual Tree Diagram:");
        printTree(tree2);
    }
}
