# Data Structures and Algorithms Practice 🚀

Welcome to my personal Data Structures and Algorithms (DSA) preparation repository! This project serves as a code vault,
algorithmic template library, and interview preparation tracker tailored for top-tier software engineering interviews
(Google, FAANG, and tier-1 product companies).

Every solution and practice template is written in clean, modern Java, fully documented with problem links, detailed
descriptions, constraints, examples, Big-O complexities, and includes its own standalone `main()` test runner.

---

## 📊 Quick Links & Resources

* ⚡ **[DSA Studio Live Visualizer](https://ranveer.is-a.dev/Data-Structure-and-Algorithms/)** (or local [visualizer/index.html](./visualizer/index.html)): Interactive algorithmic invariant workbench with step-by-step simulations, prediction challenge mode, whiteboard dry-run trace table, and diagnostic wizard.
* 🏆 **[Solved Problems Tracker & Practice Queue (SOLVED.md)](./SOLVED.md)**: Categorized table of all solved questions,
  complexities, and 16 curated Google Easy & Medium practice Templates.
* 🚀 **[Fast Learning & Deep Mastery Guide](./notes/Fast_Learning_and_Deep_Mastery_Guide.md)**: Accelerated learning
  blueprint, 20-minute rule, invariant proofs, constraint cheat codes, pattern triggers & 5-minute review routine.
* 🎯 **[Google SWE Interview Prep Guide](./notes/Google_Interview_Guide.md)**: Complete 45-minute live coding blueprint,
  evaluation rubric, clarifying questions checklist, and high-frequency follow-ups.
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
│   ├── Hashing/                  # Hash table, roman numerals, frequency maps
│   │   └── RomanToInteger.java
│   ├── Arrays/                   # Arrays, two pointers, prefix sums, sliding window
│   │   ├── TwoSum.java
│   │   ├── ContainsDuplicate.java
│   │   ├── Anagram.java
│   │   ├── AlienDictionary.java
│   │   ├── TopKFrequentElements.java
│   │   ├── MeetingRooms.java              # [Google Easy Practice]
│   │   ├── ContainerWithMostWater.java    # [Google Medium Practice]
│   │   ├── ThreeSum.java                  # [Google Medium Practice]
│   │   └── MergeIntervals.java            # [Google Medium Practice]
│   ├── Strings/                  # String manipulation, pattern matching
│   │   └── LongestSubstringWithoutRepeating.java # [Google Medium Practice]
│   ├── LinkedList/              # Singly, doubly, fast & slow pointers, reversal
│   │   ├── Reverse.java
│   │   ├── FindMiddleNode.java
│   │   ├── FindLoop.java
│   │   ├── FindKthElementFromEnd.java
│   │   └── RemoveDuplicates.java
│   ├── Stacks_Queues/            # Stack, queue, monotonic stack, parentheses
│   │   ├── ValidParentheses.java
│   │   ├── MakeStringGreat.java
│   │   ├── MinOperation.java
│   │   ├── RemoveStarsFromString.java
│   │   ├── ReversePolishNotation.java
│   │   ├── ReverseString.java
│   │   ├── SortStack.java
│   │   ├── QueueWithStack.java
│   │   ├── StackWithQueue.java
│   │   ├── ValidateStackSequences.java    # [Medium Practice]
│   │   └── DailyTemperatures.java         # [Google Medium Practice]
│   ├── Trees/                    # Binary Trees, BSTs, traversals, LCA
│   │   ├── InvertBinaryTree.java          # [Google Easy Practice]
│   │   ├── DiameterOfBinaryTree.java      # [Google Easy Practice]
│   │   ├── ValidateBinarySearchTree.java  # [Google Medium Practice]
│   │   └── LowestCommonAncestor.java      # [Google Medium Practice]
│   ├── Graphs/                   # BFS, DFS, shortest paths, topological sort
│   │   ├── NumberOfIslands.java           # [Google Medium Practice]
│   │   ├── CourseSchedule.java            # [Google Medium Practice]
│   │   └── RottingOranges.java            # [Google Medium Practice]
│   ├── DP/                       # Dynamic programming (1D, 2D, knapsack, intervals)
│   │   └── CoinChange.java                # [Google Medium Practice]
│   ├── Sorting_Searching/        # Binary search, merge sort, quick sort
│   │   └── SearchRotatedSortedArray.java  # [Google Medium Practice]
│   ├── Templates/                # Reusable advanced algorithmic Templates
│   │   ├── DSU.java              # Disjoint Set Union (Path Compression + Union by Rank)
│   │   ├── SegmentTree.java      # Segment Tree for Range Queries & Point Updates
│   │   └── Trie.java             # Prefix Tree for string dictionary search
│   └── Utils/                    # Data structure implementations, parsers & profilers
│       ├── TreeNode.java         # LeetCode binary tree node (with fromLeetCode & print)
│       ├── TreeVisualizer.java   # LeetCode string parser & 2D ASCII tree visualizer
│       ├── ArrayVisualizer.java  # Two Pointers, Sliding Window, Stack & Binary Search visualizer
│       ├── Benchmark.java        # Micro-benchmarking, ops/sec & memory profiler
│       ├── ListNode.java         # Standard LeetCode singly linked list node
│       ├── Node.java             # Generic bidirectional node
│       ├── LinkedList.java       # Custom singly linked list with full API
│       ├── DoublyLinkedList.java # Custom doubly linked list implementation
│       ├── Stack.java            # Node-based integer stack
│       ├── StackTemplate.java    # Generic ArrayList-backed stack
│       └── Queue.java            # Node-based FIFO queue
├── visualizer/                   # Standalone offline interactive algorithmic visualizer
│   ├── index.html                # Interactive simulator, pattern cues & code tracer
│   ├── styles.css                # Dark glassmorphism, responsive visualizer components
│   ├── patterns-data.js          # Pattern triggers, mental models, Big-O & Java blueprints
│   ├── visualizers.js            # Algorithmic state generators for all core patterns
│   └── app.js                    # Playback engine, step scrubber & DOM canvas renderers
├── test/
│   ├── AllTestsRunner.java       # Automated test runner (45 tests passing)
│   └── test.SampleTest.java           # JUnit 5 test example
├── notes/                        # In-depth interview notes, playbooks & cheatsheets
├── SOLVED.md                     # Live tracking table & practice queue
└── README.md
```

---

## ⚡ Interactive DSA Pattern Visualizer Platform

This repository includes a standalone, zero-dependency offline web application in [
`visualizer/`](./visualizer/index.html) designed for visual pattern mastery:

* 🎯 **Active Prediction Challenge Mode**: Pauses automatically at critical decision branches to test your intuition (*"Which pointer advances next and why?"*), with real-time scoring and sound feedback to build deep algorithmic muscle memory.
* 📊 **Whiteboard Dry-Run Variable Trace Table**: Auto-generates real-time variable trace tables (L, R, Area, Window, Stack, etc.) with click-to-scrub time travel and a 1-click **Copy as Markdown** button for whiteboard interview prep.
* 🧭 **Algorithmic Pattern Diagnostic Wizard**: Interactive 2-step decision tree that diagnoses any interview problem's input structure & constraints and recommends the optimal pattern with direct workbench launch.
* 🎯 **Interview Playbook & Common Traps**: 3 clarifying questions to ask interviewers, constraint rule-of-thumb cheat codes ($N \le 10^5 \implies O(N)$), and common pitfalls / off-by-one bugs.
* 📋 **Clean Starter Templates**: One-click copy for clean Java boilerplate without spoilers, ready to paste directly into LeetCode or your IDE.
* 🎬 **Interactive Step-by-Step State Simulator**: Live visual animations with Play/Pause, Step Forward/Back, scrubber slider, speed presets (0.5x to 2.5x), and Web Audio synthesizer cues.
* 🧪 **Custom Input Playground**: Type any custom array, string, tree, rotated sequence, or grid into the input box and step through the simulation!
* 💡 **Algorithmic Invariant Inspector**: Real-time HUD showing pointer values ($L, R, mid$), active stack frames, sliding window bounds, and formal mathematical invariant proofs.
* 💻 **Synchronized Java Execution Tracing**: Synchronized code viewer tracking the exact Java solution file in this repository with live active line beacons.

### How to Launch the Visualizer:
 
* 🌐 **Option 1: Live GitHub Pages Web App (No Install Required)**:
  [https://ranveer.is-a.dev/Data-Structure-and-Algorithms/](https://ranveer.is-a.dev/Data-Structure-and-Algorithms/)

* 💻 **Option 2: Open locally in your browser**:
  ```bash
  open visualizer/index.html
  ```

* 🚀 **Option 3: Run via local lightweight HTTP server**:
  ```bash
  python3 -m http.server 3000 --directory visualizer
  # Then visit http://localhost:3000 in your browser
  ```

---

## 🛠️ Developer & Practice Utilities

### 1. 🌲 LeetCode Tree Parser & 2D ASCII Visualizer (`Utils.Visualise.TreeVisualizer`)

Stop manually building binary Trees node-by-node! You can now copy-paste LeetCode input Strings directly:

```java
// Parse directly from LeetCode format
TreeNode root = TreeNode.fromLeetCode("[4, 2, 7, 1, 3, 6, 9]");

// Render 2D visual ASCII diagram in console
root.

print(); // or TreeVisualizer.printTree(root);
```

**Console Output:**

```text
   4       
  / \   
 /   \  
 2   7   
/ \ / \ 
1 3 6 9 
```

Run the visualizer demo:

```bash
java -cp out Utils.Visualise.TreeVisualizer
```

### 2. 📊 Array, Two Pointers & Window Visualizer (`Utils.Visualise.ArrayVisualizer`)

Print rich terminal ASCII snapshots for pointer convergence, sliding window bounding boxes, and monotonic stacks right
in your console:

```java
// Two pointers snapshot with L and R markers
ArrayVisualizer.printTwoPointers(heights, left, right, "Move R left");

// Sliding window bracket representation
ArrayVisualizer.

printSlidingWindow(text, left, right, "Valid distinct window");

// Monotonic stack inspection
ArrayVisualizer.

printStackState(temperatures, currentIndex, stack, "Popped previous colder day");
```

Run the array visualizer demo:

```bash
java -cp out Utils.Visualise.ArrayVisualizer
```

### 3. ⏱️ Micro-Benchmarking & Space Profiler (`Utils.Benchmark`)

Compare alternative data structures and algorithms with high-precision execution timing, throughput (`ops/sec`), and
heap memory allocation tracking:

```java
Benchmark.compare(
    "int[128] Array",
            () ->{ /* approach 1 */ },
        "HashMap<Character, Integer>",
        ()->{ /* approach 2 */ },
        100_000
        );
```

Run the benchmark demo:

```bash
java -cp out Utils.Benchmark
```

---

## ⚡ How to Run Solutions & Practice Tests

Every problem class and template includes its own self-contained `public static void main(String[] args)` test runner.

### Option 1: In IntelliJ IDEA

* Open any file (e.g. `src/Arrays/MergeIntervals.java` or `src/DP/CoinChange.java`).
* Click the green **Run (▶)** icon in the gutter next to `main()` or press `Ctrl + Shift + R` (`Control + Shift + R` on
  macOS).

### Option 2: Run Individual Files via Terminal

```bash
# Compile all source files
javac -d out $(find src -name "*.java")

# Run any practice problem to test your code
java -cp out Trees.InvertBinaryTree
java -cp out Arrays.MergeIntervals
java -cp out Sorting_Searching.SearchRotatedSortedArray
java -cp out DP.CoinChange
java -cp out Graphs.NumberOfIslands
java -cp out Stacks_Queues.DailyTemperatures
```

### Option 3: Run the Automated Full Test Suite

Run the centralized test suite to verify all completed solutions:

```bash
javac -d out $(find src test -name "*.java" ! -name "test.SampleTest.java")
java -cp out test.AllTestsRunner
```

---

## 📝 Problem Practice Template Format

Each practice problem in this repository follows a clean, standardized format with no solutions spoiled:

```java
package Arrays;

/**
 * ============================================================================
 * Problem: Merge Intervals
 * LeetCode #56 | Difficulty: Medium
 * Company: Google Interview Question
 * Link: https://leetcode.com/problems/merge-intervals/
 * ============================================================================
 *
 * 📖 PROBLEM DESCRIPTION:
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge all
 * overlapping intervals...
 *
 * 📥 EXAMPLES:
 *   Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *   Output: [[1,6],[8,10],[15,18]]
 *
 * ⚙️ CONSTRAINTS:
 *   1 <= intervals.length <= 10^4
 *
 * 💡 GOOGLE INTERVIEW HINTS:
 *   - What happens when you sort intervals by start time?
 *   - How do you update currentEnd when intervals overlap?
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        // TODO: Implement your solution here
        return new int[][]{};
    }

    static void main(String[] args) {
        MergeIntervals solver = new MergeIntervals();
        // Built-in test cases with [PASS] / [TODO] feedback!
    }
}
```

---

## 🎯 Preparation Milestones

- [x] Implement foundational data structures (`LinkedList`, `DoublyLinkedList`, `Stack`, `Queue`).
- [x] Implement advanced Templates (`DSU`, `SegmentTree`, `Trie`).
- [x] Build developer tools (`TreeVisualizer` with ASCII 2D rendering & `Benchmark` memory profiler).
- [x] Build comprehensive revision guides (`Fast_Learning_and_Deep_Mastery_Guide.md`, `Google_Interview_Guide.md`).
- [x] Standardize automated test runner (`test.AllTestsRunner`, 45 passing tests).
- [ ] Solve the curated **16 Google Easy & Medium Practice Queue** in [`SOLVED.md`](./SOLVED.md).
- [ ] Complete NeetCode 150 core problem set.
- [ ] Regular weekly contest participation and upsolving.

---

## 🔗 Useful Links & Platforms

* [LeetCode](https://leetcode.com/)
* [NeetCode Roadmap](https://neetcode.io/roadmap)
* [Take U Forward (Striver's SDE Sheet)](https://takeuforward.org/)
