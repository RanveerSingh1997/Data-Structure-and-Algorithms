package arrays;

/**
 * ============================================================================
 * Problem: Container With Most Water
 * LeetCode #11 | Difficulty: Medium
 * Company: Google Interview Question (Greedy Two Pointers Classic)
 * Link: https://leetcode.com/problems/container-with-most-water/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * You are given an integer array `height` of length n. There are n vertical lines
 * drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the
 * container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 *
 * Formula: Area = (right - left) * min(height[left], height[right])
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
 *   Output: 49
 *   Explanation: The vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 *   In this case, the max area of water the container can contain is between
 *   index 1 (height 8) and index 8 (height 7):
 *   Width = 8 - 1 = 7, Height = min(8, 7) = 7 -> Area = 7 * 7 = 49.
 *
 * Example 2:
 *   Input: height = [1, 1]
 *   Output: 1
 *   Explanation: Width = 1 - 0 = 1, Height = min(1, 1) = 1 -> Area = 1.
 *
 * Example 3:
 *   Input: height = [4, 3, 2, 1, 4]
 *   Output: 16
 *   Explanation: Width = 4 - 0 = 4, Height = min(4, 4) = 4 -> Area = 16.
 *
 * ⚙️ CONSTRAINTS:
 *  - n == height.length
 *  - 2 <= n <= 10^5
 *  - 0 <= height[i] <= 10^4
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - What happens if you start with the maximum possible width (`left = 0`, `right = n - 1`)?
 *  - Which pointer should you move inwards to potentially find a larger area?
 *  - Why is it mathematically impossible for the shorter line to form a larger container
 *    with any remaining inner lines?
 */
public class ContainerWithMostWater {

    /**
     * Finds the maximum water area between two vertical lines.
     *
     * @param height array of vertical line heights
     * @return maximum water container area
     */
    public int maxArea(int[] height) {
        // TODO: Implement your solution here
        return 0;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        ContainerWithMostWater solver = new ContainerWithMostWater();

        System.out.println("=== Testing: LeetCode 11 - Container With Most Water ===");

        // Test 1: [1, 8, 6, 2, 5, 4, 8, 3, 7] -> 49
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res1 = solver.maxArea(height1);
        System.out.println("Test 1 Result: " + res1 + " | Expected: 49");
        if (res1 == 49) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [1, 1] -> 1
        int[] height2 = {1, 1};
        int res2 = solver.maxArea(height2);
        System.out.println("Test 2 Result: " + res2 + " | Expected: 1");
        if (res2 == 1) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: [4, 3, 2, 1, 4] -> 16
        int[] height3 = {4, 3, 2, 1, 4};
        int res3 = solver.maxArea(height3);
        System.out.println("Test 3 Result: " + res3 + " | Expected: 16");
        if (res3 == 16) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
