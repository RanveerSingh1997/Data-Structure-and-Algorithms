# SPEC-050: Subarray Sum (Target Indices / Maximum Subarray with Sum K)

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Hashing / Prefix Sum / Sliding Window`  
> **Source**: [LeetCode #560 - Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/) · [GFG - Subarray with Given Sum](https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/)  
> **Video Explanation**: [RisingBrain - Maximum Subarray with Sum K | Brute Force to Optimised (Sliding Window & Prefix Sum)](https://youtu.be/dgjKO46bu3A)  
> **Documentation / Read Link**: [TakeUForward - Longest Subarray with Sum K](https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/) · [NeetCode.io - Subarray Sum Equals K](https://neetcode.io/problems/subarray-sum-equals-k)  
> **Target Complexity**: Time `O(N)` | Space `O(N)` (or `O(1)` space via sliding window when all elements are non-negative)  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Find the starting and ending indices `[start, end]` of a contiguous subarray whose elements sum up to `target`. By maintaining a running `currentSum` and recording the earliest occurrence of each prefix sum in a hash map `(prefixSum -> index)`, if `(currentSum - target)` has been seen before at index `j`, the slice from `j + 1` to `i` is guaranteed to sum exactly to `target`. For strictly non-negative arrays, an optimal sliding window two-pointer strategy achieves this in $O(1)$ space.

### 1.2 Full Description
Given an integer array `array` and an integer `target`, return an array containing the `[start, end]` indices (0-indexed, inclusive) of any continuous subarray whose elements add up to `target`. If no such subarray exists, return an empty array `[]`.

### 1.3 Method Signature
```java
package Hashing;

public class SubSumArray {
    public static int[] subarraySum(int[] array, int target) {
        // Implementation adheres to this specification
    }
}
```

### 1.4 Pre-Conditions
- `array` is non-null.
- Length $0 \le N \le 10^5$.
- Elements and `target` may be positive, zero, or negative: $-10^9 \le \text{array}[i], \text{target} \le 10^9$.

### 1.5 Post-Conditions
- Returns `[start, end]` where $0 \le \text{start} \le \text{end} < N$ and $\sum_{k=\text{start}}^{\text{end}} \text{array}[k] = \text{target}$.
- If multiple such subarrays exist, returning any valid subarray is valid.
- If no valid subarray exists, returns `[]`.
- Input array `array` is not modified.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Prefix Sum Difference Property**: Let $P[i] = \sum_{k=0}^{i} \text{array}[k]$ be the cumulative sum up to index $i$ (with base $P[-1] = 0$).
- The sum of elements between indices $j+1$ and $i$ is:
  $$\sum_{k=j+1}^{i} \text{array}[k] = P[i] - P[j]$$
- Therefore, a subarray ending at index $i$ sums to `target` if and only if:
  $$P[j] = P[i] - \text{target} \quad \text{for some } -1 \le j < i$$
- **Hash Table Invariant**: At each step $i$, `hashMap` stores $(P[j] \to j)$ for all previous indices $j < i$. If $P[i] - \text{target}$ exists in the map, a valid subarray starts at $j + 1$ and ends at $i$.
- **Sliding Window Invariant (Positive Elements Only)**: When all elements $> 0$, the sum function is strictly monotonic with respect to window expansion/contraction. Right pointer expands to increase sum; left pointer contracts when sum exceeds target.

### 2.2 Proof of Correctness Sketch
- **Completeness**: If any subarray $[j+1, i]$ sums to `target`, then $P[i] - P[j] = \text{target} \implies P[j] = P[i] - \text{target}$. Since $j < i$, index $j$ was processed in a prior iteration and registered in `hashMap`. When the loop reaches $i$, `key = currentSum - target` will find $j$.
- **Termination Guarantee**: Single linear pass from $i = 0$ to $N-1$, performing $O(1)$ amortized hash map lookups. Loop strictly terminates after $N$ iterations.

---

## 3. Constraints & Complexity Budget

### 3.1 Constraint Boundaries
| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `N` (Input size) | $0 \le N \le 10^5$ | $O(N)$ linear pass required; $O(N^2)$ brute force will TLE |
| `target`, `array[i]` | $[-10^9, 10^9]$ | Cumulative sum may exceed 32-bit int if large; use `long` if summing large values |
| Auxiliary Space | Up to $N$ entries | Hash table holds up to $N+1$ prefix entries in worst case |

### 3.2 Complexity Budget
- **Time Complexity Target**: $O(N)$
- **Auxiliary Space Target**: $O(N)$ (general with negative numbers); $O(1)$ space for non-negative sliding window variant
- **Brute Force Bottleneck**: Evaluating every pair $(i, j)$ requires $O(N^2)$ checks.

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output | Rationale / Invariant Verified |
| :--- | :--- | :--- | :---: | :--- |
| **TC-01** | Basic Subarray | `nums=[1, 2, 3, 4, 5], target=9` | `[1, 3]` | Standard positive array match ($2+3+4=9$) |
| **TC-02** | Multiple Possible Subarrays | `nums=[1, 2, 2, 3, 1], target=5` | `[0, 2]` | Matches earliest valid slice ($1+2+2=5$) |
| **TC-03** | Handles Negative Numbers | `nums=[3, 4, -7, 5, 1], target=5` | `[3, 4]` | Verifies non-monotonic prefix sum recovery |
| **TC-04** | Entire Array Matches | `nums=[2, 2, 2, 2], target=8` | `[0, 3]` | Full range from index 0 to $N-1$ |
| **TC-05** | No Subarray Found | `nums=[1, 1, 1], target=10` | `[]` | Clean exit when target cannot be formed |
| **TC-06** | Single Element Target Match | `nums=[5], target=5` | `[0, 0]` | Boundary single-item window |
| **TC-07** | Target 0 with zero element | `nums=[1, 0, 2], target=0` | `[1, 1]` | Subarray with single zero element |

---

## 5. Design & Algorithmic Strategy

### 5.1 Approach Comparison
| Approach | Time | Space | Constraints / Applicability |
| :--- | :---: | :---: | :--- |
| **1. Brute Force Double Loop** | $O(N^2)$ | $O(1)$ | Feasible only for $N \le 1000$ |
| **2. Prefix Sum + Hash Map (Optimal General)** | $O(N)$ | $O(N)$ | Works for all numbers (positive, negative, zeros) |
| **3. Sliding Window (Two Pointers)** | $O(N)$ | $O(1)$ | Strictly requires non-negative elements (monotonic sum) |

### 5.2 Failure Modes & Pitfalls
- **Missing Base Prefix `(0, -1)`**: Forgetting to initialize `hashMap.put(0, -1)` causes failures when the valid subarray starts at index 0 ($P[i] = \text{target}$).
- **Overwriting First Occurrence**: Using `map.put(currentSum, i)` instead of `putIfAbsent` shortens the distance when looking for maximum length subarrays.
- **Assuming Positivity**: Sliding window breaks if negative numbers exist because contracting the window does not monotonically decrease the sum.

---

## 6. Verification Trace Table

*Trace for `nums = [1, 2, 3, 4, 5], target = 9`*:

| Step | Index $i$ | `nums[i]` | `currentSum` | `key = currentSum - 9` | Map State Before | Action | Result |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| Init | - | - | 0 | - | `{0: -1}` | Initialize base | Continue |
| 1 | 0 | 1 | 1 | -8 | `{0: -1}` | -8 not in map | `map.put(1, 0)` |
| 2 | 1 | 2 | 3 | -6 | `{0: -1, 1: 0}` | -6 not in map | `map.put(3, 1)` |
| 3 | 2 | 3 | 6 | -3 | `{0: -1, 1: 0, 3: 1}` | -3 not in map | `map.put(6, 2)` |
| 4 | 3 | 4 | 10 | 1 | `{0: -1, 1: 0, 3: 1, 6: 2}` | **1 found at index 0** | Return `[0 + 1, 3]` = **`[1, 3]`** |
