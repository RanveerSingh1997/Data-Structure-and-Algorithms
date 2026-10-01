# SPEC-020: Valid Parentheses

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Stacks_Queues / Stack`  
> **Source**: [LeetCode #20 - Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)  
> **Target Complexity**: Time `O(N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given a string `s` containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.
An input string is valid if:
1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

### 1.2 Method Signature
```java
package Stacks_Queues;

public class ValidParentheses {
    public boolean isValid(String s);
}
```

### 1.3 Pre-Conditions
- $1 \le s.\text{length} \le 10^4$.
- `s` consists of parentheses only: `'()[]{}'`.

### 1.4 Post-Conditions
- Returns `true` if string is valid bracket sequence, `false` otherwise.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **LIFO Bracket Invariant**: At step $i$, the stack contains unmatched opening brackets in the exact order they appeared.
- Upon encountering a closing bracket:
  1. The stack must not be empty.
  2. The element on top of the stack must match the closing bracket's type.
- At end of string, the stack must be completely empty.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $1 \le n \le 10^4$ | $O(N)$ single pass. |
| Auxiliary Space | $O(N)$ | Stack stores up to $N$ characters. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `s` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Simple pair | `"()"` | `true` |
| **TC-02** | Multiple adjacent types | `"()[]{}"` | `true` |
| **TC-03** | Mismatched type | `"(]"` | `false` |
| **TC-04** | Interleaved invalid | `"([)]"` | `false` |
| **TC-05** | Properly nested | `"{[]}"` | `true` |
| **TC-06** | Single open | `"["` | `false` |
| **TC-07** | Single close | `"]"` | `false` |
| **TC-08** | Empty string | `""` | `true` |

---

## 5. Design & Algorithmic Strategy

- Push opening brackets onto stack.
- Pop and verify matching pair on closing bracket.
- Return `stack.isEmpty()` after processing all characters.
