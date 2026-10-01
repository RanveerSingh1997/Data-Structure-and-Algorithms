# SPEC-042: Trapping Rain Water

> **Status**: `VERIFIED`  
> **Difficulty**: `🔴 Hard`  
> **Category**: `Arrays / Two Pointers / Monotonic Stack`  
> **Source**: [LeetCode #42 - Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given $n$ non-negative integers representing an elevation map where the width of each bar is $1$, compute how much water it can trap after raining.

A bar at index $i$ can trap water if and only if there exists a taller bar to its left and a taller bar to its right. The volume of water trapped above bar $i$ is determined by the minimum of the maximum height to its left and the maximum height to its right, minus the height of bar $i$ itself:
$$\text{water}[i] = \max\Big(0, \; \min(\text{leftMax}[i], \, \text{rightMax}[i]) - \text{height}[i]\Big)$$

### 1.2 Method Signature
```java
package Arrays;

public class TrappingRainWater {
    /**
     * Computes total trapped rain water using optimal O(1) space two-pointer approach.
     *
     * @param heights elevation map array
     * @return total units of trapped rain water
     */
    public static int trapOpti(int[] heights);

    /**
     * Baseline prefix/suffix or boundary-scan approach for comparison and verification.
     *
     * @param heights elevation map array
     * @return total units of trapped rain water
     */
    public static int trap(int[] heights);
}
```

### 1.3 Pre-Conditions
- `heights` must not be null. If `heights == null` or `heights.length < 3`, 0 water can be trapped.
- Array length: $0 \le n \le 2 \times 10^4$.
- Elevation values: $0 \le \text{heights}[i] \le 10^5$.

### 1.4 Post-Conditions
- Returns non-negative integer representing total trapped water.
- The input array `heights` must remain unmodified (pure function).
- Execution must terminate in $O(N)$ operations with $O(1)$ auxiliary memory.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
For any index $i$, the water level is bounded by $\min(\max_{0 \le j \le i} \text{heights}[j], \, \max_{i \le k < n} \text{heights}[k])$.

**Two-Pointer Invariant**:
Maintain two pointers `left = 0` and `right = n - 1`, along with running variables `leftMax = heights[left]` and `rightMax = heights[right]`.
- If `leftMax < rightMax`:
  We know with 100% certainty that the global maximum to the right of `left` is **at least** `rightMax` (which is strictly greater than `leftMax`). Therefore, the bottleneck for bar `left` is unconditionally `leftMax`, regardless of any unvisited heights in between.
  $$\therefore \text{water}[left] = leftMax - \text{heights}[left]$$
  We can safely resolve index `left`, accumulate its water, and advance `left++`.
- If `leftMax >= rightMax`:
  Symmetrically, the bottleneck for bar `right` is unconditionally `rightMax`.
  $$\therefore \text{water}[right] = rightMax - \text{heights}[right]$$
  We safely resolve index `right`, accumulate its water, and decrement `right--`.

### 2.2 Proof of Correctness Sketch
- **Completeness**: At each step, either `left` advances or `right` decrements. Since `left` starts at 0 and `right` starts at $n-1$, the pointers strictly approach each other and terminate when `left >= right` after exactly $n - 1$ steps.
- **Soundness**: Every bar from $0$ to $n-1$ is evaluated under its mathematically guaranteed bottleneck height. No bar is double-counted or omitted.

---

## 3. Constraints & Complexity Budget

### 3.1 Constraint Boundaries
| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` (array size) | $0 \le n \le 2 \times 10^4$ | $O(N)$ required. $O(N^2)$ (brute force scanning left/right per bar) takes up to $4 \times 10^8$ ops and will TLE. |
| `heights[i]` | $0 \le \text{heights}[i] \le 10^5$ | Max possible total water $\approx 2 \times 10^4 \times 10^5 = 2 \times 10^9$, fits within standard 32-bit signed Java `int` ($\approx 2.14 \times 10^9$). |
| Memory Limits | Standard JVM heap | Auxiliary Space budget: $O(1)$ optimal; $O(N)$ acceptable for DP prefix/suffix arrays. |

### 3.2 Complexity Budget
- **Target Time**: $O(N)$ single-pass two pointers.
- **Target Space**: $O(1)$ auxiliary variables (`left`, `right`, `leftMax`, `rightMax`, `water`).

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `heights` | Expected Output | Rationale / Invariant Verified |
| :--- | :--- | :--- | :---: | :--- |
| **TC-01** | LeetCode Classic Example 1 | `[0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]` | `6` | Multi-valley, varying plateau heights. |
| **TC-02** | User Custom Example | `[0, 2, 0, 3, 1, 0, 1, 3, 2, 1]` | `9` | Deep internal valleys flanked by height 3. |
| **TC-03** | LeetCode Classic Example 2 | `[4, 2, 0, 3, 2, 5]` | `9` | Asymmetric bowl with internal dips. |
| **TC-04** | Empty / Null Input | `null` or `[]` | `0` | Boundary safety: no NPE or IndexOutOfBounds. |
| **TC-05** | Insufficient Bars (< 3) | `[2]`, `[2, 3]` | `0` | Geometric requirement: water needs at least 2 walls and 1 floor. |
| **TC-06** | Monotonically Increasing | `[1, 2, 3, 4, 5]` | `0` | No right wall exists for any bar. |
| **TC-07** | Monotonically Decreasing | `[5, 4, 3, 2, 1]` | `0` | No left wall can hold water against lower rights. |
| **TC-08** | All Identical / Flat | `[3, 3, 3, 3]` | `0` | Flat surface cannot trap water. |
| **TC-09** | Single Valley / Bowl | `[3, 0, 3]` | `3` | Elementary trap: $(3 - 0) \times 1 = 3$. |
| **TC-10** | High Edges, Flat Floor | `[5, 1, 1, 1, 5]` | `12` | Uniform valley between distant boundaries: $3 \times (5 - 1) = 12$. |

---

## 5. Design & Algorithmic Strategy

### 5.1 Comparison of Approaches
| Approach | Time | Space | Strategy |
| :--- | :---: | :---: | :--- |
| **1. Brute Force** | $O(N^2)$ | $O(1)$ | For each index $i$, scan left for $\max$ and right for $\max$. Redundant scanning. |
| **2. Dynamic Programming (Prefix/Suffix)** | $O(N)$ | $O(N)$ | Precompute `leftMax[n]` and `rightMax[n]` arrays. Clean, but allocates $2N$ integers. |
| **3. Monotonic Decreasing Stack** | $O(N)$ | $O(N)$ | Computes water horizontally bounded by stack top. Good for stream processing. |
| **4. Two Pointers (Optimal)** | $O(N)$ | $O(1)$ | Move pointer with smaller max inwards. Zero heap allocation, single pass. |

### 5.2 Common Pitfalls
1. **Unchecked array size**: Calling `heights[left]` when array length is 0 or 1 throws `ArrayIndexOutOfBoundsException`.
2. **Boundary indexing**: Using `n = heights.length - 1` and loop bound `i < n` can accidentally skip the final index in boundary checks.
3. **Integer overflow on large inputs**: Although standard LeetCode fits in `int`, if $N \cdot \max(H) > 2 \times 10^9$, accumulator would need `long`.

---

## 6. Verification Trace Table

*Trace for Input: `heights = [3, 0, 2, 0, 4]` ($N = 5$)*:

| Step | Pointer State | Current Bar Heights | `leftMax`, `rightMax` | Condition | Water Added | Running Total | Next Action |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| 0 | `L=0, R=4` | `h[0]=3, h[4]=4` | `lMax=3, rMax=4` | `lMax < rMax` | - | 0 | `L++ -> L=1` |
| 1 | `L=1, R=4` | `h[1]=0, h[4]=4` | `lMax=3, rMax=4` | `lMax < rMax` | $3 - 0 = 3$ | 3 | `L++ -> L=2` |
| 2 | `L=2, R=4` | `h[2]=2, h[4]=4` | `lMax=3, rMax=4` | `lMax < rMax` | $3 - 2 = 1$ | 4 | `L++ -> L=3` |
| 3 | `L=3, R=4` | `h[3]=0, h[4]=4` | `lMax=3, rMax=4` | `lMax < rMax` | $3 - 0 = 3$ | 7 | `L++ -> L=4` |
| 4 | `L=4, R=4` | Loop ends (`L == R`) | - | - | 0 | **7** | Return 7 |
