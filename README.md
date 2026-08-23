# Data Structures and Algorithms Practice

Welcome to my Data Structures and Algorithms (DSA) preparation repository! 🚀 

This repository serves as my personal tracker and code vault for DSA problems I solve during my interview preparation journey. It is structured to keep things organized, easily accessible, and simple to review.

## 📂 Repository Structure

The code is organized by topic to make it easier to focus on specific patterns and concepts:

```text
src/
 ├── arrays/               # Array manipulation, prefix sums, sliding window, two pointers
 ├── strings/              # String algorithms, pattern matching, anagrams
 ├── linked_list/          # Singly, doubly, circular linked lists, slow/fast pointers
 ├── stacks_queues/        # Stack, Queue, Monotonic stacks, Deque
 ├── trees/                # Binary Trees, BSTs, Traversals, Trie
 ├── graphs/               # BFS, DFS, Shortest Path, MST, Topological Sort
 ├── dp/                   # Dynamic Programming (1D, 2D, Knapsack, DP on trees)
 ├── greedy/               # Greedy algorithms and intervals
 ├── sorting_searching/    # Binary Search, Merge Sort, Quick Sort, Custom Sorts
 ├── math/                 # Number theory, Primes, GCD, Modulo arithmetic
 ├── recursion/            # Standard recursion problems
 ├── backtracking/         # Generating permutations, combinations, N-Queens
 └── bit_manipulation/     # Bitwise operators, XOR properties
```

## 📝 Naming Conventions & Organization

To maintain consistency, I follow these conventions:

- **Classes/Files**: Named after the problem, e.g., `TwoSum.java`, `MergeIntervals.java`.
- **Comments & Documentation**: 
  - Each file includes the problem link (LeetCode, Codeforces, GeeksforGeeks, etc.).
  - A brief explanation of the **Approach**.
  - **Time & Space Complexity** at the top of the solution method.

### Example Solution Template

```java
package arrays;

/**
 * Problem: Two Sum
 * Link: https://leetcode.com/problems/two-sum/
 * 
 * Approach:
 * Use a HashMap to store the numbers and their indices.
 * For each number `nums[i]`, check if `target - nums[i]` exists in the map.
 * 
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
```

## 🎯 Goals

- [ ] Complete foundational patterns (Sliding Window, Two Pointers, BFS/DFS, etc.).
- [ ] Solve top 150/75 problems (e.g., NeetCode 150 or Blind 75).
- [ ] Participate in weekly contests and upsolve problems.
- [ ] Maintain consistent daily commits.

## 🔗 Useful Resources
*   [LeetCode](https://leetcode.com/)
*   [NeetCode Roadmap](https://neetcode.io/roadmap)
*   [Take U Forward (Striver's SDE Sheet)](https://takeuforward.org/)
