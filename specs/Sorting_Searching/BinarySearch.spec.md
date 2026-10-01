# SPEC-704: Binary Search

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Sorting_Searching / Binary Search`  
> **Source**: [LeetCode #704 - Binary Search](https://leetcode.com/problems/binary-search/)  
> **Target Complexity**: Time `O(log N)` | Space `O(log N)` (Recursive) / `O(1)` (Iterative)  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given an array of integers `nums` which is sorted in ascending order, and an integer `target`, write a function to search `target` in `nums`. If `target` exists, then return its index. Otherwise, return `-1`.

### 1.2 Method Signature
```java
package Sorting_Searching;

public class BinarySearch {
    public static int search(int[] nums, int target);
}
```

### 1.3 Pre-Conditions
- `nums` is sorted in strictly ascending order with all unique values.
- $1 \le \text{nums.length} \le 10^4$.
- $-10^4 \le \text{nums}[i], \text{target} \le 10^4$.

### 1.4 Post-Conditions
- Returns 0-based index of `target` if found, else `-1`.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Search Space Interval**: If `target` exists in `nums`, it is guaranteed to lie within the closed interval $[left, right]$.
- Calculate midpoint safely: $mid = left + (right - left) / 2$.
- If `nums[mid] == target`, target found at $mid$.
- If `nums[mid] < target`, by monotonicity, target cannot lie in $[left, mid]$, so reduce search space to $[mid + 1, right]$.
- If `nums[mid] > target`, target cannot lie in $[mid, right]$, so reduce search space to $[left, mid - 1]$.
- Terminates when $left > right$, proving target does not exist.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $1 \le n \le 10^4$ | Bounded by $\lceil \log_2(10^4) \rceil \approx 14$ iterations. |
| Space | Call stack | $O(\log N)$ recursive depth. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `nums` | Target | Expected Output |
| :--- | :--- | :--- | :---: | :---: |
| **TC-01** | Target present | `[-1, 0, 3, 5, 9, 12]` | `9` | `4` |
| **TC-02** | Target absent | `[-1, 0, 3, 5, 9, 12]` | `2` | `-1` |
| **TC-03** | Single element match | `[5]` | `5` | `0` |
| **TC-04** | Single element mismatch | `[5]` | `-5` | `-1` |
| **TC-05** | Even length array | `[-1, 0, 2, 4, 6, 8]` | `4` | `3` |

---

## 5. Design & Algorithmic Strategy

- Recursive binary search with middle computation avoiding integer overflow.
