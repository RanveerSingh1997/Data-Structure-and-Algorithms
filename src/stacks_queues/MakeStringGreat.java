package stacks_queues;

import java.util.Stack;

/**
 * ============================================================================
 * Problem: Make The String Great
 * LeetCode #1544 | Difficulty: Easy
 * Link: https://leetcode.com/problems/make-the-string-great/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given a string `s` of lower and upper case English letters.
 *
 * A good string is a string which doesn't have two adjacent characters `s[i]` and `s[i + 1]` where:
 *   - 0 <= i <= s.length - 2
 *   - s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 *
 * To make the string good, you can choose two adjacent characters that make the string bad
 * and remove them. You can keep doing this until the string becomes good.
 *
 * Return the string after making it good. The answer is guaranteed to be unique under given constraints.
 *
 * 📥 EXAMPLES:
 *   Example 1: s = "leEeetcode" -> "leetcode" (Remove "Ee")
 *   Example 2: s = "abBAcC"     -> "" (Remove "bB" -> "aAcC", remove "aA" -> "cC", remove "cC" -> "")
 *   Example 3: s = "s"          -> "s"
 *
 * ⚙️ CONSTRAINTS:
 *   - 1 <= s.length <= 100
 *   - s contains only lower and upper case English letters.
 *
 * ⏱️ COMPLEXITY:
 *   - Time Complexity: O(N), where N is the length of string `s`.
 *   - Space Complexity: O(N) for the character stack.
 */
public class MakeStringGreat {

    public static void main(String[] args) {
        MakeStringGreat solver = new MakeStringGreat();
        System.out.println("=== Testing: LeetCode 1544 - Make The String Great ===");
        System.out.println("leEeetcode -> " + solver.makeGood("leEeetcode") + " (Expected: leetcode)");
        System.out.println("abBAcC     -> \"" + solver.makeGood("abBAcC") + "\" (Expected: \"\")");
        System.out.println("s          -> " + solver.makeGood("s") + " (Expected: s)");
    }

    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty()) {
                boolean isBad = Character.toLowerCase(stack.peek()) == Character.toLowerCase(c)
                        && Character.isLowerCase(stack.peek()) != Character.isLowerCase(c);
                if (isBad) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder string = new StringBuilder();
        while (!stack.isEmpty()) {
            string.append(stack.pop());
        }
        return string.reverse().toString();
    }
}
