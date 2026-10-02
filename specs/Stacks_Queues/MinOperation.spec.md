# SPEC-1598: Crawler Log Folder

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Stacks_Queues / Counter / Stack`  
> **Source**: [LeetCode #1598 - Crawler Log Folder](https://leetcode.com/problems/crawler-log-folder/)  
> **Video Explanation**: [Kevin Naughton - Crawler Log Folder](https://youtu.be/z6pZ3aQy_F4)  
> **Documentation / Read Link**: [LeetCode Editorial](https://leetcode.com/problems/crawler-log-folder/editorial/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Track directory hierarchy depth using an integer counter `depth = 0`. For `"../"`, decrement `depth = Math.max(0, depth - 1)` (preventing negative depth below root); for `"./"`, do nothing; for any folder name `"dir/"`, increment `depth++`. Return `depth` at the end without heap allocation.

### 1.2 Description
Given a list of folder navigation operations:
- `"../"` : Move to parent folder (stay in main folder if already at root).
- `"./"`  : Remain in current folder.
- `"x/"`  : Move to child folder named `x`.
Return the minimum operations needed to return to the main folder.

### 1.2 Method Signature
```java
package Stacks_Queues;

public class MinOperation {
    public int minOperations(String[] logs);
}
```

### 1.3 Pre-Conditions
- $1 \le \text{logs.length} \le 10^3$.

### 1.4 Post-Conditions
- Returns non-negative integer representing current folder depth.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- Current folder depth is an integer $D \ge 0$.
- For operation `log`:
  - If `log == "../"`: $D = \max(0, D - 1)$
  - If `log == "./"`: $D$ remains unchanged
  - Else (`child folder`): $D = D + 1$
- At the end, $D$ operations are required to return to root.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `logs.length` | $1 \le N \le 10^3$ | $O(N)$ single pass. |
| Auxiliary Space | $O(1)$ | Single integer depth counter. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `logs` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Navigating up and down | `["d1/","d2/","../","d21/","./"]` | `2` |
| **TC-02** | Staying at root on excess `../` | `["d1/","../","../","../"]` | `0` |
| **TC-03** | Deep directory | `["d1/","d2/","./","d3/","../","d31/"]` | `3` |

---

## 5. Design & Algorithmic Strategy

- Track folder depth with integer variable `depth`, initialized to 0.
- Return `depth`.
