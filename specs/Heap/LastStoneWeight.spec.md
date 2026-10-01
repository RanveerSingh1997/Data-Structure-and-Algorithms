# SPEC-1046: Last Stone Weight

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Heap / Priority Queue`  
> **Source**: [LeetCode #1046 - Last Stone Weight](https://leetcode.com/problems/last-stone-weight/)  
> **Target Complexity**: Time `O(N \log N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
You are given an array of integers `stones` where `stones[i]` is the weight of the $i$-th stone.
We play a game with the stones: on each turn, we choose the heaviest two stones with weights $x$ and $y$ with $x \le y$. The result of this smash is:
- If $x == y$, both stones are destroyed.
- If $x \ne y$, the stone of weight $x$ is destroyed, and the stone of weight $y$ has new weight $y - x$.
At the end of the game, there is at most one stone left. Return the weight of the last remaining stone, or `0` if there are no stones left.

### 1.2 Method Signature
```java
package Heap;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones);
}
```

### 1.3 Pre-Conditions
- $1 \le \text{stones.length} \le 30$.
- $1 \le \text{stones}[i] \le 1000$.

### 1.4 Post-Conditions
- Returns weight of last surviving stone, or `0`.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Max-Heap Invariant**: Always extract the two greatest elements from a max-heap in $O(\log N)$ time each.
- The smash operation strictly decreases the number of stones by either 1 (if $x \ne y$) or 2 (if $x == y$).
- Guaranteed to terminate in at most $N - 1$ smashes.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $1 \le n \le 30$ | Highly constrained; $O(N \log N)$ takes $< 200$ operations. |
| Auxiliary Space | $O(N)$ | Max-Heap holding $N$ elements. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `stones` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Multi-element standard | `[2, 7, 4, 1, 8, 1]` | `1` |
| **TC-02** | Single stone | `[1]` | `1` |
| **TC-03** | Equal pair destroys both | `[2, 2]` | `0` |
| **TC-04** | Unequal pair | `[1, 2]` | `1` |

---

## 5. Design & Algorithmic Strategy

- Insert all stones into `PriorityQueue<>(Collections.reverseOrder())`.
- Poll two max elements while size > 1, re-insert difference if non-zero.
