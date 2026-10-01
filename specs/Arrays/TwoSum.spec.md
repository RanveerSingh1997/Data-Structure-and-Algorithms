# SPEC-001: Two Sum

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Arrays / HashMap / Two Pointers`  
> **Source**: [LeetCode #1 - Two Sum](https://leetcode.com/problems/two-sum/)  
> **Target Complexity**: Time `O(N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.
You may assume that each input would have exactly one solution, and you may not use the same element twice. You can return the answer in any order.

### 1.2 Method Signature
```java
package Arrays;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target);
    public static int[] twoSumUsingTwoPointer(int[] numbers, int target); // For sorted arrays
}
```

### 1.3 Pre-Conditions
- `nums` is non-null with $2 \le \text{nums.length} \le 10^4$.
- Each element $-10^9 \le \text{nums}[i] \le 10^9$.
- Target: $-10^9 \le \text{target} \le 10^9$.
- Exactly one valid answer exists unless specified as an edge check.

### 1.4 Post-Conditions
- Returns array of two distinct indices `[i, j]` where $i \ne j$ and $\text{nums}[i] + \text{nums}[j] = \text{target}$.
- Input array `nums` is not modified.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Single-Pass Hash Map Invariant**: At step $i$, the map contains all prefix pairs $(val, index)$ for indices $0 \le j < i$.
- For current element $\text{nums}[i]$, the required complement is $C = \text{target} - \text{nums}[i]$.
- If $C \in \text{map}$, then $(map[C], i)$ is the unique pair. If $C \notin \text{map}$, $\text{nums}[i]$ cannot pair with any element in $0 \dots i-1$, so we register $(\text{nums}[i], i)$ for future elements.

### 2.2 Proof of Correctness Sketch
- If pair $(a, b)$ sums to `target` with indices $j < i$, then when loop reaches $i$, index $j$ was already visited and stored in the hash map. Thus, lookup at $i$ is guaranteed to find $j$.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $2 \le n \le 10^4$ | $O(N)$ expected. $O(N^2)$ brute force takes up to $10^8$ operations. |
| Value range | $[-10^9, 10^9]$ | Difference target - nums[i] fits in standard 64-bit/32-bit integer. |
| Auxiliary Space | $O(N)$ | Hash table stores up to $N$ entries. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Classic standard pair | `nums=[2, 7, 11, 15], target=9` | `[0, 1]` |
| **TC-02** | Complement later in array | `nums=[3, 2, 4], target=6` | `[1, 2]` |
| **TC-03** | Identical numbers | `nums=[3, 3], target=6` | `[0, 1]` |
| **TC-04** | Negative integers | `nums=[-1, -2, -3, -4, -5], target=-8` | `[2, 4]` |
| **TC-05** | No solution case | `nums=[1, 2, 3], target=7` | `[]` |

---

## 5. Design & Algorithmic Strategy

- **Approach 1 (Brute Force)**: Double loop checking all pairs. Time $O(N^2)$, Space $O(1)$.
- **Approach 2 (Hash Map)**: Single pass tracking complements. Time $O(N)$, Space $O(N)$.
- **Approach 3 (Two Pointers)**: Applicable when array is sorted. $L=0, R=n-1$. Time $O(N)$, Space $O(1)$.

---

## 6. Verification Trace Table

*Trace for `nums = [2, 7, 11, 15], target = 9`*:

| Step | Current $i$ | `nums[i]` | Complement `9 - nums[i]` | Map State Before | Action / Result |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 0 | 0 | 2 | 7 | `{}` | `7` not in map -> `map.put(2, 0)` |
| 1 | 1 | 7 | 2 | `{2: 0}` | `2` found in map -> Return `[0, 1]` |
