# SPEC-876: Middle of the Linked List

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `LinkedList / Fast & Slow Pointers`  
> **Source**: [LeetCode #876 - Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/)  
> **Video Explanation**: [NeetCode - Middle of the Linked List](https://youtu.be/A2_ldqM4QcY)  
> **Documentation / Read Link**: [NeetCode.io - Middle of the Linked List](https://neetcode.io/problems/middle-of-the-linked-list) · [TakeUForward - Find Middle of Linked List](https://takeuforward.org/data-structure/find-the-middle-of-a-linked-list/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Use two pointers (`slow` and `fast`) initialized at `head`. Advance `slow` by 1 node and `fast` by 2 nodes each iteration. When `fast == null` (for even-length lists) or `fast.next == null` (for odd-length lists), `slow` will be located directly at the middle node (or second middle node for even lengths).

### 1.2 Description
Given the head of a singly linked list, return the middle node of the linked list. If there are two middle nodes, return the second middle node.

### 1.2 Method Signature
```java
package LinkedList;

import Utils.LinkedList;
import Utils.Node;

public class FindMiddleNode {
    public static Node findMiddleNode(Node head);
    public static Node findMiddleNode(LinkedList list);
}
```

### 1.3 Pre-Conditions
- Number of nodes: $0 \le N \le 100$.

### 1.4 Post-Conditions
- Returns node at index $\lfloor N / 2 \rfloor$ (0-indexed).

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **2:1 Velocity Invariant**:
  - `slow` moves 1 step per cycle.
  - `fast` moves 2 steps per cycle.
  - Distance traveled by `fast` is exactly $2 \times \text{distance}(\text{slow})$.
- When `fast == null` (even length) or `fast.next == null` (odd length), `slow` is exactly at the middle node $\lfloor N / 2 \rfloor$.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $0 \le n \le 100$ | Single pass with $N/2$ iterations. |
| Auxiliary Space | $O(1)$ | Two pointers. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output Node Value |
| :--- | :--- | :--- | :---: |
| **TC-01** | Odd length | `1 -> 2 -> 3 -> 4 -> 5` | `3` |
| **TC-02** | Even length | `1 -> 2 -> 3 -> 4 -> 5 -> 6` | `4` |
| **TC-03** | Single node | `1` | `1` |
| **TC-04** | Null list | `null` | `null` |

---

## 5. Design & Algorithmic Strategy

- Advance `slow = slow.next` and `fast = fast.next.next` until `fast` reaches end.
