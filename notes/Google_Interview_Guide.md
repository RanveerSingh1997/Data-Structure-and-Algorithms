# 🎯 Google SWE Interview Preparation Guide (Easy & Medium Focus)

This guide provides an actionable, end-to-end framework specifically calibrated for **Google Software Engineer (L3 / L4 / L5)** coding rounds.

---

## 🏛️ The Google Interview Evaluation Rubric

Google interviewers evaluate candidates across **4 core pillars**:

1. **Algorithmic Problem Solving (40%)**:
   * Did you recognize the optimal data structure and algorithmic pattern quickly?
   * Can you articulate *why* a naive approach is suboptimal and mathematically prove *why* the optimal approach works?
2. **Coding Quality & Fluency (30%)**:
   * Clean, idiomatic code with meaningful variable names (`currEnd`, `inDegree`, `lastIndex`).
   * Clean separation of concerns (helper methods, modular subroutines).
   * No syntax hesitation or sloppy index errors (`<` vs `<=`, off-by-one).
3. **Communication & Collaboration (20%)**:
   * Treating the interview as a pair-programming session.
   * Thinking out loud; explaining trade-offs before writing code.
   * Asking sharp clarifying questions before touching the keyboard.
4. **Testing, Verification & Edge Cases (10%)**:
   * Proactively dry-running your code with a sample trace before the interviewer asks.
   * Testing boundaries: empty inputs, single element, negative numbers, all duplicates, large inputs.

---

## ⏱️ The 45-Minute Interview Blueprint

```
[00:00 - 05:00]  Clarification, Constraints & Examples
[05:00 - 15:00]  Brainstorming, Trade-offs & Verifying the Approach
[15:00 - 35:00]  Clean, Modular Implementation
[35:00 - 40:00]  Step-by-Step Dry-Run & Edge Case Verification
[40:00 - 45:00]  Big-O Complexity & Google Follow-Up Questions
```

> [!IMPORTANT]
> **Never write code before your interviewer agrees with your approach!**
> Pitch the brute force first (30 seconds), then state its bottleneck, propose the optimal approach, and wait for a nod or confirmation before coding.

---

## 📋 Google Easy & Medium Core Problem Matrix

The 12 curated questions below represent the most frequent algorithmic patterns tested across Google SWE interviews. All implementations and test suites are located in `src/`.

| # | Problem | Difficulty | Category | File Path | Time | Space | Core Google Insight |
|:---|:---|:---:|:---|:---|:---:|:---:|:---|
| 226 | **Invert Binary Tree** | 🟢 Easy | Trees / Recursion | [`InvertBinaryTree.java`](../src/trees/InvertBinaryTree.java) | `O(N)` | `O(H)` | Swap left & right at each node; handle iterative BFS queue for unbalanced tree safety. |
| 252 | **Meeting Rooms** | 🟢 Easy | Intervals / Sorting | [`MeetingRooms.java`](../src/arrays/MeetingRooms.java) | `O(N log N)` | `O(1)` | Sort by start time; any overlap MUST be adjacent. |
| 543 | **Diameter of Binary Tree** | 🟢 Easy | Trees / Post-Order DFS | [`DiameterOfBinaryTree.java`](../src/trees/DiameterOfBinaryTree.java) | `O(N)` | `O(H)` | Helper returns height; updates global max with `leftDepth + rightDepth`. |
| 3 | **Longest Substring Without Repeating** | 🟡 Medium | Sliding Window | [`LongestSubstringWithoutRepeating.java`](../src/strings/LongestSubstringWithoutRepeating.java) | `O(N)` | `O(min(N,M))` | `int[128]` last-seen lookup; jump `left = max(left, lastSeen + 1)`. |
| 11 | **Container With Most Water** | 🟡 Medium | Two Pointers / Greedy | [`ContainerWithMostWater.java`](../src/arrays/ContainerWithMostWater.java) | `O(N)` | `O(1)` | Shrink from outside; discard shorter line because smaller width cannot beat current area. |
| 33 | **Search in Rotated Sorted Array** | 🟡 Medium | Binary Search | [`SearchRotatedSortedArray.java`](../src/sorting_searching/SearchRotatedSortedArray.java) | `O(log N)` | `O(1)` | One half is always sorted; check if target falls in sorted boundary. |
| 56 | **Merge Intervals** | 🟡 Medium | Intervals / Greedy | [`MergeIntervals.java`](../src/arrays/MergeIntervals.java) | `O(N log N)` | `O(N)` | Sort by start; merge when `nextStart <= currEnd`, else push. |
| 200 | **Number of Islands** | 🟡 Medium | Grid BFS / DFS | [`NumberOfIslands.java`](../src/graphs/NumberOfIslands.java) | `O(M * N)` | `O(M * N)` | 4-directional connected components; sink visited land to prevent redundant visits. |
| 207 | **Course Schedule** | 🟡 Medium | Topological Sort | [`CourseSchedule.java`](../src/graphs/CourseSchedule.java) | `O(V + E)` | `O(V + E)` | Kahn's Algorithm (in-degrees + BFS) or 3-color DFS cycle detection. |
| 236 | **Lowest Common Ancestor** | 🟡 Medium | Trees / Post-Order DFS | [`LowestCommonAncestor.java`](../src/trees/LowestCommonAncestor.java) | `O(N)` | `O(H)` | Both subtrees return non-null -> root is LCA; else propagate non-null child. |
| 322 | **Coin Change** | 🟡 Medium | Dynamic Programming | [`CoinChange.java`](../src/dp/CoinChange.java) | `O(amount * C)` | `O(amount)` | Greedy fails (e.g. [1,3,4] for 6); 1D bottom-up DP or BFS unweighted shortest path. |
| 739 | **Daily Temperatures** | 🟡 Medium | Monotonic Stack | [`DailyTemperatures.java`](../src/stacks_queues/DailyTemperatures.java) | `O(N)` | `O(N)` | Monotonic decreasing stack storing indices; amortized O(N) single-pass. |

---

## 💡 Clarifying Questions Playbook for Google Interviews

Google heavily marks down candidates who start coding before asking clarifying questions. Use this checklist:

### 1. Arrays & Strings
* *"Can the input be null or empty?"*
* *"Are integers signed, positive, negative, or zero?"*
* *"Can the array contain duplicate elements?"*
* *"What is the character set for strings? ASCII, lowercase only, or full Unicode/emojis?"*
* *"Should the output preserve the original order of elements?"*
* *"Can we modify the input array in-place, or should it remain immutable?"*

### 2. Intervals & Calendars
* *"Are the interval boundaries inclusive `[start, end]` or half-open `[start, end)`?"*
* *"If meeting A ends at 10:00 and meeting B starts at 10:00, is that considered a conflict?"*
* *"Is the input intervals array pre-sorted?"*
* *"Can intervals have duration zero (`[5, 5]`)?"*

### 3. Trees & Graphs
* *"Can the graph contain cycles or self-loops?"*
* *"Is the graph connected, or could there be multiple disconnected components?"*
* *"Can node values be negative or duplicate?"*
* *"In tree problems, are target nodes guaranteed to exist in the tree?"*
* *"Do nodes have parent pointers, or only child pointers?"*

### 4. Dynamic Programming & Optimization
* *"Can elements be reused an unlimited number of times (unbounded knapsack) or at most once (0/1 knapsack)?"*
* *"What should we return if no valid solution exists? (-1, null, empty array?)"*
* *"What are the maximum bounds of `N` and `amount`? (To detect 32-bit integer overflow)."*

---

## 🚀 The Top Google Follow-Up Archetypes

Google interviewers frequently push beyond the standard problem statement:

### Follow-Up 1: The "Big Data / Streaming" Question
* **Interviewer**: *"What if the input is too large to fit in memory (e.g. 1 Terabyte of intervals or numbers)?"*
* **Response**:
  1. External Merge Sort: Chunk the data into disk-sized blocks, sort each block in RAM, write back, and perform a K-way merge using a Min-Heap.
  2. MapReduce / Distributed Partitioning: Hash-partition data across worker nodes.
  3. Reservoir Sampling: If sampling `K` random items from an infinite stream.

### Follow-Up 2: The "Dynamic / Real-Time Updates" Question
* **Interviewer**: *"What if islands are added dynamically one by one in real-time?"* (LC 305)
* **Response**: Use **Disjoint Set Union (DSU)** with path compression and union by rank. Each new land cell unites with up to 4 neighbors in nearly `O(1)` amortized time (`O(α(N))`).

### Follow-Up 3: The "Memory / Stack Overflow" Question
* **Interviewer**: *"What happens if the binary tree has 500,000 nodes and is degenerate (a straight line)?"*
* **Response**: Recursive DFS will throw a `StackOverflowError` in Java. Convert to iterative traversal using an explicit heap-allocated `Deque` or Morris Traversal (`O(1)` space using threaded trees).

---

## 🧪 Running Solutions and Verification

Compile and run all Google test suites:

```bash
# Compile all files
javac -d out $(find src test -name "*.java" ! -name "SampleTest.java")

# Run full test runner
java -cp out test.AllTestsRunner

# Run individual Google question
java -cp out trees.InvertBinaryTree
java -cp out arrays.MergeIntervals
java -cp out dp.CoinChange
```
