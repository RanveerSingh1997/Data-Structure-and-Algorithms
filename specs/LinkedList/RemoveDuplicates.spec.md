# SPEC-LRD: Remove Duplicates from Linked List

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `LinkedList / HashSet`  
> **Source**: Classical Linked List Interview Problem  
> **Target Complexity**: Time `O(N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given the head of an unsorted linked list, remove all duplicate nodes such that each value appears at most once in the list, preserving original relative order.

### 1.2 Method Signature
```java
package LinkedList;

import Utils.LinkedList;
import Utils.Node;

public class RemoveDuplicates {
    public void removeDuplicates(Node head);
    public void removeDuplicates(LinkedList list);
}
```

### 1.3 Pre-Conditions
- `head` is node of singly linked list.

### 1.4 Post-Conditions
- List has all duplicate values spliced out in-place.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Seen Set Invariant**: Maintain `Set<Integer> seen`.
- At node `curr`:
  - If `seen.contains(curr.value)`: splice out `curr` via `prev.next = curr.next`.
  - Else: `seen.add(curr.value)` and `prev = curr`.
- All nodes in prefix `head ... prev` have strictly distinct values.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $0 \le n \le 10^4$ | $O(N)$ pass with HashSet. |
| Auxiliary Space | $O(N)$ | Set storing up to $N$ unique values. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input List | Expected Output List |
| :--- | :--- | :--- | :---: |
| **TC-01** | Multi-duplicates | `1 -> 2 -> 2 -> 3 -> 1` | `1 -> 2 -> 3` |
| **TC-02** | All identical | `2 -> 2 -> 2 -> 2` | `2` |
| **TC-03** | Already distinct | `1 -> 2 -> 3` | `1 -> 2 -> 3` |
| **TC-04** | Single node | `5` | `5` |

---

## 5. Design & Algorithmic Strategy

- Single pass using HashSet to record seen values; update pointer references to drop duplicate nodes.
