# SPEC-875: Koko Eating Bananas

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Sorting_Searching / Binary Search on Answer`  
> **Source**: [LeetCode #875 - Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)  
> **Video Explanation**: [NeetCode - Koko Eating Bananas](https://youtu.be/U2SozAs9RzA)  
> **Documentation / Read Link**: [NeetCode.io - Eating Bananas](https://neetcode.io/problems/eating-bananas) · [TakeUForward - Koko Eating Bananas](https://takeuforward.org/binary-search/koko-eating-bananas/)  
> **Target Complexity**: Time `O(N \log(\max(piles)))` | Space `O(1)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Core Summary (Read Without Navigating)
Binary search the optimal speed $k$ within range $[1, \max(piles)]$. The hours required $\sum \lceil pile[i] / k \rceil$ monotonically decreases as $k$ increases. If current speed $k$ finishes within $h$ hours, record $k$ as a valid answer and attempt a slower speed in the left half ($R = mid - 1$); otherwise speed up ($L = mid + 1$).

### 1.2 Description
Koko loves to eat bananas. There are $n$ piles of bananas, the $i$-th pile has `piles[i]` bananas. The guards will come back in $h$ hours.
Koko can decide her bananas-per-hour eating speed of $k$. Each hour, she chooses some pile and eats $k$ bananas from that pile. If the pile has less than $k$ bananas, she eats all of them and will not eat any more bananas during this hour.
Return the minimum integer $k$ such that she can eat all the bananas within $h$ hours.

### 1.2 Method Signature
```java
package Sorting_Searching;

public class MinEatingSpeed {
    public static int minEatingSpeed(int[] piles, int h);
}
```

### 1.3 Pre-Conditions
- $1 \le \text{piles.length} \le 10^4$.
- $\text{piles.length} \le h \le 10^9$.
- $1 \le \text{piles}[i] \le 10^9$.

### 1.4 Post-Conditions
- Returns minimal integer $k \ge 1$ such that $\sum \lceil \text{pile} / k \rceil \le h$.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Monotonic Feasibility Function**:
  Let $f(k) = \sum_{i=1}^n \lceil \text{piles}[i] / k \rceil$ be the total hours needed at speed $k$.
  $f(k)$ is monotonically non-increasing in $k$: as speed $k$ increases, required hours strictly decrease or remain equal.
- Search space for $k$ is $[1, \max(piles)]$.
- Binary search invariant:
  - If $f(mid) \le h$, speed $mid$ is feasible; optimal speed must lie in $[left, mid]$. So $right = mid$.
  - If $f(mid) > h$, speed $mid$ is too slow; optimal speed must lie in $[mid + 1, right]$. So $left = mid + 1$.
- Terminates when $left == right$, pinning the exact minimal feasible $k$.

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $1 \le n \le 10^4$ | Feasibility check is $O(N)$. |
| `max(piles)` | $10^9$ | Binary search takes $\lceil \log_2(10^9) \rceil \approx 30$ iterations. Total $\approx 3 \times 10^5$ ops. |
| Auxiliary Space | $O(1)$ | Constant variables. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | `piles` | `h` | Expected Output $k$ |
| :--- | :--- | :--- | :---: | :---: |
| **TC-01** | Standard multi-pile | `[3, 6, 7, 11]` | `8` | `4` |
| **TC-02** | Exact hour budget | `[30, 11, 23, 4, 20]` | `5` | `30` |
| **TC-03** | Generous hour budget | `[30, 11, 23, 4, 20]` | `6` | `23` |
| **TC-04** | Small custom case 1 | `[1, 4, 3, 2]` | `9` | `2` |
| **TC-05** | Tight boundary case | `[25, 10, 23, 4]` | `4` | `25` |

---

## 5. Design & Algorithmic Strategy

- Binary search across speed bounds $left = 1, right = \max(piles)$.
- For each midpoint, compute hours using ceiling division: `(pile + k - 1) / k`.
