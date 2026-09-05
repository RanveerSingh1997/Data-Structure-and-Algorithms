package graphs;

/**
 * ============================================================================
 * Problem: Rotting Oranges
 * LeetCode #994 | Difficulty: Medium
 * Company: Google Interview Question (Canonical Multi-Source BFS)
 * Link: https://leetcode.com/problems/rotting-oranges/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * You are given an m x n grid where each cell can have one of three values:
 *  - 0 representing an empty cell,
 *  - 1 representing a fresh orange, or
 *  - 2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten
 * orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible (i.e. some fresh orange can never be reached), return -1.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: grid = [
 *     [2, 1, 1],
 *     [1, 1, 0],
 *     [0, 1, 1]
 *   ]
 *   Output: 4
 *   Explanation:
 *     Minute 0: [0,0] is rotten. Fresh count = 6.
 *     Minute 1: [0,1] and [1,0] rot.
 *     Minute 2: [0,2] and [1,1] rot.
 *     Minute 3: [2,1] rots.
 *     Minute 4: [2,2] rots.
 *     All fresh oranges rotted in 4 minutes.
 *
 * Example 2:
 *   Input: grid = [
 *     [2, 1, 1],
 *     [0, 1, 1],
 *     [1, 0, 1]
 *   ]
 *   Output: -1
 *   Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
 *                because rotting only happens 4-directionally.
 *
 * Example 3:
 *   Input: grid = [[0, 2]]
 *   Output: 0
 *   Explanation: Since there are already 0 fresh oranges at minute 0, the answer is just 0.
 *
 * ⚙️ CONSTRAINTS:
 *  - m == grid.length
 *  - n == grid[i].length
 *  - 1 <= m, n <= 10
 *  - grid[i][j] is 0, 1, or 2.
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - Why does DFS fail to find the MINIMUM time?
 *    (Rotting occurs simultaneously from all rotten oranges in parallel waves -> Multi-Source BFS!).
 *  - Initial pass: Enqueue ALL cells with value 2 simultaneously and count initial fresh oranges.
 *  - Process the BFS level-by-level (each level = 1 minute).
 *  - When queue becomes empty, if `freshCount > 0`, return -1; otherwise return elapsed minutes.
 */
public class RottingOranges {

    /**
     * Finds the minimum minutes to rot all fresh oranges using multi-source BFS.
     *
     * @param grid 2D matrix where 0=empty, 1=fresh, 2=rotten
     * @return minimum minutes, or -1 if impossible
     */
    public int orangesRotting(int[][] grid) {
        // TODO: Implement your solution here
        return -1;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        RottingOranges solver = new RottingOranges();

        System.out.println("=== Testing: LeetCode 994 - Rotting Oranges ===");

        // Test 1: Standard case -> 4
        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int res1 = solver.orangesRotting(grid1);
        System.out.println("Test 1 Result: " + res1 + " | Expected: 4");
        if (res1 == 4) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: Impossible case -> -1
        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        int res2 = solver.orangesRotting(grid2);
        System.out.println("Test 2 Result: " + res2 + " | Expected: -1");
        if (res2 == -1) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Already 0 fresh -> 0
        int[][] grid3 = {{0, 2}};
        int res3 = solver.orangesRotting(grid3);
        System.out.println("Test 3 Result: " + res3 + " | Expected: 0");
        if (res3 == 0) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
