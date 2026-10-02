# SPEC-217: Contains Duplicate

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Arrays / HashSet`  
> **Source**: [LeetCode #217 - Contains Duplicate](https://leetcode.com/problems/contains-duplicate/)  
> **Video Explanation**: [NeetCode - Contains Duplicate](https://youtu.be/3OamzN90kPg)  
> **Documentation / Read Link**: [NeetCode.io - Contains Duplicate](https://neetcode.io/problems/duplicate-integer) · [TakeUForward - Contains Duplicate](https://takeuforward.org/data-structure/contains-duplicate-in-array/)  
> **Target Complexity**: Time `O(N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Iterate through the array and insert each element into a hash set. Since sets store unique elements, if `set.add(num)` returns false (or `set.contains(num)` is true), a duplicate exists, allowing an immediate early exit in $O(N)$ time and $O(N)$ space.

### 1.2 Description
Given an integer array `nums`, return `true` if any value appears at least twice in the array, and return `false` if every element is distinct.

### 1.2 Method Signature
```java
package Arrays;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums);
}
```

### 1.3 Pre-Conditions
- Array `nums` can be null or empty.
- Length bounds: $0 \le \text{nums.length} \le 10^5$.
- Element values: $-10^9 \le \text{nums}[i] \le 10^9$.

### 1.4 Post-Conditions
- Returns boolean `true` if duplicate exists, `false` otherwise.
- Pure function (does not modify array).

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Set Invariant**: Before inspecting element $\text{nums}[i]$, the hash set `seen` contains all distinct elements in $\text{nums}[0 \dots i-1]$.
- If `!seen.add(nums[i])`, then $\text{nums}[i] \in \text{nums}[0 \dots i-1]$, proving the existence of a duplicate in $O(1)$ amortized lookup.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $0 \le n \le 10^5$ | $O(N)$ required. $O(N^2)$ brute-force double loop will TLE. |
| Auxiliary Space | $O(N)$ | Set holds up to $N$ unique integers. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `nums` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Simple duplicate | `[1, 2, 3, 1]` | `true` |
| **TC-02** | All distinct | `[1, 2, 3, 4]` | `false` |
| **TC-03** | Multiple duplicates | `[1, 1, 1, 3, 3, 4, 3, 2, 4, 2]` | `true` |
| **TC-04** | Empty or single element | `[]` or `[1]` | `false` |
| **TC-05** | Negative elements duplicate | `[-1, -2, -3, -1]` | `true` |

---

## 5. Design & Algorithmic Strategy

- **Approach 1 (Sorting)**: Sort `nums` in $O(N \log N)$ and check adjacent elements. Space $O(1)$ or $O(N)$ depending on sorting algorithm.
- **Approach 2 (HashSet - Optimal)**: Iterate and add to `seen`. Early return on collision. Time $O(N)$, Space $O(N)$.
