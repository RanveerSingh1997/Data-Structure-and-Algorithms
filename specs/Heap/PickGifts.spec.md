# SPEC-2558: Take Gifts From the Richest Pile

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Heap / Max-Heap`  
> **Source**: [LeetCode #2558 - Take Gifts From the Richest Pile](https://leetcode.com/problems/take-gifts-from-the-richest-pile/)  
> **Target Complexity**: Time `O(N + K \log N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
You are given an integer array `gifts` denoting the number of gifts in various piles. Every second, you:
1. Choose the pile with the maximum number of gifts.
2. If there is more than one pile with the maximum gifts, choose any.
3. Leave behind the floor of the square root of the number of gifts in the pile.
4. Take the rest of the gifts.
Return the number of gifts remaining after $k$ seconds.

### 1.2 Method Signature
```java
package Heap;

public class PickGifts {
    public int pickGifts(int[] gifts, int k);
}
```

### 1.3 Pre-Conditions
- $1 \le \text{gifts.length} \le 10^3$.
- $1 \le \text{gifts}[i] \le 10^9$.
- $1 \le k \le 10^3$.

### 1.4 Post-Conditions
- Returns total remaining sum after $k$ extractions and square root replacements.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Max-Heap Invariant**: The root of the max-heap is always the largest remaining pile.
- In each of the $k$ rounds: `max = heap.poll()`, `heap.offer((int) Math.sqrt(max))`.
- After $k$ iterations, sum all elements remaining in the heap.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `N`, `K` | $1 \le N, K \le 10^3$ | Heapify $O(N)$, $K$ updates take $O(K \log N)$. |
| Auxiliary Space | $O(N)$ | Priority queue storage. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `gifts` | $k$ | Expected Output |
| :--- | :--- | :--- | :---: | :---: |
| **TC-01** | Multi-round example | `[25, 64, 9, 4, 100]` | `4` | `29` |
| **TC-02** | Single pile | `[1, 1, 1, 1]` | `4` | `4` |

---

## 5. Design & Algorithmic Strategy

- Use a max-heap `PriorityQueue<>(Collections.reverseOrder())`.
- Poll max, push `(int) Math.sqrt(max)`, repeat $k$ times.
