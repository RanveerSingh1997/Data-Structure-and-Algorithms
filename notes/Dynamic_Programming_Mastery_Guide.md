# 🧮 Dynamic Programming (DP) Mastery Guide

Dynamic Programming is simply **recursion without doing the same work twice**. It applies to problems with:
1. **Optimal Substructure**: The optimal solution to the overall problem can be constructed from optimal solutions to subproblems.
2. **Overlapping Subproblems**: The same smaller subproblems are solved repeatedly in a naive recursion tree.

---

## 1. The 4-Step Systematic DP Framework

Never jump directly into coding a DP array. Always follow this 4-step mental sequence:

```
Step 1: DEFINE STATE         -> What does dp[i] or dp[i][j] mean in plain English?
Step 2: BASE CASES           -> What are the smallest trivial subproblems you already know?
Step 3: RECURRENCE RELATION  -> How does the current state transition from smaller subproblems?
Step 4: SPACE OPTIMIZATION   -> Do you only need the previous row or previous 2 variables?
```

---

## 2. Top-Down (Memoization) vs. Bottom-Up (Tabulation)

| Feature | Top-Down (Memoization) | Bottom-Up (Tabulation) |
| :--- | :--- | :--- |
| **Direction** | Starts at the final target, breaks down recursively | Starts at base cases (`dp[0]`), builds up iteratively |
| **Memory** | Call stack + Cache (`O(N)` or `O(N * M)`) | Iterative array (often optimizable to `O(1)` space) |
| **Subproblem Visits** | Computes only the states that are actually reached | Visits every state in order |
| **Risk** | Can cause `StackOverflowError` if recursion tree is deep ($N > 10,000$) | Safe from stack overflow; easiest to profile |

---

## 3. The 4 Core DP Archetypes

### Archetype 1: 1D Linear Decision DP
* **When to use**: Decisions made at each element along an array where choices affect future availability (e.g., Climbing Stairs, House Robber).
* **State Definition**: `dp[i]` = the maximum value or number of ways up to index $i$.

#### Example: House Robber / Max Non-Adjacent Sum
At house $i$, you have two choices:
1. **Rob house $i$**: You cannot rob house $i-1$, so add `nums[i] + dp[i-2]`.
2. **Skip house $i$**: The maximum loot remains `dp[i-1]`.
$$\text{dp}[i] = \max(\text{nums}[i] + \text{dp}[i-2], \text{dp}[i-1])$$

#### Space Optimization from $O(N)$ to $O(1)$:
Notice that `dp[i]` only ever depends on `dp[i-1]` and `dp[i-2]`. You do NOT need a whole array! Two variables suffice:

```java
public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];

    int prev2 = 0;           // Represents dp[i-2]
    int prev1 = nums[0];     // Represents dp[i-1]

    for (int i = 1; i < nums.length; i++) {
        int current = Math.max(nums[i] + prev2, prev1);
        prev2 = prev1;
        prev1 = current;
    }

    return prev1; // Final answer in O(1) space!
}
```

---

### Archetype 2: 0/1 Knapsack vs. Unbounded Knapsack
This is one of the most critical conceptual divides in Dynamic Programming:

| Property | 0/1 Knapsack | Unbounded Knapsack (e.g. Coin Change) |
| :--- | :--- | :--- |
| **Item Usage** | Each item used **at most once** | Each item can be used **infinitely many times** |
| **Loop Direction** | Iterate capacity **BACKWARDS** ($W \to \text{weight}$) | Iterate capacity **FORWARDS** ($\text{weight} \to W$) |

#### Why does the loop direction matter?
> [!IMPORTANT]
> - **In Unbounded Knapsack (Coin Change)**: When you update `dp[amount]` using `dp[amount - coin]`, you *want* to allow using the same coin multiple times in the same step. Processing from left to right allows newly computed values to immediately be reused!
> - **In 0/1 Knapsack**: If you iterate forwards, `dp[w]` will reuse the result from `dp[w - weight]`, which already incorporated that same item. Running the inner loop **backwards** ensures you only read values from the *previous* round!

#### Generic Template: Unbounded Knapsack (Coin Change)
```java
public int coinChange(int[] coins, int amount) {
    int max = amount + 1;
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, max);
    dp[0] = 0; // Base case: 0 coins needed to make amount 0

    // Forwards loop allows multiple uses of the same coin
    for (int coin : coins) {
        for (int i = coin; i <= amount; i++) {
            dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
    }

    return dp[amount] > amount ? -1 : dp[amount];
}
```

---

### Archetype 3: 2D Grid / Matrix Path DP
* **When to use**: Finding min cost, max profit, or number of ways to navigate a 2D grid moving only right and down (e.g., Unique Paths, Minimum Path Sum).
* **State Definition**: `dp[r][c]` = min cost / total ways to reach cell `(r, c)` from `(0, 0)`.
* **Recurrence**:
  $$\text{dp}[r][c] = \min(\text{dp}[r - 1][c], \text{dp}[r][c - 1]) + \text{grid}[r][c]$$

```
Base row (r=0): Can only arrive from the left.
Base col (c=0): Can only arrive from above.
Every other cell (r, c): Min of (from above, from left).
```

#### Space Optimization:
Since `dp[r][c]` only depends on the current row and the row directly above it, a 2D $R \times C$ array can be flattened into a single 1D array of size $C$, reducing space from $O(R \times C)$ to $O(C)$!

---

### Archetype 4: Two Strings / Sequence Alignment (LCS)
* **When to use**: Longest Common Subsequence, Edit Distance, Palindromic Subsequences.
* **State Definition**: `dp[i][j]` = answer for prefix $s_1[0 \dots i-1]$ and prefix $s_2[0 \dots j-1]$.
* **Recurrence Invariant**:
  - If characters match ($s_1[i-1] == s_2[j-1]$): extend the match from both prefixes $\implies 1 + \text{dp}[i-1][j-1]$.
  - If characters differ: take the best choice by skipping either a character from $s_1$ or from $s_2$ $\implies \max(\text{dp}[i-1][j], \text{dp}[i][j-1])$.

```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                dp[i][j] = 1 + dp[i - 1][j - 1]; // Character match
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Skip character
            }
        }
    }
    return dp[m][n];
}
```

---

## 4. Algorithmic Diagnostic: DP vs. Greedy vs. Backtracking

In an interview, choosing the wrong strategy in the first 5 minutes can sink the round. Use this quick diagnostic:

```
                                  Does the problem ask for...
                                              |
        +-------------------------------------+-------------------------------------+
        |                                                                           |
"Find ALL valid combinations / permutations"                           "Find MIN / MAX / COUNT of ways"
        |                                                                           |
  BACKTRACKING                                                       Does a local optimal choice
 (O(2^N) or O(N!))                                                    always lead to a global optimum?
                                                                                    |
                                                          +-------------------------+-------------------------+
                                                          |                                                   |
                                                         YES                                                 NO
                                                          |                                                   |
                                                        GREEDY                                        DYNAMIC PROGRAMMING
                                                  (Intervals, Sorting)                            (Overlapping subproblems)
```
