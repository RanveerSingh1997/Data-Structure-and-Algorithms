# SPEC-141: Linked List Cycle

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `LinkedList / Floyd's Cycle Detection`  
> **Source**: [LeetCode #141 - Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given `head`, the head of a linked list, determine if the linked list has a cycle in it.
Return `true` if there is some cycle in the linked list; otherwise return `false`.

### 1.2 Method Signature
```java
package LinkedList;

import Utils.LinkedList;
import Utils.Node;

public class FindLoop {
    public boolean hasLoop(Node head);
    public boolean hasLoop(LinkedList list);
}
```

### 1.3 Pre-Conditions
- Number of nodes: $0 \le N \le 10^4$.

### 1.4 Post-Conditions
- Returns boolean `true` if cycle exists, `false` otherwise.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Floyd's Tortoise and Hare Invariant**:
  - `slow` advances by 1, `fast` advances by 2.
  - If no cycle exists, `fast` reaches `null` in $N/2$ steps.
  - If a cycle of length $C$ exists, each step decreases the distance between `fast` and `slow` by 1 modulo $C$.
  - Therefore, `fast` and `slow` must collide within $C$ steps inside the loop.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $0 \le n \le 10^4$ | $O(N)$ operations. |
| Auxiliary Space | $O(1)$ | No HashSet needed; constant pointer variables. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Acyclic list | `1 -> 2 -> 3 -> null` | `false` |
| **TC-02** | Cyclic list | `1 -> 2 -> 3 -> 1` | `true` |
| **TC-03** | Single node self-loop | `1 -> 1` | `true` |
| **TC-04** | Single node no cycle | `1 -> null` | `false` |
| **TC-05** | Empty list | `null` | `false` |

---

## 5. Design & Algorithmic Strategy

- Two pointers `slow` and `fast` initialized to `head`.
- Advance while `fast != null && fast.next != null`. Check if `slow == fast`.
