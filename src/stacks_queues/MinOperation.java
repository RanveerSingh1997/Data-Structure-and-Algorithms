package stacks_queues;

import java.util.Stack;

/**
 * ============================================================================
 * Problem: Crawler Log Folder
 * LeetCode #1598 | Difficulty: Easy
 * Link: https://leetcode.com/problems/crawler-log-folder/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * The Leetcode file system keeps a log each time some user performs a change folder operation.
 * The operations are described below:
 *   - "../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
 *   - "./"  : Remain in the same folder.
 *   - "x/"  : Move to the child folder named x (this folder is guaranteed to always exist).
 *
 * You are given a list of strings `logs` where `logs[i]` is the operation performed by the user at the ith step.
 * Return the minimum number of operations needed to go back to the main folder after the change folder operations.
 *
 * 📥 EXAMPLES:
 *   Example 1: logs = ["d1/","d2/","../","d21/","./"] -> 2
 *   Example 2: logs = ["d1/","d2/","./","d3/","../","d31/"] -> 3
 *   Example 3: logs = ["d1/","../","../","../"] -> 0
 *
 * ⚙️ CONSTRAINTS:
 *   - 1 <= logs.length <= 10^3
 *   - 2 <= logs[i].length <= 10
 *
 * ⏱️ COMPLEXITY:
 *   - Time Complexity: O(N), where N is logs.length.
 *   - Space Complexity: O(N) using Stack (or O(1) using integer depth counter).
 */
public class MinOperation {

    public static void main(String[] args) {
        MinOperation solver = new MinOperation();
        System.out.println("=== Testing: LeetCode 1598 - Crawler Log Folder ===");
        System.out.println("Test 1: " + solver.minOperations(new String[]{"d1/", "d2/", "../", "d3/", "../", "d4/", "../", "d5/"}) + " (Expected: 2)");
        System.out.println("Test 2: " + solver.minOperations(new String[]{"d1/", "../", "../", "../"}) + " (Expected: 0)");
        System.out.println("Test 3: " + solver.minOperations(new String[]{"d1/", "d2/", "../", "d21/", "./"}) + " (Expected: 2)");
    }

    public int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();
        for (String str : logs) {
            if (str.equals("../")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (str.equals("./")) {
                continue;
            } else {
                stack.push(str);
            }
        }
        return stack.size();
    }
}
