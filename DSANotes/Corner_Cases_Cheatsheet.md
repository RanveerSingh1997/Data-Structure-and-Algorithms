# 🛡️ Algorithmic Corner Cases & Interview Traps Cheatsheet

> Inspired by **Yangshun Tay's [Tech Interview Handbook](https://github.com/yangshun/tech-interview-handbook)**.

Top-tier interviewers (Google, Meta, Amazon) rarely fail candidates on syntax. They evaluate whether you proactively anticipate **boundary conditions, integer overflows, off-by-one errors, and degenerated inputs** before typing code.

Use this checklist during **Stage 1 (Clarifying Questions)** and **Stage 4 (Manual Verification Trace)** of your interviews.

---

## 1. 📊 Arrays & Two Pointers

### High-Yield Corner Cases:
* **Empty array (`nums.length == 0`)** or null reference.
* **Single-element array (`nums.length == 1`)**: Will your two pointers `left < right` loop terminate immediately without calculating?
* **Two-element array**: Does your loop handle odd vs. even elements symmetrically?
* **Array with duplicate elements**: E.g., in *3Sum*, do you skip duplicates (`while (l < r && nums[l] == nums[l+1]) l++`)?
* **Array with negative numbers**: Sorting behavior and square products (e.g., `nums[i] * nums[i]` can change order).
* **All elements are identical**: E.g., `[2, 2, 2, 2, 2]`. Does partition logic in QuickSort degenerate to $O(N^2)$?
* **Integer Overflow on Sum / Midpoint**:
  ```java
  // ❌ TRAP: Overflow when left + right > Integer.MAX_VALUE
  int mid = (left + right) / 2;

  // ✅ SAFE: Provably avoids 32-bit signed overflow
  int mid = left + (right - left) / 2;
  ```

---

## 2. 🔤 Strings & Sliding Window

### High-Yield Corner Cases:
* **Empty string (`s.length() == 0`)** or null.
* **String with 1 character**: Is a single char a valid palindrome? (Yes).
* **All unique characters vs. All identical characters**: E.g., `"aaaaa"` for *Longest Substring Without Repeating*.
* **Character Set Boundaries**:
  * Is input strictly lowercase English (`a-z`, size 26)?
  * Standard ASCII (size 128)?
  * Extended ASCII (size 256) or UTF-16 / Unicode / Emojis?
* **Special Characters & Whitespace**: Spaces, punctuation, mixed casing in *Valid Palindrome*.
* **Shrinking Window Index Jumps**:
  ```java
  // ❌ TRAP: Moving left backward when duplicate was seen BEFORE current window left!
  left = lastSeen.get(ch) + 1;

  // ✅ SAFE: Monotonically advance left boundary
  left = Math.max(left, lastSeen.get(ch) + 1);
  ```

---

## 3. 🔗 Linked Lists

### High-Yield Corner Cases:
* **Null Head (`head == null`)**: Instant guard clause required.
* **Single Node List (`head.next == null`)**: Does reversing return the node unharmed?
* **Two Nodes**: Does swapping or deletion handle the head or tail properly?
* **Cycle Existence**: Does fast-slow pointer guard against `fast == null || fast.next == null`?
* **Odd vs. Even Length**:
  * For finding middle node: Does interviewer expect the first or second middle node in an even list?
  ```java
  // Returns second middle for even length (e.g., 1->2->3->4 returns 3)
  while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
  }
  ```
* **Dummy Head Technique**: Always use `ListNode dummy = new ListNode(0); dummy.next = head;` whenever the head node might be removed, prepended, or merged!

---

## 4. 🌲 Binary Trees & BSTs

### High-Yield Corner Cases:
* **Empty Tree (`root == null`)**: Return 0, null, or true depending on question contract.
* **Single Node Tree**: Height is 0 or 1? (Clarify definition: node count vs. edge count).
* **Degenerate / Skewed Tree**: A tree that is essentially a linked list (all left or all right children).
  * *Trap*: Recursive call stack depth becomes $O(N)$, causing `StackOverflowError` if $N = 10^5$. Mention iterative BFS or Morris Traversal as follow-up!
* **BST Boundary Invariant Traps**:
  ```java
  // ❌ TRAP: Comparing only node.val > node.left.val and node.val < node.right.val
  // Fails on: [5, 4, 6, null, null, 3, 7] where 3 is in right subtree of 5!

  // ✅ SAFE: Pass min and max range down recursion with 64-bit Long bounds
  boolean isValidBST(TreeNode root, Long min, Long max) {
      if (root == null) return true;
      if (min != null && root.val <= min) return false;
      if (max != null && root.val >= max) return false;
      return isValidBST(root.left, min, (long) root.val) &&
             isValidBST(root.right, (long) root.val, max);
  }
  ```
* **Negative Node Values**: `Integer.MIN_VALUE` as node values will overflow if using integer min/max.

---

## 5. 🌐 Graphs & Traversals

### High-Yield Corner Cases:
* **Disconnected Components**: Graph with islands or vertices that have degree 0. Always loop through all $V$ vertices in outer DFS/BFS wrapper.
* **Cycles in Directed vs. Undirected Graphs**:
  * Undirected: track `parent` node so you don't falsely detect backwards edge as cycle.
  * Directed: use 3-state coloring (`0 = UNVISITED`, `1 = VISITING`, `2 = VISITED`) to detect back-edges.
* **Self-Loops & Multi-Edges**: A node pointing to itself (`u -> u`).
* **Matrix Out-of-Bounds**:
  ```java
  boolean inBounds(int r, int c, int rows, int cols) {
      return r >= 0 && r < rows && c >= 0 && c < cols;
  }
  ```
* **Queue Growth in BFS**: Never enqueue without immediately marking visited! Enqueueing before marking leads to exponential duplicate additions and OutOfMemoryError.

---

## 6. 🔎 Binary Search

### High-Yield Corner Cases:
* **Target not found**: Returns `-1` or insertion index?
* **Array of length 1 or 2**:
* **Duplicates in Rotated Sorted Array**: When `nums[mid] == nums[left] == nums[right]`, you cannot tell which half is sorted; must shrink `left++` and `right--`, degrading to $O(N)$.
* **Search on Answer Space (Predicate / Feasibility)**:
  * What is the search domain `[low, high]`?
  * Example (*Koko Eating Bananas*): `low = 1`, `high = max(piles)`.
  * Avoid `low = 0` to prevent division by zero!

---

## 7. ⏱️ Dynamic Programming

### High-Yield Corner Cases:
* **Target Amount / State is 0**: Base case `dp[0] = 0` (e.g., 0 coins to make 0 amount).
* **Unreachable State**: When no combination can form the target, return `-1` or infinity.
  * *Trap*: `dp[i] = Math.min(dp[i], dp[i - coin] + 1)` will overflow into negative numbers if `dp[i - coin]` is initialized to `Integer.MAX_VALUE`!
  ```java
  // ✅ SAFE: Use amount + 1 as sentinel infinity
  int maxVal = amount + 1;
  Arrays.fill(dp, maxVal);
  dp[0] = 0;
  ```
* **Off-by-one in DP Table Sizing**: Table should almost always be size `N + 1` or `(M + 1) x (N + 1)` to cleanly store base cases at index 0.

---

## 8. 🧮 Intervals & Scheduling

### High-Yield Corner Cases:
* **Unsorted input**: Always clarify: *"Are intervals sorted by start time?"* (95% of interval problems require sorting first).
* **Equal endpoints**: E.g., `[1, 4]` and `[4, 5]`. Do they overlap at point 4? (Clarify with interviewer: inclusive vs exclusive bounds).
* **One interval completely contained inside another**: `[1, 10]` and `[2, 5]`. Merged result is `[1, 10]`, not `[1, 5]`.
  ```java
  currEnd = Math.max(currEnd, nextEnd);
  ```

---

## 9. 🥞 Monotonic Stack & Queue

### High-Yield Corner Cases:
* **Strictly increasing/decreasing vs. Non-decreasing**: Use `<` or `<=` based on whether duplicate values should trigger a pop.
* **Remaining elements in stack at termination**: What happens to elements that never encounter a "next greater element"? (Usually assigned `-1` or `0`).
* **Storing Indices vs. Values**: In 90% of monotonic stack problems (*Daily Temperatures*, *Largest Rectangle in Histogram*), store **indices** in the stack so you can compute distances `i - stack.peek()`.

---

## 10. ⚡ 30-Second Pre-Coding Sanity Checklist

Before you write the final return statement, ask yourself:
1. Did I handle `null` and empty inputs?
2. Can any calculation (`mid`, sum, product) exceed $2^{31} - 1$?
3. Did I advance pointer(s) inside every branch of the `while` loop (no infinite loops)?
4. Did I test with the smallest possible input ($N=0, 1$)?
5. Did I check `<` vs `<=` at boundary indices?
