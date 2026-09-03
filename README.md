# Data Structures and Algorithms Practice 🚀

Welcome to my personal Data Structures and Algorithms (DSA) preparation repository! This project serves as a code vault, algorithmic template library, and interview preparation tracker.

Every solution is written in clean, modern Java, fully documented with problem links and Big-O complexities, and includes its own standalone `main()` test runner.

---

## 📊 Quick Links & Resources

* 🏆 **[Solved Problems Tracker (SOLVED.md)](./SOLVED.md)**: Categorized table of all solved questions with time/space complexities and links.
* 📚 **[Revision Notes & Cheatsheets (notes/)](./notes/)**:
  * [Patterns Cheat Sheet](./notes/Patterns.md)
  * [Time & Space Complexity Guide](./notes/TimeComplexity.md)
  * [Algorithm Quick Refresh](./notes/Algorithm_Quick_Refresh.md)
  * [Java Syntax & Collections Cheatsheet](./notes/Java_Syntax_Cheatsheet.md)
  * [Bit Manipulation Tricks](./notes/Bit_Manipulation_Tricks.md)
  * [Math & Number Theory](./notes/Math_Number_Theory.md)

---

## 📂 Repository Structure

```text
.
├── src/
│   ├── Hashing/              # Hash table, roman numerals, frequency maps
│   ├── arrays/               # Arrays, two pointers, prefix sums, sliding window
│   ├── strings/              # String manipulation, pattern matching
│   ├── linked_list/          # Singly, doubly, fast & slow pointers, reversal
│   ├── stacks_queues/        # Stack, queue, monotonic stack, parentheses
│   ├── trees/                # Binary trees, BSTs, traversals, LCA
│   ├── graphs/               # BFS, DFS, shortest paths, topological sort
│   ├── dp/                   # Dynamic programming (1D, 2D, knapsack, intervals)
│   ├── greedy/               # Greedy choices and interval scheduling
│   ├── sorting_searching/    # Binary search, merge sort, quick sort
│   ├── recursion/            # Recursion foundations
│   ├── backtracking/         # Combinations, permutations, N-Queens
│   ├── bit_manipulation/     # Bitwise tricks and XOR properties
│   ├── math/                 # Prime factorization, GCD, modular arithmetic
│   ├── templates/            # Reusable advanced algorithmic templates
│   │   ├── DSU.java          # Disjoint Set Union (Path Compression + Union by Rank)
│   │   ├── SegmentTree.java  # Segment Tree for Range Queries & Point Updates
│   │   └── Trie.java         # Prefix Tree for string dictionary search
│   └── utils/                # Data structure implementations & node models
│       ├── ListNode.java     # Standard LeetCode singly linked list node
│       ├── TreeNode.java     # Standard LeetCode binary tree node
│       ├── Node.java         # Generic bidirectional node
│       ├── LinkedList.java   # Custom singly linked list with full API
│       ├── DoublyLinkedList.java # Custom doubly linked list implementation
│       ├── Stack.java        # Node-based integer stack
│       ├── StackTemplate.java# Generic ArrayList-backed stack
│       └── Queue.java        # Node-based FIFO queue
├── test/
│   ├── AllTestsRunner.java   # Standalone test runner (no external JARs needed)
│   └── SampleTest.java       # JUnit 5 test example
├── notes/                    # Comprehensive interview notes and cheatsheets
├── SOLVED.md                 # Live tracking table of solved problems
└── README.md
```

---

## ⚡ How to Run Solutions & Tests

Every problem class and template includes a `public static void main(String[] args)` method with built-in test cases.

### Option 1: In IntelliJ IDEA
* Open any file (e.g. `TwoSum.java`, `ValidParentheses.java`, or `Trie.java`).
* Click the green **Run (▶)** icon in the gutter next to `public static void main(String[] args)` or press `Ctrl + Shift + R` (`Control + Shift + R` on macOS).

### Option 2: Run Individual Files via Terminal
```bash
# Compile all source files
javac -d out $(find src -name "*.java")

# Run specific problem solutions
java -cp out arrays.TwoSum
java -cp out Hashing.RomanToInteger
java -cp out stacks_queues.ValidParentheses
java -cp out linked_list.Reverse
java -cp out templates.Trie
```

### Option 3: Run the Full Test Suite
Run the automated test runner to verify all solutions and data structure edge cases:
```bash
javac -d out $(find src test -name "*.java" ! -name "SampleTest.java")
java -cp out test.AllTestsRunner
```

---

## 📝 Problem Documentation Template

Each problem in this repository follows a clean, standardized format:

```java
package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Two Sum
 * Link: https://leetcode.com/problems/two-sum/
 * 
 * Approach:
 * Use a HashMap to store seen values mapped to their indices.
 * For each element nums[i], check if (target - nums[i]) exists in the map.
 * 
 * Time Complexity:  O(N)
 * Space Complexity: O(N)
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum solver = new TwoSum();
        int[] result = solver.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println("Output: " + java.util.Arrays.toString(result) + " (Expected [0, 1])");
    }
}
```

---

## 🎯 Preparation Goals

- [x] Implement foundational data structures (LinkedList, DoublyLinkedList, Stack, Queue).
- [x] Implement advanced templates (DSU, Segment Tree, Trie).
- [x] Standardize test runner with `main()` methods for every problem.
- [ ] Complete NeetCode 150 / Blind 75 core problem set.
- [ ] Implement Trees & BST solutions (traversals, LCA, validate BST).
- [ ] Implement Graph patterns (BFS, DFS, Dijkstra, Topological Sort).
- [ ] Master 1D & 2D Dynamic Programming patterns.
- [ ] Regular weekly contest participation and upsolving.

---

## 🔗 Useful Links & Platforms
* [LeetCode](https://leetcode.com/)
* [NeetCode Roadmap](https://neetcode.io/roadmap)
* [Take U Forward (Striver's SDE Sheet)](https://takeuforward.org/)
