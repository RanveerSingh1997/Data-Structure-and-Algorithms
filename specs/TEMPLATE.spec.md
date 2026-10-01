# SPEC-000: [Problem Title]

> **Status**: `DRAFT` | `APPROVED` | `IMPLEMENTED` | `VERIFIED`  
> **Difficulty**: `🟢 Easy` | `🟡 Medium` | `🔴 Hard`  
> **Category**: `[e.g., Arrays / Two Pointers / Dynamic Programming]`  
> **Source**: `[LeetCode #XYZ / External Link]`  
> **Target Complexity**: Time `O(...)` | Space `O(...)`  

---

## 1. Problem Formulation & API Contract

### 1.1 Description
[Concise, unambiguous statement of the problem, input formats, and expected output behavior.]

### 1.2 Method Signature
```java
package [Category];

public class [ProblemName] {
    public static [ReturnType] [methodName]([ParamType] [paramName]) {
        // Implementation adheres to this specification
    }
}
```

### 1.3 Pre-Conditions
- Input parameter is non-null unless explicitly allowed.
- [e.g., Array length satisfies $1 \le N \le 10^5$.]
- [e.g., Array elements are bounded by $-10^4 \le \text{nums}[i] \le 10^4$.]

### 1.4 Post-Conditions
- Returns [exact description of returned value].
- Mutates / Does NOT mutate input structure in-place.
- Guaranteed to terminate within [Time Budget].

---

## 2. Mathematical Invariants & Algorithmic Principles

### 2.1 Core Invariant
- **Loop / State Invariant**: At step $k$, [what invariant property holds true? E.g., all elements to the left of $L$ have water trapped determined solely by $leftMax$].
- **Monotonicity / Ordering**: [Any monotonicity or invariant property leveraged].

### 2.2 Proof of Correctness Sketch
- **Why this approach is sound**: [Mathematical or inductive argument showing why optimal substructure or greedy choice guarantees correctness].
- **Termination Guarantee**: [Why loops or recursions strictly make progress toward base case].

---

## 3. Constraints & Complexity Budget

### 3.1 Constraint Boundaries
| Parameter | Range | Implication / Budget |
| :--- | :--- | :--- |
| `n` (Input size) | `[e.g., 1 <= n <= 10^5]` | Requires $O(N)$ or $O(N \log N)$ algorithm; $O(N^2)$ will TLE |
| `val` (Element range) | `[e.g., -10^9 <= val <= 10^9]` | Watch for integer 32-bit overflow (use `long` if summing) |
| Memory Limits | Standard JVM heap | Max auxiliary space $O(1)$ or $O(N)$ |

### 3.2 Complexity Budget
- **Time Complexity Target**: $O(\dots)$
- **Auxiliary Space Target**: $O(\dots)$
- **Brute Force Bottleneck**: $O(\dots)$ — fails because [explain redundant computation or exponential branching].

---

## 4. Acceptance Criteria & Test Matrix

| ID | Test Scenario | Input | Expected Output | Rationale / Invariant Verified |
| :--- | :--- | :--- | :--- | :--- |
| **TC-01** | Happy Path (Standard) | `[e.g., ...]` | `...` | Validates standard multi-element execution |
| **TC-02** | Minimal Input / Empty | `[e.g., [] or [1]]` | `...` | Boundary check; prevents off-by-one / out-of-bounds |
| **TC-03** | Monotonically Increasing | `[e.g., [1, 2, 3, 4]]` | `...` | Verifies behavior when no valleys or reversals exist |
| **TC-04** | Monotonically Decreasing | `[e.g., [4, 3, 2, 1]]` | `...` | Verifies symmetry and inverse bounds |
| **TC-05** | All Elements Identical | `[e.g., [2, 2, 2, 2]]` | `...` | Tests plateau behavior and equality conditions |
| **TC-06** | Peak at Boundary | `[e.g., [10, 1, 2, 1]]` | `...` | Validates edge-pinned maxima |
| **TC-07** | Extreme / Stress Case | `[e.g., N = 10^5]` | `...` | Stress tests against Time Limit Exceeded (TLE) & StackOverflow |

---

## 5. Design & Algorithmic Strategy

### 5.1 Approach Comparison
| Approach | Time | Space | Pros | Cons |
| :--- | :---: | :---: | :--- | :--- |
| **1. Brute Force** | $O(N^2)$ | $O(1)$ | Simple to implement | Redundant recalculations; TLE on large $N$ |
| **2. Prefix / Aux Memory** | $O(N)$ | $O(N)$ | Intuitive $O(N)$ pass | Extra heap allocation |
| **3. Invariant Two-Pointer (Optimal)**| $O(N)$ | $O(1)$ | Optimal speed, zero allocation | Subtle pointer movement invariants |

### 5.2 Failure Modes & Pitfalls
- **Trap 1**: Off-by-one errors on boundary indices (`0` vs `1`, `n-1` vs `n`).
- **Trap 2**: Integer overflow on cumulative sums or products.
- **Trap 3**: Premature termination before evaluating trailing elements.

---

## 6. Verification Trace Table

*Dry-run trace on test case `[Input Example]`*:

| Step | State / Pointers ($L, R$) | Current Values | Invariant / Condition Check | Auxiliary / Accumulator | Next Action |
| :---: | :---: | :---: | :---: | :---: | :---: |
| 0 | `L=0, R=5` | `arr[L]=..., arr[R]=...` | `...` | `result = 0` | Advance pointer |
| 1 | `L=1, R=5` | `...` | `...` | `result = ...` | ... |
| 2 | `...` | `...` | `...` | `result = ...` | Terminate loop |
