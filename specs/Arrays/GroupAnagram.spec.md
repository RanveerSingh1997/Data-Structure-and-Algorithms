# SPEC-049: Group Anagrams

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Arrays / Strings / HashMap`  
> **Source**: [LeetCode #49 - Group Anagrams](https://leetcode.com/problems/group-anagrams/)  
> **Video Explanation**: [NeetCode - Group Anagrams](https://youtu.be/vzdNOK2oDA4)  
> **Documentation / Read Link**: [NeetCode.io - Group Anagrams](https://neetcode.io/problems/anagram-groups) · [TakeUForward - Group Anagrams](https://takeuforward.org/data-structure/group-anagrams/)  
> **Target Complexity**: Time `O(N \cdot K \log K)` | Space `O(N \cdot K)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Anagrams share the exact same sorted character representation (or character count frequency vector). Use this canonical key as the entry in a hash map `Map<String, List<String>>`. For each word, sort its characters (or build its count tuple), look up the bucket, and append the word. Returning `new ArrayList<>(map.values())` groups all anagrams.

### 1.2 Description
Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.

### 1.2 Method Signature
```java
package Arrays;

import java.util.List;

public class GroupAnagram {
    public static List<List<String>> groupAnagrams(String[] strs);
}
```

### 1.3 Pre-Conditions
- $1 \le \text{strs.length} \le 10^4$.
- $0 \le \text{strs}[i].\text{length} \le 100$.
- `strs[i]` consists of lowercase English letters.

### 1.4 Post-Conditions
- Returns list of anagram groups where each string belongs to exactly one group.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Canonical Key Invariant**: Two strings $s_1$ and $s_2$ are anagrams if and only if their sorted character representations are identical:
  $$\text{sort}(s_1) = \text{sort}(s_2)$$
- Using the sorted string representation as a hash map key, all anagrams hash to the same bucket.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `N` (number of strings) | $1 \le N \le 10^4$ | Linear in $N$. |
| `K` (max string length) | $0 \le K \le 100$ | $K \log K$ per string is $\le 100 \log_2(100) \approx 700$ ops. Total $\le 7 \times 10^6$ ops. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output |
| :--- | :--- | :--- | :---: |
| **TC-01** | Multiple groups | `["act", "pots", "tops", "cat", "stop", "hat"]` | `[["act", "cat"], ["pots", "tops", "stop"], ["hat"]]` |
| **TC-02** | Single empty string | `[""]` | `[[""]]` |
| **TC-03** | Single character | `["x"]` | `[["x"]]` |

---

## 5. Design & Algorithmic Strategy

- Sort characters of each string to obtain canonical signature.
- Insert into `HashMap<String, List<String>>`.
- Return list of map values.
