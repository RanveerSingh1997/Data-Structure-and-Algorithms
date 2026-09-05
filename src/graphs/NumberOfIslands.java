package graphs;

/**
 * ============================================================================
 * Problem: Number of Islands
 * LeetCode #200 | Difficulty: Medium
 * Company: Google Interview Question (Iconic Graph / Matrix Traversal)
 * Link: https://leetcode.com/problems/number-of-islands/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given an m x n 2D binary grid `grid` which represents a map of '1's (land)
 * and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically (4 directions: up, down, left, right).
 * Diagonals are NOT connected.
 *
 * You may assume all four edges of the grid are surrounded by water.
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: grid = [
 *     ["1","1","1","1","0"],
 *     ["1","1","0","1","0"],
 *     ["1","1","0","0","0"],
 *     ["0","0","0","0","0"]
 *   ]
 *   Output: 1
 *
 * Example 2:
 *   Input: grid = [
 *     ["1","1","0","0","0"],
 *     ["1","1","0","0","0"],
 *     ["0","0","1","0","0"],
 *     ["0","0","0","1","1"]
 *   ]
 *   Output: 3
 *
 * Example 3 (Diagonals do not connect):
 *   Input: grid = [
 *     ["1","0"],
 *     ["0","1"]
 *   ]
 *   Output: 2
 *
 * ⚙️ CONSTRAINTS:
 *  - m == grid.length
 *  - n == grid[i].length
 *  - 1 <= m, n <= 300
 *  - grid[i][j] is '0' or '1'.
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - How can you model the grid as an undirected graph?
 *  - Iterate through every cell (r, c). When you encounter '1':
 *      1. Increment the island count.
 *      2. Launch a DFS or BFS to visit and "sink" (mark as '0') all connected land cells.
 *  - Clarifying question to ask: "Can I mutate the grid in-place, or must I use a `visited[][]` boolean array?"
 *  - Follow-up Google question: "What if land cells are dynamically added one by one over time?"
 *    -> LeetCode 305 (Number of Islands II) using Disjoint Set Union (DSU).
 */
public class NumberOfIslands {

    /**
     * Counts the number of 4-directionally connected land islands.
     *
     * @param grid 2D character matrix of '1' (land) and '0' (water)
     * @return total number of islands
     */
    public int numIslands(char[][] grid) {
        // TODO: Implement your solution here
        return 0;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();

        System.out.println("=== Testing: LeetCode 200 - Number of Islands ===");

        // Test 1: Single large island -> 1
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        int res1 = solver.numIslands(grid1);
        System.out.println("Test 1 Result: " + res1 + " | Expected: 1");
        if (res1 == 1) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: Three distinct islands -> 3
        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int res2 = solver.numIslands(grid2);
        System.out.println("Test 2 Result: " + res2 + " | Expected: 3");
        if (res2 == 3) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: Diagonal cells -> 2
        char[][] grid3 = {
                {'1', '0'},
                {'0', '1'}
        };
        int res3 = solver.numIslands(grid3);
        System.out.println("Test 3 Result: " + res3 + " | Expected: 2");
        if (res3 == 2) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }
    }
}
