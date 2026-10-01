# SPEC-013: Roman to Integer

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Hashing / Strings`  
> **Source**: [LeetCode #13 - Roman to Integer](https://leetcode.com/problems/roman-to-integer/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Roman numerals are represented by seven different symbols: `I`, `V`, `X`, `L`, `C`, `D` and `M`.
Given a roman numeral string `s`, convert it to an integer. Handle subtraction rules (`IV`=4, `IX`=9, `XL`=40, `XC`=90, `CD`=400, `CM`=900).

### 1.2 Method Signature
```java
package Hashing;

public class RomanToInteger {
    public int romanToInteger(String s);
}
```

### 1.3 Pre-Conditions
- $1 \le s.\text{length} \le 15$.
- `s` contains only valid Roman characters (`'I'`, `'V'`, `'X'`, `'L'`, `'C'`, `'D'`, `'M'`).
- It is guaranteed that `s` is a valid roman numeral in the range $[1, 3999]$.

### 1.4 Post-Conditions
- Returns equivalent integer value in $[1, 3999]$.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- At each index $i$, check if the two-character substring $s[i \dots i+1]$ is a subtraction pair (e.g. `IV`, `IX`).
- If yes: add two-character value and advance index by 2.
- If no: add single-character value $s[i]$ and advance index by 1.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `length` | $1 \le L \le 15$ | $O(N)$ bounded by 15 iterations. Strictly $O(1)$ runtime. |
| Auxiliary Space | $O(1)$ | Fixed mapping table of 13 entries. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input `s` | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Single character | `"I"` | `1` |
| **TC-02** | Additive combination | `"III"` | `3` |
| **TC-03** | Subtractive single | `"IV"` | `4` |
| **TC-04** | Subtractive tens | `"IX"` | `9` |
| **TC-05** | Mixed values | `"LVIII"` | `58` |
| **TC-06** | Complex numeral | `"MCMXCIV"` | `1994` |

---

## 5. Design & Algorithmic Strategy

- Store single and two-character values in static lookup table.
- Scan left-to-right greedily matching 2-character prefixes before single characters.
