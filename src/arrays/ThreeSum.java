package arrays;

import java.util.Collections;
import java.util.List;

/**
 * ============================================================================
 * Problem: 3Sum
 * LeetCode #15 | Difficulty: Medium
 * Company: Google Interview Question (Top High Frequency)
 * Link: https://leetcode.com/problems/3sum/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given an integer array `nums`, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: nums = [-1, 0, 1, 2, -1, -4]
 *   Output: [[-1, -1, 2], [-1, 0, 1]]
 *   Explanation:
 *     nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 *     nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 *     nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 *     The distinct triplets are [-1, 0, 1] and [-1, -1, 2].
 *
 * Example 2:
 *   Input: nums = [0, 1, 1]
 *   Output: []
 *   Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 *   Input: nums = [0, 0, 0]
 *   Output: [[0, 0, 0]]
 *
 * ⚙️ CONSTRAINTS:
 *  - 3 <= nums.length <= 3000
 *  - -10^5 <= nums[i] <= 10^5
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - Can sorting the array first simplify duplicate prevention and two-pointer search?
 *  - Fix the first number `nums[i]`. If `nums[i] > 0`, can any remaining positive numbers sum to 0?
 *  - How do you skip duplicate values of `nums[i]`?
 *  - Once you fix `nums[i]`, the problem reduces to Two Sum II with target `-nums[i]`.
 *  - When you find a valid triplet, how do you advance `left` and `right` while skipping duplicates?
 */
public class ThreeSum {

    /**
     * Finds all unique triplets in the array which give the sum of zero.
     *
     * @param nums array of integers
     * @return list of unique triplets summing to 0
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // TODO: Implement your solution here
        return Collections.emptyList();
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        ThreeSum solver = new ThreeSum();

        System.out.println("=== Testing: LeetCode 15 - 3Sum ===");

        // Test 1: [-1, 0, 1, 2, -1, -4] -> [[-1,-1,2], [-1,0,1]]
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res1 = solver.threeSum(nums1);
        System.out.println("Test 1 Result:   " + res1);
        System.out.println("Test 1 Expected: [[-1, -1, 2], [-1, 0, 1]] (order within list may vary)");
        if (res1 != null && res1.size() == 2) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [0, 1, 1] -> []
        int[] nums2 = {0, 1, 1};
        List<List<Integer>> res2 = solver.threeSum(nums2);
        System.out.println("Test 2 Result:   " + res2);
        System.out.println("Test 2 Expected: []");
        if (res2 != null && res2.isEmpty()) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: [0, 0, 0] -> [[0, 0, 0]]
        int[] nums3 = {0, 0, 0};
        List<List<Integer>> res3 = solver.threeSum(nums3);
        System.out.println("Test 3 Result:   " + res3);
        System.out.println("Test 3 Expected: [[0, 0, 0]]");
        if (res3 != null && res3.size() == 1) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
