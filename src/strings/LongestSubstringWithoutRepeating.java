package strings;

/**
 * ============================================================================
 * Problem: Longest Substring Without Repeating Characters
 * LeetCode #3 | Difficulty: Medium
 * Company: Google Interview Question (Top 3 Most Asked)
 * Link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: s = "abcabcbb"
 *   Output: 3
 *   Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *   Input: s = "bbbbb"
 *   Output: 1
 *   Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *   Input: s = "pwwkew"
 *   Output: 3
 *   Explanation: The answer is "wke", with the length of 3.
 *                Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Example 4 (The Backwards Jump Trap):
 *   Input: s = "abba"
 *   Output: 2
 *   Explanation: The longest substrings are "ab" or "ba".
 *                When encountering the second 'a', ensure your left pointer doesn't jump backwards!
 *
 * ⚙️ CONSTRAINTS:
 *  - 0 <= s.length <= 5 * 10^4
 *  - s consists of English letters, digits, symbols and spaces.
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - How does a Sliding Window with two pointers (left and right) apply here?
 *  - Can you store the last seen index of each character so `left` jumps directly?
 *  - What data structure provides O(1) lookup for character frequencies or indices?
 *    (For ASCII, an `int[128]` array is faster than HashMap).
 *  - Make sure `left` never moves backwards: `left = Math.max(left, lastSeenIndex + 1)`.
 */
public class LongestSubstringWithoutRepeating {

    /**
     * Finds the length of the longest substring without repeating characters.
     *
     * @param s input string
     * @return length of longest non-repeating substring
     */
    public int lengthOfLongestSubstring(String s) {
        // TODO: Implement your solution here
        return 0;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solver = new LongestSubstringWithoutRepeating();

        System.out.println("=== Testing: LeetCode 3 - Longest Substring Without Repeating Characters ===");

        // Test 1: "abcabcbb" -> 3
        int res1 = solver.lengthOfLongestSubstring("abcabcbb");
        System.out.println("Test 1 ('abcabcbb') Result: " + res1 + " | Expected: 3");
        if (res1 == 3) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: "bbbbb" -> 1
        int res2 = solver.lengthOfLongestSubstring("bbbbb");
        System.out.println("Test 2 ('bbbbb') Result: " + res2 + " | Expected: 1");
        if (res2 == 1) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: "pwwkew" -> 3
        int res3 = solver.lengthOfLongestSubstring("pwwkew");
        System.out.println("Test 3 ('pwwkew') Result: " + res3 + " | Expected: 3");
        if (res3 == 3) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }

        // Test 4: "abba" -> 2
        int res4 = solver.lengthOfLongestSubstring("abba");
        System.out.println("Test 4 ('abba') Result: " + res4 + " | Expected: 2");
        if (res4 == 2) {
            System.out.println("  [PASS] Test 4");
        } else {
            System.out.println("  [TODO] Test 4 not passing yet");
        }

        // Test 5: "" -> 0
        int res5 = solver.lengthOfLongestSubstring("");
        if (res5 == 0) {
            System.out.println("  [PASS] Test 5 (Empty string)");
        } else {
            System.out.println("  [TODO] Test 5 not passing yet");
        }
    }
}
