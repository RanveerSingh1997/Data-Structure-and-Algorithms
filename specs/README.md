# 📐 Spec-Driven Development (SDD) in DSA

Spec-Driven Development (SDD) is the engineering practice where **formal specifications precede all code and test design**. In an algorithmic and system-design repository, code without a specification leads to hidden edge cases, violated invariants, suboptimal time bounds, and lack of mathematical rigor.

---

## 🔁 The 5-Phase SDD Lifecycle

```text
  ┌─────────────────────────────────────────────────────────────┐
  │ 1. SPECIFY                                                  │
  │    Draft `specs/<Category>/<Problem>.spec.md`               │
  │    (API contract, invariants, constraints, test matrix)     │
  └──────────────────────────────┬──────────────────────────────┘
                                 │
                                 ▼
  ┌─────────────────────────────────────────────────────────────┐
  │ 2. TEST DESIGN                                              │
  │    Encode Acceptance Criteria into test cases               │
  │    (Standalone `main()` + `test.AllTestsRunner`)            │
  └──────────────────────────────┬──────────────────────────────┘
                                 │
                                 ▼
  ┌─────────────────────────────────────────────────────────────┐
  │ 3. IMPLEMENT                                                │
  │    Implement clean, idiomatic Java solution in `src/`       │
  │    (Preserving loop invariants & algorithmic bounds)        │
  └──────────────────────────────┬──────────────────────────────┘
                                 │
                                 ▼
  ┌─────────────────────────────────────────────────────────────┐
  │ 4. VERIFY                                                   │
  │    Run `./sdd verify` and `./sdd test <Problem>`            │
  │    (Zero regressions, test pass, complexity proof checked)  │
  └──────────────────────────────┬──────────────────────────────┘
                                 │
                                 ▼
  ┌─────────────────────────────────────────────────────────────┐
  │ 5. SYNC & CLOSE                                             │
  │    Mark Spec as `VERIFIED`, update `SOLVED.md` & `README.md`│
  └─────────────────────────────────────────────────────────────┘
```

---

## 🏷️ Specification Lifecycle States

Each `.spec.md` document tracks its current readiness state in its header metadata:

| State | Definition | Gate Criteria |
| :--- | :--- | :--- |
| `DRAFT` | Initial requirement and invariant brainstorming. | Problem statement, constraints, and test scenarios outlined. |
| `APPROVED` | Design, Big-O budgets, and edge cases verified. | Ready for implementation; peer / self reviewed. |
| `IMPLEMENTED` | Java solution written in `src/<Category>/<Problem>.java`. | Code compiles; basic happy path tests passing. |
| `VERIFIED` | Full acceptance matrix passes in `AllTestsRunner.java`. | Zero regressions, edge cases green, benchmark verified. |

---

## 📁 Directory Structure

```text
specs/
├── TEMPLATE.spec.md                   # Canonical specification template
├── README.md                          # This workflow guide
├── Arrays/                            # Array, two pointers, sliding window specs
│   ├── TrappingRainWater.spec.md
│   └── ContainerWithMostWater.spec.md
├── Strings/                           # String manipulation specs
├── LinkedList/                        # Linked list manipulation specs
├── Stacks_Queues/                     # Stack, Queue, Monotonic stack specs
├── Trees/                             # Binary Trees & BST specs
├── Graphs/                            # Graph & Topological sort specs
├── DP/                                # Dynamic Programming specs
├── Sorting_Searching/                 # Binary Search & Sort specs
├── Heap/                              # Priority Queue & Heap specs
└── Templates/                         # Reusable core template specs (DSU, Trie, Segment Tree)
```

---

## 🛠️ Using the `./sdd` CLI Tool

Use the repository-level `./sdd` command to manage specs and code:

```bash
# 1. Scaffold a new specification and Java file boilerplate
./sdd new Arrays TrappingRainWater

# 2. View SDD coverage dashboard and status
./sdd status

# 3. Verify spec compliance, build status, and run all tests
./sdd verify

# 4. Run tests for a specific problem or test runner
./sdd test TrappingRainWater
./sdd test all
```
