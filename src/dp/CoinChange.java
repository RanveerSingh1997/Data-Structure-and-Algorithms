package dp;

/**
 * ============================================================================
 * Problem: Coin Change
 * LeetCode #322 | Difficulty: Medium
 * Company: Google Interview Question (Canonical 1D Dynamic Programming)
 * Link: https://leetcode.com/problems/coin-change/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * You are given an integer array `coins` representing coins of different denominations
 * and an integer `amount` representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin (Unbounded Knapsack).
 *
 * 📥 EXAMPLES:
 *
 * Example 1:
 *   Input: coins = [1, 2, 5], amount = 11
 *   Output: 3
 *   Explanation: 11 = 5 + 5 + 1 (3 coins).
 *
 * Example 2:
 *   Input: coins = [2], amount = 3
 *   Output: -1
 *   Explanation: No combination of 2-cent coins can form 3.
 *
 * Example 3:
 *   Input: coins = [1], amount = 0
 *   Output: 0
 *   Explanation: 0 coins needed to make amount 0.
 *
 * Example 4 (The Greedy Failure Trap):
 *   Input: coins = [1, 3, 4], amount = 6
 *   Output: 2
 *   Explanation: Greedy picks 4 + 1 + 1 = 3 coins.
 *                Optimal DP picks 3 + 3 = 2 coins!
 *
 * ⚙️ CONSTRAINTS:
 *  - 1 <= coins.length <= 12
 *  - 1 <= coins[i] <= 2^31 - 1
 *  - 0 <= amount <= 10^4
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *  - Why does the greedy choice fail here?
 *  - Define `dp[i]` as the minimum number of coins needed to make amount `i`.
 *  - What is the recurrence relation?
 *    `dp[i] = min(dp[i], dp[i - coin] + 1)` for all `coin` in `coins` where `coin <= i`.
 *  - Base case: `dp[0] = 0`.
 *  - What sentinel value should you initialize the DP table with to prevent integer overflow?
 *    (Use `amount + 1` instead of `Integer.MAX_VALUE`).
 */
public class CoinChange {

    /**
     * Computes the minimum number of coins needed to make up the given amount.
     *
     * @param coins  array of available coin denominations
     * @param amount target money amount
     * @return minimum coins needed, or -1 if impossible
     */
    public int coinChange(int[] coins, int amount) {
        // TODO: Implement your solution here
        return -1;
    }

    // ========================================================================
    // 🧪 TEST SUITE (Run this file to verify your implementation)
    // ========================================================================

    public static void main(String[] args) {
        CoinChange solver = new CoinChange();

        System.out.println("=== Testing: LeetCode 322 - Coin Change ===");

        // Test 1: [1, 2, 5], amount = 11 -> 3
        int[] coins1 = {1, 2, 5};
        int res1 = solver.coinChange(coins1, 11);
        System.out.println("Test 1 Result: " + res1 + " | Expected: 3");
        if (res1 == 3) {
            System.out.println("  [PASS] Test 1");
        } else {
            System.out.println("  [TODO] Test 1 not passing yet");
        }

        // Test 2: [2], amount = 3 -> -1
        int[] coins2 = {2};
        int res2 = solver.coinChange(coins2, 3);
        System.out.println("Test 2 Result: " + res2 + " | Expected: -1");
        if (res2 == -1) {
            System.out.println("  [PASS] Test 2");
        } else {
            System.out.println("  [TODO] Test 2 not passing yet");
        }

        // Test 3: [1], amount = 0 -> 0
        int res3 = solver.coinChange(new int[]{1}, 0);
        System.out.println("Test 3 Result: " + res3 + " | Expected: 0");
        if (res3 == 0) {
            System.out.println("  [PASS] Test 3");
        } else {
            System.out.println("  [TODO] Test 3 not passing yet");
        }

        // Test 4 (Greedy trap): [1, 3, 4], amount = 6 -> 2
        int[] coins4 = {1, 3, 4};
        int res4 = solver.coinChange(coins4, 6);
        System.out.println("Test 4 (Greedy Trap) Result: " + res4 + " | Expected: 2");
        if (res4 == 2) {
            System.out.println("  [PASS] Test 4");
        } else {
            System.out.println("  [TODO] Test 4 not passing yet");
        }
    }
}
