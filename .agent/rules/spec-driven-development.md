---
description: Enforce Spec-Driven Development (SDD) rules and workflow for algorithms and data structures
trigger: always_on
---

# Spec-Driven Development (SDD) Rules

1. Before writing or significantly modifying any algorithmic solution in `src/`, check for or create a corresponding specification in `specs/<Category>/<ProblemName>.spec.md`.
2. Follow the standardized template at `specs/TEMPLATE.spec.md`.
3. Explicitly verify the core algorithmic invariants, edge-case acceptance matrix, and Big-O budget before generating Java code.
4. Ensure every problem contains a test suite in its `main()` runner matching its specification's test matrix, and register core problems in `test/AllTestsRunner.java`.
5. Run `./sdd verify <ProblemName>` to confirm specification compliance and passing test cases.
