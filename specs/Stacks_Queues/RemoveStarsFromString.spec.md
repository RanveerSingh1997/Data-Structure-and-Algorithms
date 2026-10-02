# SPEC-2390: Removing Stars From a String

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Stacks_Queues / String / Stack`  
> **Source**: [LeetCode #2390 - Removing Stars From a String](https://leetcode.com/problems/removing-stars-from-a-string/)  
> **Video Explanation**: [NeetCode - Removing Stars From a String](https://youtu.be/pRyFUtlh4pY)  
> **Documentation / Read Link**: [LeetCode Editorial](https://leetcode.com/problems/removing-stars-from-a-string/editorial/)  
> **Target Complexity**: Time `O(N)` | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Iterate through the string maintaining a character stack (or `StringBuilder` / pointer). Append each regular character. Whenever an asterisk `*` is encountered, pop the most recently added non-star character from the stack. Convert remaining stack characters into the result string.

### 1.2 Description
You are given a string `s`, which contains stars `*`. In one operation, you can choose a star and remove the closest non-star character to its left, as well as the star itself. Return the string after all stars have been removed.

### 1.2 Method Signature
```java
package Stacks_Queues;

public class RemoveStarsFromString {
    public String removeStars(String s);
}
```

### 1.3 Pre-Conditions
- $1 \le s.\text{length} \le 10^5$.
- `s` consists of lowercase English letters and stars `*`.
- Star operations are guaranteed to be valid.

### 1.4 Post-Conditions
- Returns modified string with all star cancellations resolved.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- A star `*` acts as a backspace / `pop()` operation on a character stack:
  - If char $\ne$ `'*'`, `push(char)`.
  - If char $==$ `'*'`, `pop()`.
- The stack holds the prefix of surviving characters at all times.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $1 \le n \le 10^5$ | $O(N)$ single pass. |
| Auxiliary Space | $O(N)$ | Character stack / StringBuilder. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `s` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Multiple internal stars | `"leet**cod*e"` | `"lecoe"` |
| **TC-02** | All characters deleted | `"erase*****"` | `""` |
| **TC-03** | No stars present | `"abc"` | `"abc"` |

---

## 5. Design & Algorithmic Strategy

- Use a `StringBuilder` or stack as a character accumulator.
- Delete last character on `'*'`, else append.
