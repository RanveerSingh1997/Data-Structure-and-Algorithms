package stacks_queues;

import java.util.Stack;

/**
 * ============================================================================
 * Problem: Removing Stars From a String
 * LeetCode #2390 | Difficulty: Medium
 * Link: https://leetcode.com/problems/removing-stars-from-a-string/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * You are given a string `s`, which contains stars `*`.
 *
 * In one operation, you can:
 *   - Choose a star in s.
 *   - Remove the closest non-star character to its left, as well as remove the star itself.
 *
 * Return the string after all stars have been removed.
 *
 * Note:
 *   - The input will be generated such that the operation is always possible.
 *   - It can be shown that the resulting string will always be unique.
 *
 * 📥 EXAMPLES:
 *   Example 1: s = "leet**cod*e" -> "lecoe"
 *     - The closest character to the 1st star is 't' in "leet". s becomes "lee*cod*e".
 *     - The closest character to the 2nd star is 'e' in "lee". s becomes "lecod*e".
 *     - The closest character to the 3rd star is 'd' in "lecod". s becomes "lecoe".
 *   Example 2: s = "erase*****" -> ""
 *
 * ⚙️ CONSTRAINTS:
 *   - 1 <= s.length <= 10^5
 *   - s consists of lowercase English letters and stars `*`.
 *   - The operation above can be performed on s.
 *
 * ⏱️ COMPLEXITY:
 *   - Time Complexity: O(N), where N is the length of string `s`.
 *   - Space Complexity: O(N) auxiliary space.
 */
public class RemoveStarsFromString {

    public static void main(String[] args) {
        RemoveStarsFromString solver = new RemoveStarsFromString();
        System.out.println("=== Testing: LeetCode 2390 - Removing Stars From a String ===");
        System.out.println("Test 1: " + solver.removeStars("leet**cod*e") + " (Expected: lecoe)");
        System.out.println("Test 2: " + solver.removeStars("erase*****") + " (Expected: \"\")");
    }

    public String removeStars(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '*' && !stack.isEmpty()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }
}
