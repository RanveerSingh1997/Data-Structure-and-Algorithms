# SPEC-347: Top K Frequent Elements

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Arrays / Heap / Hash Table`  
> **Source**: [LeetCode #347 - Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)  
> **Target Complexity**: Time `O(N log K)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. You may return the answer in any order.

### 1.2 Method Signature
```java
package Arrays;

public class TopKFrequentElements {
    public int[] topFrequent(int[] nums, int k);
}
```

### 1.3 Pre-Conditions
- $1 \le \text{nums.length} \le 10^5$.
- $k$ is in the range $[1, \text{number of unique elements}]$.
- The answer is guaranteed to be unique.

### 1.4 Post-Conditions
- Returns array of length $k$ containing the $k$ elements with the highest frequencies.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Min-Heap of Size K**: Maintain a min-heap sorted by frequency.
- When iterating through distinct elements, if `heap.size() > k`, evict the minimum frequency element (`heap.poll()`).
- At termination, the heap contains strictly the $k$ largest frequency elements.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $1 \le n \le 10^5$ | $O(N \log K)$ or $O(N)$ (bucket sort). |
| `k` | $1 \le k \le \text{unique count}$ | Auxiliary priority queue bound is $O(K)$. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Multi-element standard | `nums=[1,1,1,2,2,3], k=2` | `[1, 2]` |
| **TC-02** | Single element | `nums=[1], k=1` | `[1]` |
| **TC-03** | Negative numbers | `nums=[-1, -1, 2], k=1` | `[-1]` |
| **TC-04** | All unique, k = n | `nums=[1, 2, 3], k=3` | `[1, 2, 3]` |

---

## 5. Design & Algorithmic Strategy

- **Min-Heap (Implemented)**: Count frequencies into HashMap, maintain min-heap of size $k$. Time $O(N \log K)$, Space $O(N)$.
- **Bucket Sort (Alternative)**: Index array by frequency $[0 \dots N]$. Time $O(N)$, Space $O(N)$.
