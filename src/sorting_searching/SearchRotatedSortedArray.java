package sorting_searching;

/**
 * ============================================================================
 * Problem: Search in Rotated Sorted Array
 * LeetCode #33 | Difficulty: Medium
 * Company: Google Interview Question (Iconic Binary Search Problem)
 * Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * There is an integer array `nums` sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, `nums` is possibly rotated at an unknown
 * pivot index k (1 <= k < nums.length) such that the resulting array is:
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]].
 *
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array `nums` after the possible rotation and an integer `target`, return the
 * index of `target` if it is in `nums`, or -1 if it is not in `nums`.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 0
 *   Output: 4
 *
 * Example 2:
 *   Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 3
 *   Output: -1
 *
 * Example 3:
 *   Input: nums = [1], target = 0
 *   Output: -1
 *
 * Example 4:
 *   Input: nums = [3, 1], target = 1
 *   Output: 1
 *
 * ⚙️ CONSTRAINTS:
 *  - 1 <= nums.length <= 5000
 *  - -10^4 <= nums[i] <= 10^4
 *  - All values of nums are unique.
 *  - nums is an ascending array that is possibly rotated.
 *  - -10^4 <= target <= 10^4
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - In any rotated sorted array, if you take the midpoint, at least one half
 *    (either left..mid or mid..right) is GUARANTEED to be normally sorted!
 *  - How can you check if the left half is sorted? (`nums[left] <= nums[mid]`).
 *  - If the left half is sorted, how do you determine whether target lies inside it?
 *  - What if target lies outside that sorted half?
 */
public class SearchRotatedSortedArray {

    /**
     * Searches for target in a rotated sorted array in O(log N) time.
     *
     * @param nums   possibly rotated sorted array of unique integers
     * @param target value to find
     * @return 0-based index of target, or -1 if not found
     */
    public int search(int[] nums, int target) {
        // TODO: Implement your solution here
        return -1;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        SearchRotatedSortedArray solver = new SearchRotatedSortedArray();

        System.out.println("=== Testing: LeetCode 33 - Search in Rotated Sorted Array ===");

        // Test 1: [4, 5, 6, 7, 0, 1, 2], target = 0 -> 4
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int res1 = solver.search(nums1, 0);
        System.out.println("Test 1 Result: " + res1 + " | Expected: 4");
        if (res1 == 4) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [4, 5, 6, 7, 0, 1, 2], target = 3 -> -1
        int res2 = solver.search(nums1, 3);
        System.out.println("Test 2 Result: " + res2 + " | Expected: -1");
        if (res2 == -1) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: [1], target = 0 -> -1
        int res3 = solver.search(new int[]{1}, 0);
        System.out.println("Test 3 Result: " + res3 + " | Expected: -1");
        if (res3 == -1) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }

        // Test 4: [3, 1], target = 1 -> 1
        int res4 = solver.search(new int[]{3, 1}, 1);
        System.out.println("Test 4 Result: " + res4 + " | Expected: 1");
        if (res4 == 1) {
            System.out.println("  [PASS] Test 4");
        } else {
            System.out.println("  [TODO] Test 4 not passing yet");
        }
    }
}
