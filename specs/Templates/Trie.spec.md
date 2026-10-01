# SPEC-208: Implement Trie (Prefix Tree)

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Templates / Tree / Prefix Tree`  
> **Source**: [LeetCode #208 - Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/)  
> **Target Complexity**: Time `O(L)` per operation | Space `O(\sum L \cdot 26)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
Implement the `Trie` class:
- `Trie()` Initializes the trie object.
- `void insert(String word)` Inserts the string `word` into the trie.
- `boolean search(String word)` Returns `true` if the string `word` is in the trie (i.e., was inserted before), and `false` otherwise.
- `boolean startsWith(String prefix)` Returns `true` if there is a previously inserted string `word` that has the prefix `prefix`, and `false` otherwise.

### 1.2 Method Signature
```java
package Templates;

public class Trie {
    public Trie();
    public void insert(String word);
    public boolean search(String word);
    public boolean startsWith(String prefix);
}
```

### 1.3 Pre-Conditions
- $1 \le \text{word.length}, \text{prefix.length} \le 2000$.
- `word` and `prefix` consist only of lowercase English letters.

### 1.4 Post-Conditions
- Correct boolean returned for exact search vs prefix query.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Trie Invariant**: Each path from root to a node corresponds to a string prefix.
- `curr = curr.children[char - 'a']`.
- A word is present if and only if the traversal succeeds through all characters and the final node has `isEndOfWord == true`.
- A prefix exists if traversal succeeds through all characters of the prefix, regardless of `isEndOfWord`.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `L` (word length) | $1 \le L \le 2000$ | Each operation traverses exactly $L$ nodes: strictly $O(L)$. |
| Auxiliary Space | $O(N \cdot \Sigma)$ | 26 child references per internal node. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Operations | Expected Output |
| :--- | :--- | :--- | :--- |
| **TC-01** | Word inserted | `insert("apple")` | - |
| **TC-02** | Exact match | `search("apple")` | `true` |
| **TC-03** | Prefix only (not word) | `search("app")` | `false` |
| **TC-04** | Valid prefix | `startsWith("app")` | `true` |
| **TC-05** | Insert prefix as word | `insert("app"), search("app")` | `true` |

---

## 5. Design & Algorithmic Strategy

- Internal `TrieNode` with `TrieNode[26] children` and `boolean isEndOfWord`.
- Root initialized in constructor.
