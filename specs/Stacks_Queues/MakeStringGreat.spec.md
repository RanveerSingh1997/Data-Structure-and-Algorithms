# SPEC-1544: Make The String Great

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Stacks_Queues / String / Stack`  
> **Source**: [LeetCode #1544 - Make The String Great](https://leetcode.com/problems/make-the-string-great/)  
> **Target Complexity**: Time `O(N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given a string `s` of lower and upper case English letters, remove adjacent opposite-cased duplicate characters (e.g. `'a'` and `'A'`) iteratively until no such adjacent pair exists.

### 1.2 Method Signature
```java
package Stacks_Queues;

public class MakeStringGreat {
    public String makeGood(String s);
}
```

### 1.3 Pre-Conditions
- $1 \le s.\text{length} \le 100$.
- `s` contains only uppercase and lowercase English letters.

### 1.4 Post-Conditions
- Returns the resulting good string.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- Two characters $c_1, c_2$ are bad neighbors iff $|c_1 - c_2| = 32$.
- Maintain stack of characters: if current char and `stack.peek()` differ by 32, pop the stack; otherwise push.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $1 \le n \le 100$ | $O(N)$ linear pass. |
| Space | $O(N)$ | Character stack. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `s` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Simple pair removal | `"leEeetcode"` | `"leetcode"` |
| **TC-02** | Cascading collapse | `"abBAcC"` | `""` |
| **TC-03** | Single character | `"s"` | `"s"` |

---

## 5. Design & Algorithmic Strategy

- Single pass using stack. Compare `Math.abs(curr - stack.peek()) == 32`.
