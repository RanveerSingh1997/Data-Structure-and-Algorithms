# SPEC-SS: Sort Stack

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Stacks_Queues / Monotonic Stack`  
> **Source**: Classical Stack Interview Problem (Cracking the Coding Interview)  
> **Video Explanation**: [TakeUForward - Sort a Stack using Recursion](https://youtu.be/MOGBRkkOhkY)  
> **Documentation / Read Link**: [GFG - Sort a stack using recursion](https://www.geeksforgeeks.org/sort-a-stack-using-recursion/)  
> **Target Complexity**: Time `O(N^2)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Use recursion or an auxiliary stack to insert elements into sorted order. Pop the top element and recursively sort the remainder of the stack. On backtracking, insert the held element into its correct sorted position in the stack (popping any elements larger/smaller than it to the recursion call stack, placing the element, and restoring popped items).

### 1.2 Description
Given a stack of integers, sort it in-place such that the smallest items are on top. You may use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array).

### 1.2 Method Signature
```java
package Stacks_Queues;

import Utils.StackTemplate;

public class SortStack {
    public static void sortStack(StackTemplate<Integer> stack);
}
```

### 1.3 Pre-Conditions
- `stack` is non-null. Can be empty or contain any number of positive/negative integers.

### 1.4 Post-Conditions
- `stack` is sorted such that the minimum element is at the top.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- Maintain `tempStack` such that its elements are in sorted order (largest at the bottom, smallest at top or vice-versa).
- When popping `current` from `stack`:
  - Transfer all elements from `tempStack` back to `stack` that are greater than `current`.
  - Push `current` onto `tempStack`.
- Invariant: `tempStack` remains monotonically ordered throughout.
- Finally, transfer `tempStack` back to `stack`.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $0 \le n \le 10^3$ | $O(N^2)$ worst case comparisons. |
| Auxiliary Space | $O(N)$ | Exactly one auxiliary stack of size up to $N$. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input (Top -> Bottom) | Expected Output (Top -> Bottom) |
| :--- | :--- | :--- | :---: |
| **TC-01** | Unsorted elements | `[3, 1, 4, 2]` | `[1, 2, 3, 4]` |
| **TC-02** | Already sorted | `[1, 2, 3, 4]` | `[1, 2, 3, 4]` |
| **TC-03** | Reverse sorted | `[4, 3, 2, 1]` | `[1, 2, 3, 4]` |
| **TC-04** | With duplicates | `[3, 1, 3, 2, 1]` | `[1, 1, 2, 3, 3]` |
| **TC-05** | Negative values | `[-1, 3, -5, 2]` | `[-5, -1, 2, 3]` |
| **TC-06** | Empty / Single | `[]` / `[5]` | `[]` / `[5]` |

---

## 5. Design & Algorithmic Strategy

- Use a secondary stack `tempStack`.
- Insert sort each popped element into its proper position in `tempStack`.
