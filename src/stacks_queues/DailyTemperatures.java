package stacks_queues;

import java.util.Arrays;

/**
 * ============================================================================
 * Problem: Daily Temperatures
 * LeetCode #739 | Difficulty: Medium
 * Company: Google Interview Question (Canonical Monotonic Stack Problem)
 * Link: https://leetcode.com/problems/daily-temperatures/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given an array of integers `temperatures` representing the daily temperatures,
 * return an array `answer` such that `answer[i]` is the number of days you have
 * to wait after the ith day to get a warmer temperature.
 *
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
 *   Output: [1, 1, 4, 2, 1, 1, 0, 0]
 *   Explanation:
 *     Day 0 (73): Day 1 (74) is warmer -> wait 1 day.
 *     Day 1 (74): Day 2 (75) is warmer -> wait 1 day.
 *     Day 2 (75): Next warmer is Day 6 (76) -> wait 4 days.
 *     Day 3 (71): Day 5 (72) is warmer -> wait 2 days.
 *     Day 4 (69): Day 5 (72) is warmer -> wait 1 day.
 *     Day 5 (72): Day 6 (76) is warmer -> wait 1 day.
 *     Day 6 (76): No future warmer day -> 0.
 *     Day 7 (73): No future warmer day -> 0.
 *
 * Example 2:
 *   Input: temperatures = [30, 40, 50, 60]
 *   Output: [1, 1, 1, 0]
 *
 * Example 3:
 *   Input: temperatures = [30, 60, 90]
 *   Output: [1, 1, 0]
 *
 * ⚙️ CONSTRAINTS:
 *  - 1 <= temperatures.length <= 10^5
 *  - 30 <= temperatures[i] <= 100
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - How can you solve this in O(N) time without checking all future days for each index?
 *  - Consider a Monotonic Decreasing Stack.
 *  - What should you store on the stack?
 *    (Hint: Store INDICES on the stack rather than values, because indices give you both
 *     the day's position and its temperature via `temperatures[index]`).
 *  - When you see a temperature higher than `temperatures[stack.peek()]`, what does that mean
 *    for all the days waiting on the stack?
 */
public class DailyTemperatures {

    /**
     * Finds the number of days until a warmer temperature for each day.
     *
     * @param temperatures array of daily temperatures
     * @return array of wait days until a warmer temperature
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // TODO: Implement your solution here
        return new int[]{};
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        DailyTemperatures solver = new DailyTemperatures();

        System.out.println("=== Testing: LeetCode 739 - Daily Temperatures ===");

        // Test 1: [73, 74, 75, 71, 69, 72, 76, 73] -> [1, 1, 4, 2, 1, 1, 0, 0]
        int[] temps1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] res1 = solver.dailyTemperatures(temps1);
        int[] exp1 = {1, 1, 4, 2, 1, 1, 0, 0};
        System.out.println("Test 1 Result:   " + Arrays.toString(res1));
        System.out.println("Test 1 Expected: " + Arrays.toString(exp1));
        if (Arrays.equals(res1, exp1)) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [30, 40, 50, 60] -> [1, 1, 1, 0]
        int[] temps2 = {30, 40, 50, 60};
        int[] res2 = solver.dailyTemperatures(temps2);
        int[] exp2 = {1, 1, 1, 0};
        if (Arrays.equals(res2, exp2)) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: [30, 60, 90] -> [1, 1, 0]
        int[] temps3 = {30, 60, 90};
        int[] res3 = solver.dailyTemperatures(temps3);
        int[] exp3 = {1, 1, 0};
        if (Arrays.equals(res3, exp3)) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
