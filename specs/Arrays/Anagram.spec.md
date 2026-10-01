# SPEC-242: Valid Anagram

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Arrays / Strings / Frequency Map`  
> **Source**: [LeetCode #242 - Valid Anagram](https://leetcode.com/problems/valid-anagram/)  
> **Target Complexity**: Time `O(N)` | Space `O(1)` (Fixed alphabet size 26)  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Given two strings `str1` and `str2`, return `true` if `str2` is an anagram of `str1`, and `false` otherwise.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

### 1.2 Method Signature
```java
package Arrays;

public class Anagram {
    public static boolean isAnagram(String str1, String str2);
}
```

### 1.3 Pre-Conditions
- Both strings consist of lowercase English letters (`'a'` through `'z'`).
- Length bounds: $0 \le |str1|, |str2| \le 5 \times 10^4$.

### 1.4 Post-Conditions
- Returns boolean `true` if character counts match identically, `false` otherwise.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- Two strings $S_1, S_2$ are anagrams if and only if:
  1. $|S_1| = |S_2|$
  2. $\forall c \in \Sigma, \, \text{count}(c, S_1) = \text{count}(c, S_2)$
- Using a single 26-element integer table `int[26] count`:
  Increment for $S_1[i]$ and decrement for $S_2[i]$. After the pass, all 26 entries must be exactly $0$.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| String lengths | $0 \le L \le 5 \times 10^4$ | $O(N)$ linear pass. Sorting takes $O(N \log N)$. |
| Alphabet size | $\Sigma = 26$ | $O(1)$ auxiliary memory (fixed array `int[26]`). |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Standard true anagram | `str1="anagram", str2="nagaram"` | `true` |
| **TC-02** | Common words | `str1="tea", str2="eat"` | `true` |
| **TC-03** | Mismatch letters | `str1="rat", str2="cat"` | `false` |
| **TC-04** | Different lengths | `str1="a", str2="ab"` | `false` |
| **TC-05** | Empty strings | `str1="", str2=""` | `true` |

---

## 5. Design & Algorithmic Strategy

- **Sorting**: Convert to char arrays and `Arrays.sort()`. $O(N \log N)$ time, $O(N)$ space.
- **Frequency Counter (Optimal)**: Single `int[26]` array. One pass to count up/down, check all zero. $O(N)$ time, $O(1)$ space.
