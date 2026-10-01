package Arrays;

/**
 * ============================================================================
 * Problem: Trapping Rain Water
 * LeetCode #42 | Difficulty: Hard
 * Spec: specs/Arrays/TrappingRainWater.spec.md
 * Invariant: Two-Pointer Bottleneck (Water is bounded by the smaller of leftMax/rightMax)
 * ============================================================================
 *
 * Time Complexity:  O(N) - Single pass with two pointers
 * Space Complexity: O(1) - Constant auxiliary variables
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        System.out.println("=== Testing: Trapping Rain Water (Spec-042) ===");

        // Test 1: Standard LeetCode example 1
        int[] t1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        assert trapOpti(t1) == 6 : "Test 1 failed";
        assert trap(t1) == 6 : "Test 1 (trap) failed";

        // Test 2: Custom multi-valley example
        int[] t2 = {0, 2, 0, 3, 1, 0, 1, 3, 2, 1};
        assert trapOpti(t2) == 9 : "Test 2 failed";
        assert trap(t2) == 9 : "Test 2 (trap) failed";

        // Test 3: Asymmetric bowl
        int[] t3 = {4, 2, 0, 3, 2, 5};
        assert trapOpti(t3) == 9 : "Test 3 failed";
        assert trap(t3) == 9 : "Test 3 (trap) failed";

        // Test 4: Empty and small arrays (Boundary Safety)
        assert trapOpti(new int[]{}) == 0 : "Empty array test failed";
        assert trapOpti(new int[]{1}) == 0 : "Single element test failed";
        assert trapOpti(new int[]{1, 2}) == 0 : "Two elements test failed";

        // Test 5: Monotonically increasing / decreasing
        assert trapOpti(new int[]{1, 2, 3, 4, 5}) == 0 : "Increasing array test failed";
        assert trapOpti(new int[]{5, 4, 3, 2, 1}) == 0 : "Decreasing array test failed";

        // Test 6: Single valley
        assert trapOpti(new int[]{3, 0, 3}) == 3 : "Single valley test failed";

        // Test 7: Wide flat bottom
        assert trapOpti(new int[]{5, 1, 1, 1, 5}) == 12 : "Flat bottom test failed";

        System.out.println("All Trapping Rain Water specification test cases PASSED! [10/10]");
    }

    /**
     * Optimal Solution: Two Pointers
     *
     * Invariant:
     * - If leftMax < rightMax, the boundary bottleneck for index left is strictly leftMax.
     *   Therefore, water at left is determined purely by (leftMax - heights[left]).
     * - Symmetrically, if rightMax <= leftMax, the bottleneck for right is rightMax.
     *
     * Time: O(N) | Space: O(1)
     */
    public static int trapOpti(int[] heights) {
        if (heights == null || heights.length < 3) {
            return 0;
        }

        int left = 0;
        int right = heights.length - 1;
        int leftMax = heights[left];
        int rightMax = heights[right];
        int totalWater = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, heights[left]);
                totalWater += (leftMax - heights[left]);
            } else {
                right--;
                rightMax = Math.max(rightMax, heights[right]);
                totalWater += (rightMax - heights[right]);
            }
        }

        return totalWater;
    }

    /**
     * Dynamic Programming Approach (Prefix & Suffix Maximums)
     *
     * Computes leftMax[i] and rightMax[i] pre-arrays.
     * Time: O(N) | Space: O(N)
     */
    public static int trap(int[] heights) {
        if (heights == null || heights.length < 3) {
            return 0;
        }

        int n = heights.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = heights[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], heights[i]);
        }

        rightMax[n - 1] = heights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], heights[i]);
        }

        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - heights[i];
        }

        return totalWater;
    }
}
