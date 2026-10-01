# SPEC-128: Longest Consecutive Sequence

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Hashing / HashSet / Arrays`  
> **Source**: [LeetCode #128 - Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)  
> **Target Complexity**: Time `O(N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given an unsorted array of integers `nums`, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in $O(N)$ time.

### 1.2 Method Signature
```java
package Hashing;

public class LongestConsecutiveSequence {
    public static int longestConsecutiveSequence(int[] array);
}
```

### 1.3 Pre-Conditions
- $0 \le \text{array.length} \le 10^5$.
- $-10^9 \le \text{array}[i] \le 10^9$.

### 1.4 Post-Conditions
- Returns length of the longest streak of integers $x, x+1, x+2, \dots$ present in the array.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- A number $x$ is the **start of a consecutive streak** if and only if $(x - 1) \notin \text{HashSet}$.
- If $(x - 1) \in \text{HashSet}$, skipping $x$ ensures each streak is counted starting from its true minimum.
- Each number is visited at most twice (once in outer loop, once in streak expansion), guaranteeing $O(N)$ amortized runtime.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $0 \le n \le 10^5$ | Sorting takes $O(N \log N)$; HashSet achieves required $O(N)$. |
| Auxiliary Space | $O(N)$ | HashSet stores up to $N$ unique elements. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Multi-element streak | `[100, 4, 200, 1, 3, 2]` | `4` (sequence: 1, 2, 3, 4) |
| **TC-02** | Long sequence | `[0,3,7,2,5,8,4,6,0,1]` | `9` (sequence: 0 through 8) |
| **TC-03** | Empty array | `[]` | `0` |
| **TC-04** | Single element | `[10]` | `1` |
| **TC-05** | No consecutive numbers | `[1, 3, 5, 7]` | `1` |

---

## 5. Design & Algorithmic Strategy

- Populate `HashSet` with all numbers.
- For each number $x$, only expand while $x+1 \in \text{set}$ if $x-1 \notin \text{set}$.
- Maintain maximum streak encountered.
