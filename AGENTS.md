# 🤖 AI Agent Protocol: Spec-Driven Development (SDD)

This repository enforces **Spec-Driven Development (SDD)**. Every algorithm, data structure, and design pattern must be
formally specified before implementation.

---

## 📜 Core Directives for AI Assistants

1. **NO UN-SPECIFIED CODE**: Never write or overhaul algorithmic code in `src/` without first drafting or updating its
   specification in `specs/<Category>/<ProblemName>.spec.md`.
2. **MATHEMATICAL INVARIANTS FIRST**: Every spec must define the **core invariant** (loop invariant, state transition,
   or structural property) and proof of why the optimal approach terminates and is sound.
3. **TEST-FIRST DERIVATION**: The test scenarios in the specification's Acceptance Matrix must be directly mapped to
   assertions in the class's `main()` runner and `test/AllTestsRunner.java`.
4. **COMPLEXITY BUDGET ENFORCEMENT**: Code must adhere to the time and space budgets documented in the spec.
5. **AUTOMATED VERIFICATION**: Always run `./sdd verify <ProblemName>` and ensure zero test failures before presenting
   the solution to the user.

---

## 🔁 5-Step SDD Workflow

Whenever the user requests a new algorithm, solution, or refactor:

```text
1. SPECIFY      ───>  ./sdd new <Category> <ProblemName>
                      Fill in specs/<Category>/<ProblemName>.spec.md
                      (Invariants, Constraints, Edge-case Matrix)

2. ALIGN        ───>  Review invariant and complexity budget with user

3. TEST DESIGN  ───>  Implement acceptance test assertions (assert ...)

4. IMPLEMENT    ───>  Write clean, idiomatic Java in src/<Category>/<ProblemName>.java

5. VERIFY       ───>  Run `./sdd verify <ProblemName>` & `./sdd status`
```

---

## 🛠️ CLI Toolkit

| Task                                 | Command                              |
|:-------------------------------------|:-------------------------------------|
| **Scaffold new spec & code**         | `./sdd new <Category> <ProblemName>` |
| **Verify problem spec & tests**      | `./sdd verify <ProblemName>`         |
| **Run repository-wide verification** | `./sdd verify`                       |
| **Run specific test**                | `./sdd test <ProblemName>`           |
| **Check SDD coverage & matrix**      | `./sdd status`                       |

---

## 📁 File Organization

- Specs live in: `specs/<Category>/<ProblemName>.spec.md`
- Source code lives in: `src/<Category>/<ProblemName>.java`
- Canonical Template: `specs/TEMPLATE.spec.md`
- Global test suite: `test/AllTestsRunner.java`
