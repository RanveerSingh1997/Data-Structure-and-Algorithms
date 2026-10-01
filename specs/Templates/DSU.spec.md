# SPEC-DSU: Disjoint Set Union (Union-Find)

> **Status**: `VERIFIED`  
> **Difficulty**: `🟡 Medium`  
> **Category**: `Templates / Graph / Union-Find`  
> **Source**: Core Algorithmic Template  
> **Target Complexity**: Time `O(\alpha(N))` amortized per op | Space `O(N)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
Design a Disjoint Set Union (DSU) data structure supporting:
- `find(i)`: Return representative root of element `i` with path compression.
- `union(i, j)`: Unify sets containing `i` and `j` by rank.
- `connected(i, j)`: Query whether `i` and `j` belong to the same connected component.

### 1.2 Method Signature
```java
package Templates;

public class DSU {
    public DSU(int n);
    public int find(int i);
    public boolean union(int i, int j);
    public boolean connected(int i, int j);
}
```

### 1.3 Pre-Conditions
- $n \ge 1$ elements labeled $0 \dots n-1$.

### 1.4 Post-Conditions
- Disjoint set partition maintained accurately under arbitrary sequence of union and find calls.

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariants
- **Path Compression**: During `find(i)`, assign `parent[i] = find(parent[i])`, flattening tree height directly to the root.
- **Union by Rank**: Attach shallower tree under root of deeper tree (`rank[root1] > rank[root2]`). If equal, increment rank of receiving root.
- Amortized complexity is $O(\alpha(N))$ where $\alpha$ is the Inverse Ackermann function ($\alpha(N) < 5$ for all practical universe scale $N < 10^{80}$).

---

## 3. Constraints & Complexity Budget

| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` | $1 \le n \le 10^6$ | Nearly constant time per operation. |
| Auxiliary Space | $O(N)$ | Two arrays: `parent[n]` and `rank[n]`. |

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Initial Setup | Operations | Expected Output |
| :--- | :--- | :--- | :--- | :--- |
| **TC-01** | Initial disjointness | `DSU(5)` | `connected(0, 2)` | `false` |
| **TC-02** | Transitive connectivity | `DSU(5)` | `union(0, 1), union(1, 2)` | `connected(0, 2) == true` |
| **TC-03** | Independent component | `DSU(5)` | `union(0, 1), union(1, 2)` | `connected(0, 3) == false` |
| **TC-04** | Merging two components | `DSU(5)` | `union(3, 4), union(2, 3)` | `connected(0, 4) == true` |

---

## 5. Design & Algorithmic Strategy

- `parent` initialized to self-loops ($parent[i] = i$), `rank` initialized to 1.
- Recursive `find` with path compression.
- Rank-based `union`.
