# SPEC-206: Reverse Linked List

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `LinkedList / Pointers`  
> **Source**: [LeetCode #206 - Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given the head of a singly linked list, reverse the list, and return the reversed list.

### 1.2 Method Signature
```java
package LinkedList;

import Utils.Node;

public class Reverse {
    public Node reverseList(Node head);
    public Node reverseIterative(Node head);
    public Node reverseRecursive(Node head);
}
```

### 1.3 Pre-Conditions
- `head` can be null or refer to a chain of $1 \le N \le 5000$ nodes.

### 1.4 Post-Conditions
- Returns the new head of the reversed linked list.
- Reversal must be done in-place with $O(1)$ extra space for the iterative approach.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Three-Pointer Invariant**: Maintain `prev`, `curr`, and `next`.
- At each step, all nodes prior to `curr` have their `.next` pointers reversed pointing to their predecessors.
- Invert link: `curr.next = prev`, then shift window: `prev = curr`, `curr = next`.
- Terminates when `curr == null`, with `prev` as the new head.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $0 \le n \le 5000$ | $O(N)$ single pass. |
| Auxiliary Space | $O(1)$ | Constant pointers (`prev`, `curr`, `next`). |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Multi-node list | `1 -> 2 -> 3 -> 4 -> 5` | `5 -> 4 -> 3 -> 2 -> 1` |
| **TC-02** | Single node | `[1]` | `[1]` |
| **TC-03** | Empty list | `null` | `null` |
| **TC-04** | Two nodes | `1 -> 2` | `2 -> 1` |

---

## 5. Design & Algorithmic Strategy

- Iterative 3-pointer reversal modifies links in-place in $O(N)$ time and $O(1)$ space.
