# SPEC-150: Evaluate Reverse Polish Notation

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Stacks_Queues / Stack / Math`  
> **Source**: [LeetCode #150 - Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/)  
> **Video Explanation**: [NeetCode - Evaluate Reverse Polish Notation](https://youtu.be/iu0082c4HDE)  
> **Documentation / Read Link**: [NeetCode.io - Evaluate Reverse Polish Notation](https://neetcode.io/problems/evaluate-reverse-polish-notation) · [GFG - RPN Evaluation](https://www.geeksforgeeks.org/evaluate-the-value-of-an-arithmetic-expression-in-reverse-polish-notation-in-java/)  
> **Target Complexity**: Time `O(N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Evaluate postfix expressions using a stack. Scan tokens: if an integer is seen, push it onto the stack. If an operator (`+`, `-`, `*`, `/`) is seen, pop operand $b$, then operand $a$, evaluate $a \text{ op } b$, and push the result back onto the stack. The final remaining stack value is the expression result.

### 1.2 Description
Evaluate the value of an arithmetic expression in Reverse Polish Notation (postfix). Valid operators are `+`, `-`, `*`, and `/`. Each operand may be an integer or another expression. Division truncates toward zero.

### 1.2 Method Signature
```java
package Stacks_Queues;

public class ReversePolishNotation {
    public int evalRPN(String[] tokens);
}
```

### 1.3 Pre-Conditions
- $1 \le \text{tokens.length} \le 10^4$.
- `tokens[i]` is an operator (`"+"`, `"-"`, `*`, `"/"`) or an integer in $[-200, 200]$.
- The expression is guaranteed to be mathematically valid; division by zero does not occur.

### 1.4 Post-Conditions
- Returns single evaluated integer result.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Operand Stack Invariant**: At each token:
  - If token is an operand (number), push onto operand stack.
  - If token is an operator $\oplus$: pop the right operand $b$, pop the left operand $a$, compute $a \oplus b$, and push the result back onto the stack.
- At end, exactly one value remains on the stack: the expression result.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `tokens.length` | $1 \le N \le 10^4$ | $O(N)$ single-pass evaluation. |
| Auxiliary Space | $O(N)$ | Stack stores intermediate numerical operands. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `tokens` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Simple addition and multiply | `["2","1","+","3","*"]` | `9` |
| **TC-02** | Truncating division | `["4","13","5","/","+"]` | `6` |
| **TC-03** | Complex multi-operator | `["10","6","9","3","+","-11","*","/","*","17","+","5","+"]` | `22` |
| **TC-04** | Single number | `["18"]` | `18` |

---

## 5. Design & Algorithmic Strategy

- Scan tokens left to right using an integer stack.
- Handle operators by popping two elements, performing operation, and pushing result back.
