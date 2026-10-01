# SPEC-011: Container With Most Water

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Arrays / Two Pointers / Greedy`  
> **Source**: [LeetCode #11 - Container With Most Water](https://leetcode.com/problems/container-with-most-water/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
You are given an integer array `height` of length $n$. There are $n$ vertical lines drawn such that the two endpoints of the $i$-th line are $(i, 0)$ and $(i, \text{height}[i])$.

Find two lines that together with the x-axis form a container, such that the container contains the most water.

$$\text{Area}(L, R) = (R - L) \times \min(\text{height}[L], \, \text{height}[R])$$

### 1.2 Method Signature
```java
package Arrays;

public class ContainerWithMostWater {
    /**
     * Finds the maximum area of water a container can hold using optimal two pointers.
     *
     * @param height array of line heights
     * @return maximum water volume
     */
    public int maxArea(int[] height);
}
```

### 1.3 Pre-Conditions
- `height != null` and `2 <= height.length <= 10^5`.
- Elements bounded by $0 \le \text{height}[i] \le 10^4$.

### 1.4 Post-Conditions
- Returns non-negative integer representing maximum area.
- Operates in $O(N)$ time with $O(1)$ auxiliary space.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
Start with maximum possible width: $L = 0, R = n - 1$.
The capacity is limited by the shorter line: $\min(\text{height}[L], \text{height}[R])$.

**Greedy Invariant**:
If $\text{height}[L] < \text{height}[R]$, the line at $L$ can never form a larger area with any other right boundary $R' < R$, because:
1. The width $(R' - L)$ is strictly smaller than $(R - L)$.
2. The height is bounded by $\min(\text{height}[L], \text{height}[R']) \le \text{height}[L]$.
Therefore, $\text{Area}(L, R') < \text{Area}(L, R)$ for all $L < R' < R$.
Hence, $L$ is eliminated without missing any potential global maximum.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $2 \le n \le 10^5$ | $O(N)$ required. $O(N^2)$ checks $5 \times 10^9$ pairs and fails. |
| `height[i]` | $0 \le \text{height}[i] \le 10^4$ | Max area $10^5 \times 10^4 = 10^9$, comfortably within 32-bit `int`. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `height` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Classic LeetCode 1 | `[1, 8, 6, 2, 5, 4, 8, 3, 7]` | `49` |
| **TC-02** | Minimal Pair | `[1, 1]` | `1` |
| **TC-03** | Mountain Shaped | `[4, 3, 2, 1, 4]` | `16` |
| **TC-04** | Monotonic Ascending | `[1, 2, 4, 3]` | `4` |
| **TC-05** | Monotonic Descending | `[10, 9, 8, 7, 6, 5, 4, 3, 2, 1]` | `25` |

---

## 5. Design & Algorithmic Strategy

- **Two Pointers**: Initialize $L = 0, R = n - 1$. While $L < R$: calculate area, update max, and advance the pointer pointing to the shorter vertical bar.
- **Time Complexity**: $O(N)$
- **Space Complexity**: $O(1)$
