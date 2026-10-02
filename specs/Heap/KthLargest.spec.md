# SPEC-703: Kth Largest Element in a Stream

> **Status**: `VERIFIED`  
> **Difficulty**: `🟢 Easy`  
> **Category**: `Heap / Min-Heap`  
> **Source**: [LeetCode #703 - Kth Largest Element in a Stream](https://leetcode.com/problems/kth-largest-element-in-a-stream/)  
> **Video Explanation**: [NeetCode - Kth Largest Element in a Stream](https://youtu.be/hOjcdrqMoQ8)  
> **Documentation / Read Link**: [NeetCode.io - Kth Largest in Stream](https://neetcode.io/problems/kth-largest-element-in-a-stream) · [GFG - Kth Largest in Stream](https://www.geeksforgeeks.org/kth-largest-element-in-a-stream/)  
> **Target Complexity**: Time `O(N \log K)` initialization, `O(\log K)` per add | Space `O(K)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Maintain a Min-Heap of exact capacity $K$. When a new element arrives, add it to the Min-Heap. If the size of the heap exceeds $K$, remove the minimum root (`poll()`). Because the heap only retains the top $K$ largest elements seen so far, the root element (`peek()`) is mathematically guaranteed to be the $K$-th largest.

### 1.2 Description
Design a class to find the $k$-th largest element in a stream. Note that it is the $k$-th largest element in the sorted order, not the $k$-th distinct element.
Implement `KthLargest`:
- `KthLargest(int k, int[] nums)` Initializes the object with the integer `k` and the stream of integers `nums`.
- `int add(int val)` Appends the integer `val` to the stream and returns the element representing the $k$-th largest element in the stream.

### 1.2 Method Signature
```java
package Heap;

public class KthLargest {
    public KthLargest(int k, int[] nums);
    public int add(int val);
}
```

### 1.3 Pre-Conditions
- $1 \le k \le 10^4$.
- $0 \le \text{nums.length} \le 10^4$.
- It is guaranteed that there will be at least $k$ elements in the array when you search for the $k$-th element.

### 1.4 Post-Conditions
- `add(val)` returns the current $k$-th largest element.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Min-Heap of Capacity K Invariant**: The heap holds exactly the $k$ largest elements seen so far in the stream.
- The root of the min-heap (`heap.peek()`) is the smallest among these $k$ elements, which by definition is the $k$-th largest overall element.
- When `add(val)` is called, push `val`. If `heap.size() > k`, evict the minimum element via `heap.poll()`.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `k` | $1 \le k \le 10^4$ | Min-heap holds at most $k$ integers. |
| Calls to `add` | Up to $10^4$ | Each call takes $O(\log K)$ heap operations. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | $k$, Initial `nums` | Stream Calls | Expected Returns |
| :--- | :--- | :--- | :--- | :--- |
| **TC-01** | Standard LeetCode | `k=3, nums=[4, 5, 8, 2]` | `add(3), add(5), add(10), add(9), add(4)` | `4, 5, 5, 8, 8` |
| **TC-02** | Empty initial array | `k=1, nums=[]` | `add(-3), add(-2), add(-4), add(0), add(4)` | `-3, -2, -2, 0, 4` |
| **TC-03** | Negative values | `k=2, nums=[5, -1, 2]` | `add(3), add(6), add(4)` | `3, 5, 5` |

---

## 5. Design & Algorithmic Strategy

- Standard min-heap priority queue bounded by size $k$.
