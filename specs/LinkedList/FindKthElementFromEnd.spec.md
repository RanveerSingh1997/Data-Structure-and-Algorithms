# SPEC-LKTH: Find Kth Element From End

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `LinkedList / Two Pointers`  
> **Source**: Classical Linked List Interview Problem  
> **Video Explanation**: [TakeUForward - Remove/Find Nth Node from End of Linked List](https://youtu.be/XVuQxVej6y8)  
> **Documentation / Read Link**: [TakeUForward - Nth Node from End](https://takeuforward.org/data-structure/remove-nth-node-from-the-end-of-a-linked-list/) · [GFG - Nth node from end of linked list](https://www.geeksforgeeks.org/nth-node-from-the-end-of-a-linked-list/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Use two pointers (`leader` and `follower`) separated by $k$ nodes. Advance `leader` forward $k$ steps. If `leader` hits null before $k$ steps, $k$ exceeds list length. Then move both pointers forward one step at a time. When `leader` reaches null, `follower` is situated exactly at the $k$-th node from the end.

### 1.2 Description
Find and return the $k$-th node from the end of a singly linked list (1-indexed: $k=1$ is the tail node). Return `null` if $k$ exceeds the length of the list or $k \le 0$.

### 1.2 Method Signature
```java
package LinkedList;

import Utils.LinkedList;
import Utils.Node;

public class FindKthElementFromEnd {
    public Node findKthElement(Node head, int k);
    public Node findKthElement(LinkedList list, int k);
}
```

### 1.3 Pre-Conditions
- `head` is node of singly linked list. $k \in \mathbb{Z}$.

### 1.4 Post-Conditions
- Returns node at index $N - k$, or `null` if $k > N$ or $k \le 0$.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Fixed Window Invariant**:
  - Advance `fast` pointer $k$ steps ahead of `slow`.
  - If `fast` becomes `null` before $k$ steps, $k > N$.
  - When `fast` is $k$ steps ahead, advance both `fast` and `slow` together until `fast == null`.
  - When `fast` falls off the end (step $N$), `slow` is exactly at step $N - k$.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $0 \le n \le 10^5$ | Single pass $O(N)$ with no list length precomputation. |
| Auxiliary Space | $O(1)$ | Two pointer references. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | List | $k$ | Expected Output Node Value |
| :--- | :--- | :--- | :---: | :---: |
| **TC-01** | Last element ($k=1$) | `10 -> 20 -> 30 -> 40 -> 50` | `1` | `50` |
| **TC-02** | Middle element ($k=3$) | `10 -> 20 -> 30 -> 40 -> 50` | `3` | `30` |
| **TC-03** | First element ($k=5$) | `10 -> 20 -> 30 -> 40 -> 50` | `5` | `10` |
| **TC-04** | Out of bounds ($k=10$) | `10 -> 20 -> 30 -> 40 -> 50` | `10` | `null` |
| **TC-05** | Invalid $k \le 0$ | `10 -> 20` | `0` | `null` |

---

## 5. Design & Algorithmic Strategy

- Two-pointer sliding window of width $k$.
