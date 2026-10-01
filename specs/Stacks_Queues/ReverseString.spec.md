# SPEC-344: Reverse String

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Stacks_Queues / Two Pointers / Stack`  
> **Source**: [LeetCode #344 - Reverse String](https://leetcode.com/problems/reverse-string/)  
> **Target Complexity**: Time `O(N)` | Space `O(N)` (via Stack)  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given a string `str`, return a new string with the characters reversed using a LIFO stack.

### 1.2 Method Signature
```java
package Stacks_Queues;

public class ReverseString {
    public static String reverseString(String str);
}
```

### 1.3 Pre-Conditions
- `str` can be null or empty.

### 1.4 Post-Conditions
- Returns reversed string or `null` if input was null.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- A LIFO (Last-In, First-Out) stack reverses input sequence by definition: the character pushed at index $i$ is popped at step $n - 1 - i$.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `length` | $0 \le N \le 10^5$ | $O(N)$ linear pass. |
| Auxiliary Space | $O(N)$ | Stack holds $N$ characters. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Standard word | `"ABCDEF"` | `"FEDCBA"` |
| **TC-02** | Empty string | `""` | `""` |
| **TC-03** | Single character | `"A"` | `"A"` |
| **TC-04** | Null input | `null` | `null` |

---

## 5. Design & Algorithmic Strategy

- Push all characters of `str` onto a stack.
- Pop characters and append to `StringBuilder`.
