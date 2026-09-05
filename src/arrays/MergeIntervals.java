package arrays;

import java.util.Arrays;

/**
 * ============================================================================
 * Problem: Merge Intervals
 * LeetCode #56 | Difficulty: Medium
 * Company: Google Interview Question (Top #1 Most Asked)
 * Link: https://leetcode.com/problems/merge-intervals/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge all
 * overlapping intervals, and return an array of the non-overlapping intervals
 * that cover all the intervals in the input.
 *
 * Intervals that touch at a single point (e.g., [1, 4] and [4, 5]) are considered
 * overlapping and should be merged into [1, 5].
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]
 *   Output: [[1, 6], [8, 10], [15, 18]]
 *   Explanation: Since intervals [1, 3] and [2, 6] overlap, merge them into [1, 6].
 *
 * Example 2:
 *   Input: intervals = [[1, 4], [4, 5]]
 *   Output: [[1, 5]]
 *   Explanation: Intervals [1, 4] and [4, 5] touch at 4, so merge them into [1, 5].
 *
 * Example 3 (Nested Intervals):
 *   Input: intervals = [[1, 4], [2, 3]]
 *   Output: [[1, 4]]
 *   Explanation: [2, 3] is completely swallowed inside [1, 4].
 *
 * ⚙️ CONSTRAINTS:
 *  - 1 <= intervals.length <= 10^4
 *  - intervals[i].length == 2
 *  - 0 <= start_i <= end_i <= 10^4
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - If you sort intervals by their start times (`intervals[i][0]`), what can you say
 *    about when two adjacent intervals overlap?
 *  - Overlap condition: `nextStart <= currentEnd`.
 *  - How do you update `currentEnd` when merging? `currentEnd = Math.max(currentEnd, nextEnd)`.
 *  - What happens when `nextStart > currentEnd`? (Add `[currentStart, currentEnd]` to results and reset).
 *  - Don't forget to push the final interval after finishing the loop!
 */
public class MergeIntervals {

    /**
     * Merges all overlapping intervals.
     *
     * @param intervals 2D array of [start, end] intervals
     * @return merged non-overlapping intervals
     */
    public int[][] merge(int[][] intervals) {
        // TODO: Implement your solution here
        return new int[][]{};
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        MergeIntervals solver = new MergeIntervals();

        System.out.println("=== Testing: LeetCode 56 - Merge Intervals ===");

        // Test 1: [[1,3],[2,6],[8,10],[15,18]] -> [[1,6],[8,10],[15,18]]
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] res1 = solver.merge(intervals1);
        System.out.println("Test 1 Result:   " + Arrays.deepToString(res1));
        System.out.println("Test 1 Expected: [[1, 6], [8, 10], [15, 18]]");
        if (Arrays.deepEquals(res1, new int[][]{{1, 6}, {8, 10}, {15, 18}})) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: Touching intervals [[1,4],[4,5]] -> [[1,5]]
        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] res2 = solver.merge(intervals2);
        System.out.println("Test 2 Result:   " + Arrays.deepToString(res2));
        System.out.println("Test 2 Expected: [[1, 5]]");
        if (Arrays.deepEquals(res2, new int[][]{{1, 5}})) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Nested interval [[1,4],[2,3]] -> [[1,4]]
        int[][] intervals3 = {{1, 4}, {2, 3}};
        int[][] res3 = solver.merge(intervals3);
        System.out.println("Test 3 Result:   " + Arrays.deepToString(res3));
        System.out.println("Test 3 Expected: [[1, 4]]");
        if (Arrays.deepEquals(res3, new int[][]{{1, 4}})) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
